package no.nav.modig.frontend;

import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

public class NavExtraResources {

	static final JavaScriptResourceReference JQUERY_UI_DATEPICKER_RESOURCE = new JQueryDependentResourceReference("jquery/plugins/jquery-ui-1.11.1.custom.js");
	static final JavaScriptResourceReference TRANSITIONS_RESOURCE = new JQueryDependentResourceReference("nav_extra/transitions.js");
	static final JavaScriptResourceReference AJAX_LOADER_RESOURCE = new JQueryDependentResourceReference("nav_extra/AjaxLoader.js");
	static final JavaScriptResourceReference DROPDOWN_RESOURCE = new JQueryDependentResourceReference("nav_extra/dropdown.js");
	static final JavaScriptResourceReference TOOLTIP_RESOURCE = new JQueryDependentResourceReference("nav_extra/tooltip.js");

	static final PackageResourceReference HJELPETEKST_LESS_RESOURCE = lessReference("nav_extra/hjelpetekst.less");
	static final PackageResourceReference HODER_LESS_RESOURCE = lessReference("nav_extra/hoder.less");
	static final PackageResourceReference KNAPPERAD_LESS_RESOURCE = lessReference("nav_extra/knapperader.less");
	static final PackageResourceReference LISTER_LESS_RESOURCE = lessReference("nav_extra/lister.less");
	static final PackageResourceReference MODALER_LESS_RESOURCE = lessReference("nav_extra/modaler.less");
	static final PackageResourceReference PANELER_LESS_RESOURCE = lessReference("nav_extra/paneler.less");
	static final PackageResourceReference SKJEMA_LESS_RESOURCE = lessReference("nav_extra/skjema.less");
	static final PackageResourceReference STEGINDIKATOR_LESS_RESOURCE = lessReference("nav_extra/stegindikator.less");
	static final PackageResourceReference UTILITIES_LESS_RESOURCE = lessReference("nav_extra/utilities.less");
	static final PackageResourceReference VALIDERING_LESS_RESOURCE = lessReference("nav_extra/validering.less");
	static final PackageResourceReference WICKET_MODAL_WINDOW_RESOURCE = lessReference("nav_extra/wicket-modal.less");

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
