package no.nav.modig.frontend.compressors;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.wicket.css.ICssCompressor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.model.resource.processor.decorator.CopyrightKeeperProcessorDecorator;
import ro.isdc.wro.model.resource.processor.impl.css.JawrCssMinifierProcessor;

/**
 * Compresses css using the Wro4j library
 */
public class Wro4jCssCompressor implements ICssCompressor {
    private static final Logger log = LoggerFactory.getLogger(Wro4jCssCompressor.class);

    private final JawrCssMinifierProcessor processor = new JawrCssMinifierProcessor();
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
