appAdmin.directive('fileModel', ['$parse', function ($parse) {
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

appAdmin.controller('newsCategoryController', function ($scope, $http, $rootScope, $location, $window) {
	// if($window.localStorage.getItem("token")) {
	// 	if($rootScope.currentUser.role!="MARKETING" && $rootScope.currentUser.role!="MANAGER") {
	// 		console.log($rootScope.currentUser.role);
	// 		alert("Không có quyền truy cập");
	// 		$location.path("");
	// 	}
	// }
	$http.get($rootScope.linkapi + "category-news/").then(function(res) {
		$scope.listNewsCategory = res.data;
	},function(error) {
		//do something;
	});

	$scope.hien = function(news) {
		news.status = true;
		var link = $rootScope.linkapi + "category-news/" + news.categoryNewsId;
		$http.put(link,news,{}).then(function(res) {
			
		}, function(error) {
			news.status = false;
		});
	}

	$scope.an = function(news) {
		news.status = false;
		var link = $rootScope.linkapi + "category-news/" + news.categoryNewsId;
		$http.put(link,news,{}).then(function(res) {
			
		}, function(error) {
			news.status = true;
		});
	}

	$scope.update = function(news) {
		$scope.checkModal = 'update';
		$scope.newsTemp = news;
		$scope.news = {
			categoryNewsId : news.categoryNewsId,
			categoryName : news.categoryName,
			status : news.status
		}
	}

	$scope.create = function() {
		$scope.checkModal = 'create';
		$scope.news = {
			categoryNewsId : 0,
			categoryName : "",
			status : true
		}
	}

	$scope.confirmUpdate = function() {
		var link = $rootScope.linkapi + "category-news/" + $scope.news.categoryNewsId;
		$http.put(link,$scope.news,{}).then(function(res) {
			$scope.newsTemp.categoryName = $scope.news.categoryName;
			$scope.news = $scope.newsTemp;
			$("#createNewsCategory").modal("toggle");
		}, function(error) {
			// body...
		});
	}

	$scope.confirmCreate = function() {
		var news = {
			categoryNewsId : $scope.news.categoryNewsId,
			categoryName : $scope.news.categoryName,
			status : $scope.news.status
		}
		var link = $rootScope.linkapi + "category-news/";
		$http.post(link,news,{}).then(function(res) {
			news.categoryNewsId = res.data;
			$scope.listNewsCategory.push(news);
			$("#createNewsCategory").modal("toggle");
		}, function(error) {
			// body...
		});
	}

});

appAdmin.controller('jobCategoryController', function ($scope, $http, $rootScope, $location, $window) {
	// if($window.localStorage.getItem("token")) {
	// 	if($rootScope.currentUser.role!="HR" && $rootScope.currentUser.role!="MANAGER") {
	// 		alert("Không có quyền truy cập");
	// 		$location.path("");
	// 	}
	// }
	$http.get($rootScope.linkapi + "category-job/").then(function(res) {
		$scope.listJobCategory = res.data;
	},function(error) {
		//do something;
	});
	
	$scope.hien = function(job) {
		job.status = true;
		var link = $rootScope.linkapi + "category-job/" + job.categoryJobId;
		$http.put(link,job,{}).then(function(res) {
			
		}, function(error) {
			job.status = false;
		});
	}

	$scope.an = function(job) {
		job.status = false;
		var link = $rootScope.linkapi + "category-job/" + job.categoryJobId;
		$http.put(link,job,{}).then(function(res) {
			
		}, function(error) {
			job.status = true;
		});
	}

	$scope.update = function(job) {
		$scope.checkModal = 'update';
		$scope.jobTemp = job;
		$scope.job = {
			categoryJobId : job.categoryJobId,
			categoryName : job.categoryName,
			status : job.status
		}
	}

	$scope.create = function() {
		$scope.checkModal = 'create';
		$scope.job = {
			categoryJobId : 0,
			categoryName : "",
			status : true
		}
	}

	$scope.confirmUpdate = function() {
		var link = $rootScope.linkapi + "category-job/" + $scope.job.categoryJobId;
		$http.put(link,$scope.job,{}).then(function(res) {
			$scope.jobTemp.categoryName = $scope.job.categoryName;
			$scope.job = $scope.jobTemp;
			$("#createJobCategory").modal("toggle");
		}, function(error) {
			// body...
		});
	}

	$scope.confirmCreate = function() {
		var job = {
			categoryJobId : $scope.job.categoryJobId,
			categoryName : $scope.job.categoryName,
			status : $scope.job.status
		}
		var link = $rootScope.linkapi + "category-job/";
		$http.post(link,job,{}).then(function(res) {
			job.categoryJobId = res.data;
			$scope.listJobCategory.push(job);
			$("#createJobCategory").modal("toggle");
		}, function(error) {
			// body...
		});
	}
	
});

appAdmin.controller('jobController', function ($scope, $http, $rootScope, $location, $window) {
	// if($window.localStorage.getItem("token")) {
	// 	if($rootScope.currentUser.role!="HR" && $rootScope.currentUser.role!="MANAGER") {
	// 		alert("Không có quyền truy cập");
	// 		$location.path("");
	// 	}
	// }
	$scope.show = 10;
	$scope.page = 1;
	var checkreset = false;
	var checkupdate = false;

	$http.get($rootScope.linkapi + "job/page-size/"+$scope.show).then(function(res) {
		$scope.pageSize = res.data;
	},function(error) {
		//do something;
	});

	$scope.getNumber = function(num) {
    	return new Array(num);
	}

	$scope.redirectPage = function(page) {
		$scope.checkAll = false;
		$scope.selected = [];
		$scope.page = page;
		$http.get($rootScope.linkapi + "job/" + $scope.page + "/" + $scope.show).then(function(res) {
			$scope.listJob = res.data;
		},function(error) {
			//do something;
		});
	}

	$scope.changeShow = function() {
		$scope.checkAll = false;
		$scope.selected = [];
		$scope.page = 1;
		$http.get($rootScope.linkapi + "job/" + $scope.page + "/" + $scope.show).then(function(res) {
			$scope.listJob = res.data;
			$http.get($rootScope.linkapi + "job/page-size/"+$scope.show).then(function(res) {
				$scope.pageSize = res.data;
			},function(error) {
				//do something;
			});
		},function(error) {
			//do something;
		});
	}

	$http.get($rootScope.linkapi + "job/" + $scope.page + "/" + $scope.show).then(function(res) {
		$scope.listJob = res.data;
	},function(error) {
		//do something;
	});

	$http.get($rootScope.linkapi + "category-job/").then(function(res) {
		$scope.listJobCategory = res.data;
	},function(error) {
		//do something;
	});

	$http.get($rootScope.linkapi + "city/").then(function(res) {
		$scope.listCity = res.data;
	},function(error) {
		//do something;
	});

	$scope.resetCreate = function () {
		if(checkreset) {
			$scope.newJob.jobName = "";
			angular.element("#thumbnail").val(null);
			$scope.newJob.thumbnail = undefined;
			$scope.newJob.description = "";
			$scope.newJob.workingForm = "";
			$scope.newJob.hot = "";
			$scope.newJob.expirationDate = "";
			$scope.newJob.address = "";
			$scope.newJob.city = null;
			$scope.newJob.salary = 0;
			$scope.newJob.categoryJob = null;
			CKEDITOR.instances['editor1'].setData("");
			CKEDITOR.instances['editor2'].setData("");
			CKEDITOR.instances['editor3'].setData("");
		}
	}

	$scope.cancel = function () {
		checkupdate = true;
	}

	$scope.view = function(job) {
		if(checkupdate) {
			angular.element("#newThumbnail").val(null);
			$scope.job.thumbnail = undefined;
		}
		$scope.check = false;
		$scope.job = job;
		CKEDITOR.instances['editor4'].setData(job.jobRequest);
		CKEDITOR.instances['editor5'].setData(job.content);
		CKEDITOR.instances['editor6'].setData(job.interest);
		CKEDITOR.instances.editor4.setReadOnly(true);
		CKEDITOR.instances.editor5.setReadOnly(true);
		CKEDITOR.instances.editor6.setReadOnly(true);
	}

	$scope.edit = function(job) {
		if(checkupdate) {
			angular.element("#newThumbnail").val(null);
			$scope.job.thumbnail = undefined;
		}
		$scope.check = true;
		$scope.jobTemp = job;
		$scope.job = {
			"jobId": job.jobId,
			"categoryJob": {
				"categoryJobId": job.categoryJob.categoryJobId,
				"categoryName": job.categoryJob.categoryName
			},
			"jobName": job.jobName,
			"salary": job.salary,
			"thumbnail": job.thumbnail,
			"description": job.description,
			"content": job.content,
			"address": job.address,
			"interest": job.interest,
			"jobRequest": job.jobRequest,
			"expirationDate": job.expirationDate,
			"dateSubmitted": job.dateSubmitted,
			"workingForm": job.workingForm,
			"status": job.status,
			"hot": job.hot,
			"city": {
				"cityId": job.city.cityId,
				"cityName": job.city.cityName
			}
		}
		CKEDITOR.instances['editor4'].setData(job.jobRequest);
		CKEDITOR.instances['editor5'].setData(job.content);
		CKEDITOR.instances['editor6'].setData(job.interest);
		CKEDITOR.instances.editor4.setReadOnly(false);
		CKEDITOR.instances.editor5.setReadOnly(false);
		CKEDITOR.instances.editor6.setReadOnly(false);
		$scope.job.thumbnail = job.thumbnail;
	}

	$scope.updateJob = function() {
		checkupdate = true;
		var link = $rootScope.linkapi + "job/" + $scope.job.jobId;
		var jobRequest = CKEDITOR.instances.editor4.getData();
		if(jobRequest=="") {
			alert("Chưa nhập yêu cầu công việc nè");
			return;
		}
		var content = CKEDITOR.instances.editor5.getData();
		if(content=="") {
			alert("Chưa nhập chi tiết công việc nè");
			return;
		}
		var interest = CKEDITOR.instances.editor6.getData();
		if(interest=="") {
			alert("Chưa nhập quyền lợi nè");
			return;
		}
		if($scope.newThumbnail!=undefined) {
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
			data.append("file", $scope.newThumbnail);
			$http.post(url, data, config).then(function(res) {
				$scope.job.thumbnail = res.data;
				$scope.job.content = content;
				$scope.job.interest = interest;
				$scope.job.jobRequest = jobRequest;
				$http.put(link,$scope.job,{}).then(function(res) {
					$scope.jobTemp.jobId = $scope.job.jobId;
					$scope.jobTemp.categoryJob.categoryJobId = $scope.job.categoryJob.categoryJobId;
					$scope.jobTemp.categoryJob.categoryName = $scope.job.categoryJob.categoryName;
					$scope.jobTemp.jobName = $scope.job.jobName;
					$scope.jobTemp.salary = $scope.job.salary;
					$scope.jobTemp.thumbnail = $scope.job.thumbnail;
					$scope.jobTemp.description = $scope.job.description;
					$scope.jobTemp.content = content;
					$scope.jobTemp.address = $scope.job.address;
					$scope.jobTemp.interest = interest;
					$scope.jobTemp.jobRequest = jobRequest;
					$scope.jobTemp.expirationDate = $scope.job.expirationDate;
					$scope.jobTemp.dateSubmitted = $scope.job.dateSubmitted;
					$scope.jobTemp.workingForm = $scope.job.workingForm;
					$scope.jobTemp.status = $scope.job.status;
					$scope.jobTemp.hot = $scope.job.hot;
					$scope.jobTemp.city.cityId = $scope.job.city.cityId;
					$scope.jobTemp.city.cityName = $scope.job.city.cityName;
					$scope.job = $scope.jobTemp;
				}, function(error) {
				// body...
				});
			}, function(error) {
				// body...
			});
		} else {
			$scope.job.content = CKEDITOR.instances.editor4.getData();
			$scope.job.interest = CKEDITOR.instances.editor5.getData();
			$scope.job.jobRequest = CKEDITOR.instances.editor6.getData();
			$http.put(link,$scope.job,{}).then(function(res) {
				$scope.jobTemp.jobId = $scope.job.jobId;
				$scope.jobTemp.categoryJob.categoryJobId = $scope.job.categoryJob.categoryJobId;
				$scope.jobTemp.categoryJob.categoryName = $scope.job.categoryJob.categoryName;
				$scope.jobTemp.jobName = $scope.job.jobName;
				$scope.jobTemp.salary = $scope.job.salary;
				$scope.jobTemp.thumbnail = $scope.job.thumbnail;
				$scope.jobTemp.description = $scope.job.description;
				$scope.jobTemp.content = content;
				$scope.jobTemp.address = $scope.job.address;
				$scope.jobTemp.interest = interest;
				$scope.jobTemp.jobRequest = jobRequest;
				$scope.jobTemp.expirationDate = $scope.job.expirationDate;
				$scope.jobTemp.dateSubmitted = $scope.job.dateSubmitted;
				$scope.jobTemp.workingForm = $scope.job.workingForm;
				$scope.jobTemp.status = $scope.job.status;
				$scope.jobTemp.hot = $scope.job.hot;
				$scope.jobTemp.city.cityId = $scope.job.city.cityId;
				$scope.jobTemp.city.cityName = $scope.job.city.cityName;
				$scope.job = $scope.jobTemp;
			}, function(error) {
			// body...
			});
		}
		$("#updateJob").modal("toggle");
	}

	// $scope.deleteJob = function(job, index) {
	// 	if(confirm("Bạn có muốn xóa hông ?")) {
	// 		var link = $rootScope.linkapi + "job/" + job.jobId;
	// 		$http.delete(link,{}).then(function(res) {
	// 			$scope.listJob.splice(index,1);
	// 		},function(error) {
	// 			//do something;
	// 		});
	// 	}
	// }

	$scope.hien = function(job) {
		job.status = true;
		var link = $rootScope.linkapi + "job/" + job.jobId;
		$http.put(link,job,{}).then(function(res) {
			
		}, function(error) {
			job.status = false;
		});
	}

	$scope.an = function(job) {
		job.status = false;
		var link = $rootScope.linkapi + "job/" + job.jobId;
		$http.put(link,job,{}).then(function(res) {
			
		}, function(error) {
			job.status = true;
		});
	}

	$scope.createJob = function(newJob) {
		checkreset = true;
		if($scope.newJob.thumbnail==undefined) {
			alert("Chưa thêm ảnh nè");
			return;
		}
		var jobRequest = CKEDITOR.instances.editor1.getData();
		if(jobRequest=="") {
			alert("Chưa nhập yêu cầu công việc nè");
			return;
		}
		var content = CKEDITOR.instances.editor2.getData();
		if(content=="") {
			alert("Chưa nhập chi tiết công việc nè");
			return;
		}
		var interest = CKEDITOR.instances.editor3.getData();
		if(interest=="") {
			alert("Chưa nhập quyền lợi nè");
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
        var url = $rootScope.linkapi + "images";
        var data = new FormData();
        data.append("file", $scope.newJob.thumbnail);
        $http.post(url, data, config).then(function(res) {
        	var date = new Date();
        	var dateStr = date.getDate() + "/" + (date.getMonth()+1) + "/" + date.getFullYear();
        	var jobObj = {
        		"address": $scope.newJob.address,
        		"categoryJob": {
        			"categoryJobId": $scope.newJob.categoryJob.categoryJobId,
        			"categoryName": $scope.newJob.categoryJob.categoryName
        		},
        		"city": {
        			"cityId": $scope.newJob.city.cityId,
        			"cityName" : $scope.newJob.city.cityName
        		},
        		"content": content,
        		"dateSubmitted": dateStr,
        		"description": $scope.newJob.description,
        		"expirationDate": $scope.newJob.expirationDate,
        		"hot": $scope.newJob.hot,
        		"interest": interest,
        		"jobId": 0,
        		"jobName": $scope.newJob.jobName,
        		"jobRequest": jobRequest,
        		"salary": $scope.newJob.salary,
        		"status": true,
        		"thumbnail": res.data,
        		"workingForm": $scope.newJob.workingForm
        	}
        	$http.post($rootScope.linkapi + "job", jobObj, {}).then(function(res) {
        		jobObj.jobId = res.data;
        		if($scope.page==1) {
        			$scope.listJob.unshift(jobObj);
        			if($scope.listJob.length>=$scope.show) {
	        			$scope.listJob.splice($scope.show-1,1);
	        		}
        		}
        		$http.get($rootScope.linkapi + "news/page-size/"+ $scope.show).then(function(res) {
        			$scope.pageSize = res.data;
        		},function(error) {
					//do something;
				});
        	}, function(error) {
        		// body...
        	});
        },function(error) {
			//body
		});
		$("#createJob").modal("toggle");
	}

	$scope.selected = [];

	$scope.checkedChange = function(job) {
		if($scope.selected.indexOf(job)===-1) {
			$scope.selected.push(job);
		} else {
			$scope.selected.splice($scope.selected.indexOf(job),1);
		}
	}

	$scope.checkedAll = function () {
		if($scope.checkAll) {
			$scope.selected = [];
			angular.forEach($scope.listJob, function (job, key) {
				job.checked = true;
				$scope.selected.push(job);
			});
		} else {
			angular.forEach($scope.listJob, function (job, key) {
				job.checked = false;
			});
			$scope.selected = [];
		}
	}

	$scope.duyetAll = function () {
		if(confirm('Bạn có muốn duyệt tất hông?')) {
			angular.forEach($scope.selected, function (job, key) {
				job.status = true;
				var link = $rootScope.linkapi + "job/" + job.jobId;
				$http.put(link,job,{}).then(function(res) {
					job.checked = false;
					$scope.checkAll = false;
					$scope.selected = [];
				}, function(error) {
					job.status = false;
				});
			});
		}
	}

	$scope.chanAll = function () {
		if(confirm('Bạn có muốn chặn tất hông?')) {
			angular.forEach($scope.selected, function (job, key) {
				job.status = false;
				var link = $rootScope.linkapi + "job/" + job.jobId;
				$http.put(link,job,{}).then(function(res) {
					job.checked = false;
					$scope.checkAll = false;
					$scope.selected = [];
				}, function(error) {
					job.status = false;
				});
			});
		}
	}

});

appAdmin.controller('postController', function ($scope, $http, $rootScope, $location, $window) {
	// if($window.localStorage.getItem("token")) {
	// 	if($rootScope.currentUser.role!="MARKETING" && $rootScope.currentUser.role!="MANAGER") {
	// 		alert("Không có quyền truy cập");
	// 		$location.path("");
	// 	}
	// }
	$scope.show = 10;
	$scope.page = 1;
	var checkreset = false;
	var checkupdate = false;

	$http.get($rootScope.linkapi + "category-news/").then(function(res) {
		$scope.listNewsCategory = res.data;
	},function(error) {
		//do something;
	});

	$http.get($rootScope.linkapi + "news/page-size/"+ $scope.show).then(function(res) {
		$scope.pageSize = res.data;
	},function(error) {
		//do something;
	});

	$http.get($rootScope.linkapi + "news/"+ $scope.page + "/" + $scope.show).then(function(res) {
		$scope.listNews = res.data;
	},function(error) {
		//do something;
	});

	$scope.getNumber = function(num) {
    	return new Array(num);
	}

	$scope.redirectPage = function(page) {
		$scope.page = page;
		$http.get($rootScope.linkapi + "news/" + $scope.page + "/" + $scope.show).then(function(res) {
			$scope.listNews = res.data;
		},function(error) {
			//do something;
		});
	}

	$scope.changeShow = function() {
		$scope.page = 1;
		$http.get($rootScope.linkapi + "news/" + $scope.page + "/" + $scope.show).then(function(res) {
			$scope.listNews = res.data;
			$http.get($rootScope.linkapi + "news/page-size/"+$scope.show).then(function(res) {
				$scope.pageSize = res.data;
			},function(error) {
				//do something;
			});
		},function(error) {
			//do something;
		});
	}

	$scope.duyet = function(news) {
    	news.status = true;
		var link = $rootScope.linkapi + "news/" + news.newsID;
		$http.put(link,news,{}).then(function(res) {
			
		}, function(error) {
			news.status = false;
		});
	}

	$scope.chan = function(news) {
    	news.status = false;
		var link = $rootScope.linkapi + "news/" + news.newsID;
		$http.put(link,news,{}).then(function(res) {
			
		}, function(error) {
			news.status = true;
		});
	}

	$scope.cancel = function () {
		checkupdate = true;
	}

	$scope.view = function(news) {
		if(checkupdate) {
			angular.element("#newThumbnail").val(null);
			$scope.news.thumbnail = undefined;
		}
    	$scope.check = false;
    	$scope.news = news;
    	CKEDITOR.instances.editor2.setReadOnly(true);
    	CKEDITOR.instances['editor2'].setData(news.content);
	}

	$scope.edit = function(news) {
		if(checkupdate) {
			angular.element("#newThumbnail").val(null);
			$scope.news.thumbnail = undefined;
		}
    	$scope.check = true;
    	$scope.newsTemp = news;
    	$scope.news = {
    		"account": {
    			"accountId": news.account.accountId
    		},
    		"categoryNews": {
    			"categoryName": news.categoryNews.categoryName,
    			"categoryNewsId": news.categoryNews.categoryNewsId
    		},
    		"content": news.content,
    		"dateSubmitted": news.dateSubmitted,
    		"description": news.description,
    		"hot": news.hot,
    		"newsID": news.newsID,
    		"status": news.status,
    		"thumbnail": news.thumbnail,
    		"title": news.title
    	}
    	CKEDITOR.instances.editor2.setReadOnly(false);
    	CKEDITOR.instances['editor2'].setData(news.content);
	}

	$scope.resetCreate = function () {
		if(checkreset) {
			$scope.newsCreate.hot = "";
			angular.element("#thumbnail").val(null);
			$scope.newsCreate.thumbnail = undefined;
			$scope.newsCreate.description = "";
			$scope.newsCreate.title = "";
			$scope.newsCreate.categoryNews = null;
			CKEDITOR.instances['editor1'].setData("");
		}
	}

	$scope.updateNews = function () {
		checkupdate = true;
		var link = $rootScope.linkapi + "news/" + $scope.news.newsID;
		var content = CKEDITOR.instances.editor2.getData();
		if(content=="") {
			alert("Chưa nhập nội dung nè");
			return;
		}
		if($scope.newThumbnail!=undefined) {
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
			data.append("file", $scope.newThumbnail);
			$http.post(url, data, config).then(function(res) {
				$scope.news.thumbnail = res.data;
				$scope.news.content = content;
				$http.put(link,$scope.news,{}).then(function(res) {
					$scope.newsTemp.newID = $scope.news.newID;
					$scope.newsTemp.account.accountId = $scope.news.account.accountId;
					$scope.newsTemp.categoryNews.categoryNewsId = $scope.news.categoryNews.categoryNewsId;
					$scope.newsTemp.categoryNews.categoryName = $scope.news.categoryNews.categoryName;
					$scope.newsTemp.content = $scope.news.content;
					$scope.newsTemp.dateSubmitted = $scope.news.dateSubmitted;
					$scope.newsTemp.description = $scope.news.description;
					$scope.newsTemp.hot = $scope.news.hot;
					$scope.newsTemp.thumbnail = $scope.news.thumbnail;
					$scope.newsTemp.title = $scope.news.title;
					$scope.newsTemp.status = $scope.news.status;
					$scope.news = $scope.newsTemp;
				}, function(error) {
					
				});
			}, function(error) {
				// body...
			});
		} else {
			$scope.news.content = content;
			$http.put(link,$scope.news,{}).then(function(res) {
				$scope.newsTemp.newID = $scope.news.newID;
				$scope.newsTemp.account.accountId = $scope.news.account.accountId;
				$scope.newsTemp.categoryNews.categoryNewsId = $scope.news.categoryNews.categoryNewsId;
				$scope.newsTemp.categoryNews.categoryName = $scope.news.categoryNews.categoryName;
				$scope.newsTemp.content = $scope.news.content;
				$scope.newsTemp.dateSubmitted = $scope.news.dateSubmitted;
				$scope.newsTemp.description = $scope.news.description;
				$scope.newsTemp.hot = $scope.news.hot;
				$scope.newsTemp.thumbnail = $scope.news.thumbnail;
				$scope.newsTemp.title = $scope.news.title;
				$scope.newsTemp.status = $scope.news.status;
				$scope.news = $scope.newsTemp;
			}, function(error) {

			});
		}
		$("#updatePost").modal("toggle");
	}

	$scope.createNews = function() {
		checkreset = true;
		var content = CKEDITOR.instances.editor1.getData();
		if(content=="") {
			alert("Chưa nhập nội dung nè");
			return;
		}
		var url = $rootScope.linkapi + "images";
		var config = {
            transformRequest: angular.identity,
            transformResponse: angular.identity,
            headers: {
            	'Accept': 'text/plain',
                'Content-Type': undefined
            }
        }
        var data = new FormData();
        data.append("file", $scope.newsCreate.thumbnail);
        $http.post(url, data, config).then(function(res) {
        	var date = new Date();
        	var dateStr = date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getFullYear()+" "+date.getHours()+":"+date.getMinutes(); 
        	var newsObj = {
        		"account": {
        			"accountId": $rootScope.currentUser.accountId
        		},
        		"categoryNews": {
        			"categoryName": $scope.newsCreate.categoryNews.categoryName,
        			"categoryNewsId": $scope.newsCreate.categoryNews.categoryNewsId
        		},
        		"content": content,
        		"dateSubmitted": dateStr,
        		"description": $scope.newsCreate.description,
        		"hot": $scope.newsCreate.hot,
        		"status": true,
        		"thumbnail": res.data,
        		"title": $scope.newsCreate.title
        	}
        	$http.post($rootScope.linkapi + "news", newsObj, {}).then(function(res) {
        		newsObj.newsID = res.data;
        		if($scope.page==1) {
        			$scope.listNews.unshift(newsObj);
        			if($scope.listNews.length>=$scope.show) {
        				$scope.listNews.splice($scope.show-1,1);
        			}
        		}
        		$http.get($rootScope.linkapi + "news/page-size/"+ $scope.show).then(function(res) {
        			$scope.pageSize = res.data;
        		},function(error) {
					//do something;
				});
        	}, function(error) {
        		// body...
        	});
        },function(error) {
			//body
		});
		$("#createPost").modal("toggle");
	}

	$scope.deleteNews = function (news,index) {
		if(confirm("Bạn có muốn xóa hông ?")) {
			var link = $rootScope.linkapi + "news/" + news.newsID;
			$http.delete(link,{}).then(function(res) {
				$scope.listNews.splice(index,1);
			},function(error) {
				//do something;
			});
		}
	}

	$scope.selected = [];

	$scope.checkedChange = function(news) {
		if($scope.selected.indexOf(news)===-1) {
			$scope.selected.push(news);
		} else {
			$scope.selected.splice($scope.selected.indexOf(news),1);
		}
	}

	$scope.checkedAll = function () {
		if($scope.checkAll) {
			$scope.selected = [];
			angular.forEach($scope.listNews, function (news, key) {
				news.checked = true;
				$scope.selected.push(news);
			});
		} else {
			angular.forEach($scope.listNews, function (news, key) {
				news.checked = false;
			});
			$scope.selected = [];
		}
	}

	$scope.duyetAll = function () {
		if(confirm('Bạn có muốn duyệt tất hông?')) {
			angular.forEach($scope.selected, function (news, key) {
				news.status = true;
				var link = $rootScope.linkapi + "news/" + news.newsID;
				$http.put(link,news,{}).then(function(res) {
					news.checked = false;
					$scope.checkAll = false;
					$scope.selected = [];
				}, function(error) {
					news.status = false;
				});
			});
		}
	}

	$scope.chanAll = function () {
		if(confirm('Bạn có muốn chặn tất hông?')) {
			angular.forEach($scope.selected, function (news, key) {
				news.status = false;
				var link = $rootScope.linkapi + "news/" + news.newsID;
				$http.put(link,news,{}).then(function(res) {
					news.checked = false;
					$scope.checkAll = false;
					$scope.selected = [];
				}, function(error) {
					news.status = false;
				});
			});
		}
	}

	$scope.deleteAll = function () {
		if(confirm('Bạn có muốn xoá tất hông?')) {
			angular.forEach($scope.selected, function (news, key) {
				var link = $rootScope.linkapi + "news/" + news.newsID;
				$http.delete(link,{}).then(function(res) {
					$scope.checkAll = false;
					$scope.selected = [];
					$scope.listNews.splice(news,1);
					$http.get($rootScope.linkapi + "news/page-size/"+ $scope.show).then(function(res) {
						$scope.pageSize = res.data;
						if($scope.pageSize==1) {
							$scope.page = 1;
							$http.get($rootScope.linkapi + "news/"+ $scope.page + "/" + $scope.show).then(function(res) {
								$scope.listNews = res.data;
							},function(error) {
								//do something;
							});
						}
					},function(error) {
						//do something;
					});
				},function(error) {
					//do something;
				});
			});
		}
	}

});

appAdmin.controller('indexController', function($scope, $window, $http, $rootScope){
	$scope.logout = function () {
		$window.localStorage.removeItem("token");
		$window.location.href = "../#!/";
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
});

appAdmin.controller('applyController', function ($scope, $http, $rootScope, $location, $window) {
	// if($window.localStorage.getItem("token")) {
	// 	if($rootScope.currentUser.role!="HR" && $rootScope.currentUser.role!="MANAGER") {
	// 		alert("Không có quyền truy cập");
	// 		$location.path("");
	// 	}
	// }
	$scope.show = 10;
	$scope.page = 1;

	$http.get($rootScope.linkapi + "apply/page-size/"+ $scope.show).then(function(res) {
		$scope.pageSize = res.data;
	},function(error) {
		//do something;
	});

	$http.get($rootScope.linkapi + "apply/"+ $scope.page + "/" + $scope.show).then(function(res) {
		$scope.listApply = res.data;
	},function(error) {
		//do something;
	});

	$scope.getNumber = function(num) {
    	return new Array(num);
	}

	$scope.redirectPage = function(page) {
		$scope.page = page;
		$http.get($rootScope.linkapi + "apply/" + $scope.page + "/" + $scope.show).then(function(res) {
			$scope.listApply = res.data;
		},function(error) {
			//do something;
		});
	}

	$scope.changeShow = function() {
		$scope.page = 1;
		$http.get($rootScope.linkapi + "apply/" + $scope.page + "/" + $scope.show).then(function(res) {
			$scope.listNews = res.data;
			$http.get($rootScope.linkapi + "apply/page-size/"+$scope.show).then(function(res) {
				$scope.pageSize = res.data;
			},function(error) {
				//do something;
			});
		},function(error) {
			//do something;
		});
	}

	$http.get($rootScope.linkapi + "category-job/").then(function(res) {
		$scope.listJobCategory = res.data;
	},function(error) {
		//do something;
	});

	$http.get($rootScope.linkapi + "apply/").then(function(res) {
		$scope.listApply = res.data;
	},function(error) {
		//do something;
	});

	$scope.duyet = function(apply) {
		apply.status = true;
		var link = $rootScope.linkapi + "apply/" + apply.applyProfleId;
		$http.put(link,apply,{}).then(function(res) {
			
		}, function(error) {
			apply.status = false;
		});
	}
});