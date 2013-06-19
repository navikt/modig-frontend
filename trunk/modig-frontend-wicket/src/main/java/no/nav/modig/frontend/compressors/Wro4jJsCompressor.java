package no.nav.modig.frontend.compressors;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.javascript.IJavaScriptCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.model.resource.processor.decorator.CopyrightKeeperProcessorDecorator;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;

/**
 * Compresses JavaScript using the Wro4J library
 */
public class Wro4jJsCompressor implements IJavaScriptCompressor {
    private static final Logger log = LoggerFactory.getLogger(Wro4jJsCompressor.class);


    private final JSMinProcessor processor = new JSMinProcessor();
    private final CopyrightKeeperProcessorDecorator copyrightKeeper = CopyrightKeeperProcessorDecorator.decorate(processor);

    @Override
    public String compress(String original) {
        StringReader reader = new StringReader(original);
        StringWriter writer = new StringWriter();
        try {
            copyrightKeeper.process(reader, writer);
            return writer.toString();
        } catch (IOException e) {
            log.warn("Could not compress css", e);
            return original;
        }
    }
}
