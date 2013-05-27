package no.nav.modig.frontend.merged.wrapped;

import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.util.time.Time;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.Cookie;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tester for {@link WrappedWebResponse}
 */
@RunWith(MockitoJUnitRunner.class)
public class WrappedWebResponseTest {

    @Mock
    private WebResponse original;

    private WrappedWebResponse wrappedWebResponse;

    @Before
    public void setUp() throws Exception {
        wrappedWebResponse = new WrappedWebResponse(original);
    }

    @Test
    public void testAddCookie() throws Exception {
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        wrappedWebResponse.addCookie(cookie);

        verify(original).addCookie(cookie);
    }

    @Test
    public void testClearCookie() throws Exception {
        Cookie cookie = new Cookie("cookieName", "cookieValue");
        wrappedWebResponse.clearCookie(cookie);

        verify(original).clearCookie(cookie);
    }

    @Test
    public void testSetHeader() throws Exception {
        wrappedWebResponse.setHeader("headerName", "headerValue");

        verify(original).setHeader("headerName", "headerValue");
    }

    @Test
    public void testAddHeader() throws Exception {
        wrappedWebResponse.addHeader("headerName", "headerValue");

        verify(original).addHeader("headerName", "headerValue");
    }

    @Test
    public void testSetDateHeader() throws Exception {
        Time time = Time.now();
        wrappedWebResponse.setDateHeader("headerName", time);

        verify(original).setDateHeader("headerName", time);
    }

    @Test
    public void testSetContentLength() throws Exception {
        wrappedWebResponse.setContentLength(10);

        verify(original).setContentLength(10);
    }

    @Test
    public void testSetContentType() throws Exception {
        wrappedWebResponse.setContentType("contentType");

        verify(original).setContentType("contentType");
    }

    @Test
    public void testSetStatus() throws Exception {
        wrappedWebResponse.setStatus(304);

        verify(original).setStatus(304);
    }

    @Test
    public void testSendError() throws Exception {
        wrappedWebResponse.sendError(500, "errormessage");

        verify(original).sendError(500, "errormessage");
    }

    @Test
    public void testEncodeRedirectURL() throws Exception {
        when(original.encodeRedirectURL("url")).thenReturn("encodedUrl");

        assertThat(wrappedWebResponse.encodeRedirectURL("url"), is("encodedUrl"));
    }

    @Test
    public void testSendRedirect() throws Exception {
        wrappedWebResponse.sendRedirect("url");

        verify(original).sendRedirect("url");
    }

    @Test
    public void testIsRedirect() throws Exception {
        when(original.isRedirect()).thenReturn(true);
        assertThat(wrappedWebResponse.isRedirect(), is(true));

        when(original.isRedirect()).thenReturn(false);
        assertThat(wrappedWebResponse.isRedirect(), is(false));

    }

    @Test
    public void testFlush() throws Exception {
        wrappedWebResponse.flush();

        verify(original).flush();
    }

    @Test
    public void testWrite() throws Exception {
        byte[] bytes = new byte[]{};
        wrappedWebResponse.write(bytes);
        verify(original).write(bytes);

        wrappedWebResponse.write(bytes, 12, 12);
        verify(original).write(bytes, 12, 12);

        wrappedWebResponse.write("charset");
        verify(original).write("charset");
    }

    @Test
    public void testEncodeURL() throws Exception {
        when(original.encodeURL("url")).thenReturn("encodedUrl");

        assertThat(wrappedWebResponse.encodeURL("url"), is("encodedUrl"));
    }

    @Test
    public void testGetContainerResponse() throws Exception {
        Object containerResponse = new Object();
        when(original.getContainerResponse()).thenReturn(containerResponse);

        assertThat(wrappedWebResponse.getContainerResponse(), is(containerResponse));

    }
}
