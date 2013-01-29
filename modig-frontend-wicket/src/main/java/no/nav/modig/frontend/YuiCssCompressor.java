package no.nav.modig.frontend;

import com.yahoo.platform.yui.compressor.CssCompressor;
import org.apache.wicket.css.ICssCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class YuiCssCompressor implements ICssCompressor {
    private static final Logger LOG = LoggerFactory.getLogger(YuiCssCompressor.class);

    @Override
    public String compress(String original) {
        try {
            CssCompressor cssCompressor = new CssCompressor(new StringReader(original));
            StringWriter stringWriter = new StringWriter();
            cssCompressor.compress(stringWriter, -1);
            return stringWriter.toString();
        } catch (IOException e) {
            LOG.warn("Kunne ikke komprimere css", e);
            return original;
        }
    }
}
