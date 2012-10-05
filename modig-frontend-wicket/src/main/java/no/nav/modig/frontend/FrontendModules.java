package no.nav.modig.frontend;

import static no.nav.modig.frontend.FrontendResources.CSS_RESOURCES;
import static no.nav.modig.frontend.FrontendResources.JS_AFFIX;
import static no.nav.modig.frontend.FrontendResources.JS_ALERT;
import static no.nav.modig.frontend.FrontendResources.JS_BUTTON;
import static no.nav.modig.frontend.FrontendResources.JS_CAROUSEL;
import static no.nav.modig.frontend.FrontendResources.JS_COLLAPSE;
import static no.nav.modig.frontend.FrontendResources.JS_DROPDOWN;
import static no.nav.modig.frontend.FrontendResources.JS_MODAL;
import static no.nav.modig.frontend.FrontendResources.JS_POPOVER;
import static no.nav.modig.frontend.FrontendResources.JS_RESOURCES_ALL;
import static no.nav.modig.frontend.FrontendResources.JS_SCROLLSPY;
import static no.nav.modig.frontend.FrontendResources.JS_TAB;
import static no.nav.modig.frontend.FrontendResources.JS_TOOLTIP;
import static no.nav.modig.frontend.FrontendResources.JS_TYPEAHEAD;

public class FrontendModules {
    public static FrontendModule ALERT = new FrontendModule.With().scripts(JS_ALERT).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule BUTTON = new FrontendModule.With().scripts(JS_BUTTON).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule CAROUSEL = new FrontendModule.With().scripts(JS_CAROUSEL).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule COLLAPSE = new FrontendModule.With().scripts(JS_COLLAPSE).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule DROPDOWN = new FrontendModule.With().scripts(JS_DROPDOWN).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule MODAL = new FrontendModule.With().scripts(JS_MODAL).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule TOOLTIP = new FrontendModule.With().scripts(JS_TOOLTIP).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule POPOVER = new FrontendModule.With().scripts(JS_POPOVER).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule SCROLLSPY = new FrontendModule.With().scripts(JS_SCROLLSPY).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule TAB = new FrontendModule.With().scripts(JS_TAB).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule TYPEAHEAD = new FrontendModule.With().scripts(JS_TYPEAHEAD).stylesheets(CSS_RESOURCES).done();
    public static FrontendModule AFFIX = new FrontendModule.With().scripts(JS_AFFIX).stylesheets(CSS_RESOURCES).done();

    public static FrontendModule ALL = new FrontendModule.With().scripts(JS_RESOURCES_ALL).stylesheets(CSS_RESOURCES).done();

}
