package servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import model.Item;
import dao.ActDAO;
import dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

public class ItemLogic {
	
	static public void getAllItems(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		ItemDAO dao = new ItemDAO();
		ArrayList<Item> list = new ArrayList<Item>(); 
		list = dao.getAllItems();
		String json = new Gson().toJson(list);
		PrintWriter out = response.getWriter();		
		out.write(json);
		out.close();		
	}
	
	static public void getItemByID (HttpServletRequest request,
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
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String id = null;
		if(jo.has("id")) {
			id = jo.get("id").getAsString();
		}
		ItemDAO dao = new ItemDAO();
		Item item = dao.getItemByID(id);
		String json = new Gson().toJson(item);
		out.write(json);
		out.close();
	}
	
	static public void deleteOneItem (HttpServletRequest request,
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
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String id = null;
		if(jo.has("id")) {
			id = jo.get("id").getAsString();
		}
		String title = null;
		if(jo.has("title")) 
			title = jo.get("title").getAsString();
		String price = null;
		if(jo.has("price")) 
			price = jo.get("price").getAsString();
		String seller = null;
		if(jo.has("seller")) 
			seller = jo.get("seller").getAsString();
		ItemDAO dao = new ItemDAO();
		if(dao.deleteOneItem(id, seller, title, price)) {
			out.write("ok");
		}
		else
			out.write("fail");
		out.close();
	}
	
	static public void updateOneItem(HttpServletRequest request,
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
		if(jo.has("id")) 
			id = jo.get("id").getAsString();
		String title = null;
		if(jo.has("title")) 
			title = jo.get("title").getAsString();
		String author = null;
		if(jo.has("author")) 
			author = jo.get("author").getAsString();
		String type = null;
		if(jo.has("type")) 
			type = jo.get("type").getAsString();
		String year = null;
		if(jo.has("year")) 
			year = jo.get("year").getAsString();
		String price = null;
		if(jo.has("price"))
			price = jo.get("price").getAsString();
		String seller = null;
		if(jo.has("seller")) 
			seller = jo.get("seller").getAsString();
		String publisher = null;
		if(jo.has("publisher")) 
			publisher = jo.get("publisher").getAsString();
		String url = null;
		if(jo.has("url"))
			url = jo.get("url").getAsString();
		String ban = null;
		if(jo.has("ban"))
			ban = jo.get("ban").getAsString();
		ItemDAO dao = new ItemDAO();
		dao.updateOneItem(id, title, author, type, year, price, seller, publisher, url, ban);
		out.write("update ok");
		out.close();
	}
	
	static public void addOneItem(HttpServletRequest request,
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
		String title = null;
		if(jo.has("title")) 
			title = jo.get("title").getAsString();
		String author = null;
		if(jo.has("author")) 
			author = jo.get("author").getAsString();
		String type = null;
		if(jo.has("type")) 
			type = jo.get("type").getAsString();
		String year = null;
		if(jo.has("year")) 
			year = jo.get("year").getAsString();
		String price = null;
		if(jo.has("price")) 
			price = jo.get("price").getAsString();
		String seller = null;
		if(jo.has("seller")) 
			seller = jo.get("seller").getAsString();
		String publisher = null;
		if(jo.has("publisher")) 
			publisher = jo.get("publisher").getAsString();
		String url = null;
		if(jo.has("url"))
			url = jo.get("url").getAsString();
		ItemDAO dao = new ItemDAO();
		Boolean bool = dao.addOneItem(title, author, type, year, price, seller, publisher, url);
		if(bool)
			out.write("add ok");
		out.close();
	}
	
	static public void getItemsBySeller(HttpServletRequest request,
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
		String username = null;
		if (jo.has("username")) {
			username = jo.get("username").getAsString();
		}
		ItemDAO dao = new ItemDAO();
		ArrayList<Item> list = new ArrayList<Item>(); 
		list = dao.getItemsBySeller(username);
		String json = new Gson().toJson(list);
		out.write(json);
		out.close();
	}
	
	static public void searchItems(HttpServletRequest request,
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
		//System.out.println(sb.toString());
		String sql = "select * from TBL_ITEM where ";
		int flag = 0;
		// convert to object
		Gson gson = new Gson();
		JsonElement je = gson.fromJson(sb.toString(), JsonElement.class);
		JsonObject jo = je.getAsJsonObject();
		String title = null;
		if(jo.has("title")) {
			title = jo.get("title").getAsString();
			if (!"".equals(title)) {
				sql += "title like '%" + title + "%' ";
				flag = 1;
			}
		}
		String author = null;
		if(jo.has("author")) {
			author = jo.get("author").getAsString();
			if (!"".equals(author)) {
				if (flag == 0)
					sql += "author like '%" + author + "%' ";
				else
					sql += "and author like '%" + author + "%' ";
				flag = 1;
			}
		}
		String type = null;
		if(jo.has("type")) {
			type = jo.get("type").getAsString();
			if (!"".equals(type)) {
				if (flag == 0)
					sql += "type = '" + type + "' ";
				else
					sql += "and type = '" + type + "' ";
				flag = 1;
			}
		}
		String year = null;
		if(jo.has("year")) {
			year = jo.get("year").getAsString();
			//System.out.println("year+"+year);
			if ((!"".equals(year)) && (!"null".equals(year))) {
				if (flag == 0)
					sql += "year = '" + year + "' ";
				else
					sql += "and year = '" + year + "' ";
				flag = 1;
			}
		}
		String seller = null;
		if(jo.has("seller")) {
			seller = jo.get("seller").getAsString();
			if (!"".equals(seller)) {
				if(flag == 0)
					sql += "seller like '%" + seller + "%' ";
				else
					sql += "and seller like '%" + seller + "%' ";
				flag = 1;
			}
		}
		String publisher = null;
		if(jo.has("publisher")) {
			publisher = jo.get("publisher").getAsString();
			if (!"".equals(publisher)) {
				if(flag == 0)
					sql += "publisher like '%" + publisher + "%' ";
				else
					sql += "and publisher like '%" + publisher + "%' ";
				flag = 1;
			}
		}
		String range = null;
		if(jo.has("range")) {
			range = jo.get("range").getAsString();
		}
		String price = null;
		if(jo.has("price")) {
			price = jo.get("price").getAsString();
			if ((!"".equals(price)) && (!"null".equals(price))) {
				if("0".equals(range)) {
					if(flag == 0)
						sql += "price <= " + price + " ";
					else
						sql += "and price <= " + price + " ";
				}
				else {
					if(flag == 0)
						sql += "price >= " + price + " ";
					else
						sql += "and price >= " + price + " ";
				}
				flag = 1;
			}
		}
		sql += ";";
		System.out.println(sql);
		ItemDAO dao = new ItemDAO();
		ArrayList<Item> list = new ArrayList<Item>(); 
		list = dao.searchItems(sql);
		String json = new Gson().toJson(list);
		out.write(json);
		out.close();
	}
	
	
}