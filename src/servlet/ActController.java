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

import dao.ActDAO;
import model.*;

import com.google.gson.*;

@SuppressWarnings("serial")
public class ActController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ActController() {
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
		} else if ("getActsBySeller".equals(action)) {
			ActLogic.getActsByUser(request, response);
		} else {
			forwardPage = "404NotFound.jsp";		
		}
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + forwardPage);
//		dispatcher.forward(request, response);
	}

}
