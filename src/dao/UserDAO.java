package dao;

import model.User;

import java.io.*;
import java.util.*;
import java.sql.*;

import com.google.appengine.api.utils.SystemProperty;

public class UserDAO {
	
	public int addOneUser(String username, String password, String email, String firstname, 
			String lastname, String address, String birthday) throws IOException, SQLException {
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
			String sql = "SELECT MAX(id) FROM TBL_USER";
			ResultSet res = null;
			int id = 0;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				id = res.getInt(1) + 1;
			}
			sql = "SELECT * FROM TBL_USER WHERE username = '" + username + "';";
			st.executeQuery(sql);
			res = st.getResultSet();
			if(res.next()) {
				return 1;
			}
			sql = "SELECT * FROM TBL_USER WHERE email = '" + email + "';";
			st.executeQuery(sql);
			res = st.getResultSet();
			if(res.next()) {
				return 2;
			}
			sql = "INSERT INTO TBL_USER VALUES (" + id + ", '" + username + "', '" 
						+ password + "', '" + firstname + "', '" + lastname + "', '" + email + "', '" 
						+ birthday + "', '" + address + "', '0');";
			st.executeUpdate(sql);
			res.close();
			conn.close();
			System.out.println(sql + " insert ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void deleteOneUser(String id) throws IOException, SQLException {
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
			String sql = "DELETE FROM TBL_USER WHERE id = " + id + ";";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " delete ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void BanOneUSer(String id) throws IOException, SQLException {
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
			String sql = "UPDATE TBL_USER SET ban = '1' WHERE id = '" + id + "'";
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " ban ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User updateOneUser(String id, String username, String password, String email, String firstname, 
			String lastname, String address, String birthday) throws IOException, SQLException {
		User user = new User();
		user.setID(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setAddress(address);
		user.setBirthday(birthday);
		user.setBan("0");
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
			String sql = "UPDATE TBL_USER SET username = '" + username + "', password = '" + password
						+ "', firstname = '" + firstname + "', lastname = '" + lastname + "', email = '" + email 
						+ "', birthday = '" + birthday + "', address = '" + address +  "' WHERE id = '" + id + "';";
			System.out.println(sql);
			st.executeUpdate(sql);
			conn.close();
			System.out.println(sql + " update ok");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User findOneUser(String username, String password) {
		User user = new User();
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
		String sql = "SELECT * FROM TBL_USER WHERE username = '" + username + "' and password = '" + password + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				user.setID(rec.getString("id"));
				user.setUsername(rec.getString("username"));
				user.setPassword(rec.getString("password"));
				user.setFirstname(rec.getString("firstname"));
				user.setLastname(rec.getString("lastname"));
				user.setEmail(rec.getString("email"));
				user.setBirthday(rec.getString("birthday"));
				user.setAddress(rec.getString("address"));
				user.setBan(rec.getString("ban"));
			}
			rec.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<User> getAllUsers() throws IOException, SQLException {
		ArrayList<User> list = new ArrayList<User>();
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
			rec = conn.createStatement().executeQuery("SELECT * FROM TBL_User;");
			while(rec.next()) {
				User user = new User();
				user.setID(rec.getString("id"));
				user.setUsername(rec.getString("username"));
				user.setPassword(rec.getString("password"));
				user.setFirstname(rec.getString("firstname"));
				user.setLastname(rec.getString("lastname"));
				user.setEmail(rec.getString("email"));
				user.setBirthday(rec.getString("birthday"));
				user.setAddress(rec.getString("address"));
				user.setBan(rec.getString("ban"));
				list.add(user);
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