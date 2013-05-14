package no.nav.modig.frontend;

import static no.nav.modig.frontend.BootstrapResources.ICONS;
import static no.nav.modig.frontend.BootstrapResources.ICONS_WHITE;
import static no.nav.modig.frontend.BootstrapResources.JS_AFFIX;
import static no.nav.modig.frontend.BootstrapResources.JS_ALERT;
import static no.nav.modig.frontend.BootstrapResources.JS_BUTTON;
import static no.nav.modig.frontend.BootstrapResources.JS_CAROUSEL;
import static no.nav.modig.frontend.BootstrapResources.JS_COLLAPSE;
import static no.nav.modig.frontend.BootstrapResources.JS_DROPDOWN;
import static no.nav.modig.frontend.BootstrapResources.JS_MODAL;
import static no.nav.modig.frontend.BootstrapResources.JS_POPOVER;
import static no.nav.modig.frontend.BootstrapResources.JS_SCROLLSPY;
import static no.nav.modig.frontend.BootstrapResources.JS_TAB;
import static no.nav.modig.frontend.BootstrapResources.JS_TOOLTIP;
import static no.nav.modig.frontend.BootstrapResources.JS_TRANSITION;
import static no.nav.modig.frontend.BootstrapResources.JS_TYPEAHEAD;
import static no.nav.modig.frontend.BootstrapResources.LESS_ACCORDION;
import static no.nav.modig.frontend.BootstrapResources.LESS_ALERTS;
import static no.nav.modig.frontend.BootstrapResources.LESS_BREADCRUMBS;
import static no.nav.modig.frontend.BootstrapResources.LESS_BUTTON;
import static no.nav.modig.frontend.BootstrapResources.LESS_BUTTON_GROUPS;
import static no.nav.modig.frontend.BootstrapResources.LESS_CAROUSEL;
import static no.nav.modig.frontend.BootstrapResources.LESS_CLOSE;
import static no.nav.modig.frontend.BootstrapResources.LESS_CODE;
import static no.nav.modig.frontend.BootstrapResources.LESS_COMPONENTANIMATIONS;
import static no.nav.modig.frontend.BootstrapResources.LESS_DROPDOWN;
import static no.nav.modig.frontend.BootstrapResources.LESS_FORMS;
import static no.nav.modig.frontend.BootstrapResources.LESS_GRID;
import static no.nav.modig.frontend.BootstrapResources.LESS_HEROUNIT;
import static no.nav.modig.frontend.BootstrapResources.LESS_LABELBADGES;
import static no.nav.modig.frontend.BootstrapResources.LESS_LAYOUTS;
import static no.nav.modig.frontend.BootstrapResources.LESS_MIXINS;
import static no.nav.modig.frontend.BootstrapResources.LESS_MODAL;
import static no.nav.modig.frontend.BootstrapResources.LESS_NAVBAR;
import static no.nav.modig.frontend.BootstrapResources.LESS_NAVS;
import static no.nav.modig.frontend.BootstrapResources.LESS_PAGER;
import static no.nav.modig.frontend.BootstrapResources.LESS_PAGINATION;
import static no.nav.modig.frontend.BootstrapResources.LESS_POPOVER;
import static no.nav.modig.frontend.BootstrapResources.LESS_PROGRESSBARS;
import static no.nav.modig.frontend.BootstrapResources.LESS_RESET;
import static no.nav.modig.frontend.BootstrapResources.LESS_RESPONSIVE_1200MIN;
import static no.nav.modig.frontend.BootstrapResources.LESS_RESPONSIVE_767MAX;
import static no.nav.modig.frontend.BootstrapResources.LESS_RESPONSIVE_768_979;
import static no.nav.modig.frontend.BootstrapResources.LESS_RESPONSIVE_NAVBAR;
import static no.nav.modig.frontend.BootstrapResources.LESS_RESPONSIVE_UTILITIES;
import static no.nav.modig.frontend.BootstrapResources.LESS_SCAFFOLDING;
import static no.nav.modig.frontend.BootstrapResources.LESS_SPRITES;
import static no.nav.modig.frontend.BootstrapResources.LESS_TABLES;
import static no.nav.modig.frontend.BootstrapResources.LESS_THUMBNAILS;
import static no.nav.modig.frontend.BootstrapResources.LESS_TOOLTIP;
import static no.nav.modig.frontend.BootstrapResources.LESS_TYPE;
import static no.nav.modig.frontend.BootstrapResources.LESS_UTILITIES;
import static no.nav.modig.frontend.BootstrapResources.LESS_VARIABLES;
import static no.nav.modig.frontend.BootstrapResources.LESS_WELLS;
import static no.nav.modig.frontend.EksternflateResources.EKSTERN_LESS;
import static no.nav.modig.frontend.EksternflateResources.FOOTER_LESS;
import static no.nav.modig.frontend.EksternflateResources.PANEL_LESS;
import static no.nav.modig.frontend.FellesResources.DROPDOWNS_LESS;
import static no.nav.modig.frontend.FellesResources.DROPDOWN_RESOURCE;
import static no.nav.modig.frontend.FellesResources.FELLES_LESS;
import static no.nav.modig.frontend.FellesResources.HTML5_SHIV_RESOURCE;
import static no.nav.modig.frontend.FellesResources.JQUERY_RESOURCE;
import static no.nav.modig.frontend.FellesResources.KNAPPER_LESS;
import static no.nav.modig.frontend.FellesResources.MIXINS_LESS;
import static no.nav.modig.frontend.FellesResources.MODUS_LESS;
import static no.nav.modig.frontend.FellesResources.NAV_DATEPICKER;
import static no.nav.modig.frontend.FellesResources.RESET_LESS;
import static no.nav.modig.frontend.FellesResources.SKJEMA_LESS;
import static no.nav.modig.frontend.FellesResources.TOOLTIP_LESS;
import static no.nav.modig.frontend.FellesResources.TOOLTIP_RESOURCE;
import static no.nav.modig.frontend.FellesResources.TRANSITIONS_RESOURCE;
import static no.nav.modig.frontend.FellesResources.TYPOGRAFI_LESS;
import static no.nav.modig.frontend.FellesResources.UNDERSCORE_RESOURCE;
import static no.nav.modig.frontend.FellesResources.VARIABLES_LESS;
import static no.nav.modig.frontend.FellesResources.WICKET_MODAL_WINDOW;


public class FrontendModules {

	public static final FrontendModule SCULPTOR = new FrontendModule.With()
			.scripts(JQUERY_RESOURCE, /*HTML5_SHIV_RESOURCE,*/ TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS, WICKET_MODAL_WINDOW, KNAPPER_LESS,
					DROPDOWNS_LESS, PANEL_LESS, FOOTER_LESS, SKJEMA_LESS, TOOLTIP_LESS, NAV_DATEPICKER, EKSTERN_LESS).done();

	public static final FrontendModule BOOTSTRAP_CORE = new FrontendModule.With()
			.scripts(JS_TRANSITION)
			.less(LESS_RESET, LESS_VARIABLES, LESS_MIXINS, LESS_SCAFFOLDING, LESS_GRID, LESS_LAYOUTS,
					LESS_TYPE, LESS_CODE, LESS_FORMS, LESS_TABLES, LESS_COMPONENTANIMATIONS, LESS_CLOSE,
					LESS_HEROUNIT, LESS_UTILITIES, WICKET_MODAL_WINDOW, NAV_DATEPICKER).done();

	public static final FrontendModule BOOTSTRAP_SPRITES = new FrontendModule.With().less(LESS_SPRITES).images(ICONS, ICONS_WHITE).done();
	public static final FrontendModule BOOTSTRAP_WELLS = new FrontendModule.With().less(LESS_WELLS).done();
	public static final FrontendModule BOOTSTRAP_NAVIGATION = new FrontendModule.With().less(LESS_NAVS, LESS_NAVBAR, LESS_BREADCRUMBS).done();
	public static final FrontendModule BOOTSTRAP_PAGER = new FrontendModule.With().less(LESS_PAGINATION, LESS_PAGER).done();
	public static final FrontendModule BOOTSTRAP_ACCORDION = new FrontendModule.With().less(LESS_ACCORDION).scripts(JS_COLLAPSE).done();
	public static final FrontendModule BOOTSTRAP_THUMBNAILS = new FrontendModule.With().less(LESS_THUMBNAILS).done();
	public static final FrontendModule BOOTSTRAP_LABELS_AND_BADGES = new FrontendModule.With().less(LESS_LABELBADGES).done();
	public static final FrontendModule BOOTSTRAP_PROGRESSBARS = new FrontendModule.With().less(LESS_PROGRESSBARS).done();
	public static final FrontendModule BOOTSTRAP_ALERTS = new FrontendModule.With().less(LESS_ALERTS).scripts(JS_ALERT).done();
	public static final FrontendModule BOOTSTRAP_BUTTON = new FrontendModule.With().less(LESS_BUTTON, LESS_BUTTON_GROUPS).scripts(JS_BUTTON).done();
	public static final FrontendModule BOOTSTRAP_CAROUSEL = new FrontendModule.With().less(LESS_CAROUSEL).scripts(JS_CAROUSEL).done();
	public static final FrontendModule BOOTSTRAP_DROPDOWN = new FrontendModule.With().less(LESS_DROPDOWN).scripts(JS_DROPDOWN).done();
	public static final FrontendModule BOOTSTRAP_MODAL = new FrontendModule.With().less(LESS_MODAL).scripts(JS_MODAL).done();
	public static final FrontendModule BOOTSTRAP_POPOVER = new FrontendModule.With().less(LESS_POPOVER).scripts(JS_POPOVER).done();
	public static final FrontendModule BOOTSTRAP_TOOLTIP = new FrontendModule.With().less(LESS_TOOLTIP).scripts(JS_TOOLTIP).done();
	public static final FrontendModule BOOTSTRAP_TAB = new FrontendModule.With().scripts(JS_TAB).done();
	public static final FrontendModule BOOTSTRAP_TYPEAHEAD = new FrontendModule.With().scripts(JS_TYPEAHEAD).done();
	public static final FrontendModule BOOTSTRAP_AFFIX = new FrontendModule.With().scripts(JS_AFFIX).done();
	public static final FrontendModule BOOTSTRAP_SCROLLSPY = new FrontendModule.With().scripts(JS_SCROLLSPY).done();

	public static final FrontendModule BOOTSTRAP_RESPONSIVE = new FrontendModule.With().less(
			LESS_RESPONSIVE_UTILITIES, LESS_RESPONSIVE_1200MIN, LESS_RESPONSIVE_768_979,
			LESS_RESPONSIVE_767MAX, LESS_RESPONSIVE_NAVBAR).done();


	public static final FrontendModule UNDERSCORE = new FrontendModule.With().scripts(JQUERY_RESOURCE, UNDERSCORE_RESOURCE).done();
}
