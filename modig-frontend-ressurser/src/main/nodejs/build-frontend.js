var args = process.argv;
var argsLength = args.length;
if (argsLength < 3) {
	var hash = '\n##################################################\n';
	console.log('\nFor få argumenter:' + hash + 'Riktig bruk er: node build-bootstrap.js src-katalog' + hash);
}
console.log('Arguments: ', args);

var baseFolder = args[2];
var resourceFolder = baseFolder + "/src/main/resources";
var generateFolder = baseFolder + "/src/main/generated-resources";

var fs = require('fs');
var util = require('util');
var remove = require('remove');
var ugly = require('uglifyjs');
var less = require('less');



// SLETT OG GJENOPPRETT GENERATED-RESOURCES
clean();


// KOPIER JAVASCRIPT-FILER
copy(resourceFolder + '/js/bootstrap/', generateFolder + '/js/bootstrap/');
copy(resourceFolder + '/js/felles/', generateFolder + '/js/modig/felles/');
copy(resourceFolder + '/js/jquery/', generateFolder + '/js/modig/jquery/');
copy(resourceFolder + '/js/jquery/', generateFolder + '/js/bootstrap/jquery/');

copyFile(resourceFolder + '/js/', generateFolder + '/js/bootstrap/', 'console.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/bootstrap/', 'html5.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/bootstrap/', 'html5.min.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/bootstrap/', 'underscore.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/bootstrap/', 'underscore-min.js');

copyFile(resourceFolder + '/js/', generateFolder + '/js/modig/', 'console.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/modig/', 'html5.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/modig/', 'html5.min.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/modig/', 'underscore.js');
copyFile(resourceFolder + '/js/', generateFolder + '/js/modig/', 'underscore-min.js');


// KOPIER LESS-FILER
copy(resourceFolder + '/less/bootstrap/', generateFolder + '/less/bootstrap/');
copy(resourceFolder + '/less/felles/', generateFolder + '/less/modig/felles/');
copy(resourceFolder + '/less/ekstern/', generateFolder + '/less/modig/ekstern/');


// KOPIER CSS-FILER
copy(resourceFolder + '/css/', generateFolder + '/css/modig/');


// KOPIER BILDER
copy(resourceFolder + '/img/', generateFolder + '/img/');



// BOOTSTRAP:

// SLÅ SAMMEN JAVASCRIPTFILER
var files = fs.readdirSync(resourceFolder + '/js/bootstrap/');
concat(resourceFolder + '/js/bootstrap/', generateFolder + '/js/bootstrap/bootstrap.js', files);

// MINIFIERE JAVASCRIPTFIL
uglifyConcatenated(generateFolder + '/js/bootstrap/bootstrap.js', generateFolder + '/js/bootstrap/bootstrap.min.js');

// SLÅ SAMMEN OG MINIFIERE LESS-FILER
runLess(resourceFolder + '/less/bootstrap/', resourceFolder + '/less/bootstrap/bootstrap.less', generateFolder + '/css/bootstrap/bootstrap.css');



// MODIG:

// SLÅ SAMMEN JAVASCRIPTFILER
files = ['transitions.js', 'felles.js', 'dropdown.js', 'tooltip.js', 'AjaxLoader.js'];
concat(resourceFolder + '/js/felles/', generateFolder + '/js/modig/modig.js', files);

// MINIFIERE JAVASCRIPTFIL
uglifyConcatenated(generateFolder + '/js/modig/modig.js', generateFolder + '/js/modig/modig.min.js');

// SLÅ SAMMEN OG MINIFIERE LESS-FILER
files = ['reset.less', 'variables.less', 'mixins.less', 'modus.less', 'typografi.less', 'felles.less', 'knapper.less',
	'contrast.less', 'dropdowns.less', 'skjema.less', 'tooltip.less', 'utilities.less'];
concat(resourceFolder + '/less/felles/', generateFolder + '/less/modig/felles.less', files);

concat(resourceFolder + '/less/ekstern/', generateFolder + '/less/modig/ekstern.less', ['panel.less', 'footer.less', 'ekstern.less']);
concat(generateFolder + '/less/modig/', generateFolder + '/less/modig/modig.less', ['felles.less', 'ekstern.less']);

remove.removeSync(generateFolder + '/less/modig/felles.less');
remove.removeSync(generateFolder + '/less/modig/ekstern.less');

runLess(generateFolder + '/less/modig/', generateFolder + '/less/modig/modig.less', generateFolder + '/css/modig/modig.css');



function clean() {
	var folder = generateFolder + '/';
	try {
		if (fs.existsSync(folder)) {
			remove.removeSync(folder);
			console.log('Sletting av generated-resources OK!');
		}
	} catch (err) {
		throw(err);
	}
	fs.mkdirSync(folder);
	fs.mkdirSync(folder + 'css/');
	fs.mkdirSync(folder + 'css/bootstrap/');
	fs.mkdirSync(folder + 'css/modig/');
	fs.mkdirSync(folder + 'img/');
	fs.mkdirSync(folder + 'js/');
	fs.mkdirSync(folder + 'js/bootstrap/');
	fs.mkdirSync(folder + 'js/bootstrap/jquery/');
	fs.mkdirSync(folder + 'js/modig/');
	fs.mkdirSync(folder + 'js/modig/felles/');
	fs.mkdirSync(folder + 'js/modig/jquery/');
	fs.mkdirSync(folder + 'less/');
	fs.mkdirSync(folder + 'less/bootstrap/');
	fs.mkdirSync(folder + 'less/modig/');
	fs.mkdirSync(folder + 'less/modig/ekstern/');
	fs.mkdirSync(folder + 'less/modig/felles/');
}

function copy(fromDir, toDir) {
	var files = fs.readdirSync(fromDir);
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		copyFile(fromDir, toDir, file);
	}
}

function copyFile(fromDir, toDir, file) {
	console.log('Kopier ' + fromDir + file + ' -> ' + toDir);
	if (fs.statSync(fromDir + file).isFile()) {
		var fullfile = fs.readFileSync(fromDir + file);
		fs.writeFileSync(toDir + file, fullfile);
	}
}

function concat(fromDir, toFile, files) {
	var fullfile = '';
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		fullfile += fs.readFileSync(fromDir + file, 'utf8');
	}
	fs.appendFileSync(toFile, fullfile, encoding = 'utf8');
}

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
	return ugly.minify(code, {fromString: true, output: {comments: true}}).code;
}

function uglifyConcatenated(fromFile, toFile) {
	var code = fs.readFileSync(fromFile, 'utf8');
	var finalCode = makeUgly(code);
	fs.writeFileSync(toFile, finalCode, 'utf8');
}

function runLess(lessDir, lessFile, cssFile) {
	console.log('Running less. Dir: ' + lessDir + ' lessFile: ' + lessFile + ' cssFile: ' + cssFile);
	var code = fs.readFileSync(lessFile, 'utf8');
	var parser = new (less.Parser)({
		paths   : [lessDir],
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

