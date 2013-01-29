package no.nav.modig.frontend;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class YuiJsCompressor implements IJavaScriptCompressor {
    private static final Logger LOG = LoggerFactory.getLogger(YuiJsCompressor.class);

    static int lineBreak = -1;
    static boolean noMunge = true;
    static boolean preserveSemi = true;
    static boolean disableOptimizations = true;

    @Override
    public String compress(String original) {
        try {
            JavaScriptCompressor jsCompressor = new JavaScriptCompressor(new StringReader(original), null);
            StringWriter stringWriter = new StringWriter();
            jsCompressor.compress(stringWriter, lineBreak, !noMunge, false, preserveSemi, disableOptimizations);
            return stringWriter.toString();
        } catch (IOException e) {
            LOG.warn("Kunne ikke komprimere scriptet", e);
            return original;
        }
    }
}
