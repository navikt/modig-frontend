package no.nav.modig.frontend;

import static no.nav.modig.frontend.NavExtraResources.AJAX_LOADER_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DATEPICKER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DROPDOWN_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DROPDOWN_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.JQUERY_UI_DATEPICKER_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.KNAPPER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.SKJEMA_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TOOLTIP_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TOOLTIP_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TRANSITIONS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.WICKET_MODAL_WINDOW_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.CONTRAST_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.GRID_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.INNLOGGING_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.JQUERY_MEGA_MENU_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.JQUERY_VALIDATE_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MATCH_MEDIA_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MEGAMENU_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MODERNIZR_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MODUS_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.NAV_GLOBAL_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.NORMALIZE_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.SCAFFOLDING_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.SITEFOOTER_ALPHABET_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.SITEFOOTER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.SITEHEADER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.TYPOGRAPHY_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.UTILITIES_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.VARIABLES_LESS_RESOURCE;


public class FrontendModules {

    public static final FrontendModule NAV_FRONTEND = new FrontendModule.With()
            .scripts(MODERNIZR_RESOURCE, NAV_GLOBAL_RESOURCE, MATCH_MEDIA_RESOURCE, JQUERY_MEGA_MENU_RESOURCE, INNLOGGING_RESOURCE)
            .less(NORMALIZE_LESS_RESOURCE, VARIABLES_LESS_RESOURCE, MIXINS_LESS_RESOURCE, MODUS_LESS_RESOURCE, TYPOGRAPHY_LESS_RESOURCE,
                    GRID_LESS_RESOURCE, MEGAMENU_LESS_RESOURCE, SCAFFOLDING_LESS_RESOURCE, SITEHEADER_LESS_RESOURCE, SITEFOOTER_LESS_RESOURCE,
                    SITEFOOTER_ALPHABET_LESS_RESOURCE, UTILITIES_LESS_RESOURCE, CONTRAST_LESS_RESOURCE)
            .done();

	public static final FrontendModule MODIA = new FrontendModule.With()
			.scripts(TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER_RESOURCE, JQUERY_UI_DATEPICKER_RESOURCE,
                    JQUERY_VALIDATE_RESOURCE)
			.less(NORMALIZE_LESS_RESOURCE, VARIABLES_LESS_RESOURCE, MIXINS_LESS_RESOURCE, MODUS_LESS_RESOURCE, TYPOGRAPHY_LESS_RESOURCE,
                    WICKET_MODAL_WINDOW_RESOURCE, KNAPPER_LESS_RESOURCE, DROPDOWN_LESS_RESOURCE, SKJEMA_LESS_RESOURCE, TOOLTIP_LESS_RESOURCE,
                    DATEPICKER_LESS_RESOURCE, UTILITIES_LESS_RESOURCE).done();

    public static final FrontendModule EKSTERNFLATE = new FrontendModule.With().less(NavExtraResources.EKSTERNFLATE_LESS_RESOURCE).done();
    public static final FrontendModule STEGINDIKATOR = new FrontendModule.With().less(NavExtraResources.STEGINDIKATOR_LESS_RESOURCE).done();
    public static final FrontendModule AJAX_LOADER = new FrontendModule.With().less(NavExtraResources.AJAX_LOADER_RESOURCE).done();
    public static final FrontendModule DATEPICKER_LESS = new FrontendModule.With().less(NavExtraResources.DATEPICKER_LESS_RESOURCE).done();
    public static final FrontendModule DROPDOWNS_LESS = new FrontendModule.With().less(NavExtraResources.DROPDOWN_LESS_RESOURCE).done();
    public static final FrontendModule KNAPPER_LESS = new FrontendModule.With().less(NavExtraResources.KNAPPER_LESS_RESOURCE).done();
    public static final FrontendModule WICKET_MODAL_WINDOW = new FrontendModule.With().less(NavExtraResources.WICKET_MODAL_WINDOW_RESOURCE).done();
    public static final FrontendModule MODIG_GRID_LESS = new FrontendModule.With().less(NavExtraResources.MODIG_GRID_LESS_RESOURCE).done();
    public static final FrontendModule SKJEMA_LESS = new FrontendModule.With().less(NavExtraResources.SKJEMA_LESS_RESOURCE).done();
    public static final FrontendModule TOOLTIP_LESS = new FrontendModule.With().less(NavExtraResources.TOOLTIP_LESS_RESOURCE).done();

    public static final FrontendModule DROPDOWN = new FrontendModule.With().scripts(NavExtraResources.DROPDOWN_RESOURCE).done();
    public static final FrontendModule FELLES_JS = new FrontendModule.With().scripts(NavExtraResources.FELLES_JS_RESOURCE).done();
    public static final FrontendModule TOOLTIP = new FrontendModule.With().scripts(NavExtraResources.TOOLTIP_RESOURCE).done();
    public static final FrontendModule TRANSITIONS = new FrontendModule.With().scripts(NavExtraResources.TRANSITIONS_RESOURCE).done();
    public static final FrontendModule JQUERY_UI_DATEPICKER = new FrontendModule.With().scripts(NavExtraResources.JQUERY_UI_DATEPICKER_RESOURCE).done();
}
