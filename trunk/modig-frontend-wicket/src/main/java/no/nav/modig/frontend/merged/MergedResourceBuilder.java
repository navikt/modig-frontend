package no.nav.modig.frontend.merged;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.caching.IResourceCachingStrategy;
import org.apache.wicket.util.IProvider;

import java.util.ArrayList;
import java.util.List;


public abstract class MergedResourceBuilder {

	private String path;
	private boolean frozen = false;
	private List<ResourceReference> references;

	public MergedResourceBuilder() {
		this.references = new ArrayList<>();
	}

	public MergedResourceBuilder setPath(String path) {
		this.path = path;
		return this;
	}

    public MergedResourceBuilder install(WebApplication app) {
		app.mount(buildRequestMapper(app));
		return this;
	}

	public IRequestMapper buildRequestMapper(final WebApplication app) {
		if (!this.frozen) {
            assertRequiredOptionsAndFreeze();
        }

		return new MergedResourceMapper(
				this.path,
				this.references,
				new PageParametersEncoder(),
				new IProvider<IResourceCachingStrategy>() {
					public IResourceCachingStrategy get() {
						return app.getResourceSettings().getCachingStrategy();
					}
				});
	}

    protected void add(ResourceReference ref) {
		if (this.frozen) {
			throw new IllegalStateException("Resources cannot be added once build() or install() methods have been called.");
		}
		this.references.add(ref);
	}

    protected void assertRequiredOptionsAndFreeze() {
		if (null == this.path) {
			throw new IllegalStateException("path must be set");
		}
		if (this.references.size() == 0) {
			throw new IllegalStateException("at least one resource must be added");
		}
		this.frozen = true;
	}
}
