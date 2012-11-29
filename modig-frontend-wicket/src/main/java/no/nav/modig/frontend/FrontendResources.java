package no.nav.modig.frontend;

import css.CssResourceMarker;
import img.ImgResourceMarker;
import js.JsResourceMarker;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;

import java.util.ArrayList;


class FrontendResources {

    static final CssResourceReference CSS_RESOURCES = new CssResourceReference(CssResourceMarker.class, "modig.css");
    static final JavaScriptResourceReference JQUERY_RESOURCE = new JavaScriptResourceReference(JsResourceMarker.class, "jquery/jquery-1.8.2.js");
    protected static final JavaScriptResourceReference HTML5_SHIV_RESOURCE = new JavaScriptResourceReference(JsResourceMarker.class, "html5.js");

    static final JavaScriptResourceReference JS_TRANSITION = new JsDepResourceReference("bootstrap/bootstrap-transition.js", JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_ALERT = new JsDepResourceReference("bootstrap/bootstrap-alert.js", JS_TRANSITION, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_BUTTON = new JsDepResourceReference("bootstrap/bootstrap-button.js", JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_CAROUSEL = new JsDepResourceReference("bootstrap/bootstrap-carousel.js", JS_TRANSITION, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_COLLAPSE = new JsDepResourceReference("bootstrap/bootstrap-collapse.js", JS_TRANSITION, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_DROPDOWN = new JsDepResourceReference("bootstrap/bootstrap-dropdown.js", JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_MODAL = new JsDepResourceReference("bootstrap/bootstrap-modal.js", JS_TRANSITION, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_TOOLTIP = new JsDepResourceReference("bootstrap/bootstrap-tooltip.js", JS_TRANSITION, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_POPOVER = new JsDepResourceReference("bootstrap/bootstrap-popover.js", JS_TOOLTIP, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_SCROLLSPY = new JsDepResourceReference("bootstrap/bootstrap-scrollspy.js", JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_TAB = new JsDepResourceReference("bootstrap/bootstrap-tab.js", JS_TRANSITION, JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_TYPEAHEAD = new JsDepResourceReference("bootstrap/bootstrap-typeahead.js", JQUERY_RESOURCE);
    static final JavaScriptResourceReference JS_AFFIX = new JsDepResourceReference("bootstrap/bootstrap-affix.js", JQUERY_RESOURCE);

    static final JavaScriptResourceReference[] JS_RESOURCES_ALL = {
            JS_TRANSITION, JS_ALERT, JS_BUTTON, JS_CAROUSEL,
            JS_COLLAPSE, JS_DROPDOWN, JS_MODAL, JS_TOOLTIP,
            JS_POPOVER, JS_SCROLLSPY, JS_TAB, JS_TYPEAHEAD,
            JS_AFFIX
    };

    static final SharedResourceReference ICONS = new SharedResourceReference(ImgResourceMarker.class, "glyphicons-halflings.png");
    static final SharedResourceReference ICONS_WHITE = new SharedResourceReference(ImgResourceMarker.class, "glyphicons-halflings-white.png");


    private static class JsDepResourceReference extends JavaScriptResourceReference {

        private final JavaScriptResourceReference[] deps;

        public JsDepResourceReference(String location, JavaScriptResourceReference... deps) {
            super(JsResourceMarker.class, location);
            this.deps = deps;
        }

        @Override
        public Iterable<? extends HeaderItem> getDependencies() {
            ArrayList<HeaderItem> headerItems = new ArrayList<>();
            for (JavaScriptResourceReference dep : deps) {
                headerItems.add(JavaScriptHeaderItem.forReference(dep));
            }
            return headerItems;
        }
    }
}
