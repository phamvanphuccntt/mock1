appHome.run(function($rootScope, $http, $window) {
	$rootScope.linkapi = "http://localhost:8080/";
});

appHome.controller('loginController', function($scope, $http, $rootScope, $location, $window) {
	if($window.localStorage.getItem("token")) {
		alert("Bạn đã đăng nhập từ trước, vui lòng đăng xuất để đăng nhập tài khoản khác");
		$location.path("/");
	}
	if($window.localStorage.getItem("user")) {
		$scope.username = JSON.parse($window.localStorage.getItem("user")).username;
		$scope.password = JSON.parse($window.localStorage.getItem("user")).password;
		$scope.remember = true;
	}
	$scope.login = function() {
		var user = $.param({
			username : $scope.username,
			password : $scope.password
		});
		var link = $rootScope.linkapi + "login";
		var req = {
			method: 'POST',
			url: link,
			headers: {
				'Accept': 'text/plain',
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			data: user
		}
		$http(req).then(function(res) {
			if(res.data=="not found") {
				alert("Tài khoản không tồn tại");
			} else if(res.data=="fail") {
				alert("Mật khẩu không chính xác");
			} else if(res.data=="not activated") {
				alert("Tài khoản chưa kích hoạt");
			} else {
				if($scope.remember) {
					var u = {'username' : $scope.username, 'password' : $scope.password}
					$window.localStorage.setItem("user", JSON.stringify(u));
				} else {
					$window.localStorage.removeItem("user");
				}
				$http.defaults.headers.common.Authorization = 'Bearer ' + res.data;
				$window.localStorage.setItem("token",res.data);
				$http.get($rootScope.linkapi + "whoami").then(function(res) {
					var account = res.data;
					var date = new Date();
					var dateStr = date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear()+" "+date.getHours()+":"+date.getMinutes();
					account.lastAccess = dateStr;
					$http.put($rootScope.linkapi + "account/" + account.username, account, {}).then(function (res) {
						// body...
					}, function (error) {
						// body...
					});
					if(res.data.role == "CANDIDATE") {
						$location.path("/");
					} else {
						$window.location.href = "Admin";
					}
				}, function (error) {
					// body...
				});
			}
		},function(error) {
			console.log(error);
		});
	}
});