package com.hhs.xgn.taskboard.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hhs.xgn.taskboard.type.Status;
import com.hhs.xgn.taskboard.type.User;
import com.hhs.xgn.taskboard.util.Getter;
import com.hhs.xgn.taskboard.util.Setter;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Hi :)");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String user=request.getParameter("username");
			String pass=request.getParameter("password");
			String id=request.getParameter("id");
			
			ArrayList<User> uar=Getter.getAllUsers();
			
			for(User u:uar){
				if(u.username.equals(user) && u.password.equals(pass)){
					if(u.status.get(id)==null){
						response.getWriter().append("null");
					}else{
						response.getWriter().append(new Gson().toJson(u.status.get(id)));
					}
					
					return;
				}
			}
			
			response.getWriter().append("Wrong username or password");
		}catch(Exception e){
			response.getWriter().append("AAAAAA!!!!!"+e+" occurred!! Failed!!!!!");
		}
	}

}
