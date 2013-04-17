package no.nav.modig.frontend;

import img.ImgResourceMarker;
import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;

import java.util.ArrayList;


class FrontendResources {

    static final JavaScriptResourceReference JQUERY_RESOURCE = new JavaScriptResourceReference(JsResourceMarker.class, "jquery/jquery-1.9.1.js");
    static final JavaScriptResourceReference HTML5_SHIV_RESOURCE = new JavaScriptResourceReference(JsResourceMarker.class, "html5.js");
    static final JavaScriptResourceReference UNDERSCORE_RESOURCE = new JavaScriptResourceReference(JsResourceMarker.class, "underscore.js");

    // BOOTSTRAP LESS - Core module
    static final JavaScriptResourceReference JS_BOOTSTRAP_TRANSITION = new JsDepResourceReference("bootstrap/bootstrap-transition.js", JQUERY_RESOURCE);

    static final PackageResourceReference LESS_BOOTSTRAP_RESET = new PackageResourceReference(LessResourceMarker.class, "bootstrap/reset.less");
    static final PackageResourceReference LESS_BOOTSTRAP_VARIABLES = new PackageResourceReference(LessResourceMarker.class, "bootstrap/variables.less");
    static final PackageResourceReference LESS_BOOTSTRAP_NAV_VARIABLES = new PackageResourceReference(LessResourceMarker.class, "felles/variables.less");
    static final PackageResourceReference LESS_BOOTSTRAP_MIXINS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/mixins.less");

    static final PackageResourceReference LESS_BOOTSTRAP_SCAFFOLDING = new PackageResourceReference(LessResourceMarker.class, "bootstrap/scaffolding.less");
    static final PackageResourceReference LESS_BOOTSTRAP_GRID = new PackageResourceReference(LessResourceMarker.class, "bootstrap/grid.less");
    static final PackageResourceReference LESS_BOOTSTRAP_LAYOUTS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/layouts.less");

    static final PackageResourceReference LESS_BOOTSTRAP_TYPE = new PackageResourceReference(LessResourceMarker.class, "bootstrap/type.less");
    static final PackageResourceReference LESS_BOOTSTRAP_CODE = new PackageResourceReference(LessResourceMarker.class, "bootstrap/code.less");
    static final PackageResourceReference LESS_BOOTSTRAP_FORMS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/forms.less");
    static final PackageResourceReference LESS_BOOTSTRAP_TABLES = new PackageResourceReference(LessResourceMarker.class, "bootstrap/tables.less");

    static final PackageResourceReference LESS_BOOTSTRAP_COMPONENTANIMATIONS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/component-animations.less");
    static final PackageResourceReference LESS_BOOTSTRAP_CLOSE = new PackageResourceReference(LessResourceMarker.class, "bootstrap/close.less");

    static final PackageResourceReference LESS_BOOTSTRAP_HEROUNIT = new PackageResourceReference(LessResourceMarker.class, "bootstrap/hero-unit.less");
    static final PackageResourceReference LESS_BOOTSTRAP_UTILITIES = new PackageResourceReference(LessResourceMarker.class, "bootstrap/utilities.less");

	// Module responsive
	static final PackageResourceReference LESS_BOOTSTRAP_RESPONSIVE_UTILITIES = new PackageResourceReference(LessResourceMarker.class, "bootstrap/responsive-utilities.less");
	static final PackageResourceReference LESS_BOOTSTRAP_RESPONSIVE_1200MIN = new PackageResourceReference(LessResourceMarker.class, "bootstrap/responsive-1200px-min.less");
	static final PackageResourceReference LESS_BOOTSTRAP_RESPONSIVE_768_979 = new PackageResourceReference(LessResourceMarker.class, "bootstrap/responsive-768px-979px.less");
	static final PackageResourceReference LESS_BOOTSTRAP_RESPONSIVE_767MAX = new PackageResourceReference(LessResourceMarker.class, "bootstrap/responsive-767px-max.less");
	static final PackageResourceReference LESS_BOOTSTRAP_RESPONSIVE_NAVBAR = new PackageResourceReference(LessResourceMarker.class, "bootstrap/responsive-navbar.less");

    // Module sprites
    static final PackageResourceReference LESS_BOOTSTRAP_SPRITES = new PackageResourceReference(LessResourceMarker.class, "bootstrap/sprites.less");

    // Module wells
    static final PackageResourceReference LESS_BOOTSTRAP_WELLS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/wells.less");

    //Module: Navigation
    static final PackageResourceReference LESS_BOOTSTRAP_NAVS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/navs.less");
    static final PackageResourceReference LESS_BOOTSTRAP_NAVBAR = new PackageResourceReference(LessResourceMarker.class, "bootstrap/navbar.less");
    static final PackageResourceReference LESS_BOOTSTRAP_BREADCRUMBS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/breadcrumbs.less");

    // Module pagination
    static final PackageResourceReference LESS_BOOTSTRAP_PAGINATION = new PackageResourceReference(LessResourceMarker.class, "bootstrap/pagination.less");
    static final PackageResourceReference LESS_BOOTSTRAP_PAGER = new PackageResourceReference(LessResourceMarker.class, "bootstrap/pager.less");

    // Module accordion + collapse
    static final PackageResourceReference LESS_BOOTSTRAP_ACCORDION = new PackageResourceReference(LessResourceMarker.class, "bootstrap/accordion.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_COLLAPSE = new JsDepResourceReference("bootstrap/bootstrap-collapse.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    // Module thumbnails
    static final PackageResourceReference LESS_BOOTSTRAP_THUMBNAILS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/thumbnails.less");

    // Module labels-badges
    static final PackageResourceReference LESS_BOOTSTRAP_LABELBADGES = new PackageResourceReference(LessResourceMarker.class, "bootstrap/labels-badges.less");

    // Module progress-bars
    static final PackageResourceReference LESS_BOOTSTRAP_PROGRESSBARS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/progress-bars.less");

    // Module alert
    static final PackageResourceReference LESS_BOOTSTRAP_ALERTS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/alerts.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_ALERT = new JsDepResourceReference("bootstrap/bootstrap-alert.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    // Module button
    static final PackageResourceReference LESS_BOOTSTRAP_BUTTON = new PackageResourceReference(LessResourceMarker.class, "bootstrap/buttons.less");
    static final PackageResourceReference LESS_BOOTSTRAP_BUTTON_GROUPS = new PackageResourceReference(LessResourceMarker.class, "bootstrap/button-groups.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_BUTTON = new JsDepResourceReference("bootstrap/bootstrap-button.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    // Module carousel
    static final PackageResourceReference LESS_BOOTSTRAP_CAROUSEL = new PackageResourceReference(LessResourceMarker.class, "bootstrap/carousel.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_CAROUSEL = new JsDepResourceReference("bootstrap/bootstrap-carousel.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    //Module dropdown
    static final PackageResourceReference LESS_BOOTSTRAP_DROPDOWN = new PackageResourceReference(LessResourceMarker.class, "bootstrap/dropdowns.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_DROPDOWN = new JsDepResourceReference("bootstrap/bootstrap-dropdown.js", JQUERY_RESOURCE);

    //Module modal
    static final PackageResourceReference LESS_BOOTSTRAP_MODAL = new PackageResourceReference(LessResourceMarker.class, "bootstrap/modal.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_MODAL = new JsDepResourceReference("bootstrap/bootstrap-modal.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    //Module tooltip
    static final PackageResourceReference LESS_BOOTSTRAP_TOOLTIP = new PackageResourceReference(LessResourceMarker.class, "bootstrap/tooltip.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_TOOLTIP = new JsDepResourceReference("bootstrap/bootstrap-tooltip.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    //Module popover
    static final PackageResourceReference LESS_BOOTSTRAP_POPOVER = new PackageResourceReference(LessResourceMarker.class, "bootstrap/popover.less");
    static final JavaScriptResourceReference JS_BOOTSTRAP_POPOVER = new JsDepResourceReference("bootstrap/bootstrap-popover.js", JS_BOOTSTRAP_TOOLTIP, JQUERY_RESOURCE);

    //Module tab
    static final JavaScriptResourceReference JS_BOOTSTRAP_TAB = new JsDepResourceReference("bootstrap/bootstrap-tab.js", JS_BOOTSTRAP_TRANSITION, JQUERY_RESOURCE);

    //Module typeahead
    static final JavaScriptResourceReference JS_BOOTSTRAP_TYPEAHEAD = new JsDepResourceReference("bootstrap/bootstrap-typeahead.js", JQUERY_RESOURCE);

    //Module affix
    static final JavaScriptResourceReference JS_BOOTSTRAP_AFFIX = new JsDepResourceReference("bootstrap/bootstrap-affix.js", JQUERY_RESOURCE);

    //Module scrollspy
    static final JavaScriptResourceReference JS_BOOTSTRAP_SCROLLSPY = new JsDepResourceReference("bootstrap/bootstrap-scrollspy.js", JQUERY_RESOURCE);


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
