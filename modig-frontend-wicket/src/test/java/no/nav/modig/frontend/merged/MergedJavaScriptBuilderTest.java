/**
 * Copyright 2012 55 Minutes (http://www.55minutes.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.nav.modig.frontend.merged;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;


public class MergedJavaScriptBuilderTest extends MergedResourceBuilderTest {
    /**
     * Verify that the test page renders as expected (i.e. with merged resource
     * href and src attributes).
     */
    @Test
    public void testRender() throws Exception {
        WicketTester tester = doRender(MergedJavaScriptBuilderTestPage.class);
        tester.assertResultPage(
                MergedJavaScriptBuilderTestPage.class,
                "MergedJavaScriptBuilderTestPage-expected.html"
        );
    }

    /**
     * Verify that merged resources can be successfully downloaded.
     */
    @Test
    public void testMergedResourcesCanBeDownloaded() throws Exception {
        WicketTester tester = doRender(MergedJavaScriptBuilderTestPage.class);
        assertDownloaded(
                tester,
                "scripts/all.js",
                "/no/nav/modig/frontend/merged/js/test.js",
                "/org/apache/wicket/ajax/res/js/wicket-event-jquery.js",
                "/org/apache/wicket/ajax/res/js/wicket-ajax-jquery.js");
    }

    /**
     * Verify that an exception is thrown if we execute install() without
     * specifying a path first.
     */
    @Test(expected = IllegalStateException.class)
    public void testMissingPathThrowsException() {
        MergedJavaScriptBuilder b = new MergedJavaScriptBuilder();
//        b.addLibrary("jquery");
        b.install(new WicketTester().getApplication());
    }

    /**
     * Verify that an exception is thrown if we execute install() without
     * specifying a resource first.
     */
    @Test(expected = IllegalStateException.class)
    public void testMissingResourceThrowsException() {
        MergedJavaScriptBuilder b = new MergedJavaScriptBuilder();
        b.setPath("/scripts/all.js");
        b.install(new WicketTester().getApplication());
    }

    protected void onAppInit(WebApplication app) {
        new MergedJavaScriptBuilder().setPath("/scripts/all.js")
                .addScript(MergedJavaScriptBuilderTestPage.JS_REFERENCE)
                .addWicketAjaxLibraries()
                .install(app);
    }
}
