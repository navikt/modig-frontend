package no.nav.modig.frontend.merged.wrapped;

import org.apache.wicket.request.IRequestParameters;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.time.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.Cookie;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tester for {@link WrappedWebRequest}
 */
@RunWith(MockitoJUnitRunner.class)
public class WrappedWebRequestTest {

    @Mock
    private WebRequest original;

    private WrappedWebRequest wrappedWebRequest;


    @Before
    public void setUp() throws Exception {
        wrappedWebRequest = new WrappedWebRequest(original);
    }

    @Test
    public void testGetCookies() throws Exception {
        List<Cookie> cookies = asList(new Cookie("cookieName", "cookieValue"));
        when(original.getCookies()).thenReturn(cookies);

        assertThat(wrappedWebRequest.getCookies(), is(cookies));
    }

    @Test
    public void testGetHeaders() throws Exception {
        List<String> headers = asList("value1", "value2");
        when(original.getHeaders("headerName")).thenReturn(headers);

        assertThat(wrappedWebRequest.getHeaders("headerName"), is(headers));
    }

    @Test
    public void testGetHeader() throws Exception {
        String header = "header";
        when(original.getHeader("headerName")).thenReturn(header);

        assertThat(wrappedWebRequest.getHeader("headerName"), is(header));
    }

    @Test
    public void testGetDateHeader() throws Exception {
        Time dateHeader = Time.now();
        when(original.getDateHeader("headerName")).thenReturn(dateHeader);

        assertThat(wrappedWebRequest.getDateHeader("headerName"), is(dateHeader));
    }

    @Test
    public void testShouldPreserveClientUrl() throws Exception {

        when(original.shouldPreserveClientUrl()).thenReturn(true);
        assertThat(wrappedWebRequest.shouldPreserveClientUrl(), is(true));

        when(original.shouldPreserveClientUrl()).thenReturn(false);
        assertThat(wrappedWebRequest.shouldPreserveClientUrl(), is(false));
    }

    @Test
    public void testGetUrl() throws Exception {
        Url url = new Url();
        when(original.getUrl()).thenReturn(url);

        assertThat(wrappedWebRequest.getUrl(), is(url));
    }

    @Test
    public void testGetClientUrl() throws Exception {
        Url clientUrl = new Url();
        when(original.getClientUrl()).thenReturn(clientUrl);

        assertThat(wrappedWebRequest.getClientUrl(), is(clientUrl));
    }

    @Test
    public void testGetPostParameters() throws Exception {
        IRequestParameters postParameters = mock(IRequestParameters.class);
        when(original.getPostParameters()).thenReturn(postParameters);

        assertThat(wrappedWebRequest.getPostParameters(), is(postParameters));
    }

    @Test
    public void testGetLocale() throws Exception {
        Locale locale = Locale.CHINESE;
        when(original.getLocale()).thenReturn(locale);

        assertThat(wrappedWebRequest.getLocale(), is(locale));
    }

    @Test
    public void testGetPrefixToContextPath() throws Exception {
        String prefix = "prefix";
        when(original.getPrefixToContextPath()).thenReturn(prefix);

        assertThat(wrappedWebRequest.getPrefixToContextPath(), is(prefix));
    }

    @Test
    public void testGetContextPath() throws Exception {
        String contextPath = "contextPath";
        when(original.getContextPath()).thenReturn(contextPath);

        assertThat(wrappedWebRequest.getContextPath(), is(contextPath));
    }

    @Test
    public void testGetFilterPath() throws Exception {
        String filterPath = "filterPath";
        when(original.getFilterPath()).thenReturn(filterPath);

        assertThat(wrappedWebRequest.getFilterPath(), is(filterPath));
    }

    @Test
    public void testGetCharset() throws Exception {
        Charset charset = Charset.forName("utf-8");
        when(original.getCharset()).thenReturn(charset);

        assertThat(wrappedWebRequest.getCharset(), is(charset));
    }

    @Test
    public void testGetContainerRequest() throws Exception {
        Object containerRequest = new Object();
        when(original.getContainerRequest()).thenReturn(containerRequest);

        assertThat(wrappedWebRequest.getContainerRequest(), is(containerRequest));
    }
}
