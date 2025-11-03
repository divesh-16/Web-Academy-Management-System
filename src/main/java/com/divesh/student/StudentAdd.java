package com.divesh.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class StudentAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement ps = null;
		
		
		try
		{
			String name = request.getParameter("sName");
			String phone = request.getParameter("sPhone");
			String address = request.getParameter("sAddress");
			
			if(name.equals("") || phone.equals("") || address.equals(""))
			{
				out.println("<script>\r\n"
						+ "    alert(\"Student's Data is not Added\");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
				rd.include(request, response);
			}
			else
			{
				if(phone.length()==10)
				{
					con = JDBCUtil.getConnection();
					
					ps = con.prepareStatement("insert into student(name,phoneno,address) values(?,?,?)");
					ps.setString(1, name);
					ps.setString(2, phone);
					ps.setString(3, address);
					
					int status = ps.executeUpdate();
					if(status<0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Student's Data is not Added\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Student's Data is Added Successfully\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
						rd.include(request, response);
					}
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Phone no should be 10 digits \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
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
