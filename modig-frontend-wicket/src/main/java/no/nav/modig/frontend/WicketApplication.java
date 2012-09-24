package no.nav.modig.frontend;

import org.apache.wicket.protocol.http.WebApplication;


public class WicketApplication extends WebApplication {

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
    }
}
