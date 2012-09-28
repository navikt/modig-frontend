package no.nav.modig.frontend;

import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class FrontendKode {
    public static final CssResourceReference CSS_RESSURS = new CssResourceReference(FrontendKode.class, "css/modig.css");
    public static final JavaScriptResourceReference JS_RESSURS = new JavaScriptResourceReference(FrontendKode.class, "js/modig.js");
}
