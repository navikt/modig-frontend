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
	static final JavaScriptResourceReference SPRAKVELGER_JS_RESOURCE = new JQueryDependentResourceReference("nav_extra/sprakvelger.js");

	static final PackageResourceReference DROPDOWN_LESS_RESOURCE = lessReference("nav_extra/vendor/bootstrap-dropdown.less");
	static final PackageResourceReference TABLES_LESS_RESOURCE = lessReference("nav_extra/vendor/bootstrap-tables.less");
	static final PackageResourceReference TOOLTIP_LESS_RESOURCE = lessReference("nav_extra/vendor/bootstrap-tooltip.less");
	static final PackageResourceReference DATEPICKER_LESS_RESOURCE = lessReference("nav_extra/vendor/jquery-ui-datepicker.less");

	static final PackageResourceReference HODER_LESS_RESOURCE = lessReference("nav_extra/hoder.less");
	static final PackageResourceReference INNHOLD_LESS_RESOURCE = lessReference("nav_extra/innholdsfortegnelse.less");
	static final PackageResourceReference KNAPPERAD_LESS_RESOURCE = lessReference("nav_extra/knapperader.less");
	static final PackageResourceReference LENKELISTE_LESS_RESOURCE = lessReference("nav_extra/lenkeliste.less");
	static final PackageResourceReference LISTER_LESS_RESOURCE = lessReference("nav_extra/lister.less");
	static final PackageResourceReference PANELER_LESS_RESOURCE = lessReference("nav_extra/paneler.less");
	static final PackageResourceReference SKJEMA_LESS_RESOURCE = lessReference("nav_extra/skjema.less");
	static final PackageResourceReference SPRAKVELGER_LESS_RESOURCE = lessReference("nav_extra/sprakvelger.less");
	static final PackageResourceReference STEGINDIKATOR_LESS_RESOURCE = lessReference("nav_extra/stegindikator.less");
	static final PackageResourceReference WICKET_MODAL_WINDOW_RESOURCE = lessReference("nav_extra/wicket-modal.less");

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
