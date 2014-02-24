var args = process.argv;
var argsLength = args.length;
if (argsLength < 3) {
    var hash = '\n##################################################\n';
    console.log('\nFor få argumenter:' + hash + 'Riktig bruk er: node build-bootstrap.js src-katalog' + hash); }
console.log('Arguments: ', args);

var baseFolder = args[2];

var fs = require('fs');
var util = require('util');
var remove = require('remove');

var ugly = require('uglifyjs');

var less = require('less');


// SLETT OG GJENOPPRETT GENERATED-RESOURCES
clean();

// KOPIER BOOTSTRAP JAVASCRIPT-FILER
copy(baseFolder + '/src/main/bootstrap/js/', baseFolder + '/src/main/generated-resources/js/bootstrap/');

// KOPIER BOOTSTRAP LESS-FILER
copy(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/generated-resources/less/bootstrap/');

// KOPIER BOOTSTRAP BILDER
copy(baseFolder + '/src/main/bootstrap/img/', baseFolder + '/src/main/generated-resources/img/');

// SLÅ SAMMEN BOOTSTRAP JAVASCRIPT-FILER
concat(baseFolder + '/src/main/bootstrap/js/', baseFolder + '/src/main/generated-resources/js/modig.js');

// MINIFIERE BOOTSTRAP JAVASCRIPT-FILER ENKELTVIS
uglifySingles(baseFolder + '/src/main/bootstrap/js/', baseFolder + '/src/main/generated-resources/js/bootstrap/');

// MINIFIERE SAMMENSLÅTT JAVASCRIPT-FIL
uglifyConcatenated(baseFolder + '/src/main/generated-resources/js/modig.js', baseFolder + '/src/main/generated-resources/js/modig.min.js');

// SLÅ SAMMEN OG MINIFIERE CSS-FILER
runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/modig.less', baseFolder + '/src/main/generated-resources/css/modig.css');


// VENTER MED Å BYGGE MODULARISERT CSS TIL DETTE ER STØTTET I BOOTSTRAP
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/common.less', baseFolder + '/src/main/generated-resources/css/bootstrap/common.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/alert.less', baseFolder + '/src/main/generated-resources/css/bootstrap/alert.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/button.less', baseFolder + '/src/main/generated-resources/css/bootstrap/button.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/carousel.less', baseFolder + '/src/main/generated-resources/css/bootstrap/carousel.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/dropdown.less', baseFolder + '/src/main/generated-resources/css/bootstrap/dropdown.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/modal.less', baseFolder + '/src/main/generated-resources/css/bootstrap/modal.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/popover.less', baseFolder + '/src/main/generated-resources/css/bootstrap/popover.css');
//runLess(baseFolder + '/src/main/bootstrap/less/', baseFolder + '/src/main/tooltip.less', baseFolder + '/src/main/generated-resources/css/bootstrap/tooltip.css');


function clean() {
    try {
        if (fs.existsSync(baseFolder + '/src/main/generated-resources/')) {
            remove.removeSync(baseFolder + '/src/main/generated-resources/');
            console.log('Sletting av generated-resources OK!');
        }
    } catch (err) {
        throw(err);
    }
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/css/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/css/bootstrap/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/img/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/js/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/js/bootstrap/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/less/');
    fs.mkdirSync(baseFolder + '/src/main/generated-resources/less/bootstrap/');
}

function copy(fromDir, toDir) {
    console.log('Kopier filer fra ' + fromDir + ' til ' + toDir);
    var files = fs.readdirSync(fromDir);
    console.log(files.length + 'filer');
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        if (fs.statSync(fromDir + file).isFile()) {
            console.log('File: ' + i + ': ', file);
            var fullfile = fs.readFileSync(fromDir + file);
            fs.writeFileSync(toDir + file, fullfile);
        }
    }
}

function concat(fromDir, toFile) {
    var files = ['bootstrap-transition.js', 'bootstrap-alert.js', 'bootstrap-button.js', 'bootstrap-carousel.js',
        'bootstrap-collapse.js', 'bootstrap-dropdown.js', 'bootstrap-modal.js', 'bootstrap-tooltip.js',
        'bootstrap-popover.js', 'bootstrap-scrollspy.js', 'bootstrap-tab.js', 'bootstrap-typeahead.js',
        'bootstrap-affix.js'];
    var fullfile = '';
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        fullfile += fs.readFileSync(fromDir + file, 'utf8');
    }
    fs.appendFileSync(toFile, fullfile, encoding = 'utf8'); }

function uglifySingles(fromDir, toDir) {
    var files = fs.readdirSync(fromDir);
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        var ind = file.indexOf('.js');
        if (file.substring(0, 1) !== '.' && ind > -1) {
            console.log('File: ' + i + ': ', file);
            var code = fs.readFileSync(fromDir + file, 'utf8');
            var finalCode = makeUgly(code) + ';';
            fs.writeFileSync(toDir + file.substring(0, ind) + '.min.js', finalCode, 'utf8');
        }
    }
}

function makeUgly(code) {
    return ugly.minify(code, {fromString:true, output:{comments:true}}).code;
}

function uglifyConcatenated(fromFile, toFile) {
    var code = fs.readFileSync(fromFile, 'utf8');
    var finalCode = makeUgly(code);
    fs.writeFileSync(toFile, finalCode, 'utf8'); }

function runLess(lessDir, lessFile, cssFile) {
    console.log('Running less. Dir: ' + lessDir + ' lessFile: ' + lessFile + ' cssFile: ' + cssFile);
    var code = fs.readFileSync(lessFile, 'utf8');
    var parser = new (less.Parser)({
        paths: [lessDir],
        filename: lessFile
    });

    parser.parse(code, function (err, tree) {
        if (err) {
            console.error(err);
        }
        try {
            var css = tree.toCSS({ compress: true, yuicompress: true });
        } catch (e) {
            console.error(util.inspect(e));
            throw e;
        }
        fs.writeFileSync(cssFile, css, 'utf8');
    });
}

