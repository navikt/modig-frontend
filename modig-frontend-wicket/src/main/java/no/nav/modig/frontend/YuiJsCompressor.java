package no.nav.modig.frontend;

import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.apache.wicket.javascript.IJavaScriptCompressor;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;


public class YuiJsCompressor implements IJavaScriptCompressor {

    static String encoding = "UTF-8";
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
            System.out.println("Kunne ikke komprimere scriptet");
            return original;
        }
    }
}
