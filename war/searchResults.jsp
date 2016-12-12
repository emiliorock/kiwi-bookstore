<!DOCTYPE html>
<html lang="en">

<head>

    <title>Search Results</title>
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
					<div class="well">
					<h4>Search Results</h4>
					<hr>
					
						<div ng-repeat="item in items">
	                    	<div class="row">	                    	
	                    		<div class="col-md-4 text-center">
	                    	 		<div class="thumbnail">
	                    				<img src="{{ item.url }}"width="160" height="160">
	                   				</div>
	                    		</div>
	                        	<div class="col-md-8 text-center">
	                            	<h3>{{ item.title }}</h3> 
	                            	<h4>by {{ item.author }}</h4>
	                            	<br>
	                            	<h3><font color="#6F8CB2">$ {{ item.price }}</font></h3>
	                            	<br>
	                            	<a class="btn btn-primary" href="itemDetails.jsp?id={{ item.id }}">View Details</a>
	                        	</div>
	                        </div>
	                        <hr>
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
					$scope.items = {
							title : "<%=request.getParameter("title")%>",
							author : "<%=request.getParameter("author")%>",
							type : "<%=request.getParameter("type")%>",
							year : "<%=request.getParameter("year")%>",
							seller : "<%=request.getParameter("seller")%>",
							publisher : "<%=request.getParameter("publisher")%>",
							range : "<%=request.getParameter("range")%>",
							price : "<%=request.getParameter("price")%>"
					};
					
					$scope.item = {
						id : "",
						title : "",
						author : "",
						type : "",
						year : "",
						seller : "",
						publisher : "",
						price : "",
						url : ""
					};

					$scope.queryData = function() {
						$http.post('item?action=searchItems',
								$scope.items).success(function(response) {
							$scope.items = response;
						}).error(function(response) {
							$scope.items = response;
						});
					};

					$scope.queryData();
					
				});
	</script>

</body>

</html>