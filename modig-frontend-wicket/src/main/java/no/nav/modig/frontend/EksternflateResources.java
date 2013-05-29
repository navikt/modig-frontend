package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


class EksternflateResources {

	static final PackageResourceReference FOOTER_LESS = lessReference("ekstern/footer.less");
	static final PackageResourceReference PANEL_LESS = lessReference("ekstern/panel.less");
	static final PackageResourceReference EKSTERN_LESS = lessReference("ekstern/ekstern.less");

	static final JavaScriptResourceReference TILBAKEMELDING_JS = jsReference("ekstern/tilbakemelding.js");


	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
