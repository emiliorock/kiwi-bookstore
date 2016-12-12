<!DOCTYPE html>
<html lang="en">

<head>

    <title>Search Page</title>
	<jsp:include page="head.jsp" />
        
</head>

<body>

	<jsp:include page="header.jsp" />
	<!-- Page Content -->
    <div class="container">
		<div ng-app="searchApp" ng-controller="searchCtrl">
			<div class="row">
       
	       		<!-- left side -->
	            <jsp:include page="leftBar.jsp" />
	            
				<!-- right side -->
	            <div class="col-md-9">
					<div class="well text-center">
						<div class="row">
							<h4>Advanced Search</h4>
							<div class= "col-md-3">
							</div>
							<div class= "col-md-6">
		    					<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Title</span>
		  							<input type="text" class="form-control" placeholder="Title" aria-describedby="basic-addon1" ng-model="keyword.title">
								</div>
								<p></p>
								<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Author</span>
		  							<input type="text" class="form-control" placeholder="Author" aria-describedby="basic-addon1" ng-model="keyword.author">
								</div>
								<p></p>
								<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Type</span>
		  							<input type="text" class="form-control" placeholder="Type" aria-describedby="basic-addon1" ng-model="keyword.type">
								</div>
								<p></p>
								<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Seller</span>
		  							<input type="text" class="form-control" placeholder="Seller" aria-describedby="basic-addon1" ng-model="keyword.seller">
								</div>
								<p></p>
								<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Year</span>
		  							<input type="text" class="form-control" placeholder="Year" aria-describedby="basic-addon1" ng-model="keyword.year">
								</div>
								<p></p>
								<div class="input-group">
		  							<span class="input-group-addon" id="basic-addon1">Publisher</span>
		  							<input type="text" class="form-control" placeholder="Publisher" aria-describedby="basic-addon1" ng-model="keyword.publisher">
								</div>
								<p></p>
								
								<div class="input-group">
      								<div class="input-group-btn">
        								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Price	<span class="caret"></span>
        								</button>
        								<ul class="dropdown-menu pull-right">
								          <li ng-click="low()"><a href="">Lower than</a></li>
								          <li ng-click="high()"><a href="">Higher than</a></li>
        								</ul>
      								</div>
      								<input id="buttondropdown" name="buttondropdown" class="form-control" placeholder="Price" type="text" ng-model="keyword.price">
    							</div>
								
								<p></p>
								<button class="btn btn-primary" ng-click="search()">Search</button>
							</div>
							<div class= "col-md-3">
							</div>
						</div>
					</div>
				</div>
			
			</div>
		</div>
	</div>

	<script>
		angular.module('searchApp', [])
			.controller('searchCtrl', ['$scope', '$location', '$http', '$window', function($scope, $location, $http, $window) {						
		    	$scope.range = "";
				$scope.keyword = {
		    			title : "",
		    			author: "",
		    			type : "",
		    			year : "",
		    			seller : "",
		    			publisher : "",
		    			range : "",
		    			price : ""
		    	};
				$scope.low = function() {
		    		$scope.range = "&range=0";
		    	};
		    	$scope.high = function() {
		    		$scope.range = "&range=1";
		    	};
				$scope.search = function() {
		    		var url = "searchResults.jsp?title=" + $scope.keyword.title 
					+ "&author=" + $scope.keyword.author
					+ "&type=" + $scope.keyword.type
					+ "&year=" + $scope.keyword.year
					+ "&seller=" + $scope.keyword.seller
					+ "&publisher=" + $scope.keyword.publisher
					+ "&price=" + $scope.keyword.price
					+ $scope.range;
		    		$window.location.href = url;
		    	};
		}]);
		
		$(function(){
			$(".dropdown-menu li a").click(function(){
			    $(".btn:first-child").text($(this).text());
			    $(".btn:first-child").val($(this).text());
			});
		});
	</script>

</body>

</html>