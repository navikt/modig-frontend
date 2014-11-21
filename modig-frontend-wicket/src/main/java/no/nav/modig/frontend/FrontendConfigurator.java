package no.nav.modig.frontend;

import no.nav.modig.frontend.compressors.Wro4jCssCompressor;
import no.nav.modig.frontend.compressors.Wro4jJsCompressor;
import no.nav.modig.frontend.less.CompiledLessResource;
import no.nav.modig.frontend.merged.MergedCssBuilder;
import no.nav.modig.frontend.merged.MergedJavaScriptBuilder;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;


public class FrontendConfigurator {

    public enum JquerySource {
        WICKET, FRONTEND, NONE
    }

    private JquerySource jquerySource = JquerySource.FRONTEND;
    private String basePath = "";

    private String jsConcatFile = "all.js";
    private String cssConcatFile = "all.css";
    private String lessCompiledFile = "less.css";


    // TODO: IMPLEMENTERE Å BRUKE SEPARATE BOOTSTRAP-KOMPONENTER - FORVENTET I BOOTSTRAP 3
    private List<JavaScriptResourceReference> jsReferences = new ArrayList<>();
    private List<JavaScriptResourceReference> wicketReferences = new ArrayList<>();
    private List<CssResourceReference> cssReferences = new ArrayList<>();
    private List<PackageResourceReference> lessReferences = new ArrayList<>();
    private List<CssResourceReference> priorityCss = new ArrayList<>();
    private List<SharedResourceReference> imgReferences = new ArrayList<>();

    private List<MetaTag> metaTagsList = new ArrayList<>();
    private List<FrontendModule> modules = new ArrayList<>();

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


    public FrontendConfigurator withModules(FrontendModule... frontendModules) {
        this.modules.addAll(asList(frontendModules));
        return this;
    }

    public FrontendConfigurator addMetas(MetaTag... metaTags) {
        metaTagsList.addAll(asList(metaTags));
        return this;
    }


    public FrontendConfigurator addScripts(JavaScriptResourceReference... resources) {
        jsReferences.addAll(asList(resources));
        return this;
    }


    public FrontendConfigurator addWicketScripts(JavaScriptResourceReference... resources) {
        wicketReferences.addAll(asList(resources));
        return this;
    }


    public FrontendConfigurator addCss(CssResourceReference... resources) {
        cssReferences.addAll(asList(resources));
        return this;
    }

    public FrontendConfigurator addLess(PackageResourceReference... resources) {
        lessReferences.addAll(asList(resources));
        return this;
    }


    public FrontendConfigurator addPriorityCss(CssResourceReference... resources) {
        priorityCss.addAll(asList(resources));
        return this;
    }


    public FrontendConfigurator addImg(SharedResourceReference... resources) {
        imgReferences.addAll(asList(resources));
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
        addModules();
        configureMeta(application);
        configurePriorityCss(application);
        configureLess(application);
        configureCss(application);
        configureJquery(application);
        configureJavascript(application);
        configureImages(application);
        configureResourcePacking(application);
    }

    private void addModules() {
        // Adding modules in reversed sequence so that the final sequence is correct
        ArrayList<FrontendModule> reversedModules = new ArrayList<>(modules);
        Collections.reverse(reversedModules);
        for (FrontendModule module : reversedModules) {
            jsReferences.addAll(0, asList(module.getScripts()));
            cssReferences.addAll(0, asList(module.getStylesheets()));
            imgReferences.addAll(0, asList(module.getImages()));
            lessReferences.addAll(0, asList(module.getLess()));
        }
    }


    private void configureResourcePacking(WebApplication application) {
        if (packResources) {

            application.getResourceSettings().setJavaScriptCompressor(new Wro4jJsCompressor());
            application.getResourceSettings().setCssCompressor(new Wro4jCssCompressor());
            if (!jsReferences.isEmpty()) {
                scriptBuilder
                        .setPath(basePath + "/js/" + jsConcatFile)
                        .addWicketAjaxLibraries(wicketReferences)
                        .install(application);
            }

            if (!cssReferences.isEmpty()) {
                cssBuilder
                        .setPath(basePath + "/css/" + cssConcatFile)
                        .install(application);
            }
        }
    }


    private void configureMeta(WebApplication application) {
        for (final MetaTag metaTag : metaTagsList) {
            application.getHeaderContributorListenerCollection().add(new IHeaderContributor() {
                @Override
                public void renderHead(IHeaderResponse response) {
                    response.render(metaTag);
                }
            });
        }
    }


    private void configureLess(WebApplication application) {
        if (lessReferences.isEmpty()) {
            return;
        }

        // Allowing less files to be loaded as resources
        SecurePackageResourceGuard resourceGuard = (SecurePackageResourceGuard) application.getResourceSettings().getPackageResourceGuard();
        resourceGuard.addPattern("+*.less");

        final CompiledLessResource compiledLessResource = new CompiledLessResource(lessReferences);
        application.getSharedResources().add("lessResource", compiledLessResource);

        final ResourceReference resRef = new SharedResourceReference("lessResource");
        application.mountResource(basePath + "/css/" + lessCompiledFile, resRef);

        // Adding reference to less file to all pages
        application.getHeaderContributorListenerCollection().add(new IHeaderContributor() {
            @Override
            public void renderHead(IHeaderResponse response) {
                response.render(CssReferenceHeaderItem.forReference(resRef));
            }
        });

        if (packResources) {
            cssBuilder.addCss(resRef);
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


    private void configurePriorityCss(WebApplication application) {
        for (final CssResourceReference reference : priorityCss) {
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


    private void configureImages(WebApplication application) {
        for (final SharedResourceReference reference : imgReferences) {
            application.mountResource(basePath + "/img/" + reference.getName(), reference);
        }
    }


    private void configureJquery(WebApplication application) {
        // Never use fiftyfive-wicket jquery
//        JavaScriptDependencySettings.get().setJQueryResource(null);

        switch (jquerySource) {
            case FRONTEND:
                JavaScriptResourceReference jqueryResource = NavFrontendResources.JQUERY_RESOURCE;
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

