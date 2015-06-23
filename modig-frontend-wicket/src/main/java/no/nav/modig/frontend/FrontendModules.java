package no.nav.modig.frontend;

import static no.nav.modig.frontend.NavExtraResources.AJAX_LOADER_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DATEPICKER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DROPDOWN_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.DROPDOWN_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.JQUERY_UI_DATEPICKER_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.SKJEMA_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TOOLTIP_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TOOLTIP_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TRANSITIONS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.WICKET_MODAL_WINDOW_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.HODER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.INNHOLD_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.KNAPPERAD_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.LENKELISTE_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.LISTER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.PANELER_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.STEGINDIKATOR_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.TABLES_LESS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.SPRAKVELGER_JS_RESOURCE;
import static no.nav.modig.frontend.NavExtraResources.SPRAKVELGER_LESS_RESOURCE;

import static no.nav.modig.frontend.NavFrontendResources.JQUERY_VALIDATE_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.MIXINS_LESS_RESOURCE;
import static no.nav.modig.frontend.NavFrontendResources.VARIABLES_LESS_RESOURCE;


public class FrontendModules {

    public static final FrontendModule NAV_FRONTEND = new FrontendModule.With()
            .less(VARIABLES_LESS_RESOURCE, MIXINS_LESS_RESOURCE)
            .done();

    public static final FrontendModule MODIA = new FrontendModule.With()
            .scripts(TRANSITIONS_RESOURCE, DROPDOWN_RESOURCE, TOOLTIP_RESOURCE, AJAX_LOADER_RESOURCE, JQUERY_UI_DATEPICKER_RESOURCE,
                    JQUERY_VALIDATE_RESOURCE)
            .less(VARIABLES_LESS_RESOURCE, MIXINS_LESS_RESOURCE, WICKET_MODAL_WINDOW_RESOURCE, DROPDOWN_LESS_RESOURCE, SKJEMA_LESS_RESOURCE,
                    TOOLTIP_LESS_RESOURCE, DATEPICKER_LESS_RESOURCE)
            .done();

    public static final FrontendModule HODER = new FrontendModule.With()
            .less(HODER_LESS_RESOURCE)
            .done();

    public static final FrontendModule KNAPPERAD = new FrontendModule.With()
            .less(KNAPPERAD_LESS_RESOURCE)
            .done();

    public static final FrontendModule LENKELISTER = new FrontendModule.With()
            .less(LENKELISTE_LESS_RESOURCE)
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

    public static final FrontendModule DATEPICKER = new FrontendModule.With()
            .less(DATEPICKER_LESS_RESOURCE)
            .scripts(JQUERY_UI_DATEPICKER_RESOURCE)
            .done();

    public static final FrontendModule DROPDOWN = new FrontendModule.With()
            .less(DROPDOWN_LESS_RESOURCE)
            .scripts(DROPDOWN_RESOURCE)
            .done();

    public static final FrontendModule WICKET_MODAL_WINDOW = new FrontendModule.With()
            .less(WICKET_MODAL_WINDOW_RESOURCE)
            .done();

    public static final FrontendModule SKJEMA = new FrontendModule.With()
            .less(SKJEMA_LESS_RESOURCE)
            .done();

    public static final FrontendModule TOOLTIP = new FrontendModule.With()
            .less(TOOLTIP_LESS_RESOURCE)
            .scripts(TOOLTIP_RESOURCE)
            .done();

    public static final FrontendModule TABLES = new FrontendModule.With()
            .less(TABLES_LESS_RESOURCE)
            .done();

    public static final FrontendModule SPRAKVELGER = new FrontendModule.With()
            .less(SPRAKVELGER_LESS_RESOURCE)
            .scripts(SPRAKVELGER_JS_RESOURCE)
            .done();

    public static final FrontendModule INNHOLDSFORTEGNELSE = new FrontendModule.With()
            .less(INNHOLD_LESS_RESOURCE)
            .done();

    public static final FrontendModule TRANSITIONS_JS = new FrontendModule.With()
            .scripts(TRANSITIONS_RESOURCE)
            .done();
}


