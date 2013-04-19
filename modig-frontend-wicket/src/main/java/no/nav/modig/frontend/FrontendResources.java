package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


class FrontendResources {

	static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("jquery/jquery-1.9.1.js");
	static final JavaScriptResourceReference HTML5_SHIV_RESOURCE = jsReference("html5.js");
	static final JavaScriptResourceReference UNDERSCORE_RESOURCE = jsReference("underscore.js");

	static final PackageResourceReference WICKET_MODAL_WINDOW = lessReference("felles/modal.less");


	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
