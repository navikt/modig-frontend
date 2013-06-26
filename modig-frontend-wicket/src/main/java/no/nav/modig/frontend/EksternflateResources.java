package no.nav.modig.frontend;

import css.CssResourceMarker;
import less.LessResourceMarker;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.PackageResourceReference;


class EksternflateResources {

	static final PackageResourceReference FOOTER_LESS = lessReference("ekstern/footer.less");
	static final PackageResourceReference PANEL_LESS = lessReference("ekstern/panel.less");
	static final PackageResourceReference EKSTERN_LESS = lessReference("ekstern/ekstern.less");
	static final CssResourceReference IE8_EKSTERN_CSS = cssReference("ie8-ekstern.css");


	private static CssResourceReference cssReference(String path) {
		return new CssResourceReference(CssResourceMarker.class, path);
	}

	private static PackageResourceReference lessReference(String path) {
		return new PackageResourceReference(LessResourceMarker.class, path);
	}
}
