package no.nav.modig.frontend;

import static no.nav.modig.frontend.FrontendResources.CSS_RESOURCES;
import static no.nav.modig.frontend.FrontendResources.ICONS;
import static no.nav.modig.frontend.FrontendResources.ICONS_WHITE;
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
import static no.nav.modig.frontend.FrontendResources.UNDERSCORE_RESOURCE;


public class FrontendModules {

    public static final FrontendModule ALERT = new FrontendModule.With().scripts(JS_ALERT).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule BUTTON = new FrontendModule.With().scripts(JS_BUTTON).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule CAROUSEL = new FrontendModule.With().scripts(JS_CAROUSEL).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule COLLAPSE = new FrontendModule.With().scripts(JS_COLLAPSE).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule DROPDOWN = new FrontendModule.With().scripts(JS_DROPDOWN).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule MODAL = new FrontendModule.With().scripts(JS_MODAL).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule TOOLTIP = new FrontendModule.With().scripts(JS_TOOLTIP).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule POPOVER = new FrontendModule.With().scripts(JS_POPOVER).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule SCROLLSPY = new FrontendModule.With().scripts(JS_SCROLLSPY).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule TAB = new FrontendModule.With().scripts(JS_TAB).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule TYPEAHEAD = new FrontendModule.With().scripts(JS_TYPEAHEAD).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();
    public static final FrontendModule AFFIX = new FrontendModule.With().scripts(JS_AFFIX).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();

    public static final FrontendModule BOOTSTRAP_ALL = new FrontendModule.With().scripts(JS_RESOURCES_ALL).stylesheets(CSS_RESOURCES).images(ICONS, ICONS_WHITE).done();

    public static final FrontendModule UNDERSCORE = new FrontendModule.With().scripts(UNDERSCORE_RESOURCE).done();
}
