// ------------------ \\
//   Carousel stuff   \\
// ------------------ \\

var navno = window.navno || {};

navno.displayCarousel = function (numArticles) {
  
  var indicators = $('.carousel-indicators');
  
  var slidesSize = indicators.find('li').length;
  indicators.attr('data-active-slide', 0).attr('aria-valuemax', slidesSize);
  
  $('#navCarousel').attr("data-size", slidesSize);
  
  var showAllCount = $('.carousel-submenu .boxcol-4').length;
  var numRows = Math.ceil(showAllCount / 4);
  
  var ieControl = $('.col-lg-12 > a.carousel-control');
  
  if (ieControl.length > 0) {
    ieControl.remove();
    // TODO in bootstrap: IE bug workaround
  } else if ($('.col-lg-12 > div[id^="marker"] > a.carousel-control').length > 0) {
    $('.col-lg-12 > div[id^="marker"] > a.carousel-control').remove();
  }
  
  if (numArticles > 3) {
    
    indicators.removeClass('hide');
    $('.carousel-control').removeClass('hide');
    $('.carousel-dropdown').removeClass('hide');
    
    var inner = $('.carousel-inner');
    inner.find('.span4').removeClass('span4-grid');
    
    // Indicate first slide page
    var items = inner.find('.item');
    items.removeClass('active').removeClass('carousel-onload');
    
    items.first().addClass('active');
    
    items.find('.featured-content').removeClass('featured-content-grid');
    
    var elemObject = $('.carousel-subcontainer');
    elemObject.addClass('hide');
    
    $('.carousel-dropdown a.btn').click(function (e) {
      // Show all
      var showAllBtn = $(this);
      
      e.preventDefault();
      
      var height = (numRows * 72) + 1;
      
      var arrow = $(this).find('span');
      
      if (elemObject.hasClass("hide")) {
        
        elemObject.css("height", "0");
        elemObject.removeClass("hide");
        
        elemObject.animate({
          height: height
        },
        {
          duration: "fast",
          start: function () {
            
            showAllBtn.text(showAllBtn.attr('data-hide-translation'));
          },
          complete: function () {
            elemObject.css("height", height);
            $('#carousel-boxgrid').attr('aria-expanded', 'true');
          }
        });
      } else {
        
        elemObject.animate({
          height: 0
        },
        {
          duration: "fast",
          start: function () {
            showAllBtn.text(showAllBtn.attr('data-show-translation'));
          },
          complete: function () {
            
            elemObject.addClass('hide');
            $('#carousel-boxgrid').attr('aria-expanded', 'false');
          }
        });
      }
    });
  }
};

(function carousel() {
  
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
}
());

(function onFocusedCarousel() {
  
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
}
());



navno.dynmaicCarouselBoxHeight = function () {

  var rowCount = $('#carousel-items').find('div.item').length;
  var itemsParent = $('#carousel-items');
  var padding = 54.75; // padding top + bottom
  
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


(function doMatchMedia() {
  
  var matchNarrowRange = window.matchMedia('(min-width: 620px) and (max-width: 669px)');
  var matchMidRange = window.matchMedia('(min-width: 670px) and (max-width: 719px)');
  var matchWiderRange = window.matchMedia('(min-width: 720px) and (max-width: 768px)');
  
  var indicators = $('ol.carousel-indicators');
  
  // Need to update resizing of boxes to fit text
  if (matchNarrowRange.matches || matchMidRange.matches || matchWiderRange.matches) {
    navno['dynmaicCarouselBoxHeight']();
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
());