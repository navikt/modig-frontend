package no.nav.modig.frontend;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class WicketApplication extends WebApplication {

    public static final CssResourceReference CSS_RESOURCE = new CssResourceReference(WicketApplication.class, "modig.css");
    public static final JavaScriptResourceReference JS_RESOURCE = new JavaScriptResourceReference(WicketApplication.class, "modig.js");

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {

        super.init();

        mountResource("/statisk/js/modig.css", CSS_RESOURCE);
        mountResource("/statisk/css/modig.css", JS_RESOURCE);
    }
}
