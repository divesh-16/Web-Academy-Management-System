package com.divesh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CoachPage
 */
public class CoachPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			String id = request.getParameter("user");
			String password = request.getParameter("password");
			
			if(id==null || password==null)
			{
				out.println("<script>\r\n"
						+ "    alert(\"Incorrect Login ID or Password !!\");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./CoachLogin.html");
				rd.include(request, response);
			}
			
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("select * from coach where cid=? and password=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("cid",id);
				
				RequestDispatcher rd = request.getRequestDispatcher("./CoachController");
				request.setAttribute("user",id);
				request.setAttribute("password",password);
				rd.forward(request, response);
			}
			else
			{
				out.println("<script>\r\n"
						+ "    alert(\"Incorrect Login ID or Password !!\");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./CoachLogin.html");
				rd.include(request, response);
			}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				//con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
