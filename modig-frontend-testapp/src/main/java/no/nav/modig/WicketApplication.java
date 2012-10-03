package no.nav.modig;

import no.nav.modig.frontend.FrontendConfigurator;
import org.apache.wicket.protocol.http.WebApplication;


public class WicketApplication extends WebApplication {

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {

        super.init();

        // MOUNTE PATH TIL CSS OG JAVASCRIPT
        new FrontendConfigurator().configure(this);
    }
}
