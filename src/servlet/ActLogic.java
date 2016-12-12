package servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import model.Act;
import dao.ActDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

public class ActLogic {
	
	static public void getActsByUser(HttpServletRequest request,
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
		ActDAO dao = new ActDAO();
		ArrayList<Act> list = new ArrayList<Act>(); 
		list = dao.getActsBySeller(username);
		String json = new Gson().toJson(list);
		out.write(json);
		out.close();
	}
	
}