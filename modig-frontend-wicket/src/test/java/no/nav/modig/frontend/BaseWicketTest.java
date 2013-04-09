package no.nav.modig.frontend;

import no.nav.modig.wicket.test.FluentWicketTester;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.resource.caching.NoOpResourceCachingStrategy;

/**
 * Base class for wicket tests
 */
public class BaseWicketTest {
    protected FluentWicketTester<WebApplication> tester;

    protected FluentWicketTester<WebApplication> wicket(InitListener initListener) {
        if(tester == null) {
            tester =  new FluentWicketTester<WebApplication>(new TestApplication(initListener));
        }
        return tester;
    }

    protected interface InitListener {
        void onInit(WebApplication application);
    }

    private class TestApplication extends WebApplication {
        private InitListener initListener;

        private TestApplication(InitListener initListener) {
            this.initListener = initListener;
        }

        @Override
        protected void init() {

            // Slår av cache-strategien slik at ressursene ikke får versjonerte url-er.
            getResourceSettings().setCachingStrategy(NoOpResourceCachingStrategy.INSTANCE);

            mountPage("/testpage", TestPage.class);
            initListener.onInit(this);
        }

        @Override
        public Class<? extends Page> getHomePage() {
            return null;
        }
    }
}
