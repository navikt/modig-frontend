package no.nav.modig.frontend.compressors;

import org.apache.wicket.css.ICssCompressor;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for {@link Wro4jCssCompressor}
 */
public class Wro4jCssCompressorTest {


    @Test
    public void testSomething() {
        ICssCompressor compressor = new Wro4jCssCompressor();
        String compressed = compressor.compress(
                "body .someClass {\n" +
                        "margin:1px;\n" +
                        "}");

        assertThat(compressed, is("body .someClass{margin:1px}"));
    }


    @Test
    public void testMediaQuery() {
        ICssCompressor compressor = new Wro4jCssCompressor();
        String compressed = compressor.compress(
                "" +
                        "@media (max-width:767px) {\n" +
                        "    body .someClass {\n" +
                        "    margin:1px;\n" +
                        "    }\n" +
                        "}");

        assertThat(compressed, is("@media(max-width:767px){body .someClass{margin:1px;}}"));
    }
}
