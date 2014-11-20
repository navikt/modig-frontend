package no.nav.modig.frontend;

import static no.nav.modig.frontend.ConditionalCssResource.IE8_EKSTERNFLATE;
import static no.nav.modig.frontend.EksternflateResources.EKSTERN_LESS;
import static no.nav.modig.frontend.EksternflateResources.FOOTER_LESS;
import static no.nav.modig.frontend.EksternflateResources.PANEL_LESS;
import static no.nav.modig.frontend.EnonicResources.JQUERY_MEGA_MENU;
import static no.nav.modig.frontend.EnonicResources.MATCH_MEDIA;
import static no.nav.modig.frontend.EnonicResources.NAV_ACCORDION_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_ARTICLE_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_AUTOCOMPLETE_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_BREADCRUMB_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_BUTTONS_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_CAROUSEL_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_CONTRAST_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_ERROR_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_FACTSHEET_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_GLOBAL;
import static no.nav.modig.frontend.EnonicResources.NAV_GRID_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_INNLOGGING;
import static no.nav.modig.frontend.EnonicResources.NAV_LINKLISTS_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_MEGAMENU_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_MIXINS_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_NORMALIZE_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_PRINT_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_RIS_ROS_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_SCAFFOLDING_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_SERVICE_NOTIFICATION_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_SITEFOOTER_ALPHABET_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_SITEFOOTER_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_SITEHEADER_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_TABLE_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_TYPOGRAPHY_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_UTILITIES_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_VARIABLES_LESS;
import static no.nav.modig.frontend.EnonicResources.NAV_WEBFONTS_LESS;
import static no.nav.modig.frontend.FellesResources.AJAX_LOADER;
import static no.nav.modig.frontend.FellesResources.CONTRAST_LESS;
import static no.nav.modig.frontend.FellesResources.DROPDOWNS_LESS;
import static no.nav.modig.frontend.FellesResources.DROPDOWN_RESOURCE;
import static no.nav.modig.frontend.FellesResources.FELLES_JS_RESOURCE;
import static no.nav.modig.frontend.FellesResources.FELLES_LESS;
import static no.nav.modig.frontend.FellesResources.HTML5_SHIV_RESOURCE;
import static no.nav.modig.frontend.FellesResources.JQUERY_UI_DATEPICKER;
import static no.nav.modig.frontend.FellesResources.JQUERY_VALIDATE;
import static no.nav.modig.frontend.FellesResources.KNAPPER_LESS;
import static no.nav.modig.frontend.FellesResources.MIXINS_LESS;
import static no.nav.modig.frontend.FellesResources.MODERNIZR_RESOURCE;
import static no.nav.modig.frontend.FellesResources.MODUS_LESS;
import static no.nav.modig.frontend.FellesResources.NAV_DATEPICKER;
import static no.nav.modig.frontend.FellesResources.RESET_LESS;
import static no.nav.modig.frontend.FellesResources.SKJEMA_LESS;
import static no.nav.modig.frontend.FellesResources.TOOLTIP_LESS;
import static no.nav.modig.frontend.FellesResources.TOOLTIP_RESOURCE;
import static no.nav.modig.frontend.FellesResources.TRANSITIONS_RESOURCE;
import static no.nav.modig.frontend.FellesResources.TYPOGRAFI_LESS;
import static no.nav.modig.frontend.FellesResources.UTILITIES_LESS;
import static no.nav.modig.frontend.FellesResources.VARIABLES_LESS;
import static no.nav.modig.frontend.FellesResources.WICKET_MODAL_WINDOW;


public class FrontendModules {

	public static final FrontendModule EKSTERNFLATE = new FrontendModule.With()
			.scripts(MODERNIZR_RESOURCE, TRANSITIONS_RESOURCE, FELLES_JS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE,
					AJAX_LOADER, NAV_GLOBAL, NAV_INNLOGGING, MATCH_MEDIA, JQUERY_MEGA_MENU)
			.conditionalCss(IE8_EKSTERNFLATE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, NAV_NORMALIZE_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS, WICKET_MODAL_WINDOW,
					KNAPPER_LESS, CONTRAST_LESS, DROPDOWNS_LESS, PANEL_LESS, SKJEMA_LESS, TOOLTIP_LESS, NAV_MIXINS_LESS, NAV_VARIABLES_LESS,
                    NAV_GRID_LESS, NAV_MEGAMENU_LESS,
					NAV_SITEHEADER_LESS, NAV_SITEFOOTER_LESS, NAV_SITEFOOTER_ALPHABET_LESS, NAV_UTILITIES_LESS, NAV_BUTTONS_LESS,
					NAV_DATEPICKER, UTILITIES_LESS, EKSTERN_LESS).done();

	public static final FrontendModule EKSTERNFLATE_CSS = new FrontendModule.With()
			.scripts(HTML5_SHIV_RESOURCE)
			.conditionalCss(IE8_EKSTERNFLATE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS,
					KNAPPER_LESS, CONTRAST_LESS, PANEL_LESS, FOOTER_LESS, SKJEMA_LESS,
					UTILITIES_LESS, EKSTERN_LESS).done();

	public static final FrontendModule MODIA = new FrontendModule.With()
			.scripts(TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER, JQUERY_UI_DATEPICKER, JQUERY_VALIDATE)
			.less(RESET_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, FELLES_LESS, WICKET_MODAL_WINDOW, KNAPPER_LESS,
					DROPDOWNS_LESS, SKJEMA_LESS, TOOLTIP_LESS, NAV_DATEPICKER, UTILITIES_LESS).done();

	public static final FrontendModule ENONIC = new FrontendModule.With()
			.scripts(MODERNIZR_RESOURCE, NAV_GLOBAL, NAV_INNLOGGING,
					MATCH_MEDIA, JQUERY_MEGA_MENU)
			.less(NAV_VARIABLES_LESS, NAV_MIXINS_LESS, NAV_NORMALIZE_LESS, NAV_GRID_LESS, NAV_AUTOCOMPLETE_LESS,
					NAV_TYPOGRAPHY_LESS, NAV_TABLE_LESS, NAV_BUTTONS_LESS, NAV_BREADCRUMB_LESS, NAV_MEGAMENU_LESS,
					NAV_LINKLISTS_LESS, NAV_SCAFFOLDING_LESS, NAV_RIS_ROS_LESS, NAV_SERVICE_NOTIFICATION_LESS,
					NAV_SITEHEADER_LESS, NAV_SITEFOOTER_LESS, NAV_SITEFOOTER_ALPHABET_LESS,
					NAV_ARTICLE_LESS, NAV_FACTSHEET_LESS, NAV_CAROUSEL_LESS, NAV_ACCORDION_LESS, NAV_ERROR_LESS,
					NAV_UTILITIES_LESS, NAV_CONTRAST_LESS, NAV_PRINT_LESS, NAV_WEBFONTS_LESS).done();

}
