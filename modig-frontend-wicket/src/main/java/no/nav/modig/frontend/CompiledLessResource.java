package no.nav.modig.frontend;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.Application;
import org.apache.wicket.css.ICssCompressor;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.caching.IStaticCacheableResource;
import org.apache.wicket.util.resource.AbstractResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;
import org.lesscss.LessCompiler;
import org.lesscss.LessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

/**
 * Resource for compiled less files. Compiles less files and serves them as css.
 */
class CompiledLessResource extends AbstractResource implements IStaticCacheableResource {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CompiledLessResource.class);

    private final List<PackageResourceReference> references;
    private CompiledUnit compiledUnit = new CompiledUnit();

    public CompiledLessResource(List<PackageResourceReference> references) {
        this.references = references;
    }


    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse resourceResponse = new ResourceResponse();
        Time lastModified = getLastModified();
        resourceResponse.setLastModified(lastModified);
        if (resourceResponse.dataNeedsToBeWritten(attributes)) {
            try {
                final byte[] compiledResource = getCompiledResource(lastModified);

                resourceResponse.setContentType("text/css");
                resourceResponse.setContentLength(compiledResource.length);
                resourceResponse.setWriteCallback(new WriteCallback() {
                    @Override
                    public void writeData(Attributes attributes) {
                        attributes.getResponse().write(compiledResource);
                    }
                });

            } catch (ResourceStreamException | LessException e) {
                resourceResponse.setError(500, "Unable to get compiled less");
                return resourceResponse;
            }
        }
        return resourceResponse;
    }

    private byte[] getCompiledResource(final Time lastModified) throws ResourceStreamException, LessException {
        // Double checked locking
        if (lastModified.greaterThan(compiledUnit.getCompiledTime())) {
            synchronized (this) {
                if (lastModified.greaterThan(compiledUnit.getCompiledTime())) {
                    String compiled = compileResources();
                    String compressed = compressResource(compiled);
                    this.compiledUnit = new CompiledUnit(compressed.getBytes(), lastModified);
                }
            }
        }
        return compiledUnit.getCompiledBytes();
    }

    public String compileResources() throws ResourceStreamException, LessException {
        String concatenatedResources = getResourceString();
        LessCompiler lessCompiler = new LessCompiler();
        return lessCompiler.compile(concatenatedResources);
    }

    private String getResourceString() throws ResourceStreamException {
        StringWriter stringWriter = new StringWriter();
        for (PackageResourceReference lessReference : references) {
            IResourceStream resourceStream = lessReference.getResource().getResourceStream();
            if (resourceStream == null) {
                throw new ResourceStreamException("Unable to find resource");
            }

            try {
                IOUtils.copy(resourceStream.getInputStream(), stringWriter, "utf-8");
            } catch (IOException | ResourceStreamNotFoundException e) {
                throw new ResourceStreamException("Unable to read resource stream", e);
            } finally {
                try {
                    resourceStream.close();
                } catch (IOException e) {
                    log.warn("Unable to close the resource stream", e);
                }
            }
        }
        return stringWriter.toString();
    }

    private String compressResource(String nonCompressed) {
        ICssCompressor compressor = getCompressor();

        if (compressor != null) {
            try {
                return compressor.compress(nonCompressed);
            } catch (Exception e) {
                log.error("Error while filtering content", e);
                return nonCompressed;
            }
        } else {
            // don't strip the comments
            return nonCompressed;
        }
    }

    public Time getLastModified() {
        Time lastModifiedTime = Time.START_OF_UNIX_TIME;
        for (PackageResourceReference reference : references) {
            Time resourceLastModifiedTime = reference.getResource().getResourceStream().lastModifiedTime();
            if (resourceLastModifiedTime.after(lastModifiedTime)) {
                lastModifiedTime = resourceLastModifiedTime;
            }
        }
        return lastModifiedTime;
    }

    @Override
    public boolean isCachingEnabled() {
        return true; // Cache compiled less file
    }

    @Override
    public Serializable getCacheKey() {
        return new CacheKey();
    }

    @Override
    public IResourceStream getCacheableResourceStream() {
        return new AbstractResourceStream() {
            @Override
            public InputStream getInputStream() throws ResourceStreamNotFoundException {
                try {
                    byte[] compiledResource = getCompiledResource(getLastModified());
                    return new ByteArrayInputStream(compiledResource);
                } catch (ResourceStreamException | LessException e) {
                    throw new ResourceStreamNotFoundException(e);
                }
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            public Time lastModifiedTime() {
                return getLastModified();
            }
        };
    }

    protected ICssCompressor getCompressor() {
        ICssCompressor compressor = null;
        if (Application.exists()) {
            compressor = Application.get().getResourceSettings().getCssCompressor();
        }
        return compressor;
    }

    public int getCompileCount() {
        return compiledUnit.getVersion();
    }

    private static class CacheKey implements Serializable {
    }


    private static final class ResourceStreamException extends Exception {
        private ResourceStreamException(String message) {
            super(message);
        }

        private ResourceStreamException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Contains the compiled bytes and last modified time for the resources at the compilation time.
     *
     * Immutable.
     */
    private static final class CompiledUnit {
        private static int count = 0;

        private final  byte[] compiledBytes;
        private final Time compiledTime;
        private final int version;

        private CompiledUnit() {
            this(new byte[]{}, Time.START_OF_UNIX_TIME);
        }

        private CompiledUnit(byte[] compiledBytes, Time compiledTime) {
            this.compiledBytes = compiledBytes;
            this.compiledTime = compiledTime;
            version = count++;
        }

        private byte[] getCompiledBytes() {
            return compiledBytes;
        }

        private Time getCompiledTime() {
            return compiledTime;
        }

        private int getVersion() {
            return version;
        }
    }

}
