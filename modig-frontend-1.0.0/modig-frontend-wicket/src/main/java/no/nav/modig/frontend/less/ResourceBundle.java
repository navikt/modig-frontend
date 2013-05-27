package no.nav.modig.frontend.less;

import org.apache.commons.io.IOUtils;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.lang.Checks;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.util.time.Time;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Bundle of {@link PackageResourceReference}.
 *
 * Used internally by {@link CompiledLessResource}
 *
 * NOT A PART OF THE PUBLIC API
 */
class ResourceBundle {
    private final List<PackageResourceReference> references;

    ResourceBundle(List<PackageResourceReference> references) {
        this.references = references;
    }

    public Time getLastModified() {
        Time lastModifiedTime = Time.START_OF_UNIX_TIME;
        for (PackageResourceReference reference : references) {
            Time resourceLastModifiedTime = getResourceStream(reference).lastModifiedTime();
            if (resourceLastModifiedTime.after(lastModifiedTime)) {
                lastModifiedTime = resourceLastModifiedTime;
            }
        }
        return lastModifiedTime;
    }

    public String getResourceString() {
        try {
            StringWriter stringWriter = new StringWriter();
            for (PackageResourceReference lessReference : references) {
                IResourceStream resourceStream = getResourceStream(lessReference);
                IOUtils.copy(resourceStream.getInputStream(), stringWriter, "utf-8");
            }
            return stringWriter.toString();
        } catch (IOException | ResourceStreamNotFoundException e) {
            throw new WicketRuntimeException("Could not concatenate resources", e);
        }
    }


    private static IResourceStream getResourceStream(PackageResourceReference lessReference) {
        IResourceStream resourceStream = lessReference.getResource().getResourceStream();
        Checks.notNull(resourceStream, "Unable to find resource stream for resource reference [{}]", lessReference);
        return resourceStream;
    }
}
