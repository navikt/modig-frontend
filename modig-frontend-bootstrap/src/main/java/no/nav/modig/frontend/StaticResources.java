package no.nav.modig.frontend;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class StaticResources {
    public static final CssResourceReference CSS_RESOURCE = new CssResourceReference(StaticResources.class, "css/modig.css");
    public static final JavaScriptResourceReference JS_RESOURCE = new JavaScriptResourceReference(StaticResources.class, "js/modig.js");
}
