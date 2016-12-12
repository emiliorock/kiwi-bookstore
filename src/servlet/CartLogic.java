package servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import model.Cart;
import model.User;
import dao.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.labs.repackaged.org.json.*;
import com.google.gson.*;

public class CartLogic {
	
	static public void deleteFromCart(HttpServletRequest request,
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
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String id = null;
		if (jo.has("id")) {
			id = jo.get("id").getAsString();
		}
		System.out.println(id + "is id");
		CartDAO dao = new CartDAO();
		dao.deleteFromCart(id);
		out.write("delete ok");
		out.close();
	}
	
	static public void addToCart(HttpServletRequest request,
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
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userSession");
		String userID = user.getID();
		String seller = null;
		if (jo.has("seller")) {
			seller = jo.get("seller").getAsString();
		}
		String itemID = null;
		if (jo.has("id")) {
			itemID = jo.get("id").getAsString();
		}
		String itemName = null;
		if (jo.has("title")) {
			itemName = jo.get("title").getAsString();
		}
		String price = null;
		if (jo.has("price")) {
			price = jo.get("price").getAsString();
		}
		String url = null;
		if (jo.has("url")) {
			url = jo.get("url").getAsString();
		}
		System.out.println(userID + seller + itemID + itemName + price + url);
		CartDAO dao = new CartDAO();
		if(dao.addToCart(userID, seller, itemID, itemName, price, url) == 0) {
			out.write("add ok");
		}
		else {
			out.write("is already in your cart");
		}
		out.close();
	}

	static public void getItemsByUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String id = null;
		if (jo.has("id")) {
			id = jo.get("id").getAsString();
		}
		CartDAO dao = new CartDAO();
		ArrayList<Cart> list = new ArrayList<Cart>(); 
		list = dao.findOneCart(id);
		String json = new Gson().toJson(list);
		out.write(json);
		out.close();
	}
	
	static public void getRate(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException, SQLException, JSONException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		CartDAO dao = new CartDAO();
		JSONArray items = new JSONArray();
		items = dao.getRate();
		String json = items.toString();
		out.write(json);
		out.close();
	}
	
	static public void getPopBySeller(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException, JSONException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = request.getReader();
		String temp = "";
		while ((temp = reader.readLine()) != null) {
			sb.append(temp);
		}
		System.out.println(sb.toString());
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject(); 
		String username = null;
		if (jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		CartDAO dao = new CartDAO();
		JSONArray items = new JSONArray();
		items = dao.getPopBySeller(username);
		String json = items.toString();
		out.write(json);
		out.close();
	}
	
}