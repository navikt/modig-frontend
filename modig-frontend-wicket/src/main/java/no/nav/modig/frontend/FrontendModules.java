package no.nav.modig.frontend;

import static no.nav.modig.frontend.NavFrontendResources.*;
import static no.nav.modig.frontend.NavFrontendResources.AJAX_LOADER;
import static no.nav.modig.frontend.NavFrontendResources.CONTRAST_LESS;
import static no.nav.modig.frontend.NavFrontendResources.DATEPICKER_LESS;
import static no.nav.modig.frontend.NavFrontendResources.DROPDOWNS_LESS;
import static no.nav.modig.frontend.NavFrontendResources.DROPDOWN_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.GRID_LESS;
import static no.nav.modig.frontend.NavFrontendResources.INNLOGGING;
import static no.nav.modig.frontend.NavFrontendResources.JQUERY_UI_DATEPICKER;
import static no.nav.modig.frontend.NavFrontendResources.JQUERY_VALIDATE;
import static no.nav.modig.frontend.NavFrontendResources.KNAPPER_LESS;
import static no.nav.modig.frontend.NavFrontendResources.MEGAMENU_LESS;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_LESS;
import static no.nav.modig.frontend.NavFrontendResources.MODERNIZR_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MODUS_LESS;
import static no.nav.modig.frontend.NavFrontendResources.NAV_GLOBAL;
import static no.nav.modig.frontend.NavFrontendResources.NORMALIZE_LESS;
import static no.nav.modig.frontend.NavFrontendResources.SITEFOOTER_ALPHABET_LESS;
import static no.nav.modig.frontend.NavFrontendResources.SITEFOOTER_LESS;
import static no.nav.modig.frontend.NavFrontendResources.SITEHEADER_LESS;
import static no.nav.modig.frontend.NavFrontendResources.SKJEMA_LESS;
import static no.nav.modig.frontend.NavFrontendResources.TOOLTIP_LESS;
import static no.nav.modig.frontend.NavFrontendResources.TOOLTIP_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.TRANSITIONS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.TYPOGRAFI_LESS;
import static no.nav.modig.frontend.NavFrontendResources.UTILITIES_LESS;
import static no.nav.modig.frontend.NavFrontendResources.VARIABLES_LESS;
import static no.nav.modig.frontend.NavFrontendResources.WICKET_MODAL_WINDOW;


public class FrontendModules {

    public static final FrontendModule NAV_FRONTEND = new FrontendModule.With()
            .scripts(MODERNIZR_RESOURCE, TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER, FELLES_JS_RESOURCE,
                    NAV_GLOBAL, MATCH_MEDIA, JQUERY_MEGA_MENU, INNLOGGING)
            .less(NORMALIZE_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, WICKET_MODAL_WINDOW, GRID_LESS, KNAPPER_LESS,
                    DROPDOWNS_LESS, DATEPICKER_LESS, PANEL_LESS, SKJEMA_LESS, TOOLTIP_LESS, MEGAMENU_LESS, SITEHEADER_LESS, SITEFOOTER_LESS,
                    SITEFOOTER_ALPHABET_LESS, UTILITIES_LESS, CONTRAST_LESS)
            .done();

	public static final FrontendModule MODIA = new FrontendModule.With()
			.scripts(TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER, JQUERY_UI_DATEPICKER, JQUERY_VALIDATE)
			.less(NORMALIZE_LESS, VARIABLES_LESS, MIXINS_LESS, MODUS_LESS, TYPOGRAFI_LESS, WICKET_MODAL_WINDOW, KNAPPER_LESS,
					DROPDOWNS_LESS, SKJEMA_LESS, TOOLTIP_LESS, DATEPICKER_LESS, UTILITIES_LESS).done();
}
