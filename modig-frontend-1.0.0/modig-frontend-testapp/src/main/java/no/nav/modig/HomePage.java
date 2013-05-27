package no.nav.modig;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;

import static no.nav.modig.wicket.resources.ResourceUtils.cssReferences;
import static no.nav.modig.wicket.resources.ResourceUtils.jsReferences;


public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;
    public static final JavaScriptResourceReference JS_RESOURCE = new JavaScriptResourceReference(HomePage.class, "HomePage.js");
    public static final CssResourceReference CSS_RESOURCE = new CssResourceReference(HomePage.class, "style.css");
    public static final PackageResourceReference LESS_RESOURCE = new PackageResourceReference(HomePage.class, "style.less");

    public HomePage(final PageParameters parameters) {

        super(parameters);

        // ADD BEHAVIORS
        add(
                jsReferences(JS_RESOURCE),
                cssReferences(CSS_RESOURCE)
        );

        // ADD COMPONENTS
        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
    }
}
