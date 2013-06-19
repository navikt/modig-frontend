package no.nav.modig.frontend;

import less.LessResourceMarker;
import org.apache.wicket.request.resource.PackageResourceReference;


class EksternflateResources {

	static final PackageResourceReference FOOTER_LESS = lessReference("ekstern/footer.less");
	static final PackageResourceReference PANEL_LESS = lessReference("ekstern/panel.less");
	static final PackageResourceReference EKSTERN_LESS = lessReference("ekstern/ekstern.less");

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
