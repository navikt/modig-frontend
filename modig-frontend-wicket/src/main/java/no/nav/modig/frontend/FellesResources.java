package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


class FellesResources {

    // WICKET-5250: JavaScriptResourceReference blir komprimert, selv om det finnes en minified versjon
    // Ettersom alt innholdet i html5shiv ligger i en kommentar blir dette fjernet.
    // Må bruke en PackageResourceReference frem til WICKET-5250 og WICKET-5251 er løst
    //static final PackageResourceReference HTML5_SHIV_RESOURCE = new PackageResourceReference(JsResourceMarker.class, "html5.js");
    static final JavaScriptResourceReference HTML5_SHIV_RESOURCE = jsReference("html5.js");

    static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("jquery/jquery-1.10.2.js");
	static final JavaScriptResourceReference UNDERSCORE_RESOURCE = new JQueryDependentResourceReference("underscore.js");
    static final JavaScriptResourceReference TRANSITIONS_RESOURCE = new JQueryDependentResourceReference("modig/transitions.js");
    static final JavaScriptResourceReference FELLES_JS_RESOURCE = new JQueryDependentResourceReference("modig/felles.js");
    static final JavaScriptResourceReference DROPDOWN_RESOURCE = new JQueryDependentResourceReference("modig/dropdown.js");
    static final JavaScriptResourceReference TOOLTIP_RESOURCE = new JQueryDependentResourceReference("modig/tooltip.js");
    static final JavaScriptResourceReference AJAX_LOADER = new JQueryDependentResourceReference("modig/AjaxLoader.js");
    static final JavaScriptResourceReference JQUERY_UI_DATEPICKER = new JQueryDependentResourceReference("jquery/plugins/jquery-ui-1.10.3.custom.js");
    static final JavaScriptResourceReference JQUERY_VALIDATE = new JQueryDependentResourceReference("jquery/plugins/jquery.validate.js");
    static final JavaScriptResourceReference CONSOLE_POLYFILL_RESOURCE = jsReference("console.js");
    static final JavaScriptResourceReference MODERNIZR_RESOURCE = jsReference("enonic/navno/libs/modernizr.2.7.1.min.js");

	static final PackageResourceReference RESET_LESS = lessReference("modig/reset.less");
    static final PackageResourceReference VARIABLES_LESS = lessReference("modig/variables.less");
	static final PackageResourceReference MIXINS_LESS = lessReference("modig/mixins.less");
	static final PackageResourceReference MODUS_LESS = lessReference("modig/modus.less");
	static final PackageResourceReference UTILITIES_LESS = lessReference("modig/utilities.less");
	static final PackageResourceReference TYPOGRAFI_LESS = lessReference("modig/typografi.less");
	static final PackageResourceReference FELLES_LESS = lessReference("modig/felles.less");
	static final PackageResourceReference KNAPPER_LESS = lessReference("modig/knapper.less");
	static final PackageResourceReference DROPDOWNS_LESS = lessReference("modig/dropdowns.less");
	static final PackageResourceReference SKJEMA_LESS = lessReference("modig/skjema.less");
	static final PackageResourceReference TOOLTIP_LESS = lessReference("modig/tooltip.less");
	static final PackageResourceReference NAV_DATEPICKER = lessReference("modig/datepicker.less");
	static final PackageResourceReference WICKET_MODAL_WINDOW = lessReference("modig/modal.less");
	static final PackageResourceReference CONTRAST_LESS = lessReference("modig/contrast.less");



	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
