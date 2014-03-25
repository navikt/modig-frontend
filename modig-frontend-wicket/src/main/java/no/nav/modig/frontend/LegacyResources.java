package no.nav.modig.frontend;

import css.CssResourceMarker;
import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


public class LegacyResources {

	static final JavaScriptResourceReference LEGACY_FELLES = jsReference("legacy/felles.js");

	static final PackageResourceReference LEGACY_STANDARD_LESS = lessReference("legacy/nav-standard.less");
	static final ConditionalCssResource LEGACY_STANDARD_IE8_CSS = new ConditionalCssResource(cssReference("legacy/nav-standard-ie8.css"), "screen", "lt IE 9");

	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}
	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
	private static CssResourceReference cssReference(String path) {
		return new CssResourceReference(CssResourceMarker.class, path);
	}
}
