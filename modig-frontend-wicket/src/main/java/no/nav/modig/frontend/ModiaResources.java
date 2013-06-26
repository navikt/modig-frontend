package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

public class ModiaResources {
	static final PackageResourceReference MODIA_COMMON_LESS = lessReference("modia/common.less");
    static final PackageResourceReference MODIA_RAMME_LESS = lessReference("modia/ramme.less");
    static final PackageResourceReference MODIA_KOMPONENTER_LESS = lessReference("modia/komponenter.less");


	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
