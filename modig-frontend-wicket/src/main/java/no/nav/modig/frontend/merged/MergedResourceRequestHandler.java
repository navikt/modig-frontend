package no.nav.modig.frontend.merged;

import no.nav.modig.frontend.merged.wrapped.WrappedWebRequest;
import no.nav.modig.frontend.merged.wrapped.WrappedWebResponse;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.IRequestCycle;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceRequestHandler;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.time.Time;

import javax.servlet.http.Cookie;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class MergedResourceRequestHandler implements IRequestHandler {

    private List<ResourceReference> resources;
    private PageParameters pageParameters;
    private Time lastModified;

    public MergedResourceRequestHandler(List<ResourceReference> resources, PageParameters params, Time lastModified) {
        this.resources = resources;
        this.pageParameters = params;
        this.lastModified = lastModified;
    }

    public void respond(IRequestCycle requestCycle) {
        WebRequest origRequest = (WebRequest) requestCycle.getRequest();

        // Explicitly set the last modified header of the response based on the last modified
        // time of the aggregate. Do this on the original response because our wrapped response
        // ignores the last modified headers contributed by each individual resource.
        WebResponse origResponse = (WebResponse) requestCycle.getResponse();
        if (this.lastModified != null) {
            origResponse.setLastModifiedTime(this.lastModified);
        }

        try {
            // Make a special response object that merges the contributions of each resource,
            // but maintains a single set of headers.
            MergedResponse merged = new MergedResponse(origResponse);
            requestCycle.setResponse(merged);

            // Make a special request object that tweaks the If-Modified-Since header to ensure
            // we don't end up in a situation where some resources respond 200 and others 304.
            // Yes, calling RequestCycle#setRequest() is frowned upon so this is a bit of a hack.
            ((RequestCycle) requestCycle).setRequest(new MergedRequest(origRequest));

            for (ResourceReference ref : this.resources) {
                ResourceRequestHandler handler = new ResourceRequestHandler(ref.getResource(), this.pageParameters);
                handler.respond(requestCycle);

                try {
                    merged.write("\n".getBytes("UTF-8"));
                } catch (IOException e) {
                    throw new WicketRuntimeException(e);
                }

                // If first resource sent 304 Not Modified that means all will.
                // We can therefore skip the rest.
                if (304 == merged.status) {
                    break;
                }
            }
            if (merged.status != 304) {
                merged.flushHeadersAndWriteOutput();
            }
        } finally {
            // Restore the original request once we're done. We don't need to restore the
            // original response because Wicket takes care of that automatically.
            ((RequestCycle) requestCycle).setRequest(origRequest);
            requestCycle.setResponse(origResponse);
        }
    }

    public void detach(IRequestCycle requestCycle) {
        this.resources = null;
        this.pageParameters = null;
        this.lastModified = null;
    }

    private static class MergedResponse extends WrappedWebResponse {

        private int status = 200;
        private boolean headersOpen = true;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        MergedResponse(WebResponse original) {
            super(original);
        }

        private void flushHeadersAndWriteOutput() {
            this.wrapped.setContentLength(os.size());
            this.wrapped.flush();
            this.wrapped.write(os.toByteArray());
        }

        @Override
        public void close() {
            // ignore
        }

        @Override
        public void reset() {
            this.headersOpen = true;
            this.os.reset();
        }

        @Override
        public void write(CharSequence sequence) {
            this.headersOpen = false;
            try {
                this.os.write(sequence.toString().getBytes());
            } catch (IOException e) {
                throw new WicketRuntimeException(e);
            }
        }

        @Override
        public void write(byte[] array) {
            this.headersOpen = false;
            try {
                this.os.write(array);
            } catch (IOException e) {
                throw new WicketRuntimeException(e);
            }
        }

        @Override
        public OutputStream getOutputStream() {
            return this.os;
        }

        @Override
        public void write(byte[] array, int offset, int length) {
            this.headersOpen = false;
            this.os.write(array, offset, length);
        }

        @Override
        public void addCookie(final Cookie cookie) {
            if (this.headersOpen) {
                this.wrapped.addCookie(cookie);
            }
        }

        @Override
        public void clearCookie(final Cookie cookie) {
            if (this.headersOpen) {
                this.wrapped.clearCookie(cookie);
            }
        }

        @Override
        public void setHeader(String name, String value) {
            if (this.headersOpen) {
                this.wrapped.setHeader(name, value);
            }
        }

        @Override
        public void addHeader(String name, String value) {
            if (this.headersOpen) {
                this.wrapped.addHeader(name, value);
            }
        }

        @Override
        public void setDateHeader(String name, Time date) {
            if (this.headersOpen && name != null && !name.equalsIgnoreCase("Last-Modified")) {
                this.wrapped.setDateHeader(name, date);
            }
        }

        @Override
        public void setContentLength(final long length) {
            // ignore
        }

        @Override
        public void setContentType(final String mimeType) {
            if (this.headersOpen) {
                this.wrapped.setContentType(mimeType);
            }
        }

        @Override
        public void setAttachmentHeader(final String filename) {
            // ignore
        }

        @Override
        public void setInlineHeader(final String filename) {
            // ignore
        }

        @Override
        public void flush() {
            //ignore
        }

        @Override
        public void setStatus(int sc) {
            if (this.headersOpen) {
                this.status = sc;
                this.wrapped.setStatus(sc);
            }
        }
    }

    /**
     * A WebRequest wrapper than allows all method calls to pass through to the wrapped request,
     * except for getDateHeader(). We need to take special action for the If-Modified-Since header
     * to fool the individual resources into behaving as a single resource with a single
     * modification date.
     */
    private class MergedRequest extends WrappedWebRequest {

        MergedRequest(WebRequest original) {
            super(original);
        }

        @Override
        public Time getDateHeader(final String name) {
            Time headerTime = this.wrapped.getDateHeader(name);
            if (headerTime != null && name != null && name.equalsIgnoreCase("If-Modified-Since")) {
                // Truncate milliseconds since the modified since header has only second precision
                long modified = lastModified.getMilliseconds() / 1000 * 1000;
                if (headerTime.getMilliseconds() < modified) {
                    // Our merged data in aggregate is newer than what the browser has cached.
                    // Therefore remove the If-Modified-Since header from the request to
                    // force all resources to respond with data.
                    headerTime = null;
                } else {
                    // Our merged data in aggregate has not changed. Set the If-Modified-Since
                    // header to an extremely high value to force all resources to respond 304.
                    headerTime = Time.millis(Long.MAX_VALUE);
                }
            }
            return headerTime;
        }
    }
}
