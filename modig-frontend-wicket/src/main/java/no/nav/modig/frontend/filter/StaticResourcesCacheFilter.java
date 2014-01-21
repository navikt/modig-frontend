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

public class StaticResourcesCacheFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(StaticResourcesCacheFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Cachefilter init");
    }

    @Override
    public void doFilter(final ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Cachefilter doFilter");
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        httpResponse.addHeader("Cache-control", "public, max-age=600");
        httpResponse.addHeader("Expires", new DateTime().plusMinutes(10).toString());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOG.debug("Cachefilter destroy");
    }
}
