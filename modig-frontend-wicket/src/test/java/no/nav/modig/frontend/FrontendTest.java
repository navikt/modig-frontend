package no.nav.modig.frontend;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.mock.MockHomePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tester modig-frontend rammeverket
 */
public class FrontendTest {

    private WicketTester createTester(final InitListener initListener) {
        WebApplication application = new WebApplication() {
            @Override
            public Class<? extends Page> getHomePage() {
                return MockHomePage.class;
            }

            @Override
            protected void init() {
                getResourceSettings().setCachingStrategy(NoOpResourceCachingStrategy.INSTANCE);
                mountPage("/testpage", TestPage.class);
                initListener.onInit(this);
            }
        };
        return new WicketTester(application);
    }

    @Test
    public void addsFrontendScriptsAndStyling() throws Exception {
        WicketTester tester = createTester(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
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
        tester.startPage(page);
        tester.assertResultPage(TestPage.class, "TestPage-expected.html");
    }

    @Test
    public void mergesAllScriptsAndCss() throws Exception {
        WicketTester tester = createTester(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
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
        tester.startPage(page);
        tester.assertResultPage(TestPage.class, "TestPage-merged-expected.html");
    }

    @Test
    @Ignore // Broken test, avhengig av rekkefølgen på htmlattributter er korrekt
    public void addsMetaTags() throws Exception {
        WicketTester tester = createTester(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
                        .addMetas(MetaTag.VIEWPORT_SCALE_1)
                        .configure(application);
            }
        });
        tester.startPage(TestPage.class);
        tester.assertResultPage(TestPage.class, "TestPage-meta-expected.html");
    }

    @Test
    public void addsConditionalResources() throws Exception {
        WicketTester tester = createTester(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                new FrontendConfigurator()
                        .configure(application);
            }
        });
        tester.startPage(TestPage.class);
        tester.assertResultPage(TestPage.class, "TestPage-conditional-expected.html");
    }
}
