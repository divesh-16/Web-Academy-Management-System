package com.divesh.coach;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.divesh.util.JDBCUtil;


public class CoachDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement ps = null;
		
		
		try
		{
			String id = request.getParameter("id");
			if(id.equals(""))
			{
				out.println("<script>\r\n"
						+ "    alert(\"Coach Data not Deleted\");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
				rd.include(request, response);
			}
			else
			{
				con = JDBCUtil.getConnection();
				ps = con.prepareStatement("delete from coach where cid=?");
				ps.setInt(1,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Data Deleted Successfully\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Data not Deleted\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
