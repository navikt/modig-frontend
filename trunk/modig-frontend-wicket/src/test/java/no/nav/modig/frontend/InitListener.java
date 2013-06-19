package no.nav.modig.frontend;

import org.apache.wicket.protocol.http.WebApplication;

/**
*
*/
interface InitListener {
    void onInit(WebApplication application);
}
