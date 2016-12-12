<!DOCTYPE html>
<html lang="en">

<head>

    <title>Kiwi </title>
	<jsp:include page="head.jsp" />

</head>

<body>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
    
    <!-- Page Content  -->
    <div class="container">
    	<div ng-app="loginApp" ng-controller="loginCtrl">
    		<div class="row">
    			
    			<div class="col-lg-3">
    			</div>
    		
    			<div class="col-lg-6">
    				<div class="well text-center">
    					<div class="row">
    					
    					<h4>Log In</h4>
    					<hr>	
    						<div class= "col-md-2">
    						</div>
    						
    						<div class="col-md-8">
    							
    							<div class="input-group">
      								<div class="input-group-btn">
        								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Log In As		<span class="caret"></span>
        								</button>
        								<ul class="dropdown-menu pull-right">
								          <li ng-click="userlogin()"><a href="">User</a></li>
								          <li ng-click="sellerlogin()"><a href="">Seller</a></li>
								          <li ng-click="adminlogin()"><a href="">Admin</a></li>
        								</ul>
      								</div>
    							</div>
    							<p></p>
    				
			    				<div class="input-group">
			  						<span class="input-group-addon" id="basic-addon1">Username</span>
			  						<input type="text" class="form-control" placeholder="Username" aria-describedby="basic-addon1" ng-model="user.username">
								</div>
								<p></p>
								<div class="input-group">
			  						<span class="input-group-addon" id="basic-addon1">Password</span>
			  						<input type="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1" ng-model="user.password">
								</div>
								<p></p>
								<hr>
								<a class="btn btn-primary" ng-click="login()">OK</a>
								<a class="btn btn-primary" onclick="goback()">Back</a>
									
								<!-- Modal content-->
								<div id="myModal" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title" style="color:#111">Invalid username or password</h4>
											</div>
											<div class="modal-body" style="color:#111">
													<p>{{ responseMSG }}<p>
											</div>
											<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											</div>
										</div>
				    				</div>
			    				</div>
    						</div>
    						<div class="col-md-2">
    						</div>
    					</div>
    				</div>
    			</div>
    			
	    		<div class="col-lg-3">
	    		</div>
    		
   			</div>
   		</div>
   	</div>
   		
   		<script>
   			var app = angular.module("loginApp", []).controller("loginCtrl", 
   					["$scope", "$location", "$http", "$window", function($scope, $location, $http, $window) {
   				$scope.user = {
   					username : "",
   					password : ""
   				};
				$scope.type = "";
				$scope.userlogin = function() {
					$scope.type = "user";
				};
				$scope.sellerlogin = function() {
					$scope.type = "seller";
				};
				$scope.adminlogin = function() {
					$scope.type = "admin";
				};
   				$scope.responseMSG = "";
   				$scope.login = function () {
   					if ($scope.type === "user") {
	   					$http.post("user?action=userlogin", $scope.user).success(function(response) {
	   						$scope.responseMSG = response;
							console.log($scope.responseMSG);
							if (response === "login ok") {
								$window.location.href = "userPage.jsp";
							} else
								$("#myModal").modal("show");
	   					}).error(function(response) {
	   						$scope.responseMSG = response;
   						});
	   				}
   					if ($scope.type === "seller") {
	   					$http.post("user?action=sellerlogin", $scope.user).success(function(response) {
	   						$scope.responseMSG = response;
							console.log($scope.responseMSG);
							if (response === "login ok") {
								$window.location.href = "sellerPage.jsp";
							} else
								$("#myModal").modal("show");
	   					}).error(function(response) {
	   						$scope.responseMSG = response;
   						});
	   				}
   					if ($scope.type === "admin") {
	   					$http.post("user?action=adminlogin", $scope.user).success(function(response) {
	   						$scope.responseMSG = response;
							console.log($scope.responseMSG);
							if (response === "login ok") {
								$window.location.href = "adminPage.jsp";
							} else
								$("#myModal").modal("show");
	   					}).error(function(response) {
	   						$scope.responseMSG = response;
   						});
	   				}
   				};
   			}]);
   			
   			$(function(){
   				$(".dropdown-menu li a").click(function(){
   				    $(".btn:first-child").text($(this).text());
   				    $(".btn:first-child").val($(this).text());
   				});
   			});
   			
   			function goback() {
   	    	    window.history.back();
   	    	}
   		</script>
   
</body>
</html>