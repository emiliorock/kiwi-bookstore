package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.utils.SystemProperty;

import model.Act;

public class ActDAO {
	
	public boolean addAct(int id, String seller, String title, String price) {
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
			ResultSet res = null;
			String sql = "SELECT MAX(id) FROM TBL_ACT;";
			int act_id = 0;
			res = null;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				act_id = res.getInt(1) + 1;
			}
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateformat.format(new Date()); 
			Timestamp timestamp = Timestamp.valueOf(time); 
			sql = "INSERT INTO TBL_ACT VALUES (" + act_id + ", \"" + seller + "\", " + id + ", \"" + title + "\", \"" 
					+ price + "\", \"add\", \"" + timestamp + "\");";
			st.executeUpdate(sql);
			res.close();
			conn.close();
			System.out.println(sql + " insert ok");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteAct(String id, String seller, String title, String price) {
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
			ResultSet res = null;
			String sql = "SELECT MAX(id) FROM TBL_ACT;";
			int act_id = 0;
			res = null;
			st.executeQuery(sql);
			res = st.getResultSet();
			if (res.next()) {
				act_id = res.getInt(1) + 1;
			}
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = dateformat.format(new Date()); 
			Timestamp timestamp = Timestamp.valueOf(time); 
			sql = "INSERT INTO TBL_ACT VALUES (" + act_id + ", \"" + seller + "\", " + id + ", \"" + title + "\", \"" 
					+ price + "\", \"delete\", \"" + timestamp + "\");";
			st.executeUpdate(sql);
			res.close();
			conn.close();
			System.out.println(sql + " insert ok");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public ArrayList<Act> getActsBySeller(String seller) {
		ArrayList<Act> list = new ArrayList<Act>();
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
		String sql = "SELECT * FROM TBL_ACT WHERE Seller = '" + seller + "';";
		try {
			rec = conn.createStatement().executeQuery(sql);
			while(rec.next()) {
				Act act = new Act();
				act.setId(rec.getString("ID"));
				act.setSeller(rec.getString("Seller"));
				act.setItemID(rec.getString("itemID"));
				act.setItemName(rec.getString("itemName"));
				act.setPrice(rec.getString("Price"));
				act.setAct(rec.getString("Act"));
				act.setTime(rec.getString("Time"));
				list.add(act);
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