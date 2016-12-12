package dao;

import model.Admin;

import java.io.*;
import java.util.*;
import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;

public class AdminDAO {
	
	public Admin findOneAdmin(String username, String password) {
		Admin admin = new Admin();
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
		String sql = "SELECT * FROM TBL_ADMIN WHERE username = '" + username + "' and password = '" + password + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				admin.setID(rec.getString("id"));
				admin.setUsername(rec.getString("username"));
				admin.setPassword(rec.getString("password"));
				admin.setEmail(rec.getString("email"));
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	
	public void addOneAdmin(String username, String password, String email) throws IOException, SQLException {
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
			String sql = "SELECT MAX(id) FROM TBL_ADMIN";
			st.executeQuery(sql);
			ResultSet res = null;
			int id = 0;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				id = res.getInt(1) + 1;
			}
			sql = "INSERT INTO TBL_ADMIN VALUES('" + id + "', '" + username + "', '" + password + "', '" + email + "');";
			st.executeUpdate(sql);
			res.close();
			conn.close();
			System.out.println(sql + " insert ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOneAdmin(String id) throws IOException, SQLException {
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
			String sql = "DELETE FROM TBL_ADMIN WHERE id = " + id + ";";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " delete ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Admin> getAllAdmins() throws IOException, SQLException {
		ArrayList<Admin> list = new ArrayList<Admin>();
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
			rec = conn.createStatement().executeQuery("SELECT * FROM TBL_ADMIN;");
			while(rec.next()) {
				Admin admin = new Admin();
				admin.setID(rec.getString("id"));
				admin.setUsername(rec.getString("username"));
				admin.setPassword(rec.getString("password"));
				admin.setEmail(rec.getString("email"));
				list.add(admin);
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