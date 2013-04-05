package no.nav.modig.frontend;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for {@link CssMinCompressor}
 */
public class CssMinCompressorTest {

    @Test
    public void testSomething() {
        CssMinCompressor compressor = new CssMinCompressor();
        String compressed = compressor.compress(
                "body .someClass {\n" +
                "margin:1px;\n" +
                "}");

        assertThat(compressed, is("body .someClass{margin:1px}"));
    }
}
