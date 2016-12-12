<!DOCTYPE html>
<html lang="en">

<head>

    <title>Sign Up Page</title>
	<jsp:include page="head.jsp" />

</head>

<body>

    <!-- Navigation -->
    <jsp:include page="header.jsp" />
    
    <div class="row">
    	<div class="col-lg-4">
    	</div>
    	<div class="col-lg-4">
    		<div class="well text-center">
    			<h4>I want to sign up as a </h4>
    			<hr>
    			<div class="row">
    				<div class="col-md-1">
    				</div>
    				<div class="col-md-10">				
						<a class="btn btn-primary" href="userSignUp.jsp">User</a>
						<a class="btn btn-primary" href="sellerSignUp.jsp">Seller</a>
						<a class="btn btn-primary" onclick="goback()">Back</a>
					</div>
					<div class="col-md-1">
					</div>	
				</div>
    		</div>
    	</div>
    	<div class="col-lg-4">
    	</div>   	
    </div>
    
    <script>
    	function goback() {
    	    window.history.back();
    	}
    </script>
    
</body>
</html>