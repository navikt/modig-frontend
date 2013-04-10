package no.nav.modig.frontend.merged;

import org.apache.wicket.ajax.WicketAjaxJQueryResourceReference;
import org.apache.wicket.ajax.WicketEventJQueryResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MergedJavaScriptBuilder extends MergedResourceBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(MergedJavaScriptBuilder.class);
	private List<ResourceReference> deps;

	public MergedJavaScriptBuilder() {
		super();
		this.deps = new ArrayList<>();
	}

	public MergedJavaScriptBuilder setPath(String path) {
		return (MergedJavaScriptBuilder) super.setPath(path);
	}

	public MergedJavaScriptBuilder addScript(ResourceReference ref) {
        this.deps.add(ref);
		return this;
	}

	public MergedJavaScriptBuilder addWicketAjaxLibraries() {
		addScript(WicketEventJQueryResourceReference.get());
		addScript(WicketAjaxJQueryResourceReference.get());
		return this;
	}


	@Override
	protected void assertRequiredOptionsAndFreeze() {
		for (ResourceReference ref : this.deps) {
			LOGGER.debug("Added script to merged builder: {}", ref);
			add(ref);
		}
		super.assertRequiredOptionsAndFreeze();
	}

}
