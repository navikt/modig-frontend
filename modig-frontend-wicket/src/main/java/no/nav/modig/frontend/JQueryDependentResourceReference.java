package no.nav.modig.frontend;

import js.JsResourceMarker;
import org.apache.wicket.Application;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.Collections;


class JQueryDependentResourceReference extends JavaScriptResourceReference {
	JQueryDependentResourceReference(String location) {
		super(JsResourceMarker.class, location);
	}

	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		final ResourceReference wicketJQueryReference;
		if (Application.exists()) {
			wicketJQueryReference = Application.get().getJavaScriptLibrarySettings().getJQueryReference();
		} else {
			wicketJQueryReference = JQueryResourceReference.get();
		}
		return Collections.singletonList(JavaScriptHeaderItem.forReference(wicketJQueryReference));
	}
}
