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

/**
 * Servlet implementation class CoachAdd
 */
public class CoachAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement ps = null;
		
		
		try
		{
			String name = request.getParameter("cName");
			String phone = request.getParameter("cPhone");
			String address = request.getParameter("cAddress");
			String salary = request.getParameter("cSalary");
			
			if(name.equals("") || phone.equals("") || address.equals("") || salary.equals(""))
			{
				out.println("<script>\r\n"
						+ "    alert(\"Coaches's Data is not Added\");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
				rd.include(request, response);
			}
			else
			{
				if(phone.length()==10)
				{
					con = JDBCUtil.getConnection();
					
					ps = con.prepareStatement("insert into coach(name,phoneno,salary,address) values(?,?,?,?)");
					ps.setString(1, name);
					ps.setString(2, phone);
					ps.setDouble(3, Double.parseDouble(salary));
					ps.setString(4, address);
					
					
					int status = ps.executeUpdate();
					if(status<0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach's Data is not Added\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach's Data is Added Successfully\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Phone no should be 10 digits \");\r\n"
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
