(function() {

	var CONFIG = {
		SNIPPETS_BASE_URL: "nav-design/snippets/"
	};

	var app = angular.module("NavDesignApp", ['ngRoute']);

	app.controller("TemplateController", function($scope, $location, $routeParams, $http, $sce, $timeout) {
		$scope.html = "";
		var url = CONFIG.SNIPPETS_BASE_URL + $routeParams.templateName + ".html?random=" + Math.random().toString(36).substring(10);
		$http.get(url).success(function(response) {
			$scope.html = response;
		}).error(function() {
			$scope.html = "<p>Ehm, dette kjenner jeg ikke til! :-(</p>";
		});
	});

	app.controller("IndexController", function($scope, $location) {
		$location.path("/snippet/read-me");
	});

	app.controller("HeaderController", function($scope, $routeParams, $location) {

		$scope.snippets = window.snippets; 
		$scope.currentPage = $routeParams.templateName || $scope.snippets[0];
		$scope.displayTextarea = window.localStorage.getItem("displayTextarea") === "true";
		$scope.buttonText = $scope.displayTextarea ? "Skjul kode" : "Vis kode";	

		$scope.displaySnippet = function(snippet) {
			$location.path("/snippet/" + snippet);
		};

		$scope.toggleTextarea = function() {
			$scope.displayTextarea = !$scope.displayTextarea;
			$scope.buttonText = $scope.displayTextarea ? "Skjul kode" : "Vis kode";
			window.localStorage.setItem("displayTextarea", $scope.displayTextarea);
		};
	
	});

	app.directive("demoHeader", function() {
		return {
			templateUrl: "assets/partials/_header.html",
			restrict: "E",
			replace: true
		}
	});

	app.filter('unsafe', function($sce) { return $sce.trustAsHtml; });

	app.config(function($routeProvider) {
		$routeProvider
			.when('/snippet/:templateName', {
				templateUrl: 'assets/templates/preview.html',
				controller: 'TemplateController'
			})
			.otherwise({
				controller: 'IndexController',
				templateUrl: 'assets/templates/preview.html',
			});
	});

})();