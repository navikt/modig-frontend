package no.nav.modig.frontend.merged;

import org.apache.wicket.request.resource.ResourceReference;


public class MergedCssBuilder extends MergedResourceBuilder {

	public MergedCssBuilder() {
		super();
	}

	public MergedCssBuilder setPath(String path) {
		return (MergedCssBuilder) super.setPath(path);
	}

	public MergedCssBuilder addCss(ResourceReference ref) {
		add(ref);
		return this;
	}

}
