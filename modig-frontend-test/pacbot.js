/*
 * Run "pacbot -d" in this folder to view the example
 * site in dev mode in your browser, or build an
 * uploadable dir by running "pacbot -b".
 */

exports.config = function() {

    // Create a new config object.
    var config = {
        port: 1234,
        assets: {}
    };

    config.root = "../";

    // Assets are specified by type, in groups,
    // and can be arrays of files or folders.
    // The assets are included in the layout file.

    config.assets.less = {
        'app-less': [
            'assets/less/app.less'
        ],

        'nav-decorator': [
            'nav-design/less/nav-decorator.less'
        ], 

        'nav-extra': [
            'nav-design/less/nav-extra.less'
        ]               
    };

    // Asset groups can also be just folders or a file.
    // Use folders if the inclusion order is arbitrary.
    config.assets.js = {
        'vendor': [
            'assets/js/vendor'
        ]
    };


    // Add some options for UglifyJS.
    config.uglifyjs = {
        output: { comments: /^!|@preserve|@license|@cc_on/i }
    };

    config.layouts = {
        'index.html': '_layouts/index.html'
    }

    // Return the config object.
    return config;

};
