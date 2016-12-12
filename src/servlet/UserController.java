package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.utils.SystemProperty;

import dao.UserDAO;
import model.*;

import com.google.gson.*;

@SuppressWarnings("serial")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {		
		try {
			dispatchRequest(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		try {
			dispatchRequest(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dispatchRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		String forwardPage = "";
		String action = request.getParameter("action");
		if ((action == null)) {
			forwardPage = "404NotFound.jsp";
		} else if ("getAllUsers".equals(action)) {
			UserLogic.getAllUsers(request, response);
		} else if ("addOneUser".equals(action)) {
			UserLogic.addOneUser(request, response);
		} else if ("addOneSeller".equals(action)) {
			UserLogic.addOneSeller(request, response); 
		} else if ("userlogin".equals(action)) {
			UserLogic.findOneUser(request, response);
		} else if ("sellerlogin".equals(action)) {
			UserLogic.findOneSeller(request, response);
		} else if ("adminlogin".equals(action)) {
			UserLogic.findOneAdmin(request, response);
		} else if ("updateOneUser".equals(action)) {
			UserLogic.updateOneUser(request, response);
		} else if ("updateOneSeller".equals(action)) {
			UserLogic.updateOneSeller(request, response);
		} else if ("logout".equals(action)) {
			UserLogic.logout(request, response);
		} else {
			forwardPage = "404NotFound.jsp";		
		}
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + forwardPage);
//		dispatcher.forward(request, response);
	}

}
