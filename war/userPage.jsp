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
    <div ng-app="itemApp" ng-controller="itemCtrl">
	    <div class="container">
			<div class="row">
	
		        <!-- left side bar -->
				<div class="col-md-3">
		        	<p class="lead"><%=user.getUsername()%></p>
		            <div class="list-group">
		            	<a href="userPage.jsp" class="list-group-item active">My Homepage</a>
		                <a href="userProfile.jsp" class="list-group-item">My Profile</a>
		                <a href="userCart.jsp" class="list-group-item">My Cart</a>
		                <a href="user?action=logout" class="list-group-item">Log Out</a>
		            </div>
		        </div>
	
				<!-- right side bar -->
	            <div class="col-md-9">
	            	<div class="well">
	            		<center><h3>Most Popular Books</h3></center>
	            		<hr>
						<div ng-repeat="item in items">
							<h4>{{ item.count }} people like <a href="itemDetails.jsp?id={{item.id}}">{{item.title}}</a></h4>
							<br>
						</div>
					</div>
	            </div>
	
	        </div>
	    </div>
    </div>


    <!-- Footer -->
    <jsp:include page="footer.jsp" />
    
    <script>
	    var app = angular.module("itemApp", [])
	        .controller("itemCtrl", function($scope, $http) {
	        	$scope.getRate = function() {
	        		$http.post('cart?action=getRate'
	            			).success(function(response) {
	            				$scope.items = response;
	            			}).error(function(response) {
	            				$scope.items = response;
	            			});
	        	};
	        	$scope.getRate();
	    });	
	</script>
	
</body>
</html>