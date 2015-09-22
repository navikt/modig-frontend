package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


public class NavFrontendResources {

    static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("decorator/libs/jquery/jquery-1.11.1.js");
    static final JavaScriptResourceReference JQUERY_VALIDATE_RESOURCE = new JQueryDependentResourceReference("jquery/plugins/jquery.validate.js");

    static final PackageResourceReference VARIABLES_LESS_RESOURCE = lessReference("includes/variables.less");
	static final PackageResourceReference MIXINS_GRID_LESS_RESOURCE = lessReference("includes/mixins/mixins-grid.less");
	static final PackageResourceReference MIXINS_KNAPPER_LESS_RESOURCE = lessReference("includes/mixins/mixins-knapper.less");
	static final PackageResourceReference MIXINS_TYPOGRAFI_LESS_RESOURCE = lessReference("includes/mixins/mixins-typografi.less");
	static final PackageResourceReference MIXINS_UTILS_LESS_RESOURCE = lessReference("includes/mixins/mixins-utils.less");


    private static JavaScriptResourceReference jsReference(String path) {
   		return new JavaScriptResourceReference(JsResourceMarker.class, path);
   	}

   	private static PackageResourceReference lessReference(String path) {
   		return new PackageResourceReference(LessResourceMarker.class, path);
   	}
}
