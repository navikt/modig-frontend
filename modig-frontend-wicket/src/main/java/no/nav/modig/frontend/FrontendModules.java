package no.nav.modig.frontend;

import static no.nav.modig.frontend.NavExtraResources.AJAX_LOADER_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DROPDOWN_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.HJELPETEKST_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.HODER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.JQUERY_UI_DATEPICKER_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.KNAPPERAD_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.LISTER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.MODALER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.PANELER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.SKJEMA_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.STEGINDIKATOR_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TOOLTIP_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TRANSITIONS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.UTILITIES_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.VALIDERING_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.WICKET_MODAL_WINDOW_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.JQUERY_VALIDATE_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_GRID_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_KNAPPER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_TYPOGRAFI_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_UTILS_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.VARIABLES_LESS_RESOURCE;


public class FrontendModules {

    public static final FrontendModule NAV_FRONTEND = new FrontendModule.With()
            .less(
                    VARIABLES_LESS_RESOURCE, MIXINS_GRID_LESS_RESOURCE, MIXINS_KNAPPER_LESS_RESOURCE, MIXINS_TYPOGRAFI_LESS_RESOURCE,
					MIXINS_UTILS_LESS_RESOURCE
            )
            .done();

    public static final FrontendModule MODIA = new FrontendModule.With()
            .scripts(TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER_RESOURCE, JQUERY_UI_DATEPICKER_RESOURCE,
                    JQUERY_VALIDATE_RESOURCE)
            .less(
                    VARIABLES_LESS_RESOURCE,
                    MIXINS_KNAPPER_LESS_RESOURCE, MIXINS_TYPOGRAFI_LESS_RESOURCE, MIXINS_UTILS_LESS_RESOURCE,
                    WICKET_MODAL_WINDOW_RESOURCE, SKJEMA_LESS_RESOURCE
            )
            .done();

    public static final FrontendModule HODER = new FrontendModule.With()
            .less(HODER_LESS_RESOURCE)
            .done();

    public static final FrontendModule KNAPPERAD = new FrontendModule.With()
            .less(KNAPPERAD_LESS_RESOURCE)
            .done();

    public static final FrontendModule LISTER = new FrontendModule.With()
            .less(LISTER_LESS_RESOURCE)
            .done();

    public static final FrontendModule PANELER = new FrontendModule.With()
            .less(PANELER_LESS_RESOURCE)
            .done();

    public static final FrontendModule STEGINDIKATOR = new FrontendModule.With()
            .less(STEGINDIKATOR_LESS_RESOURCE)
            .done();

    public static final FrontendModule AJAX_LOADER = new FrontendModule.With()
            .less(AJAX_LOADER_RESOURCE)
            .done();

    public static final FrontendModule HJELPETEKST = new FrontendModule.With()
            .less(HJELPETEKST_LESS_RESOURCE)
            .done();

    public static final FrontendModule MODALER = new FrontendModule.With()
            .less(MODALER_LESS_RESOURCE)
            .done();

    public static final FrontendModule WICKET_MODAL_WINDOW = new FrontendModule.With()
            .less(WICKET_MODAL_WINDOW_RESOURCE)
            .done();

    public static final FrontendModule SKJEMA = new FrontendModule.With()
            .less(SKJEMA_LESS_RESOURCE)
            .done();

    public static final FrontendModule UTILITIES = new FrontendModule.With()
            .less(UTILITIES_LESS_RESOURCE)
            .done();

    public static final FrontendModule VALIDERING = new FrontendModule.With()
            .less(VALIDERING_LESS_RESOURCE)
            .done();

    public static final FrontendModule TRANSITIONS_JS = new FrontendModule.With()
            .scripts(TRANSITIONS_RESOURCE)
            .done();
}


