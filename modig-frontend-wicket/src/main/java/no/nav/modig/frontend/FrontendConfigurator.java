package no.nav.modig.frontend;

import org.apache.wicket.protocol.http.WebApplication;


public class FrontendConfigurator {

    private JquerySource jquerySource = JquerySource.FRONTEND;
    private String basePath = "/statisk";


    public enum JquerySource {
        WICKET, FRONTEND, NONE
    }


    public FrontendConfigurator withJquerySource(JquerySource jquerySource) {
        this.jquerySource = jquerySource;
        return this;
    }


    public FrontendConfigurator withBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }


    public void configure(WebApplication application) {
        configureJquery(application);
        application.mountResource(basePath + "/css/modig.css", FrontendResources.CSS_RESOURCES);
        application.mountResource(basePath + "/js/modig.js", FrontendResources.JS_RESOURCES);
    }


    private void configureJquery(WebApplication application) {

        switch (jquerySource) {
            case FRONTEND:
                application.getJavaScriptLibrarySettings().setJQueryReference(FrontendResources.JQUERY_RESOURCE);
                application.mountResource(basePath + "/js/jquery/jquery.js", FrontendResources.JQUERY_RESOURCE);
                break;
            case NONE:
                application.getJavaScriptLibrarySettings().setJQueryReference(null);
                break;
            case WICKET:
                break;
        }
    }
}

