package com.hhs.xgn.taskboard.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhs.xgn.taskboard.type.Status;
import com.hhs.xgn.taskboard.type.User;
import com.hhs.xgn.taskboard.util.Getter;
import com.hhs.xgn.taskboard.util.Setter;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/StatusServlet")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet() {
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
			String oj=request.getParameter("oj");
			String title=request.getParameter("title");
			String note=request.getParameter("note");
			String id=request.getParameter("id");
			String ac=request.getParameter("ac");
			String link=request.getParameter("link");
			String tag=request.getParameter("tag");
			
			if(!ac.matches("true|false")){
				throw new Exception("ac not match true false");
			}
			
			if(!id.startsWith(oj)){
				throw new Exception("id not start with oj");
			}
			
			Status s=new Status();
			s.ac=ac.equals("true");
			s.lastUpdateTime=System.currentTimeMillis();
			s.link=link;
			s.note=note;
			s.tag=tag;
			s.oj=oj;
			s.title=title;
			
			ArrayList<User> uar=Getter.getAllUsers();
			
			for(User u:uar){
				if(u.username.equals(user) && u.password.equals(pass)){
					//find!
					u.status.put(id, s);
					
					Setter.updateUser(user, u);
					response.getWriter().append("Success!");
					return;
				}
			}
			
			response.getWriter().append("Wrong username or password");
		}catch(Exception e){
			response.getWriter().append("AAAAAA!!!!!"+e+" occurred!! Failed!!!!!");
		}
	}

}
