
var appAdmin = angular.module('adminApp', ['ngRoute']);

// appAdmin.run(function($rootScope, $http, $window, $interval) {
// 	$rootScope.linkapi = "http://localhost:8080/";
// 	if($window.localStorage.getItem("token")) {
// 		$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
// 		$http.get($rootScope.linkapi + "whoami").then(function(res) {
// 			$rootScope.currentUser = res.data;
// 			if($rootScope.currentUser.role=="CANDIDATE") {
// 				alert("Không có quyền truy cập");
// 				$window.location.href = "../";
// 			}
// 		}, function (error) {
// 			$window.localStorage.removeItem("token");
// 		});
// 	} else {
// 		alert("Không có quyền truy cập ! Xin mời đăng nhập !");
// 		$window.location.href = "../#!/login";
// 	}
// 	var checkLogin = $interval(function() {
// 		if($window.localStorage.getItem("token")) {
// 			$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
// 			$http.get($rootScope.linkapi + "whoami").then(function(res) {
// 				//body
// 			}, function (error) {
// 				$window.localStorage.removeItem("token");
// 				$window.location.href = "../#!/login";
// 				$interval.cancel(checkLogin);
// 			});
// 		}
// 	}, 60000);
// });

appAdmin.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl : '_profile-admin.html',
		controller : 'adminController'
	}).when("/quan-ly-tin-tuc", {
		templateUrl : '_quanlytintuc.html',
		controller : 'postController'
	}).when("/quan-ly-cong-viec", {
		templateUrl : '_quanlycongviec.html',
		controller : 'jobController'
	}).when("/quan-ly-tai-khoan", {
		templateUrl : '_quanlytaikhoan.html',
		controller : 'accountController'
	}).when("/quan-ly-ho-so-ung-tuyen", {
		templateUrl : '_quanlyhoso.html',
		controller : 'applyController'
	}).when("/quan-ly-slide", {
		templateUrl : '_quanlyslide.html',
		controller : 'slideController'
	}).when("/quan-ly-danh-muc-tin-tuc", {
		templateUrl : '_danhmuctintuc.html',
		controller : 'newsCategoryController'
	}).when("/quan-ly-danh-muc-cong-viec", {
		templateUrl : '_danhmuccongviec.html',
		controller : 'jobCategoryController'
	}).when("/setting", {
		templateUrl : '_setting.html',
		controller : 'settingController'
	}).otherwise({
		templateUrl : "_profile-admin.html"
	});
});