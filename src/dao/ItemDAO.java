package dao;

import model.Item;
import dao.ActDAO;

import java.io.*;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import com.google.appengine.api.utils.SystemProperty;

public class ItemDAO {
	
	public Boolean addOneItem(String title, String author, String type, String year, String price, 
			String seller, String publisher, String url) throws IOException, SQLException {
		String db_url = null;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			try {
				Class.forName("com.mysql.jdbc.GoogleDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			db_url = "jdbc:google:mysql://bookshelf-echo:us-central1:bookshelf-echo/kiwi?user=root&password=Hmx152879";
		} else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			db_url = "jdbc:mysql://107.178.223.127:3306/kiwi?user=root&password=Hmx152879";
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(db_url);
			Statement st = conn.createStatement(); 
			String sql = "SELECT MAX(id) FROM TBL_ITEM;";
			ResultSet res = null;
			int id = 0;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				id = res.getInt(1) + 1;
			}
			sql = "INSERT INTO TBL_ITEM VALUES (" + id + ", \"" + title + "\", '" 
						+ author + "', '" + type + "', '" + year + "', '" + price + "', '" 
						+ seller + "', '" + publisher + "', '" + url +"', '0');";
			st.executeUpdate(sql);
			System.out.println(sql + " insert ok");
			ActDAO dao = new ActDAO();
			dao.addAct(id, seller, title, price);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Boolean deleteOneItem(String id, String seller, String title, String price) throws IOException, SQLException {
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
			String sql = "DELETE FROM TBL_ITEM WHERE id = '" + id + "';";
			st.executeUpdate(sql);
			System.out.println(sql + " delete ok");
			ActDAO dao = new ActDAO();
			dao.deleteAct(id, seller, title, price);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void updateOneItem(String id, String title, String author, String type, String year, String price, 
			String seller, String publisher, String item_url, String ban) throws IOException, SQLException {
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
			String sql = "UPDATE TBL_ITEM SET title = \"" + title + "\", " 
					+ "author = \"" + author + "\", " 
					+ "type = \"" + type + "\", " 
					+ "year = \"" + year + "\", " 
					+ "price = \"" + price + "\", " 
					+ "seller = \"" + seller + "\", " 
					+ "publisher =\"" + publisher + "\", " 
					+ "url = \"" + item_url + "\", " 
					+ "ban =\"" + ban + "\" " 
					+ "WHERE id = '" + id + "';";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " update ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Item getItemByID(String id) throws IOException, SQLException {
		Item item = new Item();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rec = null;
		String sql = "SELECT * FROM TBL_ITEM WHERE id = '" + id + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				item.setId(rec.getString("ID"));
				item.setTitle(rec.getString("Title"));
				item.setAuthor(rec.getString("Author"));
				item.setType(rec.getString("Type"));
				item.setYear(rec.getString("Year"));
				item.setPrice(rec.getString("Price"));
				item.setSeller(rec.getString("Seller"));
				item.setPublisher(rec.getString("Publisher"));
				item.setUrl(rec.getString("Url"));
				item.setBan(rec.getString("Ban"));
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return item;
	}
	
	public ArrayList<Item> getItemsBySeller(String username) throws IOException, SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rec = null;
		String sql = "SELECT * FROM TBL_ITEM WHERE SELLER = '" + username + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				Item item = new Item();
				item.setId(rec.getString("ID"));
				item.setTitle(rec.getString("Title"));
				item.setAuthor(rec.getString("Author"));
				item.setType(rec.getString("Type"));
				item.setYear(rec.getString("Year"));
				item.setPrice(rec.getString("Price"));
				item.setSeller(rec.getString("Seller"));
				item.setPublisher(rec.getString("Publisher"));
				item.setUrl(rec.getString("Url"));
				item.setBan(rec.getString("Ban"));
				list.add(item);
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} 
	
	public ArrayList<Item> getAllItems() throws IOException, SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rec = null;
		try {
			rec = conn.createStatement().executeQuery("SELECT * FROM TBL_ITEM;");
			while(rec.next()) {
				Item item = new Item();
				item.setId(rec.getString("ID"));
				item.setTitle(rec.getString("Title"));
				item.setAuthor(rec.getString("Author"));
				item.setType(rec.getString("Type"));
				item.setYear(rec.getString("Year"));
				item.setPrice(rec.getString("Price"));
				item.setSeller(rec.getString("Seller"));
				item.setPublisher(rec.getString("Publisher"));
				item.setUrl(rec.getString("Url"));
				item.setBan(rec.getString("Ban"));
				list.add(item);
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Item> searchItems(String sql) throws IOException, SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rec = null;
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				Item item = new Item();
				item.setId(rec.getString("ID"));
				item.setTitle(rec.getString("Title"));
				item.setAuthor(rec.getString("Author"));
				item.setType(rec.getString("Type"));
				item.setYear(rec.getString("Year"));
				item.setPrice(rec.getString("Price"));
				item.setSeller(rec.getString("Seller"));
				item.setPublisher(rec.getString("Publisher"));
				item.setUrl(rec.getString("Url"));
				item.setBan(rec.getString("Ban"));
				list.add(item);
			}
			rec.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}