<!DOCTYPE html>
<html lang="en">

<head>

    <title>Item Details</title>
	<jsp:include page="head.jsp" />
        
</head>

<body>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
	
	<%@page import="model.*"%>
	<%
		User user = (User) session.getAttribute("userSession");
		Seller seller = (Seller) session.getAttribute("sellerSession");
		Admin admin = (Admin) session.getAttribute("adminSession");
	%>
	
	<!-- Page Content -->
    <div ng-app="resultsApp" ng-controller="resultsCtrl">
	    <div class="container">
	    	<div class="row">
	    	
				<jsp:include page="leftBar.jsp" />
	            
				<!-- right side -->
	            <div class="col-md-9">
					<div class="well text-center">
						<h4>Item Details</h4>
						<hr>
						<div class="row">
							<div class="col-md-3 text-center">
		                    	<img src="{{ item.url }}" class="img-thumbnail" width="160" height="160">
		                    	<p></p>
		                    	<h3>$ {{ item.price }}</h3>
		                    	<% if(user != null) { %>
		                    		<a class="btn btn-primary" ng-click="addToCart()">Add To Cart</a>
		                    	<% } %>
							</div>
							<div class="col-md-8 text-center">
								<h3> {{ item.title }} ({{item.year}})</h3>
								<h4>by {{ item.author }}</h4>
								<br>
								<h4>Book Type: {{ item.type }}</h4>
								<h4>Publisher: {{ item.publisher }}</h4>
								<h4>Seller: {{ item.seller }}</h4>
								
								<!-- OK Modal content-->
								<div id="sucsModal" class="modal fade" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title" style="color:#111">Message</h4>
											</div>
											<div class="modal-body" style="color:#111">
													<p>{{ item.title }}</p>
													<p>has been added to your cart</p>
											</div>
											<div class="modal-footer">
												<a class="btn btn-default" data-dismiss="modal">Close</a>
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
													<p>{{ item.title }}</p>
													<p>{{ responseMSG }}</p>
											</div>
											<div class="modal-footer">
												<a class="btn btn-default" data-dismiss="modal">OK</a>
											</div>
										</div>
					   				</div>
				    			</div>
								
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
	
	<script>
		var app = angular.module('resultsApp', []);
		app.controller('resultsCtrl',
				function($scope, $http, $window, $location) {
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
					
					$scope.queryData = function() {
						$http.post('item?action=getItemByID',
								$scope.item).success(function(response) {
							$scope.item = response;
						}).error(function(response) {
							$scope.item = response;
						});
					};

					$scope.queryData();
					
					$scope.responseMSG = "";
					$scope.addToCart = function() {
						$http.post("cart?action=addToCart", $scope.item).success(function(response) {
							$scope.responseMSG = response;
							if (response === "add ok") {
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
		
		function goback() {
    	    window.history.back();
    	}
	</script>

</body>

</html>