package no.nav.modig.frontend.merged.wrapped;

import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.time.Time;

import javax.servlet.http.Cookie;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

/**
 * Wraps a {@link WebRequest}
 */
public class WrappedWebRequest extends WebRequest {
    protected final WebRequest wrapped;

    public WrappedWebRequest(WebRequest wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public List<Cookie> getCookies() {
        return wrapped.getCookies();
    }

    @Override
    public List<String> getHeaders(String name) {
        return wrapped.getHeaders(name);
    }

    @Override
    public String getHeader(String name) {
        return wrapped.getHeader(name);
    }

    @Override
    public Time getDateHeader(String name) {
        return wrapped.getDateHeader(name);
    }

    @Override
    public boolean shouldPreserveClientUrl() {
        return wrapped.shouldPreserveClientUrl();
    }

    @Override
    public Url getUrl() {
        return wrapped.getUrl();
    }

    @Override
    public Url getClientUrl() {
        return wrapped.getClientUrl();
    }

    @Override
    public IRequestParameters getPostParameters() {
        return wrapped.getPostParameters();
    }

    @Override
    public Locale getLocale() {
        return wrapped.getLocale();
    }

    @Override
    public String getPrefixToContextPath() {
        return wrapped.getPrefixToContextPath();
    }

    @Override
    public String getContextPath() {
        return wrapped.getContextPath();
    }

    @Override
    public String getFilterPath() {
        return wrapped.getFilterPath();
    }

    @Override
    public Charset getCharset() {
        return wrapped.getCharset();
    }

    @Override
    public Object getContainerRequest() {
        return wrapped.getContainerRequest();
    }
}
