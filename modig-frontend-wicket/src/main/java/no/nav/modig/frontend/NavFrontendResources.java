package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


public class NavFrontendResources {

    static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("decorator/libs/jquery/jquery-1.11.1.js");
    static final JavaScriptResourceReference JQUERY_VALIDATE_RESOURCE = new JQueryDependentResourceReference("jquery/plugins/jquery.validate.js");

    static final PackageResourceReference VARIABLES_LESS_RESOURCE = lessReference("includes/variables.less");
	static final PackageResourceReference MIXINS_LESS_RESOURCE = lessReference("includes/mixins.less");


    private static JavaScriptResourceReference jsReference(String path) {
   		return new JavaScriptResourceReference(JsResourceMarker.class, path);
   	}

   	private static PackageResourceReference lessReference(String path) {
   		return new PackageResourceReference(LessResourceMarker.class, path);
   	}
}
