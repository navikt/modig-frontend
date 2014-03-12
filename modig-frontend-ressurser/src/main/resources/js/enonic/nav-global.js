

/*
 * On global language selector click
 */

var navno = window.navno || {};

(function globalLanguageSelector() {
  
  $('.site-coltrols-toolbar ul li.dropdown').click(function (e) {
    
    var dropdownList = $(this).find("ul.dropdown-menu");
    dropdownList.hasClass("hidden") ? dropdownList.removeClass("hidden"): dropdownList.addClass("hidden");
  });
}
());
//////////////////////// END ////////////////////////

/*
 * Set condition on when to show scroll-to-top button
 */
 
// -------------------------------------------
// KNAPPEN ER FORELØPIG UTKOMMENTERT FRA XSL
// -------------------------------------------

 /*(function onShowScrollToTopButton() {
 
    var buttonWrapper = $('.top-link-wrapper');
    var footerMenuTop = $('#footer-content-menu').offset().top;
    
    var isButtonSticky = false;
    var isScrollAtBottom = false;
    
    if ($(this).scrollTop() > 500 && (($(window).scrollTop() * 100) / $(window).height()) > 50) {
      buttonWrapper.addClass('sticky-top-link');
      isButtonSticky = true;
    }
    
    $(document).scroll(function () {
    
      if (isButtonSticky === false) {
        // Litt heavy med den siste betingelsen (prosentregning)? Kan kanskje være nok med å sjekke antall piksler ned på siden.
        if (isScrollAtBottom === false && $(this).scrollTop() > 500 && (($(window).scrollTop() * 100) / $(window).height()) > 50) {
          isButtonSticky = true;
          
          buttonWrapper.addClass('sticky-top-link');
        }
        else if (isScrollAtBottom === true && ($(window).scrollTop() + $(window).height()) <= footerMenuTop) {
          isButtonSticky = true;
          isScrollAtBottom = false;
          
          buttonWrapper.addClass('sticky-top-link');
        }
      }
      else if (isButtonSticky === true) {
        if ($(this).scrollTop() < 500) {
          isButtonSticky = false;
          buttonWrapper.removeClass('sticky-top-link');
        }
        else if ($(this).scrollTop() > 500 && ((buttonWrapper.offset().top + buttonWrapper.height()) >= footerMenuTop)) {
          isButtonSticky = false;
          isScrollAtBottom = true;
          buttonWrapper.removeClass('sticky-top-link');
        }
      }
    });
 }
());*/
 
//////////////////////// END ////////////////////////

/*
 * Scroll to top link for mobile devices
 */

(function scrollToTopForMobile() {

  $('#top-scroll-link').click(function (e) {
    e.preventDefault();
    
    var url = $(this).attr('href');
    var targetId = url.substring(url.lastIndexOf('#'));
    
    $('html, body').animate({
      scrollTop: $(targetId).offset().top
    }, { duration: 800 });
  });
}
());

//////////////////////// END ////////////////////////


/*
 * When dropdown lists are open and user clicks outside it.
 * This may be one of several different lists. Collect them here
 */
(function hideOpenListsOnOutsideClick() {
  
  $(document).mouseup(function (e) {
    
    var contentLanguages = $('.content-languages');
    var siteLanguages = $('.site-coltrols-toolbar ul li.dropdown');
    
    if (contentLanguages != null && contentLanguages.has(e.target).length === 0) {
      contentLanguages.find('ul').addClass('hide');
      contentLanguages.removeClass('selected');
    }
    if (siteLanguages.has(e.target).length == 0 && ! siteLanguages.find("ul.dropdown-menu").hasClass("hide")) {
      siteLanguages.find("ul.dropdown-menu").addClass("hide");
    }
  });
}
());
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


(function onExternalLinks() {
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
    return navno[ 'confirmLeaveBetasite']();
  });
  
  $("a[rel='external']").on('click', function () {
    window.open($(this).attr('href'));
    return false;
  });
}
());
//////////////////////// END ////////////////////////


/*
 * SVG stuff
 */
(function () {
  if (! Modernizr.svg && document.getElementById('svg-banner')) {
    var elem = document.getElementById('svg-banner');
    var fallback = elem.getAttribute('data-fallback');
    elem.setAttribute('src', fallback);
  }
}
());
//////////////////////// END ////////////////////////


/*
 * Hover / focus events
 */

(function () {

 $('#text-size-accessibility').on('mouseenter focusin', function() {
      $(this).find('.hidden').removeClass('hidden');
       
  }).on('mouseleave focusout', function() {
    $(this).find('.text-size-tooltip').addClass('hidden');

  }).on('click', function(e) {
    e.preventDefault();
  });
  
}
());

(function onElementFocus() {
    
  $('.siteheader .dropdown-toggle').on('focusin', function () {
    $(this).addClass('page-languages');
  }).on('focusout', function () {
    $(this).removeClass('page-languages');
  });
}
());
//////////////////////// END ////////////////////////


// ------------------------------- \\
//    Cookie handling (reusable)   \\
// ------------------------------- \\
navno.setCookie = function (c_name, value, exdays, isSession) {
  
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + exdays);
  var c_value;
  // isSession checks if it should be a session cookie (true/false), which will be deleted when browser shuts down.
  if (isSession === false) {
    c_value = escape(value) + ((exdays == null) ? "": "; expires=" + exdate.toUTCString() + ";domain=beta.nav.no;path=/;");
  } else {
    c_value = escape(value) + ((exdays == null) ? "": ";domain=beta.nav.no;path=/;made_write_conn=1295214458;");
  }
  document.cookie = c_name + "=" + c_value;
};

navno.getCookie = function (c_name) {
  
  var c_value = document.cookie;
  var c_start = c_value.indexOf(" " + c_name + "=");
  
  var c_start = ((c_start === -1) ? c_value.indexOf(c_name + "="): c_start);
  
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
// --------------------- \\
//         End           \\
// --------------------- \\


// --------------------- \\
//     High contrast     \\
// --------------------- \\

(function highContrast() {
  
  var hc = $('#high-contrast');
  
  if (hc.length > 0) {
    
    var hc_value_onload = navno[ 'getCookie']('highContrast');
    
    if (hc_value_onload === '1') {
      
      hc.removeClass('contrast-off');
      hc.addClass('contrast-on');
      
      $('body').addClass('contrast');
    }
    
    hc.click(function (e) {
      e.preventDefault();
      
      if (hc.hasClass('contrast-off')) {
        hc.removeClass('contrast-off');
        hc.addClass('contrast-on');
        
        navno[ 'setCookie']('highContrast', 1, 7, false);
        
        $('body').addClass('contrast');
      } else if (hc.hasClass('contrast-on')) {
        hc.removeClass('contrast-on');
        hc.addClass('contrast-off');
        
        navno[ 'setCookie']('highContrast', 0, 7, false);
        
        $('body').removeClass('contrast');
      }
    });
  }
}
());

// --------------------- \\
//         End           \\
// --------------------- \\

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


$(function () {
  if (window.addEventListener) { // skip Ie8
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