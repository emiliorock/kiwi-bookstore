package dao;

import model.Seller;

import java.io.*;
import java.util.*;
import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;

public class SellerDAO {
	
	public Seller findOneSeller(String username, String password) {
		Seller seller = new Seller();
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
		String sql = "SELECT * FROM TBL_SELLER WHERE username = '" + username + "' and password = '" + password + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				seller.setID(rec.getString("id"));
				seller.setUsername(rec.getString("username"));
				seller.setPassword(rec.getString("password"));
				seller.setEmail(rec.getString("email"));
				seller.setAddress(rec.getString("address"));
				seller.setBan(rec.getString("ban"));
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seller;
	}
	
	public int addOneSeller(String username, String password, String email, String address) throws IOException, SQLException {
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
			String sql = "SELECT MAX(id) FROM TBL_SELLER";
			st.executeQuery(sql);
			ResultSet res = null;
			int id = 0;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				id = res.getInt(1) + 1;
			}
			sql = "SELECT * FROM TBL_SELLER WHERE username = '" + username + "';";
			st.executeQuery(sql);
			res = st.getResultSet();
			if(res.next()) {
				System.out.println("seller username exists");
				return 1;
			}
			sql = "SELECT * FROM TBL_SELLER WHERE email = '" + email + "';";
			st.executeQuery(sql);
			res = st.getResultSet();
			if(res.next()) {
				System.out.println("seller email exists");
				return 2;
			}
			sql = "INSERT INTO TBL_SELLER VALUES('" + id + "', '" + username + "', '" + password + "', '" + email + "', '" + address + "');";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " insert ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deleteOneSeller(String id) throws IOException, SQLException {
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
			String sql = "DELETE FROM USER_SELLER WHERE id = " + id + ";";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " delete ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void BanOneSeller(String id) throws IOException, SQLException {
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
			String sql = "UPDATE TBL_SELLER SET ban = '1' WHERE id = '" + id + "'";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " ban ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Seller updateOneSeller(String id, String username, String password, String email, String address) throws IOException, SQLException {
		Seller seller = new Seller();
		seller.setID(id);
		seller.setUsername(username);
		seller.setPassword(password);
		seller.setEmail(email);
		seller.setAddress(address);
		seller.setBan("0");
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
			String sql = "UPDATE TBL_SELLER SET USERNAME = '" + username + "', PASSWORD = '" + password 
					+ "', EMAIL = '" + email + "' , ADDRESS = '" + address + "';";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " update ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seller;
	}
	
	public ArrayList<Seller> getAllSellers() throws IOException, SQLException {
		ArrayList<Seller> list = new ArrayList<Seller>();
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
			rec = conn.createStatement().executeQuery("SELECT * FROM TBL_SELLER;");
			while(rec.next()) {
				Seller seller = new Seller();
				seller.setID(rec.getString("id"));
				seller.setUsername(rec.getString("username"));
				seller.setPassword(rec.getString("password"));
				seller.setEmail(rec.getString("email"));
				seller.setAddress(rec.getString("address"));
				seller.setBan(rec.getString("ban"));
				list.add(seller);
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