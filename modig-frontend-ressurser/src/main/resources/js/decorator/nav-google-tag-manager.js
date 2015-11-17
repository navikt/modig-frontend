var dataLayer = dataLayer || [{}];

(function() {

    (function(w, d, s, l, i) {
        w[l] = w[l] || [];
        w[l].push({
            'gtm.start': new Date().getTime(), event: 'gtm.js'
        });
        var f = d.getElementsByTagName(s)[0],
            j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : '';
        j.async = true;
        j.src =
            '//www.googletagmanager.com/gtm.js?id=' + i + dl;
        f.parentNode.insertBefore(j, f);
    })(window, document, 'script', 'dataLayer', 'GTM-PM9RP3');


    var securityLevel = navno.securityLevel || 0;
    var fgKode = navno.fgkode;
    var ytelse = navno.ytelse;

    var innloggetInformasjon = {
        'securityLevel': securityLevel,
        'fgKode'       : fgKode,
        'ytelse'       : ytelse
    };

    dataLayer.push(innloggetInformasjon);
})();
