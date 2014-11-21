package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


public class NavFrontendResources {

    static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("nav_frontend/libs/jquery/jquery-1.11.1.js");

    static final JavaScriptResourceReference NAV_GLOBAL = new JQueryDependentResourceReference("nav_frontend/nav-global.js");
    static final JavaScriptResourceReference INNLOGGING = jsReference("nav_frontend/innloggingslinjen.js");

    static final JavaScriptResourceReference MATCH_MEDIA = jsReference("nav_frontend/libs/matchmedia.js");
    static final JavaScriptResourceReference JQUERY_MEGA_MENU = new JQueryDependentResourceReference("nav_frontend/libs/megamenu/jquery-accessibleMegaMenu.js");

    static final JavaScriptResourceReference TRANSITIONS_RESOURCE = new JQueryDependentResourceReference("nav_frontend/transitions.js");
    static final JavaScriptResourceReference FELLES_JS_RESOURCE = new JQueryDependentResourceReference("nav_frontend/felles.js");
    static final JavaScriptResourceReference DROPDOWN_RESOURCE = new JQueryDependentResourceReference("nav_frontend/dropdown.js");
    static final JavaScriptResourceReference TOOLTIP_RESOURCE = new JQueryDependentResourceReference("nav_frontend/tooltip.js");
    static final JavaScriptResourceReference AJAX_LOADER = new JQueryDependentResourceReference("nav_frontend/AjaxLoader.js");
    static final JavaScriptResourceReference JQUERY_UI_DATEPICKER = new JQueryDependentResourceReference("jquery/plugins/jquery-ui-1.11.1.custom.js");
    static final JavaScriptResourceReference JQUERY_VALIDATE = new JQueryDependentResourceReference("jquery/plugins/jquery.validate.js");
    static final JavaScriptResourceReference MODERNIZR_RESOURCE = jsReference("nav_frontend/libs/modernizr.2.7.1.min.js");

	static final PackageResourceReference NORMALIZE_LESS = lessReference("nav_frontend/includes/normalize.less");
    static final PackageResourceReference VARIABLES_LESS = lessReference("nav_frontend/includes/variables.less");
	static final PackageResourceReference MIXINS_LESS = lessReference("nav_frontend/includes/mixins.less");
	static final PackageResourceReference MODUS_LESS = lessReference("nav_frontend/includes/modus.less");
	static final PackageResourceReference TYPOGRAFI_LESS = lessReference("nav_frontend/includes/typografi.less");
	static final PackageResourceReference UTILITIES_LESS = lessReference("nav_frontend/includes/utilities.less");
	static final PackageResourceReference GRID_LESS = lessReference("nav_frontend/includes/grid.less");
	static final PackageResourceReference KNAPPER_LESS = lessReference("nav_frontend/includes/knapper.less");
	static final PackageResourceReference MEGAMENU_LESS = lessReference("nav_frontend/includes/megamenu.less");
	static final PackageResourceReference SITEHEADER_LESS = lessReference("nav_frontend/includes/siteheader.less");
	static final PackageResourceReference SITEFOOTER_LESS = lessReference("nav_frontend/includes/sitefooter.less");
	static final PackageResourceReference SITEFOOTER_ALPHABET_LESS = lessReference("nav_frontend/includes/sitefooter-alphabet.less");
	static final PackageResourceReference PANEL_LESS = lessReference("nav_frontend/includes/panel.less");
	static final PackageResourceReference DROPDOWNS_LESS = lessReference("nav_frontend/includes/dropdowns.less");
	static final PackageResourceReference SKJEMA_LESS = lessReference("nav_frontend/includes/skjema.less");
	static final PackageResourceReference TOOLTIP_LESS = lessReference("nav_frontend/includes/tooltip.less");
	static final PackageResourceReference DATEPICKER_LESS = lessReference("nav_frontend/includes/datepicker.less");
	static final PackageResourceReference WICKET_MODAL_WINDOW = lessReference("nav_frontend/includes/modal.less");
	static final PackageResourceReference CONTRAST_LESS = lessReference("nav_frontend/includes/contrast.less");


    private static JavaScriptResourceReference jsReference(String path) {
   		return new JavaScriptResourceReference(JsResourceMarker.class, path);
   	}

   	private static PackageResourceReference lessReference(String path) {
   		return new PackageResourceReference(LessResourceMarker.class, path);
   	}
}
