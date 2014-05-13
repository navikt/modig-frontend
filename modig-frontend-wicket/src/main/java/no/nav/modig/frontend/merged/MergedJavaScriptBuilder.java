package no.nav.modig.frontend.merged;

import org.apache.wicket.ajax.WicketAjaxJQueryResourceReference;
import org.apache.wicket.ajax.WicketEventJQueryResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class MergedJavaScriptBuilder extends MergedResourceBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(MergedJavaScriptBuilder.class);
    // Use a List (despite minor inefficiencies) to preserve the order of resources
    private List<ResourceReference> deps;

	public MergedJavaScriptBuilder() {
		super();
        this.deps = new ArrayList<>();
	}

	public MergedJavaScriptBuilder setPath(String path) {
		return (MergedJavaScriptBuilder) super.setPath(path);
	}

	public MergedJavaScriptBuilder addScript(ResourceReference ref) {
        ResourceReference.Key key = ref.getKey();
        boolean alreadyFound = false;
        for(ResourceReference dep : this.deps){
            if (key.equals(dep.getKey())) {
                alreadyFound = true;
            }
        }
        if (!alreadyFound) {
            this.deps.add(ref);
        }

		return this;
	}

	public MergedJavaScriptBuilder addWicketAjaxLibraries(List<JavaScriptResourceReference> resourceReferences) {
		addScript(WicketEventJQueryResourceReference.get());
		addScript(WicketAjaxJQueryResourceReference.get());
		for (JavaScriptResourceReference reference : resourceReferences) {
			addScript(reference);
		}
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
