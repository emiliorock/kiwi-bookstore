package servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import model.*;
import dao.*;
import mail.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

public class UserLogic {
	
	static public void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		System.out.println("logout!");
		HttpSession session = request.getSession();
		session.removeAttribute("userSession");
		session.removeAttribute("sellerSession");
		session.removeAttribute("adminSession");
		RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
		dispatcher.forward(request, response);
	}
	
	static public void getAllUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		UserDAO dao = new UserDAO();
		ArrayList<User> list = new ArrayList<User>(); 
		list = dao.getAllUsers();
		String json = new Gson().toJson(list);
		PrintWriter out = response.getWriter();		
		out.write(json);
		out.close();		
	}
	
	/**    Accept username and password and find whether the USER it is in the database     **/
	static public void findOneUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		UserDAO dao = new UserDAO();
		User user = new User();
		user = dao.findOneUser(username, password);
		if (user != null) 
			out.write("login ok");
		else 
			out.write("login failed");
		out.close();
		HttpSession session = request.getSession();
		session.setAttribute("userSession", user);
	}
	
	/**    Accept username and password and find the SELLER whether it is in the database     **/
	static public void findOneSeller(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		SellerDAO dao = new SellerDAO();
		Seller seller = new Seller();
		seller = dao.findOneSeller(username, password);
		if (seller != null) 
			out.write("login ok");
		else 
			out.write("login failed");
		out.close();
		HttpSession session = request.getSession();
		session.setAttribute("sellerSession", seller);
	}
	
	/**    Accept username and password and find the SELLER whether it is in the database     **/
	static public void findOneAdmin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		AdminDAO dao = new AdminDAO();
		Admin admin = new Admin();
		admin = dao.findOneAdmin(username, password);
		if (admin != null) 
			out.write("login ok");
		else 
			out.write("login failed");
		out.close();
		HttpSession session = request.getSession();
		session.setAttribute("adminSession", admin);
	}
	
	static public void updateOneUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		
		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String id = null;
		if(jo.has("id")) {
			id = jo.get("id").getAsString();
		}
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		String firstname = null;
		if(jo.has("firstname")) {
			firstname = jo.get("firstname").getAsString();
		}
		String lastname = null;
		if(jo.has("lastname")) {
			lastname = jo.get("lastname").getAsString();
		}
		String email = null;
		if(jo.has("email")) {
			email = jo.get("email").getAsString();
		}
		String address = null;
		if(jo.has("address")) {
			address = jo.get("address").getAsString();
		}
		String birthday = null;
		if(jo.has("birthday")) {
			birthday = jo.get("birthday").getAsString();
		}
		UserDAO dao = new UserDAO();
		User user = new User();	
		user = dao.updateOneUser(id, username, password, email, firstname, lastname, address, birthday);
		HttpSession session = request.getSession();
		session.setAttribute("userSession", user);
		out.write("update ok");
		out.close();
	}
	
	static public void updateOneSeller(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		
		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String id = null;
		if(jo.has("id")) {
			id = jo.get("id").getAsString();
		}
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		String email = null;
		if(jo.has("email")) {
			email = jo.get("email").getAsString();
		}
		String address = null;
		if(jo.has("address")) {
			address = jo.get("address").getAsString();
		}
		SellerDAO dao = new SellerDAO();
		Seller seller = new Seller();	
		seller = dao.updateOneSeller(id, username, password, email, address);
		HttpSession session = request.getSession();
		session.setAttribute("sellerSession", seller);
		out.write("update ok");
		out.close();
	}
	
	static public void addOneUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());

		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		String firstname = null;
		if(jo.has("firstname")) {
			firstname = jo.get("firstname").getAsString();
		}
		String lastname = null;
		if(jo.has("lastname")) {
			lastname = jo.get("lastname").getAsString();
		}
		String email = null;
		if(jo.has("email")) {
			email = jo.get("email").getAsString();
		}
		String address = null;
		if(jo.has("address")) {
			address = jo.get("address").getAsString();
		}
		String birthday = null;
		if(jo.has("birthday")) {
			birthday = jo.get("birthday").getAsString();
		}
		UserDAO dao = new UserDAO();
		int flag = dao.addOneUser(username, password, email, firstname, lastname, address, birthday);
//		MyAuthenticator sender = new MyAuthenticator();
//		sender.rssEmail(email);
		if(flag == 0) {
			out.write("Sign up ok");
		}
		else if(flag == 1) {
			out.write("Username already exists");
		}
		else if(flag == 2) {
			out.write("Email already exists");
		}
		out.close();
	}
	
	static public void addOneSeller(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());

		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String username = null;
		if(jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		String password = null;
		if(jo.has("password")) {
			password = jo.get("password").getAsString();
		}
		String email = null;
		if(jo.has("email")) {
			email = jo.get("email").getAsString();
		}
		String address = null;
		if(jo.has("address")) {
			address = jo.get("address").getAsString();
		}
		SellerDAO dao = new SellerDAO();
		int flag = dao.addOneSeller(username, password, email, address);
		if(flag == 0) {
			out.write("Sign up ok");
		}
		else if(flag == 1) {
			out.write("Username already exists");
		}
		else if(flag == 2) {
			out.write("Email already exists");
		}
		out.close();
	}
		
}