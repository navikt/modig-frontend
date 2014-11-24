package no.nav.modig.frontend;

import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

public class NavExtraResources {

    static final JavaScriptResourceReference AJAX_LOADER = new JQueryDependentResourceReference("nav_extra/AjaxLoader.js");
    static final JavaScriptResourceReference DROPDOWN_RESOURCE = new JQueryDependentResourceReference("nav_extra/dropdown.js");
    static final JavaScriptResourceReference FELLES_JS_RESOURCE = new JQueryDependentResourceReference("nav_extra/felles.js");
    static final JavaScriptResourceReference TOOLTIP_RESOURCE = new JQueryDependentResourceReference("nav_extra/tooltip.js");
    static final JavaScriptResourceReference TRANSITIONS_RESOURCE = new JQueryDependentResourceReference("nav_extra/transitions.js");
    static final JavaScriptResourceReference JQUERY_UI_DATEPICKER = new JQueryDependentResourceReference("jquery/plugins/jquery-ui-1.11.1.custom.js");

   	static final PackageResourceReference DATEPICKER_LESS = lessReference("nav_extra/includes/datepicker.less");
    static final PackageResourceReference DROPDOWNS_LESS = lessReference("nav_extra/includes/dropdowns.less");
    static final PackageResourceReference KNAPPER_LESS = lessReference("nav_extra/includes/knapper.less");
   	static final PackageResourceReference WICKET_MODAL_WINDOW = lessReference("nav_extra/includes/modal.less");
    static final PackageResourceReference PANEL_LESS = lessReference("nav_extra/includes/panel.less");
   	static final PackageResourceReference SKJEMA_LESS = lessReference("nav_extra/includes/skjema.less");
    static final PackageResourceReference TOOLTIP_LESS = lessReference("nav_extra/includes/tooltip.less");

    private static PackageResourceReference lessReference(String path) {
   		return new PackageResourceReference(LessResourceMarker.class, path);
   	}
}
