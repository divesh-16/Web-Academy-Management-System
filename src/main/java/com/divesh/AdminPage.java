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


public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try 
		{
			con = JDBCUtil.getConnection();
			
			String id = request.getParameter("user");
			String password = request.getParameter("password");
			
			if(id==null || password==null)
			{
				out.println("<script>\r\n"
						+ "    alert(\"Enter ID and Password!!\");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./AdminLogin.html");
				rd.include(request, response);
			}
			
			ps = con.prepareStatement("select * from admin where id=? and password=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				HttpSession session = request.getSession();
				session.setAttribute("id",id);
				
				RequestDispatcher rd = request.getRequestDispatcher("./AdminWindow.jsp");
				request.setAttribute("user",id);
				request.setAttribute("password",password);
				rd.forward(request, response);
			}
			else
			{
//				out.println("<script>\r\n"
//						+ "    alert(\"Incorrect Login ID or Password !!\");\r\n"
//						+ "</script>\r\n"
//						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./AdminLogin.html");
				rd.include(request, response);
			}
					
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
/*			try
			{
				rs.close();
				ps.close();
				con.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			*/
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
