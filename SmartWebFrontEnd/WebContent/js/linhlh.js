 appHome.controller("newsController", function($scope, $http) {
	   $http.get("http://localhost:8080/news").then(function(res) {
			$scope.newsList = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data News");
		});

	   //Phan Trang
		$scope.show = 10;
		$scope.page = 1;

		$http.get($rootScope.linkapi + "news/page-size/"+$scope.show).then(function(res) {
			$scope.pageSize = res.data;
		},function(error) {
			//do something;
		});


		$scope.getNumber = function(num) {
	    	return new Array(num);
		}

		$scope.redirectPage = function(page) {
			$scope.page = page;
			$http.get($rootScope.linkapi + "news/" + $scope.page + "/" + $scope.show).then(function(res) {
				$scope.job = res.data;
			},function(error) {
				//do something;
			});
		}

});

// appHome.controller('newsDetailController', function($rootScope, $scope, $http){	  
// 	   $http.get("http://localhost:8080/news-detail/"+ $rootScope.idNew).then(function(res) {
// 			$scope.news = res.data;
// 			console.log(res.data);
// 		},function(res) {
// 			console.log("Fail, Can not get data News");
// 		});	   
// });

appHome.controller('newsDetailController',function( $scope, $http, $routeParams){
	$scope.news = [];
	var id = $routeParams.id;
	 $http.get("http://localhost:8080/news/" + id).then(function(res) {
			$scope.news = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data News");
		});
	
});


