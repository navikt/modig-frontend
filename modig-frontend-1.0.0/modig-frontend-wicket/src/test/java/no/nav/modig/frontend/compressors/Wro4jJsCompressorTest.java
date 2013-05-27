package no.nav.modig.frontend.compressors;

import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
}
