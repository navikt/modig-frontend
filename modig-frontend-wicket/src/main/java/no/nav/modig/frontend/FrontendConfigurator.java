package no.nav.modig.frontend;

import org.apache.wicket.protocol.http.WebApplication;


public class FrontendConfigurator {

    private JquerySource jquerySource;

    public enum JquerySource {
        WICKET, FRONTEND, NONE
    }

    public void configure(WebApplication application) {

        configureJquery(application);

        application.mountResource("/statisk/css/modig.css", FrontendResources.CSS_RESOURCES);
        application.mountResource("/statisk/js/modig.js", FrontendResources.JS_RESOURCES);

    }

    private void configureJquery(WebApplication application) {
        switch (jquerySource) {
            case FRONTEND:
                break;
            case NONE:
                application.getJavaScriptLibrarySettings().setJQueryReference(null);
                break;
            case WICKET:
                break;
        }
    }
}

