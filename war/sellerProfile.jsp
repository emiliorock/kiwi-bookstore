<!DOCTYPE html>
<html lang="en">

<head>

    <title>My Profile</title>
	<jsp:include page="head.jsp" />

</head>

<body>
	
	<%@page import="model.Seller"%>
	<%
		Seller seller = (Seller) session.getAttribute("sellerSession"); 
	%>
	
    <!-- Navigation -->
    <jsp:include page="header.jsp" />

    <!-- Page Content -->
    <div ng-app="updateApp" ng-controller="updateCtrl">
    	<div class="container">
    		<div class="row">
    			
    			<!-- left side bar -->
    			<div class="col-md-3">
                <p class="lead"><%=seller.getUsername() %></p>
	                <div class="list-group">
	                    <a href="sellerPage.jsp" class="list-group-item active">My Homepage</a>
	                    <a href="sellerProfile.jsp" class="list-group-item">My Profile</a>
	                    <a href="sellerItems.jsp" class="list-group-item">My Items</a>
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
								<h3>{{ seller.username }}'s Profile</h3>
								<hr>
		    					<p><p>
		    					<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Password</span>
		  							<input type="password" class="form-control" placeholder="seller.password" aria-describedby="basic-addon1" ng-model="seller.password">
								</div>
								<p></p>
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">Email</span>
		  							<input type="text" class="form-control" placeholder="seller.email" aria-describedby="basic-addon1" ng-model="seller.email">
								</div>
								<p></p>	
								<div class="input-group">	
									<span class="input-group-addon" id="basic-addon1">Address</span>
		  							<input type="text" class="form-control" placeholder="seller.address" aria-describedby="basic-addon1" ng-model="seller.address">
								</div>
								<p></p>	
								<a class="btn btn-primary" ng-click="update()">OK</a>
								<a class="btn btn-primary" onclick="goback()">Back</a>
								
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
   				$scope.seller = {
   						id : "<%=seller.getID()%>",
   					username : "<%=seller.getUsername()%>",
   					password : "<%=seller.getPassword()%>",
   					email : "<%=seller.getEmail()%>",
   					address : "<%=seller.getAddress()%>"
   				};
   				$scope.responseMSG = "";
   				$scope.update = function () {
   					$http.post("user?action=updateOneSeller", $scope.seller).success(function(response) {
   						$scope.responseMSG = response;
						if (response === "update ok") {
							$('#myModal').modal('show')
						} 
   					}).error(function(response) {
   						$scope.responseMSG = response;
   					});
   				};
   			});
   			function goback() {
   	    	    window.history.back();
   	    	}
   		</script>
   
</body>
</html>