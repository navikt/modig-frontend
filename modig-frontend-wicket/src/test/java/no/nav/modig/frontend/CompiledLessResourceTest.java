package no.nav.modig.frontend;

import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.protocol.http.mock.MockHttpSession;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link CompiledLessResource}
 */
public class CompiledLessResourceTest extends BaseWicketTest {


    public static final PackageResourceReference LESS_RESOURCE_1 = new PackageResourceReference(CompiledLessResourceTest.class, "less/file1.less");
    public static final PackageResourceReference LESS_RESOURCE_2 = new PackageResourceReference(CompiledLessResourceTest.class, "less/file2.less");

    @Test
    public void compilesLessFiles() {
        wicket(new InitListener() {
            @Override
            public void onInit(WebApplication application) {
                SecurePackageResourceGuard resourceGuard = (SecurePackageResourceGuard) application.getResourceSettings().getPackageResourceGuard();
                resourceGuard.addPattern("+*.less");

                application.getSharedResources().add("lessResource", new CompiledLessResource(asList(LESS_RESOURCE_1, LESS_RESOURCE_2)));
                application.mountResource("less/less.css", new SharedResourceReference("lessResource"));
            }
        });

        WebApplication application = tester.tester.getApplication();
        MockHttpSession session = new MockHttpSession(application.getServletContext());
        MockHttpServletRequest request = new MockHttpServletRequest(
                application,
                session,
                application.getServletContext());

        request.setURL("less/less.css");
        tester.tester.processRequest(request);
        String response = tester.tester.getLastResponse().getBinaryResponse();

        assertThat(response, is(".box-2 {\n  display: block;\n  width: 20;\n  height: 20;\n}\n"));
    }
}
