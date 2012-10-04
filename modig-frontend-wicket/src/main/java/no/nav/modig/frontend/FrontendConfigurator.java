package no.nav.modig.frontend;

import fiftyfive.wicket.css.MergedCssBuilder;
import fiftyfive.wicket.js.JavaScriptDependencySettings;
import fiftyfive.wicket.js.MergedJavaScriptBuilder;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class FrontendConfigurator {

    public enum JquerySource {
        WICKET, FRONTEND, NONE
    }

    private JquerySource jquerySource = JquerySource.FRONTEND;
    private String basePath = "/statisk";

    private String jsConcatFile = "all.js";
    private String cssConcatFile = "all.css";

    private List<JavaScriptResourceReference> jsReferences = new ArrayList<>(asList(FrontendResources.JS_RESOURCES_ALL));
    private List<CssResourceReference> cssReferences = new ArrayList<>(asList(FrontendResources.CSS_RESOURCES));

    private MergedJavaScriptBuilder scriptBuilder = new MergedJavaScriptBuilder();
    private MergedCssBuilder cssBuilder = new MergedCssBuilder();

    private boolean packResources = true;


    public FrontendConfigurator withResourcePacking(boolean packResources) {
        this.packResources = packResources;
        return this;
    }

    public FrontendConfigurator withResourcePacking(boolean packResources, String jsConcatFile, String cssConcatFile) {
        this.packResources = packResources;
        this.jsConcatFile = jsConcatFile;
        this.cssConcatFile = cssConcatFile;
        return this;
    }

    public FrontendConfigurator addScript(JavaScriptResourceReference resource) {
        jsReferences.add(resource);
        return this;
    }


    public FrontendConfigurator addCss(CssResourceReference resource) {
        cssReferences.add(resource);
        return this;
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
        configureCss(application);
        configureJavascript(application);
        configureResourcePacking(application);
    }


    private void configureResourcePacking(WebApplication application) {
        if (packResources) {

            application.getResourceSettings().setJavaScriptCompressor(new YuiJsCompressor());
            application.getResourceSettings().setCssCompressor(new YuiCssCompressor());

            scriptBuilder
                    .setPath(basePath + "/js/" + jsConcatFile)
                    .addWicketAjaxLibraries()
                    .install(application);

            cssBuilder
                    .setPath(basePath + "/css/" + cssConcatFile)
                    .install(application);
        }
    }


    private void configureCss(WebApplication application) {
        for (final CssResourceReference reference : cssReferences) {
            application.mountResource(basePath + "/css/" + reference.getName(), reference);
            application.getHeaderContributorListenerCollection().add(new IHeaderContributor() {
                @Override
                public void renderHead(IHeaderResponse response) {
                    response.render(CssReferenceHeaderItem.forReference(reference));
                }
            });

            if (packResources) {
                cssBuilder.addCss(reference);
            }
        }
    }


    private void configureJavascript(WebApplication application) {
        for (final JavaScriptResourceReference reference : jsReferences) {
            application.mountResource(basePath + "/js/" + reference.getName(), reference);
            application.getHeaderContributorListenerCollection().add(new IHeaderContributor() {
                @Override
                public void renderHead(IHeaderResponse response) {
                    response.render(JavaScriptReferenceHeaderItem.forReference(reference));
                }
            });

            if (packResources) {
                scriptBuilder.addScript(reference);
            }
        }
    }


    private void configureJquery(WebApplication application) {
        // Never use fiftyfive-wicket jquery
        JavaScriptDependencySettings.get().setJQueryResource(null);

        switch (jquerySource) {
            case FRONTEND:
                JavaScriptResourceReference jqueryResource = FrontendResources.JQUERY_RESOURCE;
                application.getJavaScriptLibrarySettings().setJQueryReference(jqueryResource);
                application.mountResource(basePath + "/js/" + jqueryResource.getName(), jqueryResource);
                scriptBuilder.addScript(jqueryResource);
                break;
            case NONE:
                application.getJavaScriptLibrarySettings().setJQueryReference(null);
                break;
            case WICKET:
                application.getJavaScriptLibrarySettings().setJQueryReference(JQueryResourceReference.get());
                scriptBuilder.addScript(JQueryResourceReference.get());
                break;
        }
    }
}

