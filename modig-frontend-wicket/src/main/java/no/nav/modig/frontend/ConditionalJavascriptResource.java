package no.nav.modig.frontend;

import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;


public class ConditionalJavascriptResource extends JavaScriptReferenceHeaderItem {

    public static final PackageResourceReference HTML_5_SHIV_RESOURCE = FellesResources.HTML5_SHIV_RESOURCE;
    protected static final ConditionalJavascriptResource HTML5_SHIV = new ConditionalJavascriptResource(HTML_5_SHIV_RESOURCE, "lt IE 9");

    /**
     * Creates a new {@code JavaScriptReferenceHeaderItem}.
     *
     * @param reference      resource reference pointing to the javascript resource
     * @param pageParameters the parameters for this Javascript resource reference
     * @param id             id that will be used to filter duplicate reference (it's still filtered by URL
     *                       too)
     * @param defer          specifies that the execution of a script should be deferred (delayed) until after
     *                       the page has been loaded.
     * @param charset        a non null value specifies the charset attribute of the script tag
     * @param condition      the condition to use for Internet Explorer conditional comments. E.g. "IE 7".
     */
    public ConditionalJavascriptResource(ResourceReference reference, PageParameters pageParameters, String id, boolean defer, String charset, String condition) {
        super(reference, pageParameters, id, defer, charset, condition);
    }

    public ConditionalJavascriptResource(ResourceReference reference, String condition) {
        this(reference, null, null, false, "UTF-8", condition);
    }
}
