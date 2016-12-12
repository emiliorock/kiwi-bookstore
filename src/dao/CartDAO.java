package dao;

import model.Cart;

import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import com.google.appengine.api.utils.SystemProperty;
import com.google.appengine.labs.repackaged.org.json.*;

public class CartDAO {
	
	public JSONArray getRate() throws JSONException {
		JSONArray items = new JSONArray();
		String url = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:google:mysql://bookshelf-echo:us-central1:bookshelf-echo/kiwi?user=root&password=Hmx152879";
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:mysql://107.178.223.127:3306/kiwi?user=root&password=Hmx152879";
		}
		Connection conn = null;
		ResultSet rec = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "SELECT ItemID, ItemName, Seller, COUNT(UserID) FROM TBL_CART GROUP BY ItemName ORDER BY count(UserID) DESC;";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				JSONObject item = new JSONObject();
				item.put("id", rec.getString("ItemID"));
				item.put("title", rec.getString("ItemName"));
				item.put("seller", rec.getString("Seller"));
				item.put("count", rec.getString("COUNT(UserID)"));
				items.put(item);
			}
			System.out.println(items);
			rec.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public JSONArray getPopBySeller(String username) throws JSONException {
		JSONArray items = new JSONArray();
		String url = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:google:mysql://bookshelf-echo:us-central1:bookshelf-echo/kiwi?user=root&password=Hmx152879";
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:mysql://107.178.223.127:3306/kiwi?user=root&password=Hmx152879";
		}
		Connection conn = null;
		ResultSet rec = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "SELECT itemName, COUNT(UserID) FROM TBL_CART WHERE seller = '" + username + "' group by itemName;";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				JSONObject item = new JSONObject();
				item.put("title", rec.getString("itemName"));
				item.put("count", rec.getString("count(UserID)"));
				items.put(item);
			}
			System.out.println(items);
			rec.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}
	
	public ArrayList<Cart> findOneCart(String userid) {
		ArrayList<Cart> list = new ArrayList<Cart>();
		String url = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:google:mysql://bookshelf-echo:us-central1:bookshelf-echo/kiwi?user=root&password=Hmx152879";
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:mysql://107.178.223.127:3306/kiwi?user=root&password=Hmx152879";
		}
		Connection conn = null;
		ResultSet rec = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM TBL_CART WHERE userid = '" + userid + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				Cart cart = new Cart();
				cart.setId(rec.getString("id"));
				cart.setUserID(rec.getString("userid"));
				cart.setSeller(rec.getString("seller"));
				cart.setItemID(rec.getString("itemid"));
				cart.setItemName(rec.getString("itemname"));
				cart.setPrice(rec.getString("price"));
				cart.setTime(rec.getString("time"));
				list.add(cart);
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int addToCart(String userID, String seller, String itemID, String itemName, String price, String link) throws IOException, SQLException {
		String url = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:google:mysql://bookshelf-echo:us-central1:bookshelf-echo/kiwi?user=root&password=Hmx152879";
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:mysql://107.178.223.127:3306/kiwi?user=root&password=Hmx152879";
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM TBL_CART WHERE itemID = '" + itemID + "' and userID = '" + userID + "';";
			st.executeQuery(sql);
			ResultSet res = null;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				return 1;
			}
			sql = "SELECT MAX(id) FROM TBL_CART";
			st.executeQuery(sql);
			res = null;
			int id = 0;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				id = res.getInt(1) + 1;
			}
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateformat.format(new Date()); 
			Timestamp timestamp = Timestamp.valueOf(time); 
			sql = "INSERT INTO TBL_CART VALUES(\"" + id + "\", \"" + userID + "\", \"" + seller + "\", \"" 
					+ itemID + "\", \"" + itemName + "\", \"" + price + "\", \"" + link + "\", \"" + timestamp + "\");";
			System.out.println(sql);
			st.executeUpdate(sql);
			res.close();
			conn.close();
			System.out.println(sql + " insert ok");
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	public void deleteFromCart(String id) throws IOException, SQLException {
		String url = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:google:mysql://bookshelf-echo:us-central1:bookshelf-echo/kiwi?user=root&password=Hmx152879";
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			url = "jdbc:mysql://107.178.223.127:3306/kiwi?user=root&password=Hmx152879";
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			Statement st = conn.createStatement(); 
			String sql = "DELETE FROM TBL_CART WHERE id = '" + id + "';";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " delete ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}