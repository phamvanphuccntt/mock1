appHome.controller('registerController', function($scope,$http,$window,$location, $rootScope){
	$scope.dangkytaikhoan = function(){

			var date = new Date();
        	var dateCreated = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();

	 var taikhoan = {
	 	
			  "accountId": 0,
			  "dateCreated": dateCreated,
			  "email": $scope.emailSign,
			  "fullname": $scope.NameSign,
			  "lastAccess": "null",
			  "notes": "null",
			  "password": "123456789",
			  "phone": $scope.phoneSign,
			  "role": "CANDIDATE",
			  "status": false,
			  "username": $scope.usernameSign
						}
		/// Đăng Ký tài khoản
					var req = {
							method: 'POST',
							url: "http://localhost:8080/signup",
							headers: {
									'Accept': 'text/plain'
									},
								data: taikhoan
									}
				$http(req).then(function(res) {
					console.log(res.data);
					if(res.data == "duplicate username"){
						alert("User Name đã tồn tại");
					}else if(res.data == "duplicate email"){
						alert("Email đã tồn tại");
					}else if(res.data == "duplicate phone"){
						alert("Số điện thoại đã tồn tại");
					}else{
						var user = {
							"account": {
								"accountId": parseInt(res.data)
							},
							"avatar": "",
							"education": "",
							"facebook": "",
							"graduationYear": 0,
							"nativeLand": "",
							"school": "",
							"skill": "",
							"skype": "",
							"userId": 0,
							"workExperience": ""
						}
						$http.post($rootScope.linkapi + "user", user, {}).then(function (res) {
							alert('Đăng ký Tài Khoản Thành Công. Kiểm Tra Email Để Kích hoạt');
							$window.location.href="#!/login";
						}, function (error) {
							// body...
						});
					}
				},function(error){
					console.log(error);
				});

		// $http.post('http://localhost:8080/signup',taikhoan,{'content-type' : 'application/json'}).then(function(res){
		// },function(){
		// 	alert('Đăng ký Tài Khoản Thành Công. Kiểm Tra Email Để Kích hoạt');
		// 	$window.location.href="#!/login";
		// });

	}
	});

	
