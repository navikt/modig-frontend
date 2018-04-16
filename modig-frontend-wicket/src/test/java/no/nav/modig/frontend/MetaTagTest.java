package no.nav.modig.frontend;

import org.apache.wicket.Page;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;


public class MetaTagTest {

    private WicketTester tester;
    private WebApplication application;

    @Before
    public void setUp() {
        application = new WebApplication() {
            @Override
            public Class<? extends Page> getHomePage() {
                return HomePage.class;
            }
        };
        tester = new WicketTester(application);
    }

    @Test
    public void returnsMetaTagString() {
        application.getHeaderContributorListenerCollection()
                .add(new IHeaderContributor() {
                    @Override
                    public void renderHead(IHeaderResponse response) {
                        response.render(MetaTag.VIEWPORT_SCALE_1);
                    }
                });

        tester.startPage(HomePage.class);
        tester.assertContains("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"  />");
    }
}