/// LẤY THÔNG TIN CÁC NHÂN CỦA ACCOUNT SAU ĐÓ HIỆN THÔNG TIN (LẤY THEO ID CỦA ACOUNT KHI ĐĂNG NHẬP VÀO)
appAdmin.controller('adminController', function($scope , $rootScope, $window, $http){
	// if($window.localStorage.getItem("token")) {
	// 	$http.get($rootScope.linkapi + "whoami").then(function(res) {
	// 		$scope.account = res.data;
	// 	}, function (error) {
	// 		//body
	// 	});
	// }
	$scope.check = false;
	$scope.update = function() {
		$scope.check = true;
	}
	$scope.cancel = function() {
		$scope.check = false;
		$http.get($rootScope.linkapi + "whoami").then(function(res) {
			$scope.account = res.data;
		}, function (error) {
			//body
		});
	}
	$scope.confirmUpdate = function() {
		var acc = {
			email : $scope.account.email,
			fullname : $scope.account.fullname,
			phone : $scope.account.phone
		}
		var req = {
			method: 'PUT',
			url: $rootScope.linkapi + "account/" + $scope.account.username,
			headers: {
				'Accept': 'text/plain',
				'Content-Type': 'application/json'
			},
			data: $scope.account
		}
		$http(req).then(function(res) {
			if(res.data=="duplicate email") {
				alert("Email đã được sử dụng");
			} else if(res.data=="duplicate phone") {
				alert("Số điện thoại đã được sử dụng");
			} else {
				$scope.check = false;
			}
		}, function(error) {
			console.log(error);
		});
	}
});
appAdmin.controller('settingController',function($http,$scope,$rootScope,$location,$window){
	if($window.localStorage.getItem("token")) {
			if($rootScope.currentUser.role!="MANAGER") {
		alert("Không có quyền truy cập");
		$location.path("");
		}
	}
	$http.get("http://localhost:8080/setting/1").then(function(res){
		$scope.allSetting = res.data;
	},function(error){
		console.log("fail. Can not getseting !!");
	});
	$scope.check = false;

	$scope.update = function() {
		$scope.check = true;
	}
	$scope.cancel = function() {
		$scope.check = false;
		$http.get("http://localhost:8080/setting/1").then(function(res){
			$scope.allSetting = res.data;
		},function(error){
			//body
		});
	}

	$scope.confirmUpdate = function() {
		if($scope.logo!=undefined) {
			var config = {
				transformRequest: angular.identity,
				transformResponse: angular.identity,
				headers: {
					'Accept': 'text/plain',
					'Content-Type': undefined
				}
			}
			var url = $rootScope.linkapi + "images";
			var data = new FormData();
			data.append("file", $scope.logo);
			$http.post(url, data, config).then(function(res) {
				$scope.allSetting.logo = res.data;
				$http.put($rootScope.linkapi + "setting/1",$scope.allSetting,{}).then(function(res) {
					$scope.check = false;
				}, function(error) {
					//body
				});
			}, function(error) {
				
			});
		} else {
			$http.put($rootScope.linkapi + "setting/1",$scope.allSetting,{}).then(function(res) {
				$scope.check = false;
			}, function(error) {
				//body
			});
		}
	}
});
/// LẤY 8 THÔNG TIN CỦA CÁC ACCOUNT CÓ TRONG CSDL LÊN BẢNG 
appAdmin.controller('accountController', function($http,$scope , $rootScope,$location,$window){
	if($window.localStorage.getItem("token")) {
		if($rootScope.currentUser.role!="MANAGER") {
		alert("Không có quyền truy cập");
		$location.path("");
	}
}
	var checkReset = false; 
	$scope.showInOnePage = 8;
	$scope.defaultAcount = 8;
	$scope.page = 1;
	var link = "http://localhost:8080/account/amount/"+$scope.defaultAcount;
	$http.get(link).then(function(res) {
		$scope.allaccount= res.data;
		
		/*optional stuff to do after success */
	},function(res){
		console.log("Fail, Can not get data acount");

	});	
	// Lấy Số trang
	$http.get("http://localhost:8080/account/page/"+$scope.showInOnePage).then(
		function(res){
			$scope.countPage = res.data;
		},function(){
			console.log("lỗi");
		});
	// SỬ LÝ SỰ KIỆN CHUYỂN TRANG

	$scope.redirectPage = function(page) {
		$scope.page = (page-1)*$scope.showInOnePage;
		$http.get("http://localhost:8080/account/pagination/" + $scope.page + "/" + $scope.showInOnePage).then(function(res) {
			$scope.allaccount = res.data;
		},function(error) {
			//do something;
		});
	}
	// HỦY KÍCH HOẠT TÀI KHOẢN
	$scope.disactive = function(y){
		
		if(confirm("Bạn có muốn hủy kích tài khoản không ?")==true){
			 y.status = false;
			var link = "http://localhost:8080/account/"+y.username;
			$http.put(link,y,{'content-type' : 'application/json'})
			.then(function(res){
				console.log("update success");
			},function(res){
				console.log('update false');
			})
		}
		
	};
	/// KÍCH HOẠT TÀI KHOẢN
		$scope.active = function(y){
		
		if(confirm("Bạn có muốn  kích tài khoản không ?")==true){
			 y.status = true;
			var link = "http://localhost:8080/account/"+y.username;
			$http.put(link,y,{'content-type' : 'application/json'})
			.then(function(res){
				console.log("update success");
			},function(res){
				console.log('update false');
			})
		}
		
	};
	/// XÓA TÀI KHOẢN
	$scope.xoataikhoan = function(y,index){
		if(confirm("Bạn Có Muốn Xóa Tài Khoản Không ?")){
			$http.delete("http://localhost:8080/account/"+y.accountId).then(function(res){
				$scope.allaccount.splice(index,1);
			},function(error){

			})

		}
	};
	$scope.resetForm = function(){
			if(checkReset == true){
			 $scope.newemail = "";
			 $scope.newfullname ="";
			 $scope.newpassword = "";
			 $scope.newphone = "";
			 $scope.newrole = "";
			$scope.newuser = "";
			}

	};
	/// THÊM MỘT TÀI KHOẢN MỚI
		$scope.themtaikhoan = function(){
			checkReset = true;
			var link = "http://localhost:8080/account";
			var date = new Date();
        	var dateCreated = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
							var taikhoan = {
							  accountId: 0,
							  dateCreated: dateCreated,
							  email: $scope.newemail,
							  fullname: $scope.newfullname,
							  lastAccess: "null",
							  notes: "null",
							  password: $scope.newpassword,
							  phone: $scope.newphone,
							  role: $scope.newrole,
							  status: 1,
							  username: $scope.newuser

						}
						console.log(taikhoan);
							var req = {
							method: 'POST',
							url: link,
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
					$("#popupdangky").modal("toggle");
					$scope.allaccount.push(taikhoan);

					}
				},function(error){
					console.log(error);
				});
		// $http.get("http://localhost:8080/account/"+$scope.newuser).then(function(user1){
		// 	console.log($scope.newuser);
		// 	console.log(user1.data);
		// 	if(user1.data.length ==0 ){
		// 		console.log(taikhoan);
		// 		$http(req).then(function(res) {
		// 			console.log(res.data);
		// 		$("#popupdangky").modal("toggle");
		// 		$scope.allaccount.push(taikhoan);
		// 	});
		// 	}else{
		// 		alert("Tài khoản đã tồn tại");

		// 	}	
		// },function(error){
			
		// });			
						


		};
	// XEM THÔNG TIN TÀI KHOẢN
		$scope.xemtaikhoan = function(y){

			$scope.y = y;
			$scope.checkedit=true;
			console.log($scope.ac);
		};
	// CHỈNH SỬA THÔNG TIN TÀI khoản
	
	$scope.edittaikhoan = function(y){
			
			$scope.y = {
						"accountId": y.accountId,
					    "username": y.username,
					    "password": y.password,
					    "email": y.email,
					    "fullname": y.fullname,
					    "role": y.role,
					    "status": y.status,
					    "dateCreated": y.dateCreated,
					    "lastAccess": y.lastAccess,
					    "phone": y.phone,
					    "notes": y.notes
			}
			$scope.checkedit=true;
			console.log($scope.ac);
		};

		// SHOW FROM X TO Y
		$scope.show =8;
		$scope.showAmuont = function(){
				$http.get("http://localhost:8080/account/page/"+$scope.show).then(
			function(res){
			$scope.countPage = res.data;
			},function(){
			console.log("lỗi");
			});

			var linkAmuont = "http://localhost:8080/account/amount/"+$scope.show;
			$http.get(linkAmuont).then(function(res){
				$scope.allaccount= res.data;
			},function(error){
				console.log("lỗi");

			})
			
		};

	// CHỈNH SỬA THÔNG TIN TÀI khoản
	
	$scope.edittaikhoan = function(y){
			$scope.yTemp = y;
			$scope.y = {
				 	 "accountId": y.accountId,
				    "username": y.username,
				    "password": y.password,
				    "email": y.email,
				    "fullname": y.fullname,
				    "role": y.role,
				    "status": y.status,
				    "dateCreated": y.dateCreated,
				    "lastAccess": y.lastAccess,
				    "phone": y.phone,
				    "notes": y.notes
			}
			$scope.checkedit=false;
			
		};
		$scope.updateacount = function(){
			var linkupdate ="http://localhost:8080/account/"+$scope.y.username;	
					var req = {
							method: 'PUT',
							url: linkupdate,
							headers: {
									'Accept': 'text/plain'
									},
								data: $scope.y
								}
					if(confirm("Bạn Có Muốn Cập Nhập Tài Khoản Không")){
						$http(req).then(function(res){
							if(res.data =='duplicate email'){
								alert('Trùng Email');
							}else if(res.data =='duplicate phone'){
								alert('Trùng Phone');
							}else{
								alert('Cập nhập thành công');
								$scope.yTemp.accountId = $scope.y.accountId;
								$scope.yTemp.username = $scope.y.username;
								$scope.yTemp.password = $scope.y.password;
								$scope.yTemp.email = $scope.y.email;
								$scope.yTemp.fullname = $scope.y.fullname;
								$scope.yTemp.role = $scope.y.role;
								$scope.yTemp.status = $scope.y.status;
								$scope.yTemp.dateCreated = $scope.y.dateCreated;
								$scope.yTemp.lastAccess = $scope.y.lastAccess;
								$scope.yTemp.phone = $scope.y.phone;
								$scope.yTemp.notes = $scope.y.notes;
								$scope.y = $scope.yTemp;
								console.log("update thành công");
								$(".bd-example-modal-lg").modal("toggle");

							}

						},function(error){
							console.log("update thất  bại");
							console.log(error);
						});

					}

		
			
		};

});
				// CONTROLER TRANG QUẢN LÝ SLIDE
appAdmin.controller('slideController', function($http , $scope , $rootScope,$location,$window){
	// if($window.localStorage.getItem("token")) {
	// 		if($rootScope.currentUser.role!="HR" && $rootScope.currentUser.role!="MANAGER" && $rootScope.currentUser.role!="MARKETING") {
	// 	alert("Không có quyền truy cập");
	// 	$location.path("");
	// }
// }
	// GET TOÀN BỘ THÔNG TINC CÁ SLIDER TRONG CƠ SỞ DỮ LIỆU
		 
		var link = "http://localhost:8080/slider";
		$http.get(link).then(function(res){
			$scope.slider = res.data;
			$scope.edit = false;
			
			
			
		},function(res){
			console.log('fail');
		});

	$scope.suaslider = function(x){
		 $scope.edit = true;
		 $xTemp = x;
		$scope.x = {
		 "slideId": x.slideId,
   		 "image": x.image,
    	 "link": x.link,
    	 "rank": x.rank	
		}
		
		

	};
	$scope.updateSlider = function(){
			var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
            	'Accept': 'text/plain',
                'Content-Type': undefined
            }
       }
        var url ="http://localhost:8080/imageSlider";
         var data = new FormData();
          data.append("file", $scope.x.image);
          $http.post(url,data,config).then(function(res){
          	 $scope.xTemp = {
          		
				    "slideId": $scope.x.slideId,
				    "image": res.data,
				    "link": $scope.x.link,
				    "rank": $scope.x.rank
 			 
          	};
          
          	var linkSliderUpdate = "http://localhost:8080/slider/"+$scope.x.slideId;
          	$http.put(linkSliderUpdate, $scope.xTemp,{'content-type' : 'application/json'	}).then(function(res){
          		console.log($scope.xTemp);
          		$scope.xTemp.slideId= $scope.x.slideId;
          		$scope.xTemp.image= $scope.x.image;
          		$scope.xTemp.link= $scope.x.link;
          		$scope.xTemp.rank= $scope.x.rank;
          		$scope.x=$scope.xTemp ;
				$http.get("http://localhost:8080/slider").then(function(res){
				$scope.slider = res.data;
				
			
			
			
		});
          			$("#popupEdit").modal("toggle");
          	
          	},function(error){
          		console.log("Lỗi khi cập nhập slider");
          	});

          

	},function(error){
		console.log("lỗi khi cập nhập ảnh");

	});

};

});
