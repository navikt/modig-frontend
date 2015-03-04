package no.nav.modig.frontend;

import static no.nav.modig.frontend.BootstrapResources.*;
import static no.nav.modig.frontend.ConditionalCssResource.IE8_EKSTERNFLATE;
import static no.nav.modig.frontend.EksternflateResources.*;
import static no.nav.modig.frontend.EnonicResources.NAV_GLOBAL;
import static no.nav.modig.frontend.EnonicResources.NAV_PRINT_LESS;
import static no.nav.modig.frontend.FellesResources.*;
import static no.nav.modig.frontend.LegacyResources.*;


public class FrontendModules {

	public static final FrontendModule EKSTERNFLATE = new FrontendModule.With()
			.scripts(TRANSITIONS_RESOURCE, FELLES_JS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER)
			.conditionalCss(IE8_EKSTERNFLATE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS, WICKET_MODAL_WINDOW,
					KNAPPER_LESS, CONTRAST_LESS, DROPDOWNS_LESS, PANEL_LESS, SKJEMA_LESS, TOOLTIP_LESS,
					UTILITIES_LESS, EKSTERN_LESS).done();


	public static final FrontendModule EKSTERNFLATE_CSS = new FrontendModule.With()
			.scripts(HTML5_SHIV_RESOURCE)
			.conditionalCss(IE8_EKSTERNFLATE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS,
					KNAPPER_LESS, CONTRAST_LESS, PANEL_LESS, FOOTER_LESS, SKJEMA_LESS,
					NAV_DATEPICKER, UTILITIES_LESS, EKSTERN_LESS).done();


	@Deprecated
	public static final FrontendModule SCULPTOR = EKSTERNFLATE;


	public static final FrontendModule BOOTSTRAP_CORE = new FrontendModule.With()
			.scripts(JS_TRANSITION)
			.less(LESS_RESET, LESS_VARIABLES, LESS_MIXINS, LESS_SCAFFOLDING, LESS_GRID, LESS_LAYOUTS,
					LESS_TYPE, LESS_CODE, LESS_FORMS, LESS_TABLES, LESS_COMPONENTANIMATIONS, LESS_CLOSE,
					LESS_HEROUNIT, LESS_UTILITIES, WICKET_MODAL_WINDOW, NAV_DATEPICKER).done();

	public static final FrontendModule MODIA = new FrontendModule.With()
			.scripts(TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER, JQUERY_UI_DATEPICKER, JQUERY_VALIDATE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS, WICKET_MODAL_WINDOW, KNAPPER_LESS,
					DROPDOWNS_LESS, SKJEMA_LESS, TOOLTIP_LESS, NAV_DATEPICKER, UTILITIES_LESS).done();

	public static final FrontendModule LEGACY = new FrontendModule.With()
			.scripts(MODERNIZR_RESOURCE, LEGACY_FELLES, NAV_GLOBAL)
			.conditionalCss(LEGACY_STANDARD_IE8_CSS)
			.less(LEGACY_STANDARD_LESS, NAV_PRINT_LESS).done();


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


	public static final FrontendModule UNDERSCORE = new FrontendModule.With().scripts(UNDERSCORE_RESOURCE).done();
}
