package no.nav.modig.frontend.merged;

import java.util.List;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.IRequestMapper;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.handler.resource.ResourceReferenceRequestHandler;
import org.apache.wicket.request.mapper.AbstractMapper;
import org.apache.wicket.request.mapper.parameter.IPageParametersEncoder;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.caching.IResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.IStaticCacheableResource;
import org.apache.wicket.request.resource.caching.ResourceUrl;
import org.apache.wicket.util.IProvider;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.time.Time;

public class MergedResourceMapper extends AbstractMapper implements IRequestMapper {

	protected final String[] mountSegments;
	protected final List<ResourceReference> resources;
	protected final IPageParametersEncoder parametersEncoder;
	protected final IProvider<? extends IResourceCachingStrategy> cachingStrategy;

	public MergedResourceMapper(String path, List<ResourceReference> resources, IPageParametersEncoder parametersEncoder,
			IProvider<? extends IResourceCachingStrategy> cachingStrategy) {
		// TODO: validate arguments
		this.resources = resources;
		this.mountSegments = getMountSegments(path);
		this.parametersEncoder = parametersEncoder;
		this.cachingStrategy = cachingStrategy;
	}

	public int getCompatibilityScore(Request request) {
		return 0;
	}

	public IRequestHandler mapRequest(Request request) {
		PageParameters parameters = null;
		List<String> requestSegments = request.getUrl().getSegments();

		if (requestSegments.size() < this.mountSegments.length) {
			return null;
		}
		for (int i = 0; i < this.mountSegments.length; i++) {
			String segment = requestSegments.get(i);
			if (i + 1 == this.mountSegments.length) {
				parameters = extractPageParameters(request, this.mountSegments.length, this.parametersEncoder);
				ResourceUrl resourceUrl = new ResourceUrl(segment, parameters);
				this.cachingStrategy.get().undecorateUrl(resourceUrl);
				segment = resourceUrl.getFileName();
			}
			if (!segment.equals(this.mountSegments[i])) {
				return null;
			}
		}
		return new MergedResourceRequestHandler(this.resources, parameters, getLastModifiedTime(getLastModifiedReference()));
	}

	public Url mapHandler(IRequestHandler requestHandler) {

		if (!(requestHandler instanceof ResourceReferenceRequestHandler)) {
			return null;
		}

		boolean matched = false;
		ResourceReferenceRequestHandler handler = (ResourceReferenceRequestHandler) requestHandler;
		for (ResourceReference ref : this.resources) {
			if (ref.equals(handler.getResourceReference())) {
				matched = true;
				break;
			}
		}

		if (!matched) {
			return null;
		}

		Url url = new Url();
		PageParameters parameters = new PageParameters(handler.getPageParameters());
		for (int i = 0; i < this.mountSegments.length; i++) {
			String segment = mountSegments[i];
			if (i + 1 == this.mountSegments.length) {
				segment = applyCachingStrategy(segment, parameters);
			}
			url.getSegments().add(segment);
		}
		return encodePageParameters(url, parameters, this.parametersEncoder);
	}

	protected String applyCachingStrategy(String fileName, PageParameters parameters) {
		ResourceUrl resourceUrl = new ResourceUrl(fileName, parameters);
		ResourceReference lastMod = getLastModifiedReference();
		IResource res = lastMod.getResource();
		if (res instanceof IStaticCacheableResource) {
			this.cachingStrategy.get().decorateUrl(resourceUrl, (IStaticCacheableResource) res);
		}
		return resourceUrl.getFileName();
	}

	protected ResourceReference getLastModifiedReference() {
		ResourceReference lastModifiedRef = null;
		long lastMillis = -1;
		for (ResourceReference ref : this.resources) {
			Time refModified = getLastModifiedTime(ref);
			if (refModified != null && refModified.getMilliseconds() > lastMillis) {
				lastMillis = refModified.getMilliseconds();
				lastModifiedRef = ref;
			}
		}
		return lastModifiedRef != null ? lastModifiedRef : this.resources.get(0);
	}

	protected Time getLastModifiedTime(ResourceReference ref) {
		Time modified = null;
		IResource res = ref.getResource();
		if (res instanceof IStaticCacheableResource) {
			IResourceStream stream = ((IStaticCacheableResource) res).getCacheableResourceStream();
			if (stream == null) {
				throw new RuntimeException("CacheableResourceStream for resource " + ref.getKey().toString() + " not available");
			}
			modified = stream.lastModifiedTime();
		}
		return modified;
	}
}
