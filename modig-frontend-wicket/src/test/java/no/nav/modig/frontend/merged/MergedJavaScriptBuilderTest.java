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
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.protocol.http.mock.MockHttpSession;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.util.time.Time;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


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

    @Test
    public void answersWith304IfNotChangedSince() throws Exception {
        WicketTester tester = new WicketTester(new MergedApp());

        MockHttpSession session = new MockHttpSession(tester.getApplication().getServletContext());
        MockHttpServletRequest request = new MockHttpServletRequest(
                tester.getApplication(),
                session,
                tester.getApplication().getServletContext());

        request.setURL("scripts/all.js");
        request.setHeader("If-Modified-Since", formatTime(Time.now().add(Duration.days(365))));

        tester.processRequest(request);

        assertThat(tester.getLastResponse().getStatus(), is(304));
    }

    private String formatTime(Time time) {
        return DateFormat.getDateInstance(DateFormat.FULL).format(time.getMilliseconds());
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
                .addWicketAjaxLibraries(new ArrayList<JavaScriptResourceReference>())
                .install(app);
    }

    @Test
    public void duplicateResourceRemoved() throws Exception {
        MergedJavaScriptBuilder b1 = new MergedJavaScriptBuilder();
        b1.setPath("/scripts/all.js");
        b1.addScript(MergedJavaScriptBuilderTestPage.JS_REFERENCE);
        b1.assertRequiredOptionsAndFreeze();

        MergedJavaScriptBuilder b2 = new MergedJavaScriptBuilder();
        b2.setPath("/scripts/all.js");
        b2.addScript(MergedJavaScriptBuilderTestPage.JS_REFERENCE);
        b2.addScript(MergedJavaScriptBuilderTestPage.JS_REFERENCE);
        b2.assertRequiredOptionsAndFreeze();

        Field field = MergedResourceBuilder.class.getDeclaredField("references");
        field.setAccessible(true);
        assertEquals(field.get(b1), field.get(b2));
    }
}
