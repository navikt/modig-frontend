package no.nav.modig.frontend;

import js.JsResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

class EnonicResources  {

    static final JavaScriptResourceReference NAV_GLOBAL = new JQueryDependentResourceReference("enonic/navno/nav-global.js");
    static final JavaScriptResourceReference NAV_INNLOGGING = jsReference("enonic/navno/nav-innloggingslinjen.js");

    static final JavaScriptResourceReference MATCH_MEDIA = jsReference("enonic/navno/libs/matchmedia.js");
    static final JavaScriptResourceReference JQUERY_MEGA_MENU = new JQueryDependentResourceReference("enonic/navno/libs/megamenu/jquery-accessibleMegaMenu.js");

    static final PackageResourceReference NAV_VARIABLES_LESS = lessReference("enonic/nav-variables-wicket.less");
    static final PackageResourceReference NAV_MIXINS_LESS = lessReference("enonic/includes/nav-mixins.less");
    static final PackageResourceReference NAV_NORMALIZE_LESS = lessReference("enonic/includes/nav-normalize.less");
    static final PackageResourceReference NAV_GRID_LESS = lessReference("enonic/includes/nav-grid.less");
    static final PackageResourceReference NAV_RIS_ROS_LESS = lessReference("enonic/includes/nav-forms-klage-ris-ros.less");
    static final PackageResourceReference NAV_AUTOCOMPLETE_LESS = lessReference("enonic/includes/nav-autocomplete.less");

    static final PackageResourceReference NAV_TYPOGRAPHY_LESS = lessReference("enonic/includes/nav-typography.less");
    static final PackageResourceReference NAV_TABLE_LESS = lessReference("enonic/includes/nav-table.less");
    static final PackageResourceReference NAV_BUTTONS_LESS = lessReference("enonic/includes/nav-buttons.less");
    static final PackageResourceReference NAV_BREADCRUMB_LESS = lessReference("enonic/includes/nav-breadcrumb.less");
    static final PackageResourceReference NAV_MEGAMENU_LESS = lessReference("enonic/includes/nav-megamenu.less");
    static final PackageResourceReference NAV_LINKLISTS_LESS = lessReference("enonic/includes/nav-linklists.less");
    static final PackageResourceReference NAV_SCAFFOLDING_LESS = lessReference("enonic/includes/nav-scaffolding.less");

    static final PackageResourceReference NAV_SITEHEADER_LESS = lessReference("enonic/includes/nav-siteheader.less");
    static final PackageResourceReference NAV_SITEFOOTER_LESS = lessReference("enonic/includes/nav-sitefooter.less");
    static final PackageResourceReference NAV_SITEFOOTER_ALPHABET_LESS = lessReference("enonic/includes/nav-sitefooter-alphabet.less");

    static final PackageResourceReference NAV_ARTICLE_LESS = lessReference("enonic/includes/nav-article.less");
    static final PackageResourceReference NAV_FACTSHEET_LESS = lessReference("enonic/includes/nav-factsheet.less");
    static final PackageResourceReference NAV_CAROUSEL_LESS = lessReference("enonic/includes/nav-carousel.less");
    static final PackageResourceReference NAV_ACCORDION_LESS = lessReference("enonic/includes/nav-accordion.less");
    static final PackageResourceReference NAV_ERROR_LESS = lessReference("enonic/includes/nav-error.less");

    static final PackageResourceReference NAV_UTILITIES_LESS = lessReference("enonic/includes/nav-utilities.less");
    static final PackageResourceReference NAV_CONTRAST_LESS = lessReference("enonic/includes/nav-contrast.less");
    static final PackageResourceReference NAV_PRINT_LESS = lessReference("enonic/includes/nav-print.less");

    static final PackageResourceReference NAV_WEBFONTS_LESS = lessReference("enonic/includes/nav-webfonts.less");

    private static JavaScriptResourceReference jsReference(String path) {
        return new JavaScriptResourceReference(JsResourceMarker.class, path);
    }

    private static PackageResourceReference lessReference(String path) {
        return new PackageResourceReference(LessResourceMarker.class, path);
    }
}
