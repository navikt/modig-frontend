package no.nav.modig.frontend;

import css.CssResourceMarker;
import js.JsResourceMarker;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

import static java.util.Arrays.asList;


public class FrontendResources {
    public static final CssResourceReference CSS_RESOURCES = new CssResourceReference(CssResourceMarker.class, "modig.css");
    public static final JavaScriptResourceReference JQUERY_RESOURCE = new JavaScriptResourceReference(JsResourceMarker.class, "jquery/jquery-1.8.2.js");

    public static final JavaScriptResourceReference[] JS_RESOURCES_ALL = {new JavaScriptResourceReference(JsResourceMarker.class, "modig.js") {
        @Override
        public Iterable<? extends HeaderItem> getDependencies() {
            return asList(JavaScriptHeaderItem.forReference(JQUERY_RESOURCE));
        }
    }};

}
