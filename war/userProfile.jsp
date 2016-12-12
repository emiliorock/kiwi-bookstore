<!DOCTYPE html>
<html lang="en">

<head>

    <title>My Profile</title>
	<jsp:include page="head.jsp" />

</head>

<body>
	
	<%@page import="model.User"%>
	<%
		User user = (User) session.getAttribute("userSession"); 
	%>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
    
    <div ng-app="updateApp" ng-controller="updateCtrl">
		<div class="container">
			<div class="row">
					
		        <!-- left side bar -->
				<div class="col-md-3">
		        	<p class="lead"><%=user.getUsername()%></p>
		            <div class="list-group">
		            	<a href="userPage.jsp" class="list-group-item">My Homepage</a>
		                <a href="userProfile.jsp" class="list-group-item active">My Profile</a>
		                <a href="userCart.jsp" class="list-group-item">My Cart</a>
		                <a href="user?action=logout" class="list-group-item">Log Out</a>
		            </div>
		        </div>
				
				<!-- right side bar -->
				<div class="col-md-9">
					<div class="well text-center">
						<div class="row">
							<div class="col-md-3">
			    			</div>
			    			<div class="col-md-6">
			    				<h4>{{ user.username }}'s Profile</h4>
		    					<p><p>
		    					<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Password</span>
		  							<input type="password" class="form-control" placeholder="user.password" aria-describedby="basic-addon1" ng-model="user.password">
								</div>
								<p></p>
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">Email</span>
		  							<input type="text" class="form-control" placeholder="user.email" aria-describedby="basic-addon1" ng-model="user.email">
								</div>
								<p></p>	
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">First Name</span>
		  							<input type="text" class="form-control" placeholder="user.firstname" aria-describedby="basic-addon1" ng-model="user.firstname">
								</div>
								<p></p>	
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">Last Name</span>
		  							<input type="text" class="form-control" placeholder="user.lastname" aria-describedby="basic-addon1" ng-model="user.lastname">
								</div>
								<p></p>	
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">Birthday</span>
		  							<input type="text" class="form-control" placeholder="user.birthday" aria-describedby="basic-addon1" ng-model="user.birthday">
								</div>
								<p></p>	
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">Address</span>
		  							<input type="text" class="form-control" placeholder="user.address" aria-describedby="basic-addon1" ng-model="user.address">
								</div>
								<p></p>	
								<a class="btn btn-primary" ng-click="update()">OK</a>
								
								<!-- Modal -->
								<div id="myModal" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Success</h4>
											</div>
											<div class="modal-body">
												<p>Your profile has been successfully updated</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
								<!-- / Modal -->
								
			    			</div>
			    			<div class="col-md-3">
			    			</div>
			    		</div>
					</div>
				</div>
				
	   		</div>
	   	</div>
   	</div>
   		
   		
   		<script>
   			var app = angular.module("updateApp", []).controller("updateCtrl", function($scope, $http) {
   				$scope.user = {
   						id : "<%=user.getID()%>",
   					username : "<%=user.getUsername()%>",
   					password : "<%=user.getPassword()%>",
   					email : "<%=user.getEmail()%>",
   					firstname : "<%=user.getFirstname()%>",
   					lastname : "<%=user.getLastname()%>",
   					birthday : "<%=user.getBirthday()%>",
   					address : "<%=user.getAddress()%>"
   				};
   				$scope.responseMSG = "";
   				$scope.update = function () {
   					$http.post("user?action=updateOneUser", $scope.user).success(function(response) {
   						$scope.responseMSG = response;
						if (response === "update ok") {
							$('#myModal').modal('show')
						} 
   					}).error(function(response) {
   						$scope.responseMSG = response;
   					});
   				};
   			});
   		</script>
   
</body>
</html>