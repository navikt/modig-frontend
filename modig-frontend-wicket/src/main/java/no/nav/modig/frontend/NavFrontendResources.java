package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


public class NavFrontendResources {

    static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("nav_frontend/libs/jquery/jquery-1.11.1.js");

    static final JavaScriptResourceReference NAV_GLOBAL_RESOURCE = new JQueryDependentResourceReference("nav_frontend/nav-global.js");
    static final JavaScriptResourceReference INNLOGGING_RESOURCE = jsReference("nav_frontend/innloggingslinjen.js");

    static final JavaScriptResourceReference MATCH_MEDIA_RESOURCE = jsReference("nav_frontend/libs/matchmedia.js");
    static final JavaScriptResourceReference JQUERY_MEGA_MENU_RESOURCE = new JQueryDependentResourceReference("nav_frontend/libs/megamenu/jquery-accessibleMegaMenu.js");

    static final JavaScriptResourceReference JQUERY_VALIDATE_RESOURCE = new JQueryDependentResourceReference("jquery/plugins/jquery.validate.js");
    static final JavaScriptResourceReference MODERNIZR_RESOURCE = jsReference("nav_frontend/libs/modernizr.2.7.1.min.js");

	static final PackageResourceReference NORMALIZE_LESS_RESOURCE = lessReference("nav_frontend/includes/normalize.less");
    static final PackageResourceReference VARIABLES_LESS_RESOURCE = lessReference("nav_frontend/includes/variables.less");
	static final PackageResourceReference MIXINS_LESS_RESOURCE = lessReference("nav_frontend/includes/mixins.less");
	static final PackageResourceReference MODUS_LESS_RESOURCE = lessReference("nav_frontend/includes/modus.less");
	static final PackageResourceReference TYPOGRAPHY_LESS_RESOURCE = lessReference("nav_frontend/includes/typography.less");
	static final PackageResourceReference UTILITIES_LESS_RESOURCE = lessReference("nav_frontend/includes/utilities.less");
	static final PackageResourceReference GRID_LESS_RESOURCE = lessReference("nav_frontend/includes/grid.less");
	static final PackageResourceReference MEGAMENU_LESS_RESOURCE = lessReference("nav_frontend/includes/megamenu.less");
	static final PackageResourceReference SITEHEADER_LESS_RESOURCE = lessReference("nav_frontend/includes/siteheader.less");
	static final PackageResourceReference SITEFOOTER_LESS_RESOURCE = lessReference("nav_frontend/includes/sitefooter.less");
	static final PackageResourceReference SITEFOOTER_ALPHABET_LESS_RESOURCE = lessReference("nav_frontend/includes/sitefooter-alphabet.less");
	static final PackageResourceReference CONTRAST_LESS_RESOURCE = lessReference("nav_frontend/includes/contrast.less");


    private static JavaScriptResourceReference jsReference(String path) {
   		return new JavaScriptResourceReference(JsResourceMarker.class, path);
   	}

   	private static PackageResourceReference lessReference(String path) {
   		return new PackageResourceReference(LessResourceMarker.class, path);
   	}
}
