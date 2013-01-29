package no.nav.modig.frontend;

import no.nav.modig.wicket.test.FluentWicketTester;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Test;

/**
 * Tester modig-frontend rammeverket
 */
public class FrontendTest {

    private FluentWicketTester<WebApplication> tester;
    private boolean merge = false;

    @Test
    public void addsFrontendScriptsAndStyling() throws Exception {
        wicket().goTo(TestPage.class);
        wicket().tester.assertResultPage(TestPage.class, "TestPage-expected.html");
    }

    @Test
    public void mergesAllScriptsAndCss() throws Exception {
        merge = true;

        wicket().goTo(TestPage.class);
        wicket().tester.assertResultPage(TestPage.class, "TestPage-merged-expected.html");
    }

    private FluentWicketTester<WebApplication> wicket() {
        if (tester == null) {
            tester = new FluentWicketTester<WebApplication>(new TestApplication());
        }
        return tester;
    }

    private class TestApplication extends WebApplication {
        @Override
        protected void init() {
            new FrontendConfigurator()
                    .withModules(FrontendModules.ALL)
                    .addCss(TestPage.CSS_RESOURCE_REFERENCE)
                    .addScripts(TestPage.JAVA_SCRIPT_RESOURCE_REFERENCE)
                    .withResourcePacking(merge)
                    .configure(this);
        }

        @Override
        public Class<? extends Page> getHomePage() {
            return null;
        }
    }
}
