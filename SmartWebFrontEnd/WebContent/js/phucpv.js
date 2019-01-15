appHome.controller('hscnController', hoSoCaNhan_btn);

	function hoSoCaNhan_btn($scope, $http,$window){	
		$scope.makeReadonly = true;
		$scope.hscn_update = true;
		$scope.hscn_reset = true;
		getAccount();
	 	function getAccount(){
	 		$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
	 	 	$http.get("http://localhost:8080/whoami").then(function(res) {
			$scope.hscn_account = res.data;
			console.log(res.data);
			getUser();
		},function(res) {
			console.log("Fail, Can not get data account");
		});
	 	}

	 	$scope.hscn_reset_click = function(){
	 		getUser();
	 	}

	 	function getUser(){
	 		$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
	 		
	 		// var accountId = $scope.hscn_account.accountId;
	 		 $http.get("http://localhost:8080/user/" + $scope.hscn_account.accountId).then(function(res) {
			$scope.hscn_user = res.data;
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data account");
		});
	 	}

	 	 	$scope.hscn_update_click = function(){
	 		$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
	 		$http.put("http://localhost:8080/user/", $scope.hscn_user).then(function(res) {
	 		console.log("Update success")
			console.log(res)
			$scope.hscn_update = true;
			$scope.hscn_reset = true;
			$scope.makeReadonly = true;
		},function(res) {
			console.log("Update fail");
		});
	 }
		$scope.hscn_edit_click = function(){
			$scope.makeReadonly = false;
			$scope.hscn_update = false;
			$scope.hscn_reset = false;
		}
	}
appHome.controller('statusController', statusController);
function statusController($scope, $http,$window){

		getDetailJob();
	 	function getDetailJob(){
	 		$http.defaults.headers.common.Authorization = 'Bearer ' + $window.localStorage.getItem("token");
	 	 	$http.get("http://localhost:8080/apply",$scope.hscn_account.accountId).then(function(res) {
			$scope.detailJobs = res.data;
			
			console.log(res.data);
		},function(res) {
			console.log("Fail, Can not get data account");
			$scope.detailJobs = data;
		});
	 	}
}



