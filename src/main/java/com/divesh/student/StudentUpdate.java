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


public class StudentUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con = null;
		PreparedStatement ps =null;
		
		
		try
		{
			String id = request.getParameter("sid");
			String name = request.getParameter("sName");
			String address = request.getParameter("sAddress");
			String phoneno = request.getParameter("sPhoneno");
			
			con = JDBCUtil.getConnection();
			
			if(!name.equals("") && !address.equals("") && !phoneno.equals(""))
			{
				//Update all
				
				ps = con.prepareStatement("update student set name=?,phoneno=?,address=? where sid=?");
				ps.setString(1,name);
				ps.setString(2, phoneno);
				ps.setString(3, address);
				ps.setInt(4,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Student Name,PhoneNo,Address Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
			}
			else if(!name.equals("") && address.equals("") && phoneno.equals(""))
			{
				//Update only name
				ps = con.prepareStatement("update student set name=? where sid=?");
				ps.setString(1,name);
				ps.setInt(2,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Student Name Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
				
			}
			else if(name.equals("") && !address.equals("") && phoneno.equals(""))
			{
				//update only address
				ps = con.prepareStatement("update student set address=? where sid=?");
				ps.setString(1,address);
				ps.setInt(2,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Student Address Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
			}
			else if(name.equals("") && address.equals("") && !phoneno.equals(""))
			{
				//update only phoneno
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update student set phoneno=? where sid=?");
					ps.setString(1,phoneno);
					ps.setInt(2,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Student Phone-No Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else if(!name.equals("") && !address.equals("") && phoneno.equals(""))
			{
				//update name & address
				ps = con.prepareStatement("update student set name=?,address=? where sid=?");
				ps.setString(1,name);
				ps.setString(2, address);
				ps.setInt(3,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Student Name & Address Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
					rd.include(request, response);
				}
			}
			else if(!name.equals("") && address.equals("") && !phoneno.equals(""))
			{
				//update name & phoneno
				
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update student set name=?,phoneno=? where sid=?");
					ps.setString(1,name);
					ps.setString(2, phoneno);
					ps.setInt(3,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Student Name & PhoneNo Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else if(name.equals("") && !address.equals("") && !phoneno.equals(""))
			{
				//update address & phoneno
				
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update student set phoneno=?,address=? where sid=?");
					ps.setString(1, phoneno);
					ps.setString(2, address);
					ps.setInt(3,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Student PhoneNo & Address Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else
			{
				out.println("<script>\r\n"
						+ "    alert(\"Nothing to Update \");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./StudentPanel");
				rd.include(request, response);
			}
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
