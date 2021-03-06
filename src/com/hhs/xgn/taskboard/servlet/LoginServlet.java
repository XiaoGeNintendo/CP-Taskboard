package com.hhs.xgn.taskboard.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhs.xgn.taskboard.type.User;
import com.hhs.xgn.taskboard.util.Getter;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginS")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("username");
		String pass=request.getParameter("password");
		if(user==null || pass==null){
			response.getWriter().append("Where're your username and password?");
			return;
		}
		
		ArrayList<User> usr=Getter.getAllUsers();
		
		for(User x:usr){
			if(x.username.equals(user) && x.password.equals(pass)){
				request.getSession().setAttribute("username", user);
				response.getWriter().append("OK");
				return;
			}
		}
		
		response.getWriter().append("Bad username or password");
	}

}
