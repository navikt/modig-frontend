var gulp = require('gulp');
var less = require('gulp-less');
var svgspritesheet = require("gulp-svg-spritesheet");
var path = require("path");

gulp.task('svg', function () {
    gulp.src('content/nav-design/svg/*.svg')
    .pipe(svgspritesheet({
        cssPathSvg: 'images/sprite.svg',
        templateSrc: 'less.tpl',
        templateDest: 'content/nav-design/less/sprite.less'
    }))
    .pipe(gulp.dest('public/svg/sprite.svg'));
});

gulp.task('default', function() {

    return gulp.src('content/nav-design/less/nav.less')
        .pipe(less({
            paths: [path.join(__dirname, 'less', 'includes')]
        }))
        .pipe(gulp.dest('./public/css'));

});