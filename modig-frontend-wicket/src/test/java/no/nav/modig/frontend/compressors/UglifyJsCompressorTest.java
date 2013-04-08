package no.nav.modig.frontend.compressors;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link no.nav.modig.frontend.compressors.UglifyJsCompressor}
 */
public class UglifyJsCompressorTest {

    @Test
    public void minifiesJs() {
        UglifyJsCompressor compressor = new UglifyJsCompressor();
        String compressed = compressor.compress("(function(){var longVar = 10;alert(longVar);})();");

        assertThat(compressed, is("(function(){var e=10;alert(e)})();\n"));
    }
}
