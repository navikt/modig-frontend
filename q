[1mdiff --git a/modig-frontend-ressurser/src/main/resources/js/nav_frontend/libs/megamenu/jquery-accessibleMegaMenu.js b/modig-frontend-ressurser/src/main/resources/js/nav_frontend/libs/megamenu/jquery-accessibleMegaMenu.js[m
[1mindex a8c8801..911e8e0 100755[m
[1m--- a/modig-frontend-ressurser/src/main/resources/js/nav_frontend/libs/megamenu/jquery-accessibleMegaMenu.js[m
[1m+++ b/modig-frontend-ressurser/src/main/resources/js/nav_frontend/libs/megamenu/jquery-accessibleMegaMenu.js[m
[36m@@ -159,6 +159,8 @@[m [m$(document).ready(function () {[m
             _toggleMobileMenuAndSearch,     [m
             _getFooterLinks, // custom func[m
             _splitListItems, // custom func[m
[32m+[m[32m            _isElementinViewport, // custom func[m
[32m+[m[32m            _scrollElementIntoViewport, // custom func[m
             _getTouchEvent = function(event) {[m
                       return event.originalEvent.targetTouches ? event.originalEvent.targetTouches[0] : event;[m
                     };[m
[36m@@ -323,6 +325,32 @@[m [m$(document).ready(function () {[m
 [m
         };[m
 [m
[32m+[m[32m        _isElementinViewport = function(el) {[m
[32m+[m[32m          //special bonus for those using jQuery[m
[32m+[m[32m          if (typeof jQuery === "function" && el instanceof jQuery) {[m
[32m+[m[32m              el = el[0];[m
[32m+[m[32m          }[m
[32m+[m
[32m+[m[32m          var rect = el.getBoundingClientRect();[m
[32m+[m
[32m+[m[32m          return ([m
[32m+[m[32m              rect.top >= 0 &&[m
[32m+[m[32m              rect.left >= 0 &&[m
[32m+[m[32m              rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && /*or $(window).height() */[m
[32m+[m[32m              rect.right <= (window.innerWidth || document.documentElement.clientWidth) /*or $(window).width() */[m
[32m+[m[32m          );[m
[32m+[m[32m        };[m
[32m+[m
[32m+[m[32m        _scrollElementIntoViewport = function(el) {[m
[32m+[m[32m          setTimeout(function() {[m
[32m+[m[32m            if(!_isElementinViewport(el)) {[m
[32m+[m[32m              $("html, body").animate({[m
[32m+[m[32m                scrollTop: el.offset().top[m
[32m+[m[32m              }, 300);[m
[32m+[m[32m            }[m
[32m+[m[32m          }, 300)[m
[32m+[m[32m        };[m
[32m+[m
           _getFooterLinks = function (element) {[m
             [m
             var topli = element,[m
[36m@@ -332,7 +360,11 @@[m [m$(document).ready(function () {[m
                 panelWrapper = topli.find(".panel-wrapper"),[m
                 timeoutId, [m
                 setPanelWrapperHeight = function() {[m
[31m-                  panelWrapper.height(panel.height() + 50);[m
[32m+[m[32m                  var currentHeight = panelWrapper.height();[m[41m [m
[32m+[m[32m                  var newHeight = panel.height() + 50;[m
[32m+[m[32m                  if(newHeight > currentHeight) {[m
[32m+[m[32m                    panelWrapper.height(newHeight);[m
[32m+[m[32m                  }[m
                 };[m
 [m
             if (!panel.find('ul').length > 0) {[m
[36m@@ -362,45 +394,34 @@[m [m$(document).ready(function () {[m
 [m
                   if (list.length > 0) {[m
 [m
[31m-                  if (!$('html').hasClass('no-csscolumns')) { // modernizr dependent[m
[31m-                    list.removeClass().addClass('footer-columns').appendTo(panel); // menu-link-list footer-columns[m
[31m-                  }[m
[31m-                  else { // special handling if no csscolumns support[m
[31m-                    _splitListItems(panel,list);[m
[31m-                  }[m
[32m+[m[32m                    if (!$('html').hasClass('no-csscolumns')) { // modernizr dependent[m
[32m+[m[32m                      list.removeClass().addClass('footer-columns').appendTo(panel); // menu-link-list footer-columns[m
[32m+[m[32m                    }[m
[32m+[m[32m                    else { // special handling if no csscolumns support[m
[32m+[m[32m                      _splitListItems(panel,list);[m
[32m+[m[32m                    }[m
[32m+[m
[32m+[m[32m                    setPanelWrapperHeight();[m[41m [m
[32m+[m[32m                    $(window).resize(function() {[m
[32m+[m[32m                      // throttling[m
[32m+[m[32m                      clearTimeout(timeoutId);[m
[32m+[m[32m                      timeoutId = setTimeout(setPanelWrapperHeight, 300);[m
[32m+[m[32m                    });[m[41m          [m
 [m
[31m-                  setPanelWrapperHeight(); [m
[31m-                  $(window).resize(function() {[m
[31m-                    // throttling[m
[31m-                    clearTimeout(timeoutId);[m
[31m-                    timeoutId = setTimeout(setPanelWrapperHeight, 300);[m
[31m-                  });                  [m
[32m+[m[32m                    _scrollElementIntoViewport(panelWrapper);[m[41m        [m
 [m
                   }[m
 [m
                   else {[m
                     panelWrapper.height(150);[m
[32m+[m[32m                    _scrollElementIntoViewport(panelWrapper);[m
                     panel.append('<p>Fant ikke innhold</p>');[m
                   } [m
             });[m
             [m
           }// if content is not allready loaded[m
 [m
[31m-[m
[31m-           setTimeout(function() {[m
[31m-            var scrollTarget = $(window).scrollTop() + $(window).height(),[m
[31m-                offset = topli.offset().top,[m
[31m-                distanceFromFold = scrollTarget - offset;[m
[31m-[m
[31m-                if (distanceFromFold < 300) { // scroll down to link list if A-Z is close to the fold[m
[31m-                  $('html,body').animate({[m
[31m-                  scrollTop: $(window).scrollTop() + (300 - distanceFromFold)[m
[31m-               });[m
[31m-                }[m
[31m-              [m
[31m-            }, 500);[m
[31m-          };[m
[31m-[m
[32m+[m[32m        };[m
 [m
         /**[m
          * @name jQuery.fn.accessibleMegaMenu~_getPlugin[m
[36m@@ -481,10 +502,9 @@[m [m$(document).ready(function () {[m
                var footerLinksContainer = topli.find('.accessible-megafooter-panel');[m
 [m
                 if (footerLinksContainer.length > 0) {[m
[31m-                     if (!footerLinksContainer.hasClass('content-loaded')) {[m
[31m-                          _getFooterLinks (topli);[m
[31m-                       }[m
[31m-            [m
[32m+[m[32m                  if (!footerLinksContainer.hasClass('content-loaded')) {[m
[32m+[m[32m                    _getFooterLinks (topli);[m
[32m+[m[32m                  }[m[41m [m
                 }[m
 [m
                 topli.siblings().removeClass(settings.selectedTopNavItem)[m
[36m@@ -501,6 +521,8 @@[m [m$(document).ready(function () {[m
                     .filter('.' + settings.panelClass)[m
                     .attr('aria-hidden', 'false');              [m
 [m
[32m+[m[32m                _scrollElementIntoViewport(footerLinksContainer);[m
[32m+[m
                  menu.addClass(settings.jsMenuExpandedClass); // animate down // custom[m
 [m
                  _toggleExpandedEventHandlers.call(that);[m
