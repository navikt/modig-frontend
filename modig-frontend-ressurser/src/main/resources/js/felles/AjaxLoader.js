(function () {
    "use strict";

    var AjaxLoader = function AjaxLoader() {
        this.loaders = [];
        this.init();
    };

    AjaxLoader.prototype.init = function init() {
        var self = this;
        $(document).ajaxSend(function (event, jqxhr, settings) {
            var len = self.loaders.length;
            for (var i = 0; i < len; i++){
                var loader = self.loaders[i]; 
                if (loader.urlPattern.test(settings.url)){
                    self.showLoader(loader.placeElement, loader.placement, loader.imageUrl, loader.css);
                }
            } 
        });
        $(document).ajaxComplete(function (event, jqxhr, settings) {
            var len = self.loaders.length;
            for (var i = 0; i < len; i++){
                var loader = self.loaders[i];
                if (loader.urlPattern.test(settings.url)){
                    self.hideLoader(loader.placeElement);
                }
            }
        })
    };
    
    AjaxLoader.prototype.register = function register(options) {
        var defaults = {
            urlPattern: /.*/,
            placeElement: null,
            placement: 'append',
            imageUrl: '../ajaxloader/graa/loader_graa_48.gif',
            css: ''
        };

        var obj = $.extend(defaults, options);

        if (!obj.urlPattern instanceof RegExp) {
            console.error('option "urlPattern" må være et RegExp objekt');
            return;
        }
        this.loaders.push(obj)

    };
    
    AjaxLoader.prototype.hideLoader = function hideLoader(placeElement) {
        var $imageElement = $("#modiaAjaxLoader");

        if ($imageElement === null) {
            console.error('Fant ikke snurrepip.');
            return;
        }

        $imageElement.remove();
    };
    
    AjaxLoader.prototype.showLoader = function showLoader(placeElement, placement, imageUrl, css) {
        var image = '<img id="modiaAjaxLoader" style="' + css + '" src="' + imageUrl + '" />';
        var $placeElement = $(placeElement);
        
        if ($placeElement === null) {
            console.error('option "placeElement" må angis for å kunne plassere snurrepip');
        }
        
        var noSnurrePip = $('#modiaAjaxLoader').length === 0;

        if ($placeElement !== null && noSnurrePip) {
            if (placement === 'append') {
                $placeElement.append(image);
            } else if (placement === 'replace') {
                $placeElement.replaceWith(image);
            } else if (placement === 'after') {
                $placeElement.after(image);
            } else if (placement === 'before') {
                $placeElement.before(image);
            } else {
                $placeElement.prepend(image);
            }
        }
    };

    
    if (typeof(Modig) === "undefined") {
        window.Modig = {};
    }
    
    window.Modig.ajaxLoader = new AjaxLoader();
})();

