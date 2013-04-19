package no.nav.modig.frontend;

import img.ImgResourceMarker;
import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;


class BootstrapResources {

	static final JavaScriptResourceReference JQUERY_RESOURCE = FrontendResources.JQUERY_RESOURCE;


	// BOOTSTRAP LESS - Core module
	static final JavaScriptResourceReference JS_TRANSITION = new JQueryDependentResourceReference("bootstrap/bootstrap-transition.js");
	
	static final PackageResourceReference LESS_RESET = lessReference("bootstrap/reset.less");

	static final PackageResourceReference LESS_VARIABLES = lessReference("bootstrap/variables.less");
	static final PackageResourceReference LESS_NAV_VARIABLES = lessReference("felles/variables.less");
	static final PackageResourceReference LESS_MIXINS = lessReference("bootstrap/mixins.less");

	static final PackageResourceReference LESS_SCAFFOLDING = lessReference("bootstrap/scaffolding.less");
	static final PackageResourceReference LESS_GRID = lessReference("bootstrap/grid.less");
	static final PackageResourceReference LESS_LAYOUTS = lessReference("bootstrap/layouts.less");

	static final PackageResourceReference LESS_TYPE = lessReference("bootstrap/type.less");
	static final PackageResourceReference LESS_CODE = lessReference("bootstrap/code.less");
	static final PackageResourceReference LESS_FORMS = lessReference("bootstrap/forms.less");
	static final PackageResourceReference LESS_TABLES = lessReference("bootstrap/tables.less");

	static final PackageResourceReference LESS_COMPONENTANIMATIONS = lessReference("bootstrap/component-animations.less");
	static final PackageResourceReference LESS_CLOSE = lessReference("bootstrap/close.less");

	static final PackageResourceReference LESS_HEROUNIT = lessReference("bootstrap/hero-unit.less");
	static final PackageResourceReference LESS_UTILITIES = lessReference("bootstrap/utilities.less");

	// Module responsive
	static final PackageResourceReference LESS_RESPONSIVE_UTILITIES = lessReference("bootstrap/responsive-utilities.less");
	static final PackageResourceReference LESS_RESPONSIVE_1200MIN = lessReference("bootstrap/responsive-1200px-min.less");
	static final PackageResourceReference LESS_RESPONSIVE_768_979 = lessReference("bootstrap/responsive-768px-979px.less");
	static final PackageResourceReference LESS_RESPONSIVE_767MAX = lessReference("bootstrap/responsive-767px-max.less");
	static final PackageResourceReference LESS_RESPONSIVE_NAVBAR = lessReference("bootstrap/responsive-navbar.less");

	// Module sprites
	static final PackageResourceReference LESS_SPRITES = lessReference("bootstrap/sprites.less");

	// Module wells
	static final PackageResourceReference LESS_WELLS = lessReference("bootstrap/wells.less");

	//Module: Navigation
	static final PackageResourceReference LESS_NAVS = lessReference("bootstrap/navs.less");
	static final PackageResourceReference LESS_NAVBAR = lessReference("bootstrap/navbar.less");
	static final PackageResourceReference LESS_BREADCRUMBS = lessReference("bootstrap/breadcrumbs.less");

	// Module pagination
	static final PackageResourceReference LESS_PAGINATION = lessReference("bootstrap/pagination.less");
	static final PackageResourceReference LESS_PAGER = lessReference("bootstrap/pager.less");

	// Module accordion + collapse
	static final PackageResourceReference LESS_ACCORDION = lessReference("bootstrap/accordion.less");
	static final JavaScriptResourceReference JS_COLLAPSE = new JsDepResourceReference("bootstrap/bootstrap-collapse.js", JS_TRANSITION, JQUERY_RESOURCE);

	// Module thumbnails
	static final PackageResourceReference LESS_THUMBNAILS = lessReference("bootstrap/thumbnails.less");

	// Module labels-badges
	static final PackageResourceReference LESS_LABELBADGES = lessReference("bootstrap/labels-badges.less");

	// Module progress-bars
	static final PackageResourceReference LESS_PROGRESSBARS = lessReference("bootstrap/progress-bars.less");

	// Module alert
	static final PackageResourceReference LESS_ALERTS = lessReference("bootstrap/alerts.less");
	static final JavaScriptResourceReference JS_ALERT = new JsDepResourceReference("bootstrap/bootstrap-alert.js", JS_TRANSITION, JQUERY_RESOURCE);

	// Module button
	static final PackageResourceReference LESS_BUTTON = lessReference("bootstrap/buttons.less");
	static final PackageResourceReference LESS_BUTTON_GROUPS = lessReference("bootstrap/button-groups.less");
	static final JavaScriptResourceReference JS_BUTTON = new JsDepResourceReference("bootstrap/bootstrap-button.js", JS_TRANSITION, JQUERY_RESOURCE);

	// Module carousel
	static final PackageResourceReference LESS_CAROUSEL = lessReference("bootstrap/carousel.less");
	static final JavaScriptResourceReference JS_CAROUSEL = new JsDepResourceReference("bootstrap/bootstrap-carousel.js", JS_TRANSITION, JQUERY_RESOURCE);

	//Module dropdown
	static final PackageResourceReference LESS_DROPDOWN = lessReference("bootstrap/dropdowns.less");
	static final JavaScriptResourceReference JS_DROPDOWN = new JsDepResourceReference("bootstrap/bootstrap-dropdown.js", JQUERY_RESOURCE);

	//Module modal
	static final PackageResourceReference LESS_MODAL = lessReference("bootstrap/modal.less");
	static final JavaScriptResourceReference JS_MODAL = new JsDepResourceReference("bootstrap/bootstrap-modal.js", JS_TRANSITION, JQUERY_RESOURCE);

	//Module tooltip
	static final PackageResourceReference LESS_TOOLTIP = lessReference("bootstrap/tooltip.less");
	static final JavaScriptResourceReference JS_TOOLTIP = new JsDepResourceReference("bootstrap/bootstrap-tooltip.js", JS_TRANSITION, JQUERY_RESOURCE);

	//Module popover
	static final PackageResourceReference LESS_POPOVER = lessReference("bootstrap/popover.less");
	static final JavaScriptResourceReference JS_POPOVER = new JsDepResourceReference("bootstrap/bootstrap-popover.js", JS_TOOLTIP, JQUERY_RESOURCE);

	//Module tab
	static final JavaScriptResourceReference JS_TAB = new JsDepResourceReference("bootstrap/bootstrap-tab.js", JS_TRANSITION, JQUERY_RESOURCE);

	//Module typeahead
	static final JavaScriptResourceReference JS_TYPEAHEAD = new JsDepResourceReference("bootstrap/bootstrap-typeahead.js", JQUERY_RESOURCE);

	//Module affix
	static final JavaScriptResourceReference JS_AFFIX = new JsDepResourceReference("bootstrap/bootstrap-affix.js", JQUERY_RESOURCE);

	//Module scrollspy
	static final JavaScriptResourceReference JS_SCROLLSPY = new JsDepResourceReference("bootstrap/bootstrap-scrollspy.js", JQUERY_RESOURCE);

	// Bootstrap icons
	static final SharedResourceReference ICONS = new SharedResourceReference(ImgResourceMarker.class, "glyphicons-halflings.png");
	static final SharedResourceReference ICONS_WHITE = new SharedResourceReference(ImgResourceMarker.class, "glyphicons-halflings-white.png");


	private static JavaScriptResourceReference jsReference(String path) {
		return new JavaScriptResourceReference(JsResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
