package no.nav.modig.frontend;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.junit.Test;

/**
 * Tester modig-frontend rammeverket
 */
public class FrontendTest extends BaseWicketTest{

    @Test
    public void addsFrontendScriptsAndStyling() throws Exception {
        tester = wicket(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
                        .withModules(FrontendModules.BOOTSTRAP_CORE)
                        .addCss(TestPage.CSS_RESOURCE_REFERENCE)
                        .addScripts(TestPage.JAVA_SCRIPT_RESOURCE_REFERENCE)
                        .withResourcePacking(false)
                        .configure(application);
            }
        });
        TestPage page = new TestPage(new PageParameters());
        page.add(new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                response.render(CssHeaderItem.forReference(TestPage.CSS_RESOURCE_REFERENCE));
                response.render(JavaScriptHeaderItem.forReference(TestPage.JAVA_SCRIPT_RESOURCE_REFERENCE));
            }
        });

        tester.goTo(page);
        tester.tester.assertResultPage(TestPage.class, "TestPage-expected.html");
    }

    @Test
    public void mergesAllScriptsAndCss() throws Exception {
        tester = wicket(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
                        .withModules(FrontendModules.BOOTSTRAP_CORE)
                        .addCss(TestPage.CSS_RESOURCE_REFERENCE)
                        .addScripts(TestPage.JAVA_SCRIPT_RESOURCE_REFERENCE)
                        .withResourcePacking(true)
                        .configure(application);
            }
        });
        TestPage page = new TestPage(new PageParameters());
        page.add(new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                response.render(CssHeaderItem.forReference(TestPage.CSS_RESOURCE_REFERENCE));
                response.render(JavaScriptHeaderItem.forReference(TestPage.JAVA_SCRIPT_RESOURCE_REFERENCE));
            }
        });
        tester.goTo(page);
        tester.tester.assertResultPage(TestPage.class, "TestPage-merged-expected.html");
    }

    @Test
    public void addsMetaTags() throws Exception {
        tester = wicket(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
                        .addMetas(MetaTag.VIEWPORT_SCALE_1)
                        .configure(application);
            }
        });

        tester.goTo(TestPage.class);
        tester.tester.assertResultPage(TestPage.class, "TestPage-meta-expected.html");
    }

    @Test
    public void addsConditionalResources() throws Exception {
        tester = wicket(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
                        .addConditionalCss(new ConditionalCssResource(TestPage.CSS_RESOURCE_REFERENCE, "", "lt IE 8"))
                        .addConditionalJavascript(new ConditionalJavascriptResource(TestPage.JAVA_SCRIPT_RESOURCE_REFERENCE, "lt IE 8"))
                        .configure(application);
            }
        });

        tester.goTo(TestPage.class);
        tester.tester.assertResultPage(TestPage.class, "TestPage-conditional-expected.html");
    }

}
