<!DOCTYPE html>
<html lang="en">

<head>

    <title>User Sign Up</title>
	<jsp:include page="head.jsp" />

</head>

<body>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
    
    <div ng-app="signupApp" ng-controller="signupCtrl">
    	<div class="container">
    		<div class="row">
    	
    			<div class="col-lg-3">
    			</div>
    	
    			<div class="col-lg-6">
    				<div class="well text-center">   			
    					<h4>User Sign Up</h4>
	    				<hr>
	    				<form name="myForm" novalidate>
		    				<div class="form-group">
		    					<label class="control-label col-md-3" for="username">Username:</label>
								<div class="col-md-8">
		  							<input type="text" name="username" class="form-control" placeholder="Username" aria-describedby="basic-addon1" 
		  								ng-model="user.username" required>
		  						</div>
								<br>
		  						<span style="color:blue" ng-show="myForm.username.$dirty || !myForm.username.length">
		 							<span ng-show="myForm.username.$error.required"><br>Username is required</span>
		  						</span>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							<div class="form-group">
		    					<label class="control-label col-md-3" for="password">Password:</label>
								<div class="col-md-8">
		  							<input type="password" name="password" class="form-control" placeholder="Password" aria-describedby="basic-addon1" 
		  								ng-model="user.password" required>
		  						</div>
		  						<br>
		  						<span style="color:blue" ng-show="myForm.password.$dirty || !myForm.password.length">
		  							<span ng-show="myForm.password.$error.required"><br>Password is required</span>
		  						</span>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							<div class="form-group">
		    					<label class="control-label col-md-3" for="email">Email:</label>
								<div class="col-md-8">
		  							<input type="text" name="email" class="form-control" placeholder="Email" aria-describedby="basic-addon1" 
		  								ng-model="user.email" required>
		  						</div>
								<br>
		  						<span style="color:blue" ng-show="myForm.email.$dirty || !myForm.email.length">
		 							<span ng-show="myForm.email.$error.required"><br>Email is required</span>
		  						</span>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							
							<div class="form-group">
		    					<label class="control-label col-md-3" for="firstname">First name:</label>
								<div class="col-md-8">
		  							<input type="text" name="firstname" class="form-control" placeholder="First Name" aria-describedby="basic-addon1" 
		  								ng-model="user.firstname">
		  						</div>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							<br>
							<div class="form-group">
		    					<label class="control-label col-md-3" for="lastname">Last name:</label>
								<div class="col-md-8">
		  							<input type="text" name="lastname" class="form-control" placeholder="Last Name" aria-describedby="basic-addon1" 
		  								ng-model="user.lastname">
		  						</div>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							<br>
							<div class="form-group">
		    					<label class="control-label col-md-3" for="birthday">Birthday:</label>
								<div class="col-md-8">
		  							<input type="text" name="birthday" class="form-control" placeholder="Birthday" aria-describedby="basic-addon1" 
		  								ng-model="user.birthday">
		  						</div>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							<br>
							<div class="form-group">
		    					<label class="control-label col-md-3" for="address">Address:</label>
								<div class="col-md-8">
		  							<input type="text" name="address" class="form-control" placeholder="Address" aria-describedby="basic-addon1" 
		  								ng-model="user.address">
		  						</div>
		  						<div class="col-md-1">
		  						</div>
		  						<br>
							</div>
							<br>
							<hr>
							<button class="btn btn-primary" ng-click="signup()" 
								ng-disabled="myForm.username.$invalid || myForm.password.$invalid || myForm.email.$invalid">OK</button>
							<a class="btn btn-primary" ng-click="reset()">Reset</a>
							<a class="btn btn-primary" onclick="goback()">Back</a>
									
							<!-- Sign up ok Modal content-->
							<div id="sucsModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title" style="color:#111">Thanks for join Kiwi</h4>
										</div>
										<div class="modal-body" style="color:#111">
												<p>Sign up successfully</p>
												<p>Log in now</p>
										</div>
										<div class="modal-footer">
											<a class="btn btn-default" href="login.jsp">OK</a>
										</div>
									</div>
				   				</div>
			    			</div>
			    			<!-- Fail Modal content-->
							<div id="failModal" class="modal fade" role="dialog">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title" style="color:#111">Error Message</h4>
										</div>
										<div class="modal-body" style="color:#111">
												<p>{{ responseMSG }}</p>
												<p>Please try again</p>
										</div>
										<div class="modal-footer">
											<a class="btn btn-default" data-dismiss="modal">OK</a>
										</div>
									</div>
				   				</div>
			    			</div>
	    				</form>
	    						
					</div>
    			</div>
    	
    			<div class="col-lg-3">
    			</div>   
    			
    		</div>
    	</div>
    </div>
     
    <script>
    	var app = angular.module("signupApp", [])
    		.controller("signupCtrl", function($scope, $http) {
    			$scope.user = {
    				username : "",
    				password : "",
    				email : "",
    				firstname : "",
    				lastname : "",
    				birthday: "",
    				address : ""
    			};
    			$scope.responseMSG = "";
    			$scope.signup = function() {
    				$http.post('user?action=addOneUser',
    						$scope.user).success(function(response) {
    							$scope.responseMSG = response;
    							if (response === "Sign up ok") {
    								$('#sucsModal').modal('show');
    							}
    							else {
    								$('#failModal').modal('show');
    							}
    				}).error(function(response) {
    					$scope.responseMSG = response;
    				});		
    			};
    			$scope.reset = function() {
    		        $scope.user = angular.copy($scope.master);
    		    };
    		});
    	
    	function goback() {
    	    window.history.back();
    	}
    </script>
    
</body>
</html>