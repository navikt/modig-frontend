(function($) {
	'use strict';

	$.fn.navAjaxLoader = function(options) {

		var defaultVal = {
			event       : 'click',
			placeElement: null,
			placement   : 'after',
			loadImage   : 'graa/loader_graa_48.gif',
			css         : ''
		};
		var obj = $.extend(defaultVal, options);

		return this.each(function() {
			var event = obj.event;
			var placeElement = obj.placeElement;
			var placement = obj.placement;
			var loadImage = obj.loadImage;
			var css = obj.css;
			$(this).on(event, function() {
				var image = '<img src="../img/ajaxloader/' + loadImage + '" style="' + css + '"/>';
				if (placeElement !== null) {
					if (placement === 'after') {
						placeElement.append(image);
					} else {
						placeElement.prepend(image);
					}
				} else {
					console.error('option "placeElement" må angis for å kunne plassere snurrepip');
				}
			});
		});
	};
}(jQuery));

