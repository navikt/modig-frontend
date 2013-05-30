$(function () {

    $('.skriftstorrelse').tooltip({
        trigger: 'click',
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

window.onload = function () {
    var cookiec = readCookie("contrast");
    var contrast = cookiec ? cookiec : 'empty'
    setActiveStyleSheet(contrast);
};

window.onunload = function () {
    var contrast = getActiveContrast();
    createCookie('contrast', contrast, 365);
};

function createCookie(name, value, days) {
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
