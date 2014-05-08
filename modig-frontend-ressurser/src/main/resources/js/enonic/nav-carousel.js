// ------------------ \\
//   Carousel stuff   \\
// ------------------ \\

navno.displayCarousel = function (numArticles) {
  
  $('.toggle-btn-wrapper').removeClass('hide');
  
  var indicators = $('.carousel-indicators');
  
  var slidesSize = indicators.find('li').length;
  indicators.attr('data-active-slide', 0).attr('aria-valuemax', slidesSize);
  
  $('#navCarousel').attr("data-size", slidesSize);
  
  var ieControl = $('.col-lg-12 > a.carousel-control');
  if (ieControl.length > 0) {
    ieControl.remove();
    // TODO in bootstrap: IE bug workaround
  } else if ($('.col-lg-12 > div[id^="marker"] > a.carousel-control').length > 0) {
    $('.col-lg-12 > div[id^="marker"] > a.carousel-control').remove();
  }
  
  // Logikk under for vise karusellen
  if (numArticles > 3) {
    
    var inner = $(".carousel-inner");
    
    indicators.removeClass('hide');
    $('.carousel-control').removeClass('hide');
    $('.carousel-dropdown').removeClass('hide');
    
    // Indicate first slide page
    var items = inner.find('.item')
    items.removeClass('active').removeClass('carousel-onload').first().addClass('active');
    
    var firstSlide = $(".carousel-inner .hero-link-wrapper").first();
    
    var rowMargin = (inner.width() - (firstSlide.width() * 3)) / 2;
    
    var collapseHeightAfter = firstSlide.height();
    var collapseHeightBefore = $('.carousel-outer').height();
    var slideHeighExpanded = Math.ceil((collapseHeightAfter * slidesSize) + ((slidesSize -1) * rowMargin) + 15);
    var fadeSpeed = 400;
    
    var hiddenElements = $("#navCarousel .item[aria-hidden='true']");
    
    var carouselView = $('.carousel-view');
    var listView = $('.carousel-view');
    
    var carouselViewPref = localStorage.getItem('carouselView'); // View preference
    
    if (carouselViewPref === 'false') {
        hiddenElements.css("display", "block");
        inner.addClass("list");
        inner.css("height", slideHeighExpanded);
        //inner.removeAttr('style');
        
        $(".carousel-control").css("display", "none");
        $(".carousel-indicators").css("display", "none");
        
        $(".list-view").attr('data-selected', true);
        $(".carousel-view").attr('data-selected', false);
    }
    else if (carouselViewPref === null) {
        localStorage.setItem('carouselView', 'true');
    }
    
    $(".list-view").on("click", function (e) {
      e.preventDefault();
      
      carouselViewPref = localStorage.getItem('carouselView');
      
      if (carouselViewPref !== null && carouselViewPref === 'true') {
        localStorage.setItem('carouselView', 'false');
      }
      
      var thisListView = $(this);
      
      if (thisListView.attr('data-selected') === 'true' || carouselView.attr('data-is-sliding') === 'true') {
        return false;
      }
      else {
        thisListView.attr('data-selected', true).removeAttr('style');
        $('.carousel-view').attr('data-selected', false);
      }
      
      thisListView.attr('data-is-sliding', true);
      
      $(".carousel-control").fadeOut(fadeSpeed);
      $(".carousel-indicators").fadeOut(fadeSpeed, function() {
        
        inner.css("height", collapseHeightBefore);
        
        items.css("display", "block");
        inner.addClass("list");
        
        inner.animate({
          height: slideHeighExpanded
        },
        {
          duration: 500
        }).promise().done(function () {
          inner.removeAttr('style');
          thisListView.attr('data-is-sliding', false);
        });
      });
    });
    
    $(".carousel-view").on("click", function (e) {
      e.preventDefault();
      
      carouselViewPref = localStorage.getItem('carouselView');
      
      if (carouselViewPref !== null && carouselViewPref === 'false') {
        localStorage.setItem('carouselView', 'true');
      }
      
      var thisCarouselView = $(this);
      
      if ($(this).attr('data-selected') === 'true' || listView.attr('data-is-sliding') === 'true') {
        return false;
      }
      else {
        thisCarouselView.attr('data-selected', true).removeAttr('style');
        $('.list-view').attr('data-selected', false);
      }
      
      thisCarouselView.attr('data-is-sliding', true);
      
      inner.animate({
        height: collapseHeightBefore
      },
      {
        duration: 500
      }).promise().done(function () {
        
        //inner.css("height", collapseHeightAfter);
        inner.removeAttr('style');
        
        thisCarouselView.attr('data-is-sliding', false);
        inner.removeClass("list").find(".item").removeAttr("style");
        $(".carousel-indicators").fadeIn(fadeSpeed);
        $(".carousel-control").fadeIn(fadeSpeed);
      });
    });
    
    $(".hide-all").on("click", function (e) {
      e.preventDefault();
    });
  }
};


/*
 * Carousel initialization
 */

$(function () {
  
  $('.carousel').carousel({
    interval: false
  });
  
  var itemsContainer = $('#carousel-items');
  var items = itemsContainer.find('div.item > div').length;
  
  var numSlides = itemsContainer.find('div.item').length;
  
  itemsContainer.attr('aria-valuemax', numSlides);
  
  if (items > 3 && items <= 6) {
    navno[ 'displayCarousel'](items);
  } else if (items > 6) {
    
    var indicators = $('.carousel-indicators');
    
    for (var i = 2; i < numSlides; i++) {
      $('.carousel-indicators').append('<li class="" data-slide-to="' + i + '" data-target="#navCarousel" aria-selected="true" aria-controls="carousel-items" role="button" tabindex="0"></li>');
    }
    navno[ 'displayCarousel'](items);
  }
});


/*
 * Using left and right slide controls with keyboard
 */

$(function () {
  var carousel = $('#navCarousel');
  var slideItems = carousel.find(".item");
  var leftControl = $('.carousel-control.left');
  var rightControl = $('.carousel-control.right');
  
  $(carousel).add(slideItems).add(leftControl).add(rightControl).on("keyup", function (event) {
    
    if (event.keyCode == 37) {
      $(leftControl).click();
    } else if (event.keyCode == 39) {
      $(rightControl).click();
    }
  });
});



navno.dynmaicCarouselBoxHeight = function () {
  
  var rowCount = $('#carousel-items').find('div.item').length;
  var itemsParent = $('#carousel-items');
  var padding = 54.75;
  // padding top + bottom
  
  for (var i = 0; i < rowCount; i++) {
    
    var nthItem = itemsParent.find('div.item:nth-child(' +(i + 1) + ')');
    var heroLinks = nthItem.find('a.hero-link');
    
    var rowLinks = new Array(heroLinks.length);
    
    var highestValue = 0;
    heroLinks.each(function (index) {
      
      rowLinks[index] = $(this);
      currentHeight = $(this).height();
      
      if (index === 0) {
        highestValue = currentHeight;
      } else if (highestValue < currentHeight) {
        highestValue = currentHeight;
      }
    });
    $.each(rowLinks, function (index, heroLink) {
      heroLink.css('height', (highestValue + padding) + 'px');
    });
  }
};


/*
 * Matchmedia fallback when flexbox is not supported
 */

$(function () {
  
  if (! Modernizr.flexbox) {
    var matchNarrowRange = window.matchMedia('(min-width: 620px) and (max-width: 669px)');
    var matchMidRange = window.matchMedia('(min-width: 670px) and (max-width: 719px)');
    var matchWiderRange = window.matchMedia('(min-width: 720px) and (max-width: 768px)');
    
    var indicators = $('ol.carousel-indicators');
    
    // Need to update resizing of boxes to fit text
    if (matchNarrowRange.matches || matchMidRange.matches || matchWiderRange.matches) {
      navno[ 'dynmaicCarouselBoxHeight']();
    }
    
    var mqHandler = function (matchMedia) {
      
      if (typeof matchMedia === "object" && matchMedia.matches) {
        navno[ 'dynmaicCarouselBoxHeight']();
      } else {
        
        $('#carousel-items div.item a.hero-link').each(function () {
          $(this).css("height", "");
        });
      }
    };
    
    matchNarrowRange.addListener(mqHandler);
    matchMidRange.addListener(mqHandler);
    matchWiderRange.addListener(mqHandler);
  }
});