package no.nav.modig.frontend.less;

import org.apache.wicket.Application;
import org.apache.wicket.WicketRuntimeException;
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
import java.util.List;

/**
 * Resource for compiled less files. Compiles less files and serves them as css.
 * <p/>
 * NOT A PART OF THE PUBLIC API
 * <p/>
 * Use {@link no.nav.modig.frontend.FrontendConfigurator} to setup less compilation
 */
public class CompiledLessResource extends AbstractResource implements IStaticCacheableResource {
    private static final long serialVersionUID = 1L;

    private static final Logger log = LoggerFactory.getLogger(CompiledLessResource.class);

    private final ResourceBundle resourceBundle;
    private CompiledUnit compiledUnit = new CompiledUnit();
    private LessCompiler lessCompiler;

    public CompiledLessResource(List<PackageResourceReference> references) {
        this.resourceBundle = new ResourceBundle(references);
    }


    @Override
    protected ResourceResponse newResourceResponse(Attributes attributes) {
        ResourceResponse resourceResponse = new ResourceResponse();
        Time lastModified = resourceBundle.getLastModified();
        resourceResponse.setLastModified(lastModified);
        if (resourceResponse.dataNeedsToBeWritten(attributes)) {

            final byte[] compiledResource = getCompiledResource(lastModified);

            resourceResponse.setContentType("text/css");
            resourceResponse.setContentLength(compiledResource.length);
            resourceResponse.setWriteCallback(new WriteCallback() {
                @Override
                public void writeData(Attributes attributes) {
                    attributes.getResponse().write(compiledResource);
                }
            });
        }
        return resourceResponse;
    }

    private byte[] getCompiledResource(final Time lastModified) {
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

    public String compileResources() {
        try {
            String concatenatedResources = resourceBundle.getResourceString();
            return getLessCompiler().compile(concatenatedResources);
        } catch (LessException e) {
            throw new WicketRuntimeException("Could not compile concatedated less resources", e);
        }
    }

    private LessCompiler getLessCompiler() {
        // Return a fresh instance if in production mode
        // (Don't keep rhino around)
        if (Application.get().usesDeploymentConfig()) {
            return new LessCompiler();
        }

        if (lessCompiler == null) {
            lessCompiler = new LessCompiler();
        }
        return lessCompiler;
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

                byte[] compiledResource = getCompiledResource(resourceBundle.getLastModified());
                return new ByteArrayInputStream(compiledResource);
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            public Time lastModifiedTime() {
                return resourceBundle.getLastModified();
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

}
