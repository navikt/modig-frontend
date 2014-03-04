package no.nav.modig.frontend.filter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
        httpResponse.addHeader("Expires", convertToHeaderDate(expireDateTime()));

        chain.doFilter(request, response);
    }

    /**
     * Hent tiden for når cache-headeren skal utløpe.<br>
     * - I følge HTTP-spesifikasjonen (http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.3.1)
     * skal alle HTTP datetime objekter representeres i Greenwich Mean Time (GMT), eller i UTC som tilsvarer det samme.<br>
     * - Tiden settes også frem i tid basert på hvor lenge det er ønskelig at cache-headeren skal være gyldig.
     * @return datoobjekt for når cache-headeren skal utløpe.
     */
    private DateTime expireDateTime() {
        DateTime utcTime = new DateTime(DateTimeZone.UTC);
        return utcTime.plusMinutes(10);
    }
    
    private String convertToHeaderDate(DateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(PATTERN);
        return formatter.print(dateTime);
    }

    @Override
    public void destroy() {
        LOG.debug("Cachefilter destroy");
    }
}
