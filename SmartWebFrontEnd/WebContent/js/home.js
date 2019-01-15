var appHome = angular.module('homeApp', [ 'ngRoute']);

appHome.directive('fileModel', ['$parse', function ($parse) {
    return {
       restrict: 'A',
       link: function(scope, element, attrs) {
          var model = $parse(attrs.fileModel);
          var modelSetter = model.assign;
          element.bind('change', function(){
             scope.$apply(function(){
                modelSetter(scope, element[0].files[0]);
             });
          });
       }
    }; 
}]);

appHome.controller("indexController",function($http, $window,$scope,$interval,$location,$rootScope) {
	var checkLogin = $interval(function() {
		if($window.localStorage.getItem("token")) {
			$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
			$http.get("http://localhost:8080/whoami").then(function(res) {
				$scope.account = res.data;
				$scope.check=true;
				$scope.checkAdmin = false;
				if($scope.account.role!="CANDIDATE") {
					$scope.checkAdmin = true;
				}
				$interval.cancel(checkLogin);
			},function(res) {
				$scope.check=false;
				$window.localStorage.removeItem("token");
			});
		} else {
			$scope.check=false;
		}
	}, 100);
	 $http.get("http://localhost:8080/category-news").then(function(res) {
			$scope.newsCategory = res.data;
		},function(res) {
			console.log("Fail, Can not get data newsCategory");
		});

	  $scope.logout = function () {
		$window.localStorage.removeItem("token");
		$scope.check=false;
		var checkLogin = $interval(function() {
			if($window.localStorage.getItem("token")) {
				$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
				$http.get("http://localhost:8080/whoami").then(function(res) {
					$scope.account = res.data;
					$scope.check=true;
					$interval.cancel(checkLogin);
				},function(res) {
					$scope.check=false;
					$window.localStorage.removeItem("token");
				});
			} else {
				$scope.check=false;
			}
		}, 100);
		$location.path("/");
	}
	$scope.changePassword = function () {
		var oldPassword = $scope.oldPassword;
		var newPassword = $scope.newPassword;
		var reNewPassword = $scope.reNewPassword;
		if(newPassword==reNewPassword) {
			var p = $.param({
				"oldPassword" : oldPassword,
				"newPassword" : newPassword
			});
			var req = {
				method: 'POST',
				url: $rootScope.linkapi + "change-password",
				headers: {
					'Accept': 'text/plain',
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				data: p
			}
			$http(req).then(function(res) {
				if(res.data=="success") {
					alert("Success");
					$("#changepassword").modal("toggle");
					$scope.oldPassword = "";
					$scope.newPassword = "";
					$scope.reNewPassword = "";
				} else {
					alert("Nhập sai mật khẩu hiện tại");
				}
			}, function(error) {
				// body...
			});
		} else {
			alert("Mật khẩu nhập lại không trùng");
		}
	}
	$http.get("http://localhost:8080/setting/1").then(function(res) {
			$scope.setting = res.data;
		},function(res) {
			console.log("Fail, Can not get data setting");
		});
});

appHome.config(function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : 'home.html',
		controller : 'homeController'
	}).when("/tin-tuc/:idCategory", {
		templateUrl : 'sm_danh_muc_tin_tuc.html',
		controller : 'newsController'
	}).when("/job", {
		templateUrl : 'sm_danh_muc_cong_viec.html',
		controller : 'jobsController'
	}).when("/chi-tiet-tin-tuc/:id", {
		templateUrl : 'sm_chi_tiet_tin_tuc.html',
		controller : 'newsDetailController'
	}).when('/chi-tiet-cong-viec/:jobId', {
		templateUrl : 'sm_chi_tiet_cong_viec.html',
		controller : 'jobsDetailController'
	}).when("/signup", {
		templateUrl : 'sm_dang_ky.html',
		controller : 'registerController'
	}).when("/login", {
		templateUrl : 'sm_dang_nhap.html',
		controller : 'loginController'
	}).when("/trang-thai-ho-so", {
		templateUrl : 'sm_trang_thai_ho_so.html',
		controller : 'statusController'
	}).when("/ho-so-ca-nhan", {
		templateUrl : 'sm_ho_so_ca_nhan.html',
		controller : 'hscnController'
	});
});
appHome.controller("homeController", function($scope,$http) {
	   $http.get("http://localhost:8080/job/hot").then(function(res) {
			$scope.jobHot = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data JobHot");
		});
	   $http.get("http://localhost:8080/news/hot").then(function(res) {
			$scope.newsHot = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data newsHot");
		});
	   $http.get("http://localhost:8080/slider").then(function(res) {
			$scope.slide = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data Slide");
		});
	  
});

appHome.controller('jobsDetailController', function($scope,$http,$routeParams,$rootScope){
		var a = $routeParams.jobId;
	   	var link = "http://localhost:8080/job/"+a;
	   $http.get(link).then(function(res) {
			$scope.job = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data Job");
		});
	   $http.get("http://localhost:8080/job/hot").then(function(res) {
			$scope.jobHot = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data JobHot");
		});

	   $scope.applyProfile = function() {
	   	if ($scope.check) {

	   	if($scope.cv==undefined) {
			alert("Hãy tải CV lên");
			return;
		}
	   	var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
            	'Accept': 'text/plain',
                'Content-Type': undefined
            }
        }
        var url = $rootScope.linkapi + "cv";
        var data = new FormData();
        data.append("file", $scope.cv);
        
        $http.post(url, data, config).then(function(res) {
        	$http.get("http://localhost:8080/whoami").then(function(res) {
			$scope.account = res.data;
			console.log(res.data);
		}, function(error) {
        		// body...
        	});
        	var applyObj ={
        		"account": {
				    "accountId": $scope.account.accountId,
				  },
				  "applyProfleId": 0,
				  "cv": res.data,
				  "jobDetail": {
				    "jobId": a,
				  },
				  "notes": $scope.notes,
				  "status": false
        	}
        	
        	$http.post($rootScope.linkapi + "ungtuyen", applyObj, {}).then(function(res) {
        		alert("Chúc mừng bạn đã ứng tuyển thành công!");
        	}, function(error) {
        		// body...
        	});
        },function(error) {
			//body
		});
    }else{
    	alert("Bạn cần đăng nhập để ứng tuyển");
    	// $location.path("/#!login");
	   }
    }
});


appHome.controller("jobsController", function($scope,$http,$rootScope) {

	   $http.get("http://localhost:8080/job/hot").then(function(res) {
			$scope.jobHot = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data JobHot");
		});
	  
	   $http.get("http://localhost:8080/category-job").then(function(res) {
			$scope.jobCategory = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data job category");
		});
	   $http.get("http://localhost:8080/city").then(function(res) {
			$scope.city = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data city");
		});
	    $http.get("http://localhost:8080/job/1/10").then(function(res) {
			$scope.job = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data job");
		});
	   //Phan Trang
		$scope.show = 10;
		$scope.page = 1;

		$http.get($rootScope.linkapi + "job/page-size/"+$scope.show).then(function(res) {
			$scope.pageSize = res.data;
		},function(error) {
			//do something;
		});


		$scope.getNumber = function(num) {
	    	return new Array(num);
		}

		$scope.redirectPage = function(page) {
			$scope.page = page;
			$http.get($rootScope.linkapi + "job/" + $scope.page + "/" + $scope.show).then(function(res) {
				$scope.job = res.data;
			},function(error) {
				//do something;
			});
		}
});

 appHome.controller("newsController", function($rootScope, $scope, $http, $routeParams) {

 		var idCategory = $routeParams.idCategory;
	   $http.get("http://localhost:8080/news/list-by-category/" + idCategory).then(function(res) {
			$scope.newsList = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data News");
		});
	   //Phan Trang
		$scope.show = 10;
		$scope.page = 1;

		$http.get($rootScope.linkapi + "news/page-size/" + $scope.show).then(function(res) {
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
				$scope.newsList = res.data;
			},function(error) {
				//do something;
			});
		}

});


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


