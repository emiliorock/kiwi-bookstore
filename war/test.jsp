<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body>


			<div ng-app="addItemApp" ng-controller="addItemCtrl">
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Title</span>
  					<input type="text" class="form-control" placeholder="Title" aria-describedby="basic-addon1" ng-model="item.title">
				</div>
				<p></p>
				
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Author</span>
  					<input type="text" class="form-control" placeholder="Author" aria-describedby="basic-addon1" ng-model="item.author">
				</div>
				<p></p>
				
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Type</span>
  					<input type="text" class="form-control" placeholder="Type" aria-describedby="basic-addon1" ng-model="item.type">
				</div>
				<p></p>
				
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Year</span>
  					<input type="text" class="form-control" placeholder="year" aria-describedby="basic-addon1" ng-model="item.year">
				</div>
				<p></p>
				
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Price</span>
  					<input type="text" class="form-control" placeholder="Price" aria-describedby="basic-addon1" ng-model="item.price">
				</div>
				<p></p>
				
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Seller</span>
  					<input type="text" class="form-control" placeholder="Seller" aria-describedby="basic-addon1" ng-model="item.seller">
				</div>
				<p></p>
				
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Publisher</span>
  					<input type="text" class="form-control" placeholder="Publisher" aria-describedby="basic-addon1" ng-model="item.publisher">
				</div>
				<p></p>
				<div class="input-group">
  					<span class="input-group-addon" id="basic-addon1">Ban</span>
  					<input type="text" class="form-control" placeholder="Ban" aria-describedby="basic-addon1" ng-model="item.ban">
				</div>
				<p></p>
    			<button ng-click="add()">OK</button>
			</div>

<script>
angular.module('addItemApp', [])
.controller('addItemCtrl', function($scope, $http) {

    $scope.add = function() {
        $http.post('item?action=deleteOneItem', $scope.item).success(function(response) {
        	$scope.item = response;
        }).error(function(response) {
        	$scope.item = response;
        });
    };
});
</script>

</body>
</html>