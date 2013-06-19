package no.nav.modig.frontend;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class TestPage extends WebPage {

    private static final long serialVersionUID = 1L;
    public static final CssResourceReference CSS_RESOURCE_REFERENCE = new CssResourceReference(TestPage.class, "test.css");
    public static final JavaScriptResourceReference JAVA_SCRIPT_RESOURCE_REFERENCE = new JavaScriptResourceReference(TestPage.class, "test.js");

    public TestPage(final PageParameters parameters) {
        super(parameters);
    }
}
