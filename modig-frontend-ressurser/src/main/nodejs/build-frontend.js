var args = process.argv;
if (args.length < 3) {
	var hash = '\n##################################################\n';
	console.log('\nFor få argumenter:' + hash + 'Riktig bruk er: node build-frontend.js src-katalog' + hash);
}
console.log('Arguments: ', args);

var baseFolder = args[2] + '/src/main';
var fs = require('fs');
var util = require('util');
var remove = require('remove');
var ugly = require('uglifyjs');
var less = require('less');
var config = readConfigFile('/nodejs/build-frontend-config.json');

clean();        // SLETTE OG GJENOPPRETTE GENERATED-RESOURCES
copyFolders();  // KOPIERE KATALOGER
copyFiles();    // KOPIERE FILER
concatFiles();  // SLÅ SAMMEN FILER
uglifyFiles();  // MINIFIERE FILER
removeFiles();  // SLETTE FILER
runLessFiles(); // SLÅ SAMMEN OG MINIFIERE LESS-FILER


function clean() {
	var files = config.fileStructure;
	var folder = baseFolder + '/generated-resources/';
	try {
		if (fs.existsSync(folder)) {
			remove.removeSync(folder);
			console.log('Sletting av generated-resources OK!');
		}
	} catch (err) {
		console.error(err);
		throw(err);
	}
	if (!files) return;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		fs.mkdirSync(folder + file);
	}
}

function copyFolders() {
	var tasks = config.copyFolders;
	if (!tasks) return;
	for (var i = 0; i < tasks.length; i++) {
		var task = tasks[i];
		copyFolder(baseFolder + task.fromDir, baseFolder + task.toDir);
	}
}

function copyFolder(fromDir, toDir) {
	var files = fs.readdirSync(fromDir);
	if (!files) return;
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		copyFile(fromDir, toDir, file);
	}
}

function copyFiles() {
	var tasks = config.copyFiles;
	if (!tasks) return;
	for (var i = 0; i < tasks.length; i++) {
		var task = tasks[i];
		copyFile(baseFolder + task.fromDir, baseFolder + task.toDir, task.file);
	}
}


function copyFile(fromDir, toDir, file) {
	console.log('Kopier ' + fromDir + file + ' -> ' + toDir);
	if (fs.statSync(fromDir + file).isFile()) {
		var fullfile = fs.readFileSync(fromDir + file);
		fs.writeFileSync(toDir + file, fullfile);
	}
}

function concatFiles() {
	var tasks = config.concatFiles;
	if (!tasks) return;
	for (var i = 0; i < tasks.length; i++) {
		var task = tasks[i];
		concat(baseFolder + task.fromDir, baseFolder + task.toFile, task.files);
	}
}

function concat(fromDir, toFile, files) {
	if (files.indexOf('fs.readdirSync') === 0) {
		files = eval(files);
	}
	if (!files) return;
	var fullfile = '';
	for (var i = 0; i < files.length; i++) {
		var file = files[i];
		fullfile += fs.readFileSync(fromDir + file, 'utf8');
	}
	fs.appendFileSync(toFile, fullfile, encoding = 'utf8');
}

function uglifyFiles() {
	var tasks = config.uglify;
	if (!tasks) return;
	for (var i = 0; i < tasks.length; i++) {
		var task = tasks[i];
		uglifyConcatenated(baseFolder + task.fromFile, baseFolder + task.toFile);
	}
}

function uglifySingles(fromDir, toDir) {
	var files = fs.readdirSync(fromDir);
	if (!files) return;
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

function uglifyConcatenated(fromFile, toFile) {
	var code = fs.readFileSync(fromFile, 'utf8');
	var finalCode;
	try {
		finalCode = makeUgly(code);
	} catch(e) {
		console.log(e);
	}
	fs.writeFileSync(toFile, finalCode, 'utf8');
}

function makeUgly(code) {
	return ugly.minify(code, {fromString: true, output: {comments: true}}).code;
}

function removeFiles() {
	var tasks = config.remove;
	if (!tasks) return;
	for (var i = 0; i < tasks.length; i++) {
		remove.removeSync(baseFolder + tasks[i].fileName);
	}
}

function runLessFiles() {
	var tasks = config.runLess;
	if (!tasks) return;
	for (var i = 0; i < tasks.length; i++) {
		var task = tasks[i];
		runLess(baseFolder + task.fromDir, baseFolder + task.mainFile, baseFolder + task.builtFile);
	}
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

function readConfigFile(file) {
	return JSON.parse(fs.readFileSync(
			baseFolder + file,
		'utf-8',
		function (err, data) {
			if (err) {
				console.error(err);
				return;
			}
			data = JSON.parse(data);
			console.dir(data);
		}
	));
}
