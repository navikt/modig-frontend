package no.nav.modig.frontend.merged.wrapped;

import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.time.Time;

import javax.servlet.http.Cookie;
import java.io.OutputStream;

/**
 * Wraps a {@link WebResponse}
 */
public class WrappedWebResponse extends WebResponse {

    protected final WebResponse wrapped;

    public WrappedWebResponse(WebResponse wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void addCookie(Cookie cookie) {
        this.wrapped.addCookie(cookie);
    }

    @Override
    public void clearCookie(Cookie cookie) {
        this.wrapped.clearCookie(cookie);
    }

    @Override
    public void setHeader(String name, String value) {
        this.wrapped.setHeader(name, value);
    }

    @Override
    public void addHeader(String name, String value) {
        this.wrapped.addHeader(name, value);
    }

    @Override
    public void setDateHeader(String name, Time date) {
        this.wrapped.setDateHeader(name, date);
    }

    @Override
    public void setContentLength(long length) {
        this.wrapped.setContentLength(length);
    }

    @Override
    public void setContentType(String mimeType) {
        this.wrapped.setContentType(mimeType);
    }

    @Override
    public void setStatus(int sc) {
        this.wrapped.setStatus(sc);
    }

    @Override
    public void sendError(int sc, String msg) {
        this.wrapped.sendError(sc, msg);
    }

    @Override
    public String encodeRedirectURL(CharSequence url) {
        return this.wrapped.encodeRedirectURL(url);
    }

    @Override
    public void sendRedirect(String url) {
        this.wrapped.sendRedirect(url);
    }

    @Override
    public boolean isRedirect() {
        return this.wrapped.isRedirect();
    }

    @Override
    public void flush() {
        this.wrapped.flush();
    }

    @Override
    public void write(CharSequence sequence) {
        this.wrapped.write(sequence);
    }

    @Override
    public void write(byte[] array) {
        this.wrapped.write(array);
    }

    @Override
    public void write(byte[] array, int offset, int length) {
        this.wrapped.write(array, offset, length);
    }

    @Override
    public String encodeURL(CharSequence url) {
        return this.wrapped.encodeURL(url);
    }

    @Override
    public Object getContainerResponse() {
        return this.wrapped.getContainerResponse();
    }

    @Override
    public OutputStream getOutputStream() {
        return this.wrapped.getOutputStream();
    }

    @Override
    public void reset() {
        this.wrapped.reset();
    }

    @Override
    public void close() {
        this.wrapped.close();
    }
}
