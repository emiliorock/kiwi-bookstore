<!DOCTYPE html>
<html lang="en">

<head>

    <title>My Items</title>
	<jsp:include page="head.jsp" />

</head>

<body>
    
    <!-- Navigation -->
    <jsp:include page="header.jsp" />

	<%@page import="model.Seller"%>
    <%
    	Seller seller = (Seller) session.getAttribute("sellerSession");
    %>
    
	<!-- Page Content -->
	<div ng-app="itemApp" ng-controller="itemCtrl">
    	
    	<div class="container">
			 <div class="row">
				
        		<!-- left side bar -->
				<div class="col-md-3">
                	<p class="lead"><%=seller.getUsername()%></p>
                	<div class="list-group">
                		<a href="sellerPage.jsp" class="list-group-item">My Homepage</a>
                    	<a href="sellerProfile.jsp" class="list-group-item active">My Profile</a>
                    	<a href="sellerItems.jsp" class="list-group-item">My Items</a>
                	</div>
            	</div>
				
				<!-- right side bar -->
	            <form name="myForm" novalidate>
		            <div class="col-md-9">
		            	<div class="well text-center">
		            		<div class="row">
			            		<div class="col-md-1">
			            		</div>
			            		<div class="col-md-10">
			            			<h3>Item Details</h3>
			            			<hr>
			            			<div class="form-group">
		  								<label class="control-label col-md-3" for="title">Book Title:</label>
										<div class="col-md-8">
		  									<input type="text" name="title" class="form-control" placeholder="Name for the book" 
		  										aria-describedby="basic-addon1" ng-model="item.title" required>
		  								</div>
										<br>
		  								<span style="color:blue" ng-show="myForm.title.$dirty || !myForm.title.length">
		 									<span ng-show="myForm.title.$error.required"><br>Title is required</span>
		  								</span>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
		  							<div class="form-group">
		  								<label class="control-label col-md-3" for="author">Author:</label>
										<div class="col-md-8">
		  									<input type="text" name="author" class="form-control" placeholder="Author for the book" 
		  										aria-describedby="basic-addon1" ng-model="item.author" required>
		  								</div>
										<br>
		  								<span style="color:blue" ng-show="myForm.author.$dirty || !myForm.author.length">
		 									<span ng-show="myForm.author.$error.required"><br>Author is required</span>
		  								</span>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
		  							<div class="form-group">
		  								<label class="control-label col-md-3" for="type">Type:</label>
										<div class="col-md-8">
		  									<input type="text" name="type" class="form-control" placeholder="Type for the book" 
		  										aria-describedby="basic-addon1" ng-model="item.type" required>
		  								</div>
										<br>
		  								<span style="color:blue" ng-show="myForm.type.$dirty || !myForm.type.length">
		 									<span ng-show="myForm.type.$error.required"><br>Type is required</span>
		  								</span>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
		  							<div class="form-group">
		  								<label class="control-label col-md-3" for="year">Publishing Year:</label>
										<div class="col-md-8">
		  									<input type="text" name="year" class="form-control" placeholder="Publishing year of the book" 
		  										aria-describedby="basic-addon1" ng-model="item.year" required>
		  								</div>
										<br>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
		  							<div class="form-group">
		  								<label class="control-label col-md-3" for="price">Price:</label>
										<div class="col-md-8">
		  									<input type="text" name="price" class="form-control" placeholder="Price for the book" 
		  										aria-describedby="basic-addon1" ng-model="item.price" required>
		  								</div>
										<br>
		  								<span style="color:blue" ng-show="myForm.price.$dirty || !myForm.price.length">
		 									<span ng-show="myForm.price.$error.required"><br>Price is required</span>
		  								</span>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
									<div class="form-group">
		  								<label class="control-label col-md-3" for="seller">Seller:</label>
										<div class="col-md-8">
		  									<input type="text" name="seller" class="form-control" placeholder="<%=seller.getUsername()%>" 
		  										aria-describedby="basic-addon1" ng-model="item.seller" disabled>
		  								</div>
										<br>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
									<div class="form-group">
		  								<label class="control-label col-md-3" for="publisher">Publisher:</label>
										<div class="col-md-8">
		  									<input type="text" name="publisher" class="form-control" placeholder="Publisher for the book" 
		  										aria-describedby="basic-addon1" ng-model="item.publisher">
		  								</div>
										<br>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
		  							<div class="form-group">
		  								<label class="control-label col-md-3" for="url">URL:</label>
										<div class="col-md-8">
		  									<input type="text" name="url" class="form-control" placeholder="Link for the book" 
		  										aria-describedby="basic-addon1" ng-model="item.url">
		  								</div>
										<br>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
									<hr>
									<button class="btn btn-primary"  
										ng-disabled="myForm.title.$invalid||myForm.author.$invalid||myForm.type.$invalid||myForm.price.$invalid" 
										ng-click="add()">Add</button>
									<button class="btn btn-primary" ng-click="reset()">Reset</button>
								
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
													<p>{{ item.title }}</p>
													<p>has been successfully added</p>
												</div>
												<div class="modal-footer">
													<a class="btn btn-default" href="sellerItems.jsp">Close</a>
												</div>
											</div>
										</div>
									</div>
									<!-- / Modal -->
									
								</div>
								<div class="col-md-1">
		            			</div>
							</div>
		            	</div>
		            </div>
		        </form>
				<!-- / right side bar -->
			
			</div>
		</div>
	</div>
	
	<script>
		var app = angular.module("itemApp", [])
			.controller("itemCtrl", function($scope, $http) {
				$scope.item = {
					title : "",
					author : "",
					type : "",
					year : "",
					price : "",
					seller : "<%=seller.getUsername()%>",
					publisher : "",
					url : ""
				};
				
				$scope.add = function() {
					$http.post("item?action=addOneItem", $scope.item).success(function(response) {
   						$scope.responseMSG = response;
						if (response === "add ok") {
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