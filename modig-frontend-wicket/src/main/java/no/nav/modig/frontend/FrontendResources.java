package no.nav.modig.frontend;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import static java.util.Arrays.asList;


public class FrontendResources {
    public static final CssResourceReference CSS_RESOURCES = new CssResourceReference(FrontendResources.class, "css/modig.css");
    public static final JavaScriptResourceReference JQUERY_RESOURCE = new JavaScriptResourceReference(FrontendResources.class, "js/jquery/jquery-1.8.2.js");

    public static final JavaScriptResourceReference[] JS_RESOURCES_ALL = {new JavaScriptResourceReference(FrontendResources.class, "js/modig.js") {
        @Override
        public Iterable<? extends HeaderItem> getDependencies() {
            return asList(JavaScriptHeaderItem.forReference(JQUERY_RESOURCE));
        }
    }};


    public static final JavaScriptResourceReference JS_AFFIX = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-affix.js");
    public static final JavaScriptResourceReference JS_ALERT = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-alert.js");
    public static final JavaScriptResourceReference JS_BUTTON = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-button.js");
    public static final JavaScriptResourceReference JS_CAROUSEL = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-carousel.js");
    public static final JavaScriptResourceReference JS_COLLAPSE = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-collapse.js");
    public static final JavaScriptResourceReference JS_DROPDOWN = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-dropdown.js");
    public static final JavaScriptResourceReference JS_MODAL = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-modal.js");
    public static final JavaScriptResourceReference JS_POPOVER = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-popover.js");
    public static final JavaScriptResourceReference JS_SCROLLSPY = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-scrollspy.js");
    public static final JavaScriptResourceReference JS_TAB = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-tab.js");
    public static final JavaScriptResourceReference JS_TOOLTIP = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-tooltip.js");
    public static final JavaScriptResourceReference JS_TRANSITION = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-transition.js");
    public static final JavaScriptResourceReference JS_TYPEAHEAD = new JavaScriptResourceReference(FrontendResources.class, "js/bootstrap/bootstrap-typeahead.js");
}
