<!DOCTYPE html>
<html lang="en">

<head>

    <title>My Items</title>
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
    <div class="container">
		<div ng-app="itemApp" ng-controller="itemCtrl">
	        <div class="row">
	       
	       		<!-- left side -->
	            <div class="col-md-3">
	                <div class="list-group">
	                    <a href="sellerPage.jsp" class="list-group-item">My Homepage</a>
	                    <a href="sellerProfile.jsp" class="list-group-item">My Profile</a>
	                    <a href="sellerItems.jsp" class="list-group-item active">My Items</a>
	                    <a href="user?action=logout" class="list-group-item">Log Out</a>
	                </div>
	            </div>
	            
				<!-- right side -->
	            <div class="col-md-9">
					<div class="well">
						<div class="text-right">
	                        <a class="btn btn-primary" href="addNewItems.jsp">Add new items</a>
	                    </div>
						<hr>
						<div ng-repeat="item in items">
	                    	<div class="row">
	                    	<div class="col-md-4 text-center">
	                    	 	<img src="{{ item.url }}" class="img-thumbnail" width="160" height="160">
							</div>
	                        	<div class="col-md-8 text-center">
	                            	<h4>{{ item.title }}</h4> 
	                            	by {{ item.author }}
	                            	<h3><font color="#6F8CB2">$ {{ item.price}}</font></h3>
	                            	<br>
	                            	<a class="btn btn-primary" href="itemDetails.jsp?id={{ item.id }}">View Details</a>
	                            	<a class="btn btn-primary" href="updateItem.jsp?id={{ item.id }}">Update Item</a>
	                            	<a class="btn btn-primary" ng-click="deleteItem(item)"">Delete Item</a>
	                            	
	                            	<!-- OK Modal content-->
									<div id="sucsModal" class="modal fade" role="dialog">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h4 class="modal-title" style="color:#111">Message</h4>
												</div>
												<div class="modal-body" style="color:#111">
														<p>{{ titleToShow }}</p>
														<p>has been deleted from your list</p>
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
														<p>Something went wrong</p>
												</div>
												<div class="modal-footer">
													<a class="btn btn-default" onclick="javascript:window.location.reload()" data-dismiss="modal">OK</a>
												</div>
											</div>
						   				</div>
					    			</div>
	                            	
	                        	</div>
	                        </div>
	                        <hr>
	                    </div>
	
	                </div>
				</div>
			</div>
		</div>
	</div>
    
    <div class="container">
        <hr>
	</div>
    
    <script>
    	var app = angular.module("itemApp", [])
        	.controller("itemCtrl", function($scope, $http) {
				$scope.seller = {
                	id : "<%=seller.getID()%>",
                    username : "<%=seller.getUsername()%>",
                    password : "<%=seller.getPassword()%>",
                    email : "<%=seller.getEmail()%>",
                    address : "<%=seller.getAddress()%>",
                    ban : "<%=seller.getBan()%>"
                };
                $scope.getItemsBySeller = function() {
                    $http.post('item?action=getItemsBySeller',
            			$scope.seller).success(function(response) {
            				$scope.items = response;
            			}).error(function(response) {
            				$scope.items = response;
            			});
                };
                $scope.getItemsBySeller();
                $scope.responseMSG = "";
                $scope.titleToShow = "";
                $scope.deleteItem = function(item) {
                	$http.post("item?action=deleteOneItem", item).success(function(response) {
                		if (response === "ok") {
                			$scope.titleToShow = item.title;
							$('#sucsModal').modal('show');
						}
						else {
							$('#failModal').modal('show');
						}
                	}).error(function(reponse) {
                		$scope.responseMSG = response;
                	})
                };
		});	
	</script>
    
</body>
</html>