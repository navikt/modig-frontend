package no.nav.modig.frontend;

import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

public class NavExtraResources {

    static final JavaScriptResourceReference AJAX_LOADER_RESOURCE = new JQueryDependentResourceReference("nav_extra/AjaxLoader.js");
    static final JavaScriptResourceReference DROPDOWN_RESOURCE = new JQueryDependentResourceReference("nav_extra/dropdown.js");
    static final JavaScriptResourceReference FELLES_JS_RESOURCE = new JQueryDependentResourceReference("nav_extra/felles.js");
    static final JavaScriptResourceReference TOOLTIP_RESOURCE = new JQueryDependentResourceReference("nav_extra/tooltip.js");
    static final JavaScriptResourceReference TRANSITIONS_RESOURCE = new JQueryDependentResourceReference("nav_extra/transitions.js");
    static final JavaScriptResourceReference JQUERY_UI_DATEPICKER_RESOURCE = new JQueryDependentResourceReference("jquery/plugins/jquery-ui-1.11.1.custom.js");

   	static final PackageResourceReference EKSTERNFLATE_LESS_RESOURCE = lessReference("nav_extra/eksternflate.less");
   	static final PackageResourceReference STEGINDIKATOR_LESS_RESOURCE = lessReference("nav_extra/stegindikator.less");
   	static final PackageResourceReference DATEPICKER_LESS_RESOURCE = lessReference("nav_extra/datepicker.less");
    static final PackageResourceReference DROPDOWNS_LESS_RESOURCE = lessReference("nav_extra/dropdowns.less");
    static final PackageResourceReference KNAPPER_LESS_RESOURCE = lessReference("nav_extra/knapper.less");
   	static final PackageResourceReference WICKET_MODAL_WINDOW_RESOURCE = lessReference("nav_extra/modal.less");
    static final PackageResourceReference MODIG_GRID_LESS_RESOURCE = lessReference("nav_extra/modig-grid.less");
   	static final PackageResourceReference SKJEMA_LESS_RESOURCE = lessReference("nav_extra/skjema.less");
    static final PackageResourceReference TOOLTIP_LESS_RESOURCE = lessReference("nav_extra/tooltip.less");

    private static PackageResourceReference lessReference(String path) {
   		return new PackageResourceReference(LessResourceMarker.class, path);
   	}
}