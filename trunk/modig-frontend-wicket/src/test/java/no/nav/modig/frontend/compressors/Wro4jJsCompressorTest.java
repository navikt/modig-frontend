package no.nav.modig.frontend.compressors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.junit.Test;

/**
 * Tests for {@link Wro4jJsCompressor}
 */
public class Wro4jJsCompressorTest {

    @Test
    public void minifiesJs() {
        IJavaScriptCompressor compressor = new Wro4jJsCompressor();
        String compressed = compressor.compress("(function(){\n" +
                "var longVar = 10;\n" +
                "alert(longVar);\n" +
                "})();");

        assertThat(compressed, is("\n(function(){var longVar=10;alert(longVar);})();"));
    }

    @Test
    public void keepsCopyright() {
        IJavaScriptCompressor compressor = new Wro4jJsCompressor();
        String copyright = "/*!\n" +
                " * lib name \n" +
                " * Copyright 2013, NAV\n" +
                " *\n" +
                " * Date: Thu Jun 10 08:00:00 2013 +0100\n" +
                " *\n" +
                " */\n";
        String compressed = compressor.compress(
                copyright +
                        "(function(){\n" +
                        "/* a comment */" +
                        "var longVar = 10;\n" +
                        "alert(longVar);\n" +
                        "})();");

        assertThat(compressed, is(copyright + "(function(){var longVar=10;alert(longVar);})();"));
    }
}
