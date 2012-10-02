package no.nav.modig.frontend;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class FrontendConfigurator {

    private JquerySource jquerySource = JquerySource.FRONTEND;
    private String basePath = "/statisk";
    private JavaScriptResourceReference[] javaScriptResourceReferences = FrontendResources.JS_RESOURCES_ALL;


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


    public FrontendConfigurator withJavascriptResources(JavaScriptResourceReference... resources) {
        this.javaScriptResourceReferences = resources;
        return this;
    }


    public void configure(WebApplication application) {
        configureJquery(application);
        configureCss(application);
        configureJavascript(application);
    }


    private void configureCss(WebApplication application) {
        application.mountResource(basePath + "/css/modig.css", FrontendResources.CSS_RESOURCES);
        application.getHeaderContributorListenerCollection().add(new IHeaderContributor() {
            @Override
            public void renderHead(IHeaderResponse response) {
                response.render(CssReferenceHeaderItem.forReference(FrontendResources.CSS_RESOURCES));
            }
        });
    }


    private void configureJavascript(WebApplication application) {
        for (final JavaScriptResourceReference javaScriptResourceReference : javaScriptResourceReferences) {
            application.mountResource(basePath + "/js/" + javaScriptResourceReference.getName(), javaScriptResourceReference);
            application.getHeaderContributorListenerCollection().add(new IHeaderContributor() {
                @Override
                public void renderHead(IHeaderResponse response) {
                    response.render(JavaScriptReferenceHeaderItem.forReference(javaScriptResourceReference));
                }
            });
        }
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

