package no.nav.modig.frontend;

import static no.nav.modig.frontend.FrontendResources.ICONS;
import static no.nav.modig.frontend.FrontendResources.ICONS_WHITE;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_AFFIX;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_ALERT;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_BUTTON;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_CAROUSEL;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_COLLAPSE;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_DROPDOWN;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_MODAL;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_POPOVER;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_SCROLLSPY;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_TAB;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_TOOLTIP;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_TRANSITION;
import static no.nav.modig.frontend.FrontendResources.JS_BOOTSTRAP_TYPEAHEAD;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_ACCORDION;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_ALERTS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_BREADCRUMBS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_BUTTON;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_BUTTON_GROUPS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_CAROUSEL;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_CLOSE;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_CODE;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_COMPONENTANIMATIONS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_DROPDOWN;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_FORMS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_GRID;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_HEROUNIT;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_LABELBADGES;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_LAYOUTS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_MIXINS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_MODAL;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_NAVBAR;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_NAVS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_NAV_VARIABLES;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_PAGER;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_PAGINATION;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_POPOVER;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_PROGRESSBARS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_RESET;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_RESPONSIVE_1200MIN;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_RESPONSIVE_767MAX;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_RESPONSIVE_768_979;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_RESPONSIVE_NAVBAR;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_RESPONSIVE_UTILITIES;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_SCAFFOLDING;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_SPRITES;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_TABLES;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_THUMBNAILS;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_TOOLTIP;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_TYPE;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_UTILITIES;
import static no.nav.modig.frontend.FrontendResources.LESS_BOOTSTRAP_WELLS;
import static no.nav.modig.frontend.FrontendResources.UNDERSCORE_RESOURCE;


public class FrontendModules {

    public static final FrontendModule BOOTSTRAP_CORE = new FrontendModule.With().scripts(JS_BOOTSTRAP_TRANSITION).less(
		    LESS_BOOTSTRAP_RESET,
		    LESS_BOOTSTRAP_NAV_VARIABLES,
		    LESS_BOOTSTRAP_MIXINS,

		    LESS_BOOTSTRAP_SCAFFOLDING,
		    LESS_BOOTSTRAP_GRID,
		    LESS_BOOTSTRAP_LAYOUTS,

		    LESS_BOOTSTRAP_TYPE,
		    LESS_BOOTSTRAP_CODE,
		    LESS_BOOTSTRAP_FORMS,
		    LESS_BOOTSTRAP_TABLES,

		    LESS_BOOTSTRAP_COMPONENTANIMATIONS,
		    LESS_BOOTSTRAP_CLOSE,

		    LESS_BOOTSTRAP_HEROUNIT,
		    LESS_BOOTSTRAP_UTILITIES
    ).done();

    public static final FrontendModule BOOTSTRAP_SPRITES = new FrontendModule.With().less(LESS_BOOTSTRAP_SPRITES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule BOOTSTRAP_WELLS = new FrontendModule.With().less(LESS_BOOTSTRAP_WELLS).done();
    public static final FrontendModule BOOTSTRAP_NAVIGATION = new FrontendModule.With().less(LESS_BOOTSTRAP_NAVS, LESS_BOOTSTRAP_NAVBAR, LESS_BOOTSTRAP_BREADCRUMBS).done();
    public static final FrontendModule BOOTSTRAP_PAGER = new FrontendModule.With().less(LESS_BOOTSTRAP_PAGINATION, LESS_BOOTSTRAP_PAGER).done();
    public static final FrontendModule BOOTSTRAP_ACCORDION = new FrontendModule.With().less(LESS_BOOTSTRAP_ACCORDION).scripts(JS_BOOTSTRAP_COLLAPSE).done();
    public static final FrontendModule BOOTSTRAP_THUMBNAILS = new FrontendModule.With().less(LESS_BOOTSTRAP_THUMBNAILS).done();
    public static final FrontendModule BOOTSTRAP_LABELS_AND_BADGES = new FrontendModule.With().less(LESS_BOOTSTRAP_LABELBADGES).done();
    public static final FrontendModule BOOTSTRAP_PROGRESSBARS = new FrontendModule.With().less(LESS_BOOTSTRAP_PROGRESSBARS).done();
    public static final FrontendModule BOOTSTRAP_ALERTS = new FrontendModule.With().less(LESS_BOOTSTRAP_ALERTS).scripts(JS_BOOTSTRAP_ALERT).done();
    public static final FrontendModule BOOTSTRAP_BUTTON = new FrontendModule.With().less(LESS_BOOTSTRAP_BUTTON, LESS_BOOTSTRAP_BUTTON_GROUPS).scripts(JS_BOOTSTRAP_BUTTON).done();
    public static final FrontendModule BOOTSTRAP_CAROUSEL = new FrontendModule.With().less(LESS_BOOTSTRAP_CAROUSEL).scripts(JS_BOOTSTRAP_CAROUSEL).done();
    public static final FrontendModule BOOTSTRAP_DROPDOWN = new FrontendModule.With().less(LESS_BOOTSTRAP_DROPDOWN).scripts(JS_BOOTSTRAP_DROPDOWN).done();
    public static final FrontendModule BOOTSTRAP_MODAL = new FrontendModule.With().less(LESS_BOOTSTRAP_MODAL).scripts(JS_BOOTSTRAP_MODAL).done();
    public static final FrontendModule BOOTSTRAP_POPOVER = new FrontendModule.With().less(LESS_BOOTSTRAP_POPOVER).scripts(JS_BOOTSTRAP_POPOVER).done();
    public static final FrontendModule BOOTSTRAP_TOOLTIP = new FrontendModule.With().less(LESS_BOOTSTRAP_TOOLTIP).scripts(JS_BOOTSTRAP_TOOLTIP).done();
    public static final FrontendModule BOOTSTRAP_TAB = new FrontendModule.With().scripts(JS_BOOTSTRAP_TAB).done();
    public static final FrontendModule BOOTSTRAP_TYPEAHEAD = new FrontendModule.With().scripts(JS_BOOTSTRAP_TYPEAHEAD).done();
    public static final FrontendModule BOOTSTRAP_AFFIX = new FrontendModule.With().scripts(JS_BOOTSTRAP_AFFIX).done();
    public static final FrontendModule BOOTSTRAP_SCROLLSPY = new FrontendModule.With().scripts(JS_BOOTSTRAP_SCROLLSPY).done();

	public static final FrontendModule BOOTSTRAP_RESPONSIVE = new FrontendModule.With().less(
			LESS_BOOTSTRAP_RESPONSIVE_UTILITIES,
			LESS_BOOTSTRAP_RESPONSIVE_1200MIN,
			LESS_BOOTSTRAP_RESPONSIVE_768_979,
			LESS_BOOTSTRAP_RESPONSIVE_767MAX,
			LESS_BOOTSTRAP_RESPONSIVE_NAVBAR).done();


    public static final FrontendModule UNDERSCORE = new FrontendModule.With().scripts(UNDERSCORE_RESOURCE).done();
}
