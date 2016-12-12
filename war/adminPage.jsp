<!DOCTYPE html>
<html lang="en">

<head>

    <title>Admin Page</title>
	<jsp:include page="head.jsp" />

</head>

<body>

	<%@page import="model.Admin" %>
	<%
		Admin admin = (Admin)session.getAttribute("adminSession");
	%>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
    
    <!-- Page Content -->
    <div ng-app="myApp" ng-controller="myCtrl">
	    <div class="container">
	        <div class="row">
	
		        <!-- left side bar -->
				<div class="col-md-3">
		        	<p class="lead"><%=admin.getUsername()%></p>
		            <div class="list-group">
		            	<a href="adminPage.jsp" class="list-group-item active">My Homepage</a>
		                <a href="" class="list-group-item">Manage Users</a>
		                <a href="" class="list-group-item">Manage Sellers</a>
		                <a href="" class="list-group-item">Manage Items</a>
		                <a href="user?action=logout" class="list-group-item">Log Out</a>
		            </div>
		        </div>
	
				<!-- right side bar -->
	            <div class="col-md-9">
	            	<div class="well text-center">
	            		<div class="row">
							<div class="col-md-2">
			    			</div>
			    			<div class="col-md-8">
			    				<h3>Seller Activities</h3>
			    				
			    			</div>
			    			<div class="col-md-2">
			    			</div>
	            	</div>
	            </div>
	            
			</div>
		</div>
	</div>
	
	<script>
		
	</script>
	
</body>
</html>