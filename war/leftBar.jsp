<%@page import="model.*" %>

<%
	User user = (User) session.getAttribute("userSession");
	Seller seller = (Seller) session.getAttribute("sellerSession");
	Admin admin = (Admin) session.getAttribute("adminSession");
%>

<%  if(user != null) { %>
        <div class="col-md-3">
        	<p class="lead"> <%=user.getUsername() %> </p>
            <div class="list-group">
            	<a href="userPage.jsp" class="list-group-item">My Homepage</a>
                <a href="userProfile.jsp" class="list-group-item">My Profile</a>
                <a href="userCart.jsp" class="list-group-item">My Cart</a>
                <a href="user?action=logout" class="list-group-item">Log Out</a>
            </div>
        </div>
<% } else if(seller != null) { %>
        <div class="col-md-3">
        	<p class="lead"> <%=seller.getUsername() %> </p>
            <div class="list-group">
            	<a href="sellerPage.jsp" class="list-group-item">My Homepage</a>
                <a href="sellerProfile.jsp" class="list-group-item">My Profile</a>
                <a href="sellerItems.jsp" class="list-group-item">My Items</a>
                <a href="user?action=logout" class="list-group-item">Log Out</a>
            </div>
        </div>
        
<% } else if(admin != null) { %>
        <div class="col-md-3">
        	<p class="lead"> <%=admin.getUsername() %> </p>
            <div class="list-group">
            	<a href="sellerPage.jsp" class="list-group-item">My Homepage</a>
                <a href="sellerProfile.jsp" class="list-group-item">My Profile</a>
                <a href="sellerItems.jsp" class="list-group-item">My Items</a>
                <a href="user?action=logout" class="list-group-item">Log Out</a>
            </div>
        </div>
<% } else { %>
		<div class="col-md-3">
		    <div class="list-group">
		    	<a href="homepage.jsp" class="list-group-item">Back To Homepage</a>
		        <a href="signup.jsp" class="list-group-item active">Sign up Now</a>
		        <a href="login.jsp" class="list-group-item">Log In Now</a>
		    </div>
		</div>
<% } %>