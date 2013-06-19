package no.nav.modig.frontend;

import js.JsResourceMarker;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import java.util.ArrayList;


class JsDepResourceReference extends JavaScriptResourceReference {

	private final JavaScriptResourceReference[] deps;

	public JsDepResourceReference(String location, JavaScriptResourceReference... deps) {
		super(JsResourceMarker.class, location);
		this.deps = deps;
	}

	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		ArrayList<HeaderItem> headerItems = new ArrayList<>();
		for (JavaScriptResourceReference dep : deps) {
			headerItems.add(JavaScriptHeaderItem.forReference(dep));
		}
		return headerItems;
	}
}
