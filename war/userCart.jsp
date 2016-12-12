<!DOCTYPE html>
<html lang="en">

<head>

    <title>User Page</title>
	<jsp:include page="head.jsp" />

</head>

<body>

	<%@page import="model.User" %>
	<%
		User user = (User)session.getAttribute("userSession");
	%>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
    
    <!-- Page Content -->
    <div class="container">
		<div ng-app="itemApp" ng-controller="itemCtrl">
	        <div class="row">
	
		        <!-- left side bar -->
				<div class="col-md-3">
		        	<p class="lead"> <%=user.getUsername() %> </p>
		            <div class="list-group">
		            	<a href="userPage.jsp" class="list-group-item">My Homepage</a>
		                <a href="userProfile.jsp" class="list-group-item">My Profile</a>
		                <a href="userCart.jsp" class="list-group-item  active">My Cart</a>
		                <a href="user?action=logout" class="list-group-item">Log Out</a>
		            </div>
		        </div>
		        
				<!-- right side bar -->
	            <div class="col-md-9">
	            	<div class="well">
	            		<div class="text-center" ng-if="!items.length">
	            			<h3 >Your cart is empty</h3>
	            		</div>
						<div ng-repeat="item in items">
	                    	<div class="row">
	                    		
	                    		<div class="col-md-9 text-center">
	                    	 		<h4><a href="itemDetails.jsp?id={{ item.itemID }}">{{ item.itemName }}</a></h4> 
	                    			<h3>$ {{ item.price }}</h3>
	                    		</div>
	                        	<div class="col-md-3 text-center">
	                            	<h4>{{ item.time }}</h4>
	                            		<br>
	                            		<% if(user != null) { %>
		                    				<a class="btn btn-primary" ng-click="deleteFromCart(item)">Delete From Cart</a>
		                    			<% } %>
		                    		<!-- OK Modal content-->
									<div id="sucsModal" class="modal fade" role="dialog">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title" style="color:#111">Message</h4>
												</div>
												<div class="modal-body" style="color:#111">
														<p>{{ nameToShow }}</p>
														<p>has been deleted from your cart</p>
												</div>
												<div class="modal-footer">
													<a class="btn btn-default" onclick="javascript:window.location.reload()" data-dismiss="modal">Close</a>
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
														<h4 class="modal-title" style="color:#111">Message</h4>
												</div>
												<div class="modal-body" style="color:#111">
														<p>{{ item.itemName }}</p>
														<p>{{ responseMSG }}</p>
												</div>
												<div class="modal-footer">
													<a class="btn btn-default" onclick="javascript:window.location.reload()" data-dismiss="modal">OK</a>
												</div>
											</div>
						   				</div>
					    			</div>
		                    			
	                        	</div>
	                        	<hr>
	                  		</div>
	                    	<hr>
	               		</div>
					</div>
	           	</div>
	           	
	        </div>
		</div>
	</div>
	
	<script>
    	var app = angular.module("itemApp", [])
        	.controller("itemCtrl", function($scope, $http) {
        		$scope.user = {
        			id : "<%=user.getID()%>"	
        		};
				$scope.getItemsByUser = function() {
                   	$http.post('cart?action=getItemsByUser',
            			$scope.user).success(function(response) {
            				$scope.items = response;
            			}).error(function(response) {
            				$scope.items = response;
            			});
                   	};		
                $scope.getItemsByUser();
 				$scope.nameToShow = "";
                $scope.deleteFromCart = function(item) {
                	$http.post("cart?action=deleteFromCart", item).success(function(response) {
							$scope.responseMSG = response;
							if (response === "delete ok") {
								$scope.nameToShow = item.itemName;
								$('#sucsModal').modal('show');
							}
							else {
								$('#failModal').modal('show');
							}
						}).error(function(response) {
							$scope.responseMSG = response;
						});
                 };
              });	
	</script>
	
</body>
</html>