package no.nav.modig.frontend.filter;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.joda.time.format.DateTimeFormat.forPattern;

public class StaticResourcesCacheFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(StaticResourcesCacheFilter.class);
    // Formatet: 'Tue, 15 Nov 1994 08:12:31 GMT'
    private static final String PATTERN = "EE, dd MMM yyyy HH:mm:ss zz";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Cachefilter init");
    }

    @Override
    public void doFilter(final ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Cachefilter doFilter");
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        httpResponse.addHeader("Cache-control", "public, max-age=600");
        httpResponse.addHeader("Expires", convertToHeaderDate(DateTime.now().plusMinutes(10)));

        chain.doFilter(request, response);
    }
    
    private String convertToHeaderDate(DateTime dateTime) {
        return forPattern(PATTERN).print(dateTime);
    }

    @Override
    public void destroy() {
        LOG.debug("Cachefilter destroy");
    }
}
