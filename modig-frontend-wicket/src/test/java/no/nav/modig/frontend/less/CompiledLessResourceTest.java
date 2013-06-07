package no.nav.modig.frontend.less;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.ThreadContext;
import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.mock.MockHomePage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.protocol.http.mock.MockHttpServletResponse;
import org.apache.wicket.protocol.http.mock.MockHttpSession;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.SharedResourceReference;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for {@link no.nav.modig.frontend.less.CompiledLessResource}
 */
public class CompiledLessResourceTest {

    public static final PackageResourceReference LESS_RESOURCE_1 = new PackageResourceReference(CompiledLessResourceTest.class, "file1.less");
    public static final PackageResourceReference LESS_RESOURCE_2 = new PackageResourceReference(CompiledLessResourceTest.class, "file2.less");

    private WicketTester tester;

    @Test
    public void compilesLessFiles() {
        createApplication();

        MockHttpServletRequest request = createMockRequest();

        request.setURL("less/less.css");
        tester.processRequest(request);
        String response = tester.getLastResponse().getBinaryResponse();

        assertThat(response, is(".box-2 {\n  display: block;\n  width: 20;\n  height: 20;\n}\n"));
    }

    @Test
    public void reusesTheCompiledResultIfResourcesAreNotModified() throws InterruptedException {
        createApplication();
        CompiledUnit.resetVersion();

        // Wicket tester is not thread safe
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                        ThreadContext.setApplication(tester.getApplication());
                        MockHttpServletRequest request = createMockRequest();
                        request.setURL("less/less.css");
                        tester.processRequest(request);

                        MockHttpServletResponse lastResponse = tester.getLastResponse();
                        assertThat(lastResponse.getStatus(), is(200));
                        assertThat(lastResponse.getBinaryResponse(), is(".box-2 {\n  display: block;\n  width: 20;\n  height: 20;\n}\n"));
                        ThreadContext.detach();

                }
            });
        }


        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);

        CompiledLessResource lessResource = (CompiledLessResource) tester.getApplication()
                .getSharedResources()
                .get(Application.class, "lessResource", null, null, null, true)
                .getResource();
        assertThat(lessResource.getCompileCount(), is(1));

    }

    private void createApplication() {
        WebApplication webApplication = new WebApplication() {
            @Override
            public Class<? extends Page> getHomePage() {
                return MockHomePage.class;
            }

            @Override
            protected void init() {
                SecurePackageResourceGuard resourceGuard = (SecurePackageResourceGuard) this.getResourceSettings().getPackageResourceGuard();
                resourceGuard.addPattern("+*.less");

                this.getSharedResources().add("lessResource", new CompiledLessResource(asList(LESS_RESOURCE_1, LESS_RESOURCE_2)));
                this.mountResource("less/less.css", new SharedResourceReference("lessResource"));
            }
        };
        tester = new WicketTester(webApplication);
    }

    private MockHttpServletRequest createMockRequest() {
        WebApplication application = tester.getApplication();
        MockHttpSession session = new MockHttpSession(application.getServletContext());
        return new MockHttpServletRequest(
                application,
                session,
                application.getServletContext());
    }
}
