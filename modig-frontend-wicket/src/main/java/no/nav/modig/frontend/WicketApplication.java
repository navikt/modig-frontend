package no.nav.modig.frontend;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class WicketApplication extends WebApplication {

    public static final CssResourceReference CSS_RESOURCE = new CssResourceReference(StaticResources.class, "no/nav/modig/frontend/css/modig.css");
    public static final JavaScriptResourceReference JS_RESOURCE = new JavaScriptResourceReference(StaticResources.class, "js/modig.js");

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {

        super.init();

        mountResource("/statisk/css/modig.css", CSS_RESOURCE);
        mountResource("/statisk/js/modig.js", JS_RESOURCE);
    }
}
