(function ($) {
    var listbox_selector;
    var focused_option;
    $.fn.listbox = function () {
        listbox_selector = $(this);
        listbox_selector.each(function () {
            if ($(this).children("A.selected").length == 0) {
                $(this).parent().hide()
            }
        });
        listbox_selector.children("A").focus(function (e) {
            if (! $(this).parent().hasClass("active_listbox")) {
                $(this).attr("onclick", "");
                hideListbox($(".active_listbox"));
                showListbox($(this));
                $(this).click(function () {
                    return false
                })
            }
        });
        $("body").click(function () {
            hideListbox(null)
        });
        listbox_selector.click(function (e) {
            e.stopPropagation()
        });
        listbox_selector.children("A").keydown(function (e) {
            var keyCode = e.keyCode || e.which; var index = $(this).index();
            var parent = $(this).parent();
            if (e.shiftKey && keyCode === 9) {
                focused_option = $(this).prev();
                if (index > 0) {
                    parent.attr("aria-activedescendant", $(focused_option).attr("id"))
                } else {
                    hideListbox(parent)
                }
            } else {
                if (keyCode === 9) {
                    focused_option = $(this).next();
                    if (index < parent.children().length - 1) {
                        parent.attr("aria-activedescendant", $(focused_option).attr("id"))
                    } else {
                        hideListbox(parent)
                    }
                } else {
                    if (keyCode === 13) {
                        focused_option.siblings().removeClass("selected");
                        focused_option.addClass("selected");
                        parent.attr("aria-activedescendant", $(focused_option).attr("id"));
                        hideListbox(parent)
                    } else {
                        if (keyCode === 27) {
                            hideListbox(null)
                        }
                    }
                }
            }
        });
        return this
    };
    var showListbox = function (active_option) {
        var active_listbox = active_option.parent();
        var selected_option = active_listbox.children(".selected");
        active_listbox.addClass("active_listbox");
        topAndScroll(active_option);
        selected_option.siblings().css({
            display: "block"
        })
    };
    var hideListbox = function (active_listbox) {
        if (active_listbox == null) {
            active_listbox = listbox_selector;
            $(".nedtrekk_mini").removeClass("active_listbox").removeAttr("style").children(":not(.selected)").hide()
        } else {
            active_listbox.removeClass("active_listbox").removeAttr("style").children(":not(.selected)").hide()
        }
    };
    var topAndScroll = function (active_option) {
        var active_listbox = active_option.parent();
        var active_option_offset = active_option.offset();
        var active_option_height = active_option.outerHeight();
        var active_listbox_height =(active_option_height * active_listbox.children().length) + 14;
        var full_height = active_listbox_height;
        if (active_listbox_height > 400) {
            active_listbox_height = 400
        }
        var height_above_selected = active_option_height * active_option.index();
        var scrolltop = 0;
        if (full_height > active_listbox_height) {
            scrolltop = height_above_selected - active_option_offset.top + active_option_height - 13
        }
        var top = active_option_offset.top - height_above_selected - 14;
        if (top < 2) {
            top = 2
        }
        active_listbox.animate({
            scrollTop: scrolltop
        },
        3);
        if (active_listbox.parent().css("float") == "right") {
            active_listbox.offset({
                top: top, right: 0
            })
        } else {
            active_listbox.offset({
                top: top, left: active_option_offset.left - 13
            })
        }
    }
})(jQuery);
function getWindowHeight() {
    var height = 0; if (typeof (window.innerHeight) == "number") {
        height = window.innerHeight
    } else {
        if (document.documentElement && document.documentElement.clientHeight) {
            height = document.documentElement.clientHeight
        } else {
            if (document.body && document.body.clientHeight) {
                height = document.body.clientHeight
            }
        }
    }
    return height
}
function getWindowWidth() {
    var width = 0; if (typeof (window.innerWidth) == "number") {
        width = window.innerWidth
    } else {
        if (document.documentElement && document.documentElement.clientWidth) {
            width = document.documentElement.clientWidth
        } else {
            if (document.body && document.body.clientWidth) {
                width = document.body.clientWidth
            }
        }
    }
    return width
}
function getDocumentHeight() {
    return Math.max($(document).height(), $(window).height(), document.documentElement.clientHeight)
}
function getScrollTop() {
    var scrollTop = $("html").scrollTop();
    if (scrollTop == 0) {
        scrollTop = $("body").scrollTop()
    }
    return scrollTop
}
function getScrollLeft() {
    var scrollLeft = $("html").scrollLeft();
    if (scrollLeft == 0) {
        scrollLeft = $("body").scrollLeft()
    }
    return scrollLeft
}
function scrollIntoView(elementnavn, callback) {
    var opprinneligST = getScrollTop();
    var vindushoyde = getWindowHeight();
    var topp = $(elementnavn).offset().top; var hoyde = $(elementnavn).height();
    var scrollTop = opprinneligST; var minimumScrollTop = topp + hoyde - vindushoyde + 40; var maksimumScrollTop = topp; if (scrollTop < minimumScrollTop) {
        scrollTop = minimumScrollTop
    }
    if (scrollTop > maksimumScrollTop) {
        scrollTop = maksimumScrollTop
    }
    if (scrollTop != opprinneligST) {
        $("html").animate({
            scrollTop: scrollTop
        },
        300, function () {
            $("body").animate({
                scrollTop: scrollTop
            },
            300, callback)
        })
    } else {
        if (typeof (callback) != "undefined") {
            callback()
        }
    }
};
MidstilltPosisjonManager = function (target, tooltip) {
    var target = {
        top: $(target).offset().top, left: $(target).offset().left, bottom: $(target).offset().top + $(target).outerHeight(), right: $(target).offset().left + $(target).outerWidth(), width: $(target).outerWidth(), height: $(target).outerHeight()
    };
    var halvPilbredde = tooltip.skinn.pilbredde / 2;
    var pilHoyde = tooltip.skinn.pilhoyde;
    var pixelFix = 1;
    var posisjoner = null; var initPosisjoner = function () {
        if (posisjoner == null) {
            posisjoner = {
                top: {
                    top: target.top - tooltip.hoyde - tooltip.skinn.avstand, left: target.left +(target.width / 2) -(tooltip.bredde / 2) - tooltip.skinn.pilhoyde, pil: "bottom", pilCss: {
                        left:(tooltip.bredde / 2 + pilHoyde - halvPilbredde) + "px"
                    }
                },
                left: {
                    top: target.top +(target.height / 2) -(tooltip.hoyde / 2), left: target.left - tooltip.bredde - 2 * tooltip.skinn.pilhoyde - tooltip.skinn.avstand, pil: "right", pilCss: {
                        top:(tooltip.hoyde / 2 - halvPilbredde - pixelFix) + "px"
                    }
                },
                bottom: {
                    top: target.bottom + tooltip.skinn.avstand, left: target.left +(target.width / 2) -(tooltip.bredde / 2) - tooltip.skinn.pilhoyde, pil: "top", pilCss: {
                        left:(tooltip.bredde / 2 + pilHoyde - halvPilbredde) + "px"
                    }
                },
                right: {
                    top: target.top +(target.height / 2) -(tooltip.hoyde / 2), left: target.right + tooltip.skinn.avstand, pil: "left", pilCss: {
                        top:(tooltip.hoyde / 2 - halvPilbredde - pixelFix) + "px"
                    }
                }
            }
        }
    };
    return {
        oppdater: function () {
            initPosisjoner();
            if (tooltip.plass == "top") {
                tooltip.setPosition(posisjoner.top)
            } else {
                if (tooltip.plass == "left") {
                    tooltip.setPosition(posisjoner.left)
                } else {
                    if (tooltip.plass == "right") {
                        tooltip.setPosition(posisjoner.right)
                    } else {
                        if (tooltip.plass == "bottom") {
                            tooltip.setPosition(posisjoner.bottom)
                        }
                    }
                }
            }
        }
    }
};
HjelpetekstPosisjonManager = function (target, tooltip) {
    var target = {
        top: $(target).offset().top, left: $(target).offset().left, bottom: $(target).offset().top + $(target).outerHeight(), right: $(target).offset().left + $(target).outerWidth(), width: $(target).outerWidth(), height: $(target).outerHeight()
    };
    var innrykk = Math.min(20, target.width / 2);
    var pilinnrykk = tooltip.skinn.pilhoyde + 20; var halvPil =(tooltip.skinn.pilbredde / 2);
    var posisjoner = null; var initPositions = function () {
        if (posisjoner == null) {
            var tooltipHeight = $(tooltip.tooltipElement).outerHeight();
            var outerWidth = $(tooltip.tooltipElement).outerWidth();
            posisjoner = {
                topleft: {
                    top: target.top - tooltip.hoyde - tooltip.skinn.avstand, left: target.left + innrykk - halvPil - pilinnrykk, pil: "bottom", pilCss: {
                        left: pilinnrykk
                    }
                },
                topright: {
                    top: target.top - tooltip.hoyde - tooltip.skinn.avstand, left: target.right - innrykk + halvPil + pilinnrykk - outerWidth, pil: "bottom", pilCss: {
                        left: outerWidth - pilinnrykk - halvPil - halvPil
                    }
                },
                bottomleft: {
                    top: target.bottom + tooltip.skinn.avstand, left: target.left + innrykk - halvPil - pilinnrykk, pil: "top", pilCss: {
                        left: pilinnrykk
                    }
                },
                bottomright: {
                    top: target.bottom + tooltip.skinn.avstand, left: target.right - innrykk + halvPil + pilinnrykk - outerWidth, pil: "top", pilCss: {
                        left: outerWidth - pilinnrykk - halvPil - halvPil
                    }
                }
            }
        }
    };
    var erSynlig = function (posisjon) {
        var tooltipHeight = $(tooltip.tooltipElement).outerHeight();
        var tooltipWidth = $(tooltip.tooltipElement).outerWidth();
        return posisjon.top > getScrollTop() && posisjon.top + tooltipHeight < getScrollTop() + getWindowHeight() && posisjon.left > getScrollLeft() && posisjon.left + tooltipWidth < getScrollLeft() + getWindowWidth()
    };
    var pilCss; return {
        oppdater: function () {
            initPositions();
            if (erSynlig(posisjoner.topright)) {
                tooltip.setPosition(posisjoner.topright)
            } else {
                if (erSynlig(posisjoner.topleft)) {
                    tooltip.setPosition(posisjoner.topleft)
                } else {
                    if (erSynlig(posisjoner.bottomright)) {
                        tooltip.setPosition(posisjoner.bottomright)
                    } else {
                        if (erSynlig(posisjoner.bottomleft)) {
                            tooltip.setPosition(posisjoner.bottomleft)
                        } else {
                            tooltip.setPosition(posisjoner.topright)
                        }
                    }
                }
            }
        }
    }
};
if (typeof (dojo) != "undefined") {
    dojo.provide("statisk.js.hjelpetekst");
    dojo.require("statisk.js.tooltip")
}
var Hjelpetekst = {
    element: undefined, visSkjulLenke: false, forElement: function (element) {
        this.element = element; return this
    },
    visTekst: function (tekst) {
        var element = this.element; var visSkjulLenke = this.visSkjulLenke; var skjulFunksjon = function () {
            Hjelpetekst.skjul(element)
        };
        $(document).ready(function () {
            var tooltip;
            if (visSkjulLenke) {
                Hjelpetekst.skjul(element);
                tooltip = Tooltip.medSkinn(skins.hjelpetekst).medSkjulLenke(skjulFunksjon).vis(element, "auto_380", tekst)
            } else {
                if (! Hjelpetekst.harTooltip(element)) {
                    tooltip = Tooltip.medSkinn(skins.hjelpetekst).vis(element, "auto_380", tekst);
                    $(element).mouseout(skjulFunksjon)
                }
            }
            Hjelpetekst.setTooltip(element, tooltip)
        });
        Hjelpetekst.opprydding()
    },
    skjul: function (element) {
        var tooltip = Hjelpetekst.finnTooltip(element);
        if (tooltip) {
            $(tooltip).fadeOut(300, function () {
                $(tooltip).remove()
            });
            $(element).unbind("mouseout");
            Hjelpetekst.fjernTooltip(element)
        }
    },
    medSkjulLenke: function () {
        this.visSkjulLenke = true; return this
    },
    autoOppsett: function (target, tekst) {
        $(target).hover(function () {
            Hjelpetekst.forElement(target).visTekst(tekst)
        });
        $(target).click(function () {
            Hjelpetekst.medSkjulLenke().forElement(target).visTekst(tekst);
            return false
        })
    },
    opprydding: function () {
        this.element = undefined; this.visSkjulLenke = false
    },
    setTooltip: function (element, tooltip) {
        $(element).data("tooltip", tooltip)
    },
    finnTooltip: function (element) {
        return $(element).data("tooltip")
    },
    harTooltip: function (element) {
        var tooltip = this.finnTooltip(element);
        return typeof (tooltip) != "undefined" && tooltip != null
    },
    fjernTooltip: function (element) {
        $(element).removeData("tooltip")
    }
};
var skins = {
    standard: {
        klasse: "standard", pilhoyde: 7, pilbredde: 15, kantbredde: 7, avstand: 2, plassering: "midtstillt", background: "#989793"
    },
    graa_boks: {
        klasse: "graa_boks", pilhoyde: 7, pilbredde: 15, kantbredde: 1, avstand: 2, plassering: "midtstillt", background: "#989793"
    },
    hjelpetekst: {
        klasse: "hjelpetekst", pilhoyde: 12, pilbredde: 15, kantbredde: 1, avstand: 2, plassering: "ht", background: "#fcf3d7"
    },
    kopier: function (skinn) {
        var nyttSkinn = {
        };
        for (n in skinn) {
            nyttSkinn[n] = skinn[n]
        }
        return nyttSkinn
    }
};
var Tooltip = function (target, plass, skinn) {
    this.id = "tooltip" + Tooltip.teller++; this.plass = plass; this.kalkulertPlass = plass; this.forrigePlass = plass; this.timer = null; this.posisjon = {
        left: 0, top: 0
    };
    this.tooltipElement = null; this.oppdaterFunc = function () {
    };
    this.skinn = skinn; if (skinn.plassering == "midtstillt") {
        this.posisjonManager = new MidstilltPosisjonManager(target, this)
    } else {
        if (skinn.plassering == "ht") {
            this.posisjonManager = new HjelpetekstPosisjonManager(target, this)
        }
    }
};
Tooltip.prototype.oppdaterPosisjon = function () {
    var tooltip = this; var pos = this.posisjonManager.oppdater();
    if (tooltip.tooltipElement.parent().length > 0) {
        tooltip.timer = setTimeout(function () {
            tooltip.oppdaterPosisjon()
        },
        2000)
    }
};
Tooltip.prototype.setPosition = function (posisjon) {
    if (posisjon.left != this.posisjon.left || posisjon.top != this.posisjon.top) {
        this.tooltipElement.css({
            left: posisjon.left + "px", top: posisjon.top + "px", position: "absolute"
        });
        this.position = {
            top: posisjon.top, left: posisjon.left
        };
        this.tooltipElement.find(".tt_pil_" + posisjon.pil).css(posisjon.pilCss);
        this.tooltipElement.find(".tt_arrow").hide();
        this.tooltipElement.find(".tt_pil_" + posisjon.pil).show()
    }
};
Tooltip.prototype.vis = function () {
    var tooltip = this; tooltip.oppdaterPosisjon();
    if (tooltip.fadeIn) {
        tooltip.tooltipElement.fadeIn()
    } else {
        tooltip.tooltipElement.show()
    }
    tooltip.oppdaterFunc = function () {
        tooltip.oppdater()
    };
    $(window).bind("resize", tooltip.oppdaterFunc);
    $(window).scroll(tooltip.oppdaterFunc)
};
Tooltip.prototype.oppdater = function () {
    var tooltip = this; if (tooltip.timer) {
        clearTimeout(tooltip.timer)
    }
    if (tooltip.tooltipElement.parent().length == 0) {
        $(window).unbind("resize", tooltip.oppdaterFunc);
        $(window).unbind("scroll", tooltip.oppdaterFunc);
        return
    } else {
        this.timer = setTimeout(function () {
            tooltip.oppdaterPosisjon()
        },
        100)
    }
};
Tooltip.teller = 0; Tooltip.pilhoyde = 7; Tooltip.skinn = skins.standard; Tooltip.fadeIn = true; Tooltip.visSkjulLenke = false; Tooltip.liste =[]; Tooltip.tooltipMarkup = '<div class="tooltip" id="tooltipNyID"><div class="tt_boks"><div class="tt_content"><!-- --></div></div><div class="tt_pil_right tt_arrow"><!-- --></div><div class="tt_pil_bottom tt_arrow"><!-- --></div><div class="tt_pil_left tt_arrow"><!-- --></div><div class="tt_pil_top tt_arrow"><!-- --></div></div>'; Tooltip.vis = function (target, klasse, tekst) {
    if (! this.skinn) {
        this.skinn = skins.standard
    }
    var tooltip = new Tooltip(target, klasse.split("_")[0], this.skinn);
    $("BODY").append(this.tooltipMarkup);
    var tooltipElement = $("#tooltipNyID");
    var skjulLenke = this.visSkjulLenke? '<a href="#" class="skjulLenke"><span>Skjul</span></a>': ""; tooltipElement.addClass(this.skinn.klasse);
    tooltipElement.attr("id", tooltip.id);
    tooltipElement.find(".tt_content").html(tekst + skjulLenke);
    tooltip.tooltipElement = tooltipElement; tooltip.bredde = this.finnBredde(klasse, tooltipElement);
    tooltipElement.find(".tt_boks").css({
        width:(tooltip.bredde) + "px"
    });
    if (this.visSkjulLenke) {
        if (typeof (this.skjulFunksjon) != "undefined" && this.skjulFunksjon != null) {
            var skjulFunksjon = this.skjulFunksjon; tooltipElement.find(".skjulLenke").click(function () {
                skjulFunksjon();
                return false
            })
        } else {
            tooltipElement.find(".skjulLenke").click(function () {
                $("#" + tooltip.id).remove();
                return false
            })
        }
    } else {
        tooltipElement.find(".skjulLenke").hide()
    }
    tooltip.hoyde = tooltipElement.height();
    tooltip.vis();
    Tooltip.liste.push(tooltip);
    this.nullstill();
    return tooltipElement
};
Tooltip.finnBredde = function (klasse, tooltipElement) {
    var bredde = klasse.split("_")[1]; if (bredde == "auto") {
        tooltipElement.css({
            width: "auto"
        });
        bredde = tooltipElement.width() - 2 * this.skinn.pilhoyde
    } else {
        if (bredde.substring(0, 5) == "minma") {
            tooltipElement.css({
                width: "auto"
            });
            var min = bredde.split("x")[1]; var max = bredde.split("x")[2]; bredde = Math.max(Math.min(tooltipElement.width() - 2 * this.skinn.pilhoyde, max), min)
        }
    }
    return Number(bredde)
};
Tooltip.oppdater = function () {
    for (n in Tooltip.liste) {
        Tooltip.liste[n].oppdaterPosisjon()
    }
};
Tooltip.fjernAlle = function () {
    $(".tooltip").remove()
};
Tooltip.medAvstand = function (avstand) {
    this.skinn.avstand = avstand; return this
};
Tooltip.medSkinn = function (skinn) {
    this.skinn = skinn; return this
};
Tooltip.utenFadeIn = function () {
    this.fadeIn = false; return this
};
Tooltip.medSkjulLenke = function (skjulFunksjon) {
    this.visSkjulLenke = true; this.skjulFunksjon = skjulFunksjon; return this
};
Tooltip.nullstill = function () {
    this.skinn = skins.standard; this.visSkjulLenke = false; this.skjulFunksjon = null; this.fadeIn = false
};
function setCookie(name, value, lifespan, access_path, domain) {
    var cookietext = name + "=" + encodeURIComponent(value);
    if (lifespan != null) {
        var today = new Date();
        var expiredate = new Date();
        expiredate.setTime(today.getTime() + 1000 * 60 * 60 * 24 * lifespan);
        cookietext += "; expires=" + expiredate.toGMTString()
    }
    if (access_path != null) {
        cookietext += "; PATH=" + access_path
    }
    if (domain != null) {
        cookietext += "; DOMAIN=" + domain
    }
    document.cookie = cookietext; return null
}
function setDatedCookie(name, value, expire, access_path) {
    var cookietext = name + "=" + encodeURIComponent(value) +((expire == null)? "":("; expires=" + expire.toGMTString()));
    if (access_path != null) {
        cookietext += "; PATH=" + access_path
    }
    document.cookie = cookietext; return null
}
function getCookie(Name) {
    var search = Name + "="; var CookieString = document.cookie; var result = null; if (CookieString.length > 0) {
        offset = CookieString.indexOf(search);
        if (offset != - 1) {
            offset += search.length; end = CookieString.indexOf(";", offset);
            if (end == - 1) {
                end = CookieString.length
            }
            result = unescape(CookieString.substring(offset, end))
        }
    }
    return result
}
function deleteCookie(Name, Path, Domain) {
    setCookie(Name, "Deleted", - 1, Path, Domain)
}
function setSubject() {
    var selCat = document.getElementById("selectCategory");
    var selectedCat = selCat.options[document.getElementById("selectCategory").selectedIndex].innerHTML; document.getElementById("subject").setAttribute("value", selectedCat)
}
function submitCheck() {
    setSubject()
};
