var navno = navno || {
};


$(function () {

    $('#link-skriftstorrelse').tooltip({
        trigger: 'hover',
        placement: 'bottom',
        html: true
    });

    $(document).click(function (e) {
        if (!(e.target.id == "link-skriftstorrelse" || $(e.target).parents('#link-skriftstorrelse').length > 0)) {
            if ($('.tooltip-inner:visible')) {
                $('#link-skriftstorrelse').tooltip('hide');
            }
        }
    });

    $('#hoykontrast').click(function () {
        changeContrast();
    });
});

var msIEversion;

if (window.navigator.appName == "Microsoft Internet Explorer") {
    if (document.documentMode)
        msIEversion = document.documentMode; // IE8
    // IE 5-7
    else {
        msIEversion = 5; // Assume quirks mode unless proven otherwise
        if (document.compatMode) {
            if (document.compatMode == "CSS1Compat")
                msIEversion = 7; // standards mode
        }
    }
}



/*
 * Cookie handling (parameterized, reusable)
 * param1: name/key of cookie
 * param2: value
 * param3: how many days to expiration
 * param4: boolean to determine type of cookie, where boolean true is session cookie and boolean false is persistent cookie.
 */
navno.setCookie = function (c_name, value, exdays, isSession) {

    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var c_value;
    if (isSession) {
        c_value = escape(value) + ((exdays == null) ? "" : ";domain=.nav.no;path=/;made_write_conn=1295214458;");
    } else {
        c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString() + ";domain=.nav.no;path=/;");
    }
    document.cookie = c_name + "=" + c_value;
};

navno.getCookie = function (c_name) {

    var c_value = document.cookie;
    var c_start = c_value.indexOf(" " + c_name + "=");

    var c_start = ((c_start === -1) ? c_value.indexOf(c_name + "=") : c_start);

    if (c_start === -1) {
        c_value = null;
    } else {
        c_start = c_value.indexOf("=", c_start) + 1;
        var c_end = c_value.indexOf(";", c_start);
        if (c_end === -1) {
            c_end = c_value.length;
        }
        c_value = unescape(c_value.substring(c_start, c_end));
    }
    return c_value
};
//////////////////////// END ////////////////////////

window.onload = function () {
    var hc_value_onload = navno[ 'getCookie']('highContrast');

    if (hc_value_onload === '1') {
        setActiveStyleSheet('contrast');
    } else {
        setActiveStyleSheet('');
    }
};

window.onunload = function () {
    var contrast = getActiveContrast();
    navno[ 'setCookie']('highContrast', contrast === 'contrast' ? 1 : 0 , 7, false);
};

function createCookie(name, value, days, domain) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        var expires = "; expires=" + date.toGMTString();
    }
    else expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function setActiveStyleSheet(contrast) {
    if (contrast == 'empty') {
        contrast = '';
    }
    var body = document.getElementsByTagName('body')[0];
    var bodyClass = contrast;
    body.setAttribute(msIEversion < 8 ? 'className' : 'class', bodyClass);
}

function changeContrast() {
    var body = document.getElementsByTagName('body')[0];
    var bodyClass = body.getAttribute('className') || body.getAttribute('class'); //IE7
    if (bodyClass.indexOf('contrast') == -1) {
        bodyClass += ' contrast';
        body.setAttribute(msIEversion < 8 ? 'className' : 'class', bodyClass);
    } else { // Turn off contrast
        bodyClass = bodyClass.replace('contrast', '');
        body.setAttribute(msIEversion < 8 ? 'className' : 'class', bodyClass);
    }
}

function getActiveContrast() {
    var body = document.getElementsByTagName('body')[0];
    var bodyClass = body.getAttribute('className') || body.getAttribute('class'); //IE7
    if (bodyClass.indexOf('contrast') == -1) {
        return 'empty';
    } else {
        return 'contrast';
    }
}
