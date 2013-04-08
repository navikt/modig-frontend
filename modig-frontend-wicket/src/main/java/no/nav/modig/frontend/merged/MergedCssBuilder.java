package no.nav.modig.frontend.merged;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
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

	protected Behavior newContributor(final ResourceReference ref) {
		return new Behavior() {
			@Override
			public void renderHead(Component comp, IHeaderResponse response) {
				response.render(CssReferenceHeaderItem.forReference(ref));
			}
		};
	}
}
