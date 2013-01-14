package no.nav.modig.frontend;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;


public class MetaTag extends PriorityHeaderItem {

    public static final MetaTag CHARSET_UTF8 = new MetaTag("charset", "utf-8");
    public static final MetaTag VIEWPORT_SCALE_1 = new MetaTag.With()
            .attribute("name", "viewport")
            .attribute("content", "width=device-width, initial-scale=1")
            .done();
    public static final MetaTag XUA_IE_EDGE = new MetaTag.With()
            .attribute("http-equiv", "X-UA-Compatible")
            .attribute("content", "IE=edge")
            .done();


    /**
     * Construct.
     *
     * @param wrapped the actual {@link org.apache.wicket.markup.head.HeaderItem} that should have priority
     */
    private MetaTag(HeaderItem wrapped) {
        super(wrapped);
    }

    public MetaTag(String attrName, String attrValue) {
        super(new StringHeaderItem(format("<meta %s=\"%s\" />", attrName, attrValue)));
    }

    public static class With {

        private Map<String, String> attributes = new HashMap<>();

        public With attribute(String name, String value) {
            attributes.put(name, value);
            return this;
        }

        public MetaTag done() {
            StringBuilder builder = new StringBuilder();
            for (String name : attributes.keySet()) {
                builder
                        .append(name)
                        .append("=\"")
                        .append(attributes.get(name))
                        .append("\" ");
            }
            StringHeaderItem headerItem = new StringHeaderItem(format("<meta %s />", builder.toString()));
            return new MetaTag(headerItem);
        }
    }
}
