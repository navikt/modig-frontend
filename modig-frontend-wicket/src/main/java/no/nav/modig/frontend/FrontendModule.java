package no.nav.modig.frontend;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;

public class FrontendModule {

    private  JavaScriptResourceReference[] scripts;
    private CssResourceReference[] stylesheets;
    private SharedResourceReference[] images;

    public JavaScriptResourceReference[] getScripts(){
        return ArrayUtils.clone(scripts);
    }

    public CssResourceReference[] getStylesheets(){
        return ArrayUtils.clone(stylesheets);
    }

    public SharedResourceReference[] getImages() {
        return ArrayUtils.clone(images);
    }


    public static class With {

        private FrontendModule module = new FrontendModule();

        public With scripts(JavaScriptResourceReference... references) {
            module.scripts = references;
            return this;
        }

        public With stylesheets(CssResourceReference... references) {
            module.stylesheets = references;
            return this;
        }

        public With images(SharedResourceReference... references) {
            module.images = references;
            return this;
        }

        public FrontendModule done() {
            return module;
        }
    }
}
