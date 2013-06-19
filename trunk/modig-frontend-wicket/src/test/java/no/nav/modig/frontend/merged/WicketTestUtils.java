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

import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.protocol.http.mock.MockHttpSession;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;


/**
 * Helper functions and assertions for easier testing of Wicket pages
 * and components. Care has been taken to ensure that these helpers
 * automatically work with both XHTML and HTML5 documents. In some cases
 * there are separate methods to handle the two cases.
 */
public abstract class WicketTestUtils
{

    /**
     * Download the requested resource and assert that the binary contents of that
     * resource match the provided byte array.
     *
     * @param tester The WicketTester that was used to render the page being tested
     * @param resourceUri A path to a resource to download, like {@code wicket/resource/...}
     *                    (note the lack of a leading slash)
     * @param expectedBytes The expected binary contents of that resource
     *
     * @since 3.2
     */
     public static void assertDownloadEquals(WicketTester tester,
                                             String resourceUri,
                                             byte[] expectedBytes)
     {
         MockHttpSession session = new MockHttpSession(tester.getApplication().getServletContext());
         MockHttpServletRequest request = new MockHttpServletRequest(
             tester.getApplication(),
             session,
             tester.getApplication().getServletContext());
         
         request.setURL(resourceUri);
         tester.processRequest(request);
        
         byte[] actual = tester.getLastResponse().getBinaryContent();
         Assert.assertArrayEquals(expectedBytes, actual);
    }

}
