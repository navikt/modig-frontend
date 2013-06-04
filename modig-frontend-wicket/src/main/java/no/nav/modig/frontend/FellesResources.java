package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


class FellesResources {

	static final JavaScriptResourceReference JQUERY_RESOURCE = jsReference("jquery/jquery-1.9.1.js");
	static final JavaScriptResourceReference HTML5_SHIV_RESOURCE = jsReference("html5.js");
	static final JavaScriptResourceReference UNDERSCORE_RESOURCE = new JQueryDependentResourceReference("underscore.js");
	static final JavaScriptResourceReference TRANSITIONS_RESOURCE = new JQueryDependentResourceReference("felles/transitions.js");
	static final JavaScriptResourceReference FELLES_JS_RESOURCE = new JQueryDependentResourceReference("felles/felles.js");
	static final JavaScriptResourceReference DROPDOWN_RESOURCE = new JQueryDependentResourceReference("felles/dropdown.js");
	static final JavaScriptResourceReference TOOLTIP_RESOURCE = new JQueryDependentResourceReference("felles/tooltip.js");

	static final PackageResourceReference RESET_LESS = lessReference("felles/reset.less");
	static final PackageResourceReference VARIABLES_LESS = lessReference("felles/variables.less");
	static final PackageResourceReference MIXINS_LESS = lessReference("felles/mixins.less");
	static final PackageResourceReference MODUS_LESS = lessReference("felles/modus.less");
	static final PackageResourceReference UTILITIES_LESS = lessReference("felles/utilities.less");
	static final PackageResourceReference TYPOGRAFI_LESS = lessReference("felles/typografi.less");
	static final PackageResourceReference FELLES_LESS = lessReference("felles/felles.less");
	static final PackageResourceReference KNAPPER_LESS = lessReference("felles/knapper.less");
	static final PackageResourceReference DROPDOWNS_LESS = lessReference("felles/dropdowns.less");
	static final PackageResourceReference SKJEMA_LESS = lessReference("felles/skjema.less");
	static final PackageResourceReference TOOLTIP_LESS = lessReference("felles/tooltip.less");
	static final PackageResourceReference NAV_DATEPICKER = lessReference("felles/datepicker.less");
	static final PackageResourceReference WICKET_MODAL_WINDOW = lessReference("felles/modal.less");
	static final PackageResourceReference CONTRAST_LESS = lessReference("felles/contrast.less");



	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
