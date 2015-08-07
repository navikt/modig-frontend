/*
*
* Custom feature detection utover hva som kommer ut av boksen fra modernizr
* Dette scriptet bygges sammen med modernizr.js, og serves som featureDetection.min.js fra dekoratør v3
*
* */


(function () {

    Modernizr.addTest('flexbox', Modernizr.testAllProps('flex'));


    if (document.all && !window.atob) {
        // dette er IE9, ref http://tanalin.com/en/articles/ie-version-js/
        document.documentElement.className =  document.documentElement.className + ' old-browser';
    }

})();
