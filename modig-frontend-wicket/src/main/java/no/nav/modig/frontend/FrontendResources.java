package no.nav.modig.frontend;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class FrontendResources {
    public static final CssResourceReference CSS_RESOURCES = new CssResourceReference(FrontendResources.class, "css/modig.css");
    public static final JavaScriptResourceReference JS_RESOURCES = new JavaScriptResourceReference(FrontendResources.class, "js/modig.js");
    public static final JavaScriptResourceReference JQUERY_RESOURCE = new JavaScriptResourceReference(FrontendResources.class, "js/jquery/jquery-1.8.2.js");
}
