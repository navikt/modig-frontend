package no.nav.modig.frontend;

import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.tools.shell.Global;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Compresses CSS using cssmin and Rhino
 * <p/>
 * Using YUIs version of css min found here: https://github.com/yui/ycssmin
 */
public class UglifyJsCompressor implements IJavaScriptCompressor {
    private static final String COMPRESS_STRING = "function doIt(input) { return muglify(input);}";
    private URL rhinoNodeEnvJs = UglifyJsCompressor.class.getClassLoader().getResource("rhino-node-env.js");
    private URL[] urls1 = new URL[]{
            rhinoNodeEnvJs,
            UglifyJsCompressor.class.getClassLoader().getResource("uglify-js/lib/parse-js.js"),
            UglifyJsCompressor.class.getClassLoader().getResource("uglify-js/lib/process.js"),
            UglifyJsCompressor.class.getClassLoader().getResource("uglify-js/lib/consolidator.js"),
            UglifyJsCompressor.class.getClassLoader().getResource("muglify.js")
    };

    private final Function doIt;
    private final Scriptable scope;

    public UglifyJsCompressor() {
        try {
            Context ctx = Context.enter();
            Global global = new Global();
            global.init(ctx);

            scope = ctx.initStandardObjects(global);


            for (URL url : urls1) {
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                try {
                    ctx.evaluateReader(scope, inputStreamReader, url.toString(), 1, null);
                } finally {
                    inputStreamReader.close();
                }
            }

            doIt = ctx.compileFunction(scope, COMPRESS_STRING, "doIt.js", 1, null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize CssMinCompressor", e);
        }
    }

    @Override
    public String compress(String original) {
        Context ctx = Context.enter();
        return (String) doIt.call(ctx, scope, null, new Object[]{original});
    }
}
