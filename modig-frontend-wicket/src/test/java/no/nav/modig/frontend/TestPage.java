package no.nav.modig.frontend;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.internal.HtmlHeaderContainer;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;


public class TestPage extends WebPage {

    private static final long serialVersionUID = 1L;
    public static final CssResourceReference CSS_RESOURCE_REFERENCE = new CssResourceReference(TestPage.class, "test.css");
    public static final JavaScriptResourceReference JAVA_SCRIPT_RESOURCE_REFERENCE = new JavaScriptResourceReference(TestPage.class, "test.js");

    public TestPage(final PageParameters parameters) {
        super(parameters);

        add(new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                response.render(CssHeaderItem.forReference(CSS_RESOURCE_REFERENCE));
                response.render(JavaScriptHeaderItem.forReference(JAVA_SCRIPT_RESOURCE_REFERENCE));
            }
        });
    }


}
