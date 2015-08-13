var navno = navno || {};

navno.onClickEnterContentLanguage = function (langList) {

    var langClick = function (event) {

        var langSelector = $('.content-languages');

        if (typeof event !== "undefined") {
            event.stopPropagation();

            var globalLang = $("header.siteheader ul.dropdown-menu");
            if (! globalLang.hasClass("hidden")) {
                globalLang.addClass("hidden");
            }
        }


        if (! langSelector.hasClass('selected')) {
            langSelector.addClass('selected');
            langList.removeClass('hide');
        } else {
            langList.addClass('hide');
            langSelector.removeClass('selected');
        }
    }
    return langClick;
};

navno.contentLanguages = function () {

    var langSelector = $('.content-languages');

    if (langSelector.length > 0) {

        var langList = langSelector.find("ul");
        langSelector.on("click", navno[ 'onClickEnterContentLanguage'](langList));

        langSelector.on('keyup', function (e) {

            var key = e.which || e.keyCode;

            if (key === 13) {
                if (! langSelector.hasClass('selected')) {
                    langSelector.addClass('selected');
                    langList.removeClass('hide');
                } else {
                    langList.addClass('hide');
                    langSelector.removeClass('selected');
                }
            }
        });
    }
};

$(function () {
    navno[ 'contentLanguages']();
});