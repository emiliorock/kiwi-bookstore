<!DOCTYPE html>
<html lang="en">

<head>

    <title>Update Item</title>
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
	                    <a href="sellerItems.jsp" class="list-group-item">My Items</a>
	                    <a href="user?action=logout" class="list-group-item">Log Out</a>
	                </div>
	            </div>
	            
				<!-- right side -->
	            <div class="col-md-9">
					<div class="well text-center">
						<h3>Item Details</h3>
						<hr>
						<form name="myForm" novalidate>
							<div class="row">
								<div class="col-md-1">
								</div>
								<div class="col-md-10">
									<div class="form-group">
				    					<label class="control-label col-md-3" for="title">Title:</label>
										<div class="col-md-8">
				  							<input type="text" name="title" class="form-control" placeholder="{{item.title}}" aria-describedby="basic-addon1" 
				  								ng-model="item.title" required>
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
				  							<input type="text" name="author" class="form-control" placeholder="{{item.author}}" aria-describedby="basic-addon1" 
				  								ng-model="item.author" required>
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
				  							<input type="text" name="type" class="form-control" placeholder="{{item.type}}" aria-describedby="basic-addon1" 
				  								ng-model="item.type" required>
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
				  							<input type="text" name="year" class="form-control" placeholder="{{item.year}}" 
				  								aria-describedby="basic-addon1" ng-model="item.year" required>
				  						</div>
										<br>
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
		  									<input type="text" name="publisher" class="form-control" placeholder="{{item.publisher}}" 
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
		  									<input type="text" name="url" class="form-control" placeholder="{{item.url}}" 
		  										aria-describedby="basic-addon1" ng-model="item.url">
		  								</div>
										<br>
		  								<div class="col-md-1">
		  								</div>
		  								<br>
		  							</div>
		  							
									<hr>
				  					<a class="btn btn-primary" ng-click="update()">Update</a>
				  					<a class="btn btn-primary" ng-click="reset()">Reset</a>
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
													<p>{{item.title}}</p>
													<p>has been successfully updated</p>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" 
														onclick="javascript:window.location.reload()" data-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>
									<!-- / Modal -->
				  					
				  				</div>
				  				<div class="col-md-1">
				  				</div>
		  					</div>
							
						</form>
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
				$scope.item = {
						id : "<%=request.getParameter("id")%>",
						title : "",
						author : "",
						type : "",
						year : "",
						seller : "",
						publisher : "",
						price : "",
						url : "",
						ban : ""
				};
				$scope.getItem = function() {
					$http.post("item?action=getItemByID", $scope.item).success(function(response){
						$scope.item = response;
					}).error(function(response) {
						$scope.item = response;
					});
				};
				$scope.getItem();
				$scope.responseMSG = "";
				$scope.update = function() {
					$http.post("item?action=updateOneItem", $scope.item).success(function(response){
						$scope.responseMSG = response;
						if (response === "update ok") {
							$('#myModal').modal('show')
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