package no.nav.modig;

import no.nav.modig.frontend.FrontendConfigurator;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.file.Path;
import org.apache.wicket.util.time.Duration;


public class WicketApplication extends WebApplication {

    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {

        super.init();

        String htmlDir = getServletContext().getRealPath("/");
        if (htmlDir != null && !htmlDir.endsWith("/")) {
            htmlDir += "/";
        }
        getResourceSettings().getResourceFinders().add(new Path(htmlDir + "../../../../modig-frontend-bootstrap/src/main/resources"));
        getResourceSettings().getResourceFinders().add(new Path(htmlDir + "../../../../modig-frontend-bootstrap/src/main/generated-resources"));
        getResourceSettings().getResourceFinders().add(new Path(htmlDir + "../resources"));
        getResourceSettings().getResourceFinders().add(new Path(htmlDir + "../java"));
        getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);

        // MOUNTE PATH TIL CSS OG JAVASCRIPT
        new FrontendConfigurator()
                .addScript(HomePage.JS_RESOURCE)
                .addCss(HomePage.CSS_RESOURCE)
//                .withResourcePacking(this.usesDeploymentConfig())
                .configure(this);

    }
}
