package no.nav.modig.frontend;

import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;


public class ConditionalCssResource extends CssReferenceHeaderItem {

    /**
     * Creates a new {@code CSSReferenceHeaderItem}.
     *
     * @param reference      resource reference pointing to the CSS resource
     * @param pageParameters the parameters for this CSS resource reference
     * @param media          the media type for this CSS ("print", "screen", etc.)
     * @param condition      the condition to use for Internet Explorer conditional comments. E.g. "IE 7".
     */
    public ConditionalCssResource(ResourceReference reference, PageParameters pageParameters, String media, String condition) {
        super(reference, pageParameters, media, condition);
    }

    public ConditionalCssResource(ResourceReference reference, String media, String condition) {
        this(reference, null, media, condition);
    }
}
