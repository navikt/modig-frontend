var navno = navno || {
};


/*
 * On global language selector click
 */

$(function () {
	$('.site-coltrols-toolbar ul li.dropdown').click(function (e) {

		var dropdownList = $(this).find("ul.dropdown-menu");
		dropdownList.hasClass("hidden") ? dropdownList.removeClass("hidden") : dropdownList.addClass("hidden");
	});
});
//////////////////////// END ////////////////////////


/*
 * When dropdown lists are open and user clicks outside it.
 * This may be one of several different lists. Collect them here
 */

navno.handleDocClickTouch = function (e) {

	var contentLanguages = $('.content-languages');
	var siteLanguages = $('.site-coltrols-toolbar ul li.dropdown');
	var mobileSubmenu = $(".mobile-linklist-related");
	var accordion = $("aside.related-content.accordion");

	if (contentLanguages.length > 0 && contentLanguages.has(e.target).length === 0 && contentLanguages.hasClass("selected")) {
		contentLanguages.find('ul').addClass('hide');
		contentLanguages.removeClass('selected');
	}
	if (siteLanguages.has(e.target).length === 0 && !siteLanguages.find("ul.dropdown-menu").hasClass("hidden")) {
		siteLanguages.find("ul.dropdown-menu").addClass("hidden");
	}
	if (mobileSubmenu.length > 0 && mobileSubmenu.has(e.target).length === 0 && mobileSubmenu.hasClass("open") && navno.touchMovedOnArticle === false) {
		mobileSubmenu.toggleClass("open");

		var navLinkList = mobileSubmenu.find("nav");
		navLinkList.css("height", 0);

		setTimeout(function () {
				if (mobileSubmenu.hasClass("open")) {
					navLinkList.prev().find("a").attr("aria-expanded", true).attr("aria-hidden", false);
					navLinkList.attr("aria-expanded", true).attr("aria-hidden", false);
				} else {
					navLinkList.prev().find("a").attr("aria-expanded", false).attr("aria-hidden", true);
					navLinkList.attr("aria-expanded", false).attr("aria-hidden", true);
				}
			},
			400);
	}
	if (accordion.length > 0) {
		var expandedPanel = accordion.find(".expanded .accordion-panel");
		if (expandedPanel.length > 0 && accordion.has(e.target).length === 0) {

			expandedPanel.height("0");
			setTimeout(function () {
				expandedPanel.attr("aria-expanded", false).attr("aria-hidden", true).removeAttr("style").parent().removeClass("expanded js-animated").find("header a").attr("aria-expanded", false).attr("aria-hidden", true);
			}, 250);
		}
	}
};

$(function () {

	var isTouch = ('ontouchstart' in document.documentElement);

	$(document).on("click touchend", function (e) {

		if (isTouch && e.type === "touchend") {
			navno[ "handleDocClickTouch"](e);
		} else {
			navno[ "handleDocClickTouch"](e);
		}
	});
});

//////////////////////// END ////////////////////////


/*
 * On external links
 */

navno.confirmLeaveBetasite = function (url) {
	var confirmed = confirm("Siden du prøver å gå til er foreløpig ikke en del av betaversjonen. Ønsker du å forlate beta.nav.no?");
	if (confirmed === false) {
		return false;
	} else {
		if (url) {
			document.location = url;
		} else {
			return true;
		}
	}
};


$(function () {
	$("a[href*='//www.nav.no'], a[href*='//tjenester.nav.no']").on('click', function (e) {
		if ($(this).hasClass('hero-link') && typeof ga !== 'undefined' && ga.hasOwnProperty('loaded') && ga.loaded === true) {
			e.preventDefault();
			var heroHref = $(this).attr('href');
			ga('send', 'event', 'Forsideboks', 'klikk', $(this).attr('title'), {
				'hitCallback': function () {
					return navno[ 'confirmLeaveBetasite'](heroHref);
				}
			});
		} else {
			return navno[ 'confirmLeaveBetasite']();
		}
	});

	$("form.sitesearch").on('submit', function () {
		//return navno[ 'confirmLeaveBetasite']();
		return true;
	});

	$("a[rel='external']").on('click', function () {
		window.open($(this).attr('href'));
		return false;
	});
});
//////////////////////// END ////////////////////////


/*
 * SVG stuff
 */
$(function () {
	if (!Modernizr.svg && document.getElementById('svg-banner')) {
		var elem = document.getElementById('svg-banner');
		var fallback = elem.getAttribute('data-fallback');
		elem.setAttribute('src', fallback);
	}
});
//////////////////////// END ////////////////////////


/*
 * Hover / focus events
 */

$(function () {

	$('#text-size-accessibility').on('mouseenter focusin', function () {
		$(this).find('.hidden').removeClass('hidden');
	}).on('mouseleave focusout', function () {
		$(this).find('.text-size-tooltip').addClass('hidden');
	}).on('click', function (e) {
		e.preventDefault();
	});
});

$(function () {

	$('.siteheader .dropdown-toggle').on('focusin', function () {
		$(this).addClass('page-languages');
	}).on('focusout', function () {
		$(this).removeClass('page-languages');
	});
});
//////////////////////// END ////////////////////////

/*
 * Login / Logout
 */
/*
 * Login / Logout
 */

var Innloggingslinje = (function () {

	var PREVIOUS_LOGIN_STATE_COOKIE_NAME = "previous_login_state";
	var USERNAME_COOKIE_NAME = "auth_user_name";
	var LOGIN_INFO_SHOWN_COOKIE_NAME = "login.info.hasbeenshown";
	var NOT_LOGGED_IN = "0";
	var SEC_LEVEL_LE_2 = "1";
	var SEC_LEV_GE_3 = "2";

	var init = function () {
		var cookieValue = getCookie(PREVIOUS_LOGIN_STATE_COOKIE_NAME);
		switch (cookieValue) {
			case NOT_LOGGED_IN:
				$('.auth-btns .login').removeClass('hidden');
				$('.auth-btns .logout').addClass('hidden');
				resetAndHideName();
				$('.auth-btns .logout').addClass('hidden');
				break;
			case SEC_LEVEL_LE_2:
				$('.auth-btns').hide();
				resetAndHideName();
				break;
			case SEC_LEV_GE_3:
				showLoginInfoFirstTime();
				showLoginDetails(getCookie(USERNAME_COOKIE_NAME));
				break;
		}
	};

	function getCookie(key) {
		var cookies = document.cookie.split(';');
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var keyValue = cookie.split('=');
			if (keyValue[0].trim() === key) {
				return keyValue[1];
			}
		}
		return "";
	}

	function setCookie(key, value, expires) {
		document.cookie = key + '=' + value + ';expires=' + expires.toUTCString() + ';path=/;';
	}

	function deleteCookie(key) {
		setCookie(key, "", new Date(0));
	}

	function showLoginDetails(name) {
		$('.auth-btns').show();
		$('.auth-btns').addClass('idporten');
		$('.login-container .logout').removeClass('hidden');


		if (!alreadyContainsTheRightName(name)) {
			resetAndAddNewName(name);
		}
		$('.login-details').removeClass('hidden');
	}

	function alreadyContainsTheRightName(name) {
		var nameText = $('#name').text();

		if (nameText === name) {
			return true;
		}
		return false;
	}

	function showLoginInfoFirstTime() {
		if (!loginInfoHasBeenShown()) {
			setCookie(LOGIN_INFO_SHOWN_COOKIE_NAME, "", thirtyMinutes());
			fadeOutLoginInfo();
		}
	}

	function resetAndAddNewName(name) {
		var img = $('#name img');
		$('#name').text("");
		$('#name').append(img);
		$('#name').append(name);

	}

	function resetAndHideName() {
		var img = $('#name img');
		$('#name').text("");
		$('#name').append(img);
		$('.login-details').addClass('hidden');
	}

	function thirtyMinutes() {
		var date = new Date();
		date.setTime(new Date().getTime() + 30 * 60 * 1000);
		return date;
	}

	function setCookieExpiration() {
		setLoginInfoShown(thirtyMinutes());
	}

	function fadeOutLoginInfo() {
		var $tooltip = $('.logout-tooltip');
		$lukk = $tooltip.find('.lukk');
		$lukk.removeClass('hidden');
		$tooltip.removeClass('hidden').delay(3000).fadeOut('slow', function () {
			$tooltip.addClass('hidden').show();
			$lukk.addClass('hidden');
		});
	}

	function loginInfoHasBeenShown() {
		return document.cookie.indexOf(LOGIN_INFO_SHOWN_COOKIE_NAME) !== -1;
	}

	return {
		PREVIOUS_LOGIN_STATE_COOKIE_NAME: PREVIOUS_LOGIN_STATE_COOKIE_NAME,
		USERNAME_COOKIE_NAME            : USERNAME_COOKIE_NAME,
		NOT_LOGGED_IN                   : NOT_LOGGED_IN,
		SEC_LEVEL_LE_2                  : SEC_LEVEL_LE_2,
		SEC_LEV_GE_3                    : SEC_LEV_GE_3,
		LOGIN_INFO_SHOWN_COOKIE_NAME    : LOGIN_INFO_SHOWN_COOKIE_NAME,

		init                  : init,
		getCookie             : getCookie,
		setCookie             : setCookie,
		deleteCookie          : deleteCookie,
		showLoginDetails      : showLoginDetails,
		showLoginInfoFirstTime: showLoginInfoFirstTime,
		resetAndHideName      : resetAndHideName,
		thirtyMinutes         : thirtyMinutes,
		setCookieExpiration   : setCookieExpiration,
		fadeOutLoginInfo      : fadeOutLoginInfo
	};
})();


$(function () {
	Innloggingslinje.init();
	$.get(navno.authServiceUrl + '?randomness=' + Math.random() * 10, function (data) {
		var json = JSON.parse(data);
		if (!json.authenticated) {
			$('.auth-btns').show();
			$('.login-container .login').removeClass('hidden');
			Innloggingslinje.setCookie(Innloggingslinje.PREVIOUS_LOGIN_STATE_COOKIE_NAME, Innloggingslinje.NOT_LOGGED_IN, Innloggingslinje.thirtyMinutes());
			Innloggingslinje.deleteCookie(Innloggingslinje.USERNAME_COOKIE_NAME);
			Innloggingslinje.resetAndHideName();
			$('.logout').addClass('hidden');
		} else if (json.securityLevel >= 3) {
			$('.auth-btns').show();
			var name = json.name.toLowerCase();
			Innloggingslinje.showLoginInfoFirstTime();
			Innloggingslinje.showLoginDetails(name);
			Innloggingslinje.setCookie(Innloggingslinje.PREVIOUS_LOGIN_STATE_COOKIE_NAME, Innloggingslinje.SEC_LEV_GE_3, Innloggingslinje.thirtyMinutes());
			Innloggingslinje.setCookie(Innloggingslinje.USERNAME_COOKIE_NAME, name, Innloggingslinje.thirtyMinutes());
			$('.login').addClass('hidden');

		} else {
			$('.auth-btns').hide();
			Innloggingslinje.setCookie(Innloggingslinje.PREVIOUS_LOGIN_STATE_COOKIE_NAME, Innloggingslinje.SEC_LEVEL_LE_2, Innloggingslinje.thirtyMinutes());
			Innloggingslinje.resetAndHideName();
		}
	});
});

$(function () {
	$('.logout-tooltip .lukk').click(function () {
		$('.logout-tooltip').addClass('hidden');
		$('.logout-tooltip .lukk').addClass('hidden');
	});
});

$(function () {
	$('.auth-btns .logout').click(function (e) {
		e.preventDefault();
		Innloggingslinje.deleteCookie(Innloggingslinje.LOGIN_INFO_SHOWN_COOKIE_NAME);
		Innloggingslinje.deleteCookie(Innloggingslinje.USERNAME_COOKIE_NAME);
		Innloggingslinje.setCookie(Innloggingslinje.PREVIOUS_LOGIN_STATE_COOKIE_NAME, Innloggingslinje.NOT_LOGGED_IN, Innloggingslinje.thirtyMinutes());
		window.location = $(this).attr('href');
	});
});


$(function () {

	var $tooltip = $('.logout-tooltip');
	$('.login-container .login-details span').first().on('mouseenter focusin', function () {
		$tooltip.removeClass('hidden');
	}).on('mouseleave focusout', function () {
		$tooltip.addClass('hidden');
	});
});

$(function () {

	var $tooltip = $('.login-tooltip');
	$('.login-container .login').first().on('mouseenter focusin', function () {
		$tooltip.removeClass('hidden');
	}).on('mouseleave focusout', function () {
		$tooltip.addClass('hidden');
	});
});

//////////////////////// END ////////////////////////


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


/*
 * High contrast toggle
 */

$(function () {

	var hc = $('#high-contrast');

	var body = $('body');
	var hc_value_onload = navno[ 'getCookie']('highContrast');

	if (hc_value_onload === '1') {
		body.addClass('contrast');
	}

	var handleHighContrast = function (e) {
		e.preventDefault();

		if (!body.hasClass('contrast')) {
			navno[ 'setCookie']('highContrast', 1, 7, false);

			body.addClass('contrast');
		} else if (body.hasClass('contrast')) {
			navno[ 'setCookie']('highContrast', 0, 7, false);

			body.removeClass('contrast');
		}
	};
	hc.on("click", handleHighContrast);
});
//////////////////////// END ////////////////////////


/*
 * Google Analytics stuff
 */

$(function () {
	if (typeof ga !== 'undefined' && ga.hasOwnProperty('loaded') && ga.loaded === true) {

		$('.visuallyhidden.focusable').eq(0).one('focus.google-analytics', function () {
			ga('send', 'event', 'Tastatur', 'focus', $(this).text());
		});
		$('#high-contrast').find('a').on('click.google-analytics', function () {
			ga('send', 'event', 'Høykontrast', 'klikk', $(this).text());
		});
		$('#footer-content-menu').find('.letter > a').on('click.google-analytics', function () {
			ga('send', 'event', 'Innhold A-Å', 'klikk', $(this).text());
		});
		$('.carousel-control').on('click.google-analytics', function () {
			ga('send', 'event', 'Karusell', 'klikk', 'Høyre/venstre');
		});
		$('.carousel-dropdown').find('.btn').on('click.google-analytics', function () {
			ga('send', 'event', 'Karusell', 'klikk', 'Se alle');
		});

		$('#play-btn').one('click.google-analytics', function () {
			var pageTitle = $('#pagecontent').find('h1').eq(0).text() || document.title;
			ga('send', 'event', 'Talesyntese', 'Play', pageTitle);
		});
	}
});
//////////////////////// END ////////////////////////

/*
 * Footer content letters scrolling/swiping (A-Å)
 */

$(function () {
	if (window.addEventListener && $("#footer-content-menu").length > 0) {
		// skip Ie8
		var timeout, leftClicker = $('.letter-scroll-left');
		var rightClicker = $('.letter-scroll-right');
		var scrollElement = $('#footer-content-menu');

		leftClicker.click(function (e) {
			e.preventDefault();
			scrollElement.animate({
					scrollLeft: '-=120'
				},
				300);
		});
		rightClicker.click(function (e) {
			e.preventDefault();
			scrollElement.animate({
					scrollLeft: '+=120'
				},
				300);
		});

		var scrollEvents = new Array('mousedown', 'touchstart');
		var stopTouchEvents = new Array('touchleave', 'touchcancel', 'touchend');

		for (var i = 0; i < 2; i++) {
			leftClicker[0].addEventListener(scrollEvents[i], function (event) {
				timeout = setInterval(function () {
						scrollElement.animate({
								scrollLeft: '-=120'
							},
							300);
					},
					300);
			});
			rightClicker[0].addEventListener(scrollEvents[i], function (event) {
				timeout = setInterval(function () {
						scrollElement.animate({
								scrollLeft: '+=120'
							},
							300);
					},
					300);
			});
		}
		for (var i = 0; i < 3; i++) {
			leftClicker[0].addEventListener(stopTouchEvents[i], function (event) {
				clearInterval(timeout);
				return false;
			});
			rightClicker[0].addEventListener(stopTouchEvents[i], function (event) {
				clearInterval(timeout);
				return false;
			});
		}

		$(leftClicker).add(rightClicker).on('mouseup mouseout mouseleave', function (event) {
			clearInterval(timeout);
			return false;
		});
	}
});
//////////////////////// END ////////////////////////
