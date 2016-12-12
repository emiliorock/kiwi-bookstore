<!DOCTYPE html>
<html lang="en">

<head>

    <title>Seller Page</title>
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
 
            <div class="col-md-3">
                <p class="lead"><%=seller.getUsername() %></p>
                <div class="list-group">
                    <a href="sellerPage.jsp" class="list-group-item active">My Homepage</a>
                    <a href="sellerProfile.jsp" class="list-group-item">My Profile</a>
                    <a href="sellerItems.jsp" class="list-group-item">My Items</a>
                    <a href="user?action=logout" class="list-group-item">Log Out</a>
                </div>
            </div>

            <div class="col-md-9">
            	<div class="well">
					<center><h3><font color="#6F8CB2">My Popularity</font></h3></center>
					<hr>
					<div ng-repeat="item in items">
						<h4>{{ item.count }} people like <font color="#6F8CB2">{{ item.title }}</font></h4>
						<br>
					</div>
                </div>
				<div class="well">
                    <center><h3><font color="#6F8CB2">My Recent Activities</font></h3></center>
                    <hr>
                    <div ng-repeat="act in acts">
                    	<h4>{{act.act}} <font color="#6F8CB2">{{act.itemName}}</font></h4>
                    	<div class="text-right">{{act.time}}</div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    </div>
    <!-- /.container -->
	
	<jsp:include page="footer.jsp" />
	
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
                $scope.getActsBySeller = function() {
                    $http.post('act?action=getActsBySeller',
            			$scope.seller).success(function(response) {
            				$scope.acts = response;
            			}).error(function(response) {
            				$scope.acts = response;
            			});
                };
                $scope.getActsBySeller();
                $scope.getPopBySeller = function() {
                    $http.post('cart?action=getPopBySeller',
            			$scope.seller).success(function(response) {
            				$scope.items = response;
            			}).error(function(response) {
            				$scope.items = response;
            			});
                };
                $scope.getPopBySeller();
	});
	</script>
    
</body>
</html>