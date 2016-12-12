<%@page import="model.User"%>
<%@page import="model.Seller"%>
<%@page import="model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	User user = (User) session.getAttribute("userSession");
	Seller seller = (Seller) session.getAttribute("sellerSession");
	Admin admin = (Admin) session.getAttribute("adminSession");
%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="homepage.jsp">
                    <img src="http://placehold.it/150x50&text=Logo" alt="">
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                
                <ul class="nav navbar-nav">
                    <li>
                    <% if(user != null) { %>
                        <a href="userPage.jsp">My Homepage</a>
                    <% } else if(seller != null) { %>
                    	<a href="sellerPage.jsp">My Homepage</a>
                    <% } else if(admin != null) { %>
                    	<a href="">My Homepage</a>
                    <% } %>
                    </li>
                    <li>
                        <a href="search.jsp">Advanced Search</a>
                    </li>
                </ul>
               
            </div>
            
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>