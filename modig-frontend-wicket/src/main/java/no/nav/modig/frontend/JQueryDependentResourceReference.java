package no.nav.modig.frontend;

import js.JsResourceMarker;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.ArrayList;


class JQueryDependentResourceReference extends JavaScriptResourceReference {
	private final JavaScriptResourceReference[] deps;

	JQueryDependentResourceReference(String location, JavaScriptResourceReference... deps) {
		super(JsResourceMarker.class, location);
		this.deps = deps;
	}

	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		final ResourceReference wicketJQueryReference;
		if (Application.exists()) {
			wicketJQueryReference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
		} else {
			wicketJQueryReference = JQueryResourceReference.get();
		}

		ArrayList<HeaderItem> headerItems = new ArrayList<>();
		headerItems.add(JavaScriptHeaderItem.forReference(wicketJQueryReference));
		for (JavaScriptResourceReference dep : deps) {
			headerItems.add(JavaScriptHeaderItem.forReference(dep));
		}
		return headerItems;
	}
}
