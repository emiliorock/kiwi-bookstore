<!DOCTYPE html>
<html lang="en">

<head>

    <title>Welcome to Kiwi Bookstore</title>
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
    <div ng-app="searchApp" ng-controller="searchCtrl">
    	<div class="container">

        	<!-- Heading Row -->
        	<div class="row">
            	<div class="col-md-8">
                	<img class="img-responsive img-rounded" src="http://placehold.it/900x350" alt="">
            	</div>
            	<div class="col-md-4" line-height: 50px;>
                	<h1 align="center">Kiwi Bookstore</h1>
                	<br>
                	<p>"If a writer knows enough about what he is writing about, he may omit things that he knows. 
                	The dignity of movement of an iceberg is due to only one ninth of it being above water."</p>
       				<p></p>
                	<p class="text-right">Ernest Hemingway</p>
                	<br>
                	<div class="text-center">
	                	<a class="btn btn-primary btn-lg" href="signup.jsp">Sign Up</a>
	                	<a class="btn btn-primary btn-lg" href="login.jsp">Log In</a>
	                	<a class="btn btn-primary btn-lg" href="user?action=logout">Log Out</a>
                	</div>
            	</div>
        	</div>
			<hr>
			
			<!-- Search Bar -->
			<div class="row">
				<div class="col-md-8">
				</div>
				<div class="col-md-4">
					<div class="input-group">
				    	<input type="text" class="form-control pull-right" placeholder="Title of the book" aria-describedby="basic-addon1" ng-model="keyword.title">
				        	<span class="input-group-btn">
				            	<button class="btn btn-default" type="button" ng-click="search()">
				                	<span class="glyphicon glyphicon-search"></span>
				                </button>
				           	</span>
				   	</div>
				</div>
		   	</div>

	        <!-- Content Row -->
	        <div class="row">
	            <div class="col-md-4">
	                <h2>Heading 1</h2>
	                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe rem nisi accusamus error velit animi non ipsa placeat. Recusandae, suscipit, soluta quibusdam accusamus a veniam quaerat eveniet eligendi dolor consectetur.</p>
	                <a class="btn btn-default" href="#">More Info</a>
	            </div>
	            <!-- /.col-md-4 -->
	            <div class="col-md-4">
	                <h2>Heading 2</h2>
	                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe rem nisi accusamus error velit animi non ipsa placeat. Recusandae, suscipit, soluta quibusdam accusamus a veniam quaerat eveniet eligendi dolor consectetur.</p>
	                <a class="btn btn-default" href="#">More Info</a>
	            </div>
	            <!-- /.col-md-4 -->
	            <div class="col-md-4">
	                <h2>Heading 3</h2>
	                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Saepe rem nisi accusamus error velit animi non ipsa placeat. Recusandae, suscipit, soluta quibusdam accusamus a veniam quaerat eveniet eligendi dolor consectetur.</p>
	                <a class="btn btn-default" href="#">More Info</a>
	            </div>
	            <!-- /.col-md-4 -->
	        </div>

    	</div>
    </div>
    
    <br>
    <br>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp" />
    <!-- /.container -->
    
    <script>
	    angular.module('searchApp', [])
		.controller('searchCtrl', ['$scope', '$location', '$http', '$window', function($scope, $location, $http, $window) {						
	    	$scope.keyword = {
	    			title : "",
	    			author: "",
	    			type : "",
	    			seller : "",
	    			publisher : ""
	    	};
			$scope.search = function() {
	    		var url = "searchResults.jsp?title=" + $scope.keyword.title 
				+ "&author=" + $scope.keyword.author
				+ "&type=" + $scope.keyword.type
				+ "&seller" + $scope.keyword.seller
				+ "&publisher" + $scope.keyword.publisher;
	    		$window.location.href = url;
	    };
	}]);
    </script>

</body>

</html>
