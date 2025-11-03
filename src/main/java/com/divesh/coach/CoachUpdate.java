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


public class CoachUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con = null;
		PreparedStatement ps =null;
		
		
		try
		{
			String id = request.getParameter("cid");
			String name = request.getParameter("cName");
			String address = request.getParameter("cAddress");
			String phoneno = request.getParameter("cPhoneno");
			String salary = request.getParameter("cSalary");
			
			con = JDBCUtil.getConnection();
			
			if(!name.equals("") && !address.equals("") && !phoneno.equals("") && !salary.equals(""))
			{
				//Update all
				
				ps = con.prepareStatement("update coach set name=?,phoneno=?,address=?,salary=? where cid=?");
				ps.setString(1,name);
				ps.setString(2, phoneno);
				ps.setString(3, address);
				ps.setDouble(4, Double.parseDouble(salary));
				ps.setInt(5,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Name,PhoneNo,Address,Salary Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
			}
			else if(!name.equals("") && address.equals("") && phoneno.equals("") && salary.equals(""))
			{
				//Update only name
				ps = con.prepareStatement("update coach set name=? where cid=?");
				ps.setString(1,name);
				ps.setInt(2,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Name Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				
			}
			else if(name.equals("") && !address.equals("") && phoneno.equals("") && salary.equals(""))
			{
				//update only address
				ps = con.prepareStatement("update coach set address=? where cid=?");
				ps.setString(1,address);
				ps.setInt(2,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Address Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
			}
			else if(name.equals("") && address.equals("") && !phoneno.equals("") && salary.equals(""))
			{
				//update only phoneno
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update coach set phoneno=? where cid=?");
					ps.setString(1,phoneno);
					ps.setInt(2,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach Phone-No Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else if(name.equals("") && address.equals("") && phoneno.equals("") && !salary.equals(""))
			{
				//update only Salary
				
				ps = con.prepareStatement("update coach set salary=? where cid=?");
				ps.setDouble(1,Double.parseDouble(salary));
				ps.setInt(2,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Phone-No Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
			
			}
			else if(!name.equals("") && !address.equals("") && phoneno.equals("") && salary.equals(""))
			{
				//update name & address
				ps = con.prepareStatement("update coach set name=?,address=? where cid=?");
				ps.setString(1,name);
				ps.setString(2, address);
				ps.setInt(3,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Name & Address Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
			}
			else if(!name.equals("") && address.equals("") && !phoneno.equals("") && salary.equals(""))
			{
				//update name & phoneno
				
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update coach set name=?,phoneno=? where cid=?");
					ps.setString(1,name);
					ps.setString(2, phoneno);
					ps.setInt(3,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach Name & PhoneNo Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else if(!name.equals("") && address.equals("") && phoneno.equals("") && !salary.equals(""))
			{
				//update name & salary
			
				ps = con.prepareStatement("update coach set name=?,salary=? where cid=?");
				ps.setString(1,name);
				ps.setDouble(2, Double.parseDouble(salary));
				ps.setInt(3,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				
				if(status>0)
				{
					out.println("<script>\r\n"
							+ "    alert(\"Coach Name & PhoneNo Updated\");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
				else
				{
					out.println("<script>\r\n"
							+ "    alert(\"Unable to Update \");\r\n"
							+ "</script>\r\n"
							+ "");
					RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
					rd.include(request, response);
				}
					
				
				
			}
			else if(name.equals("") && !address.equals("") && !phoneno.equals("") && salary.equals(""))
			{
				//update address & phoneno
				
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update coach set phoneno=?,address=? where cid=?");
					ps.setString(1, phoneno);
					ps.setString(2, address);
					ps.setInt(3,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach PhoneNo & Address Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else if(name.equals("") && !address.equals("") && phoneno.equals("") && !salary.equals(""))
			{
					//update address & salary
					
					ps = con.prepareStatement("update coach set salary=?,address=? where cid=?");
					ps.setDouble(1, Double.parseDouble(salary));
					ps.setString(2, address);
					ps.setInt(3,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach PhoneNo & Address Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
						
					
				
			}
			else if(name.equals("") && address.equals("") && !phoneno.equals("") && !salary.equals(""))
			{
				//update salary & phoneno
				
				if(phoneno.length()==10)
				{
				
					ps = con.prepareStatement("update coach set phoneno=?,salary=? where cid=?");
					ps.setString(1, phoneno);
					ps.setDouble(2, Double.parseDouble(salary));
					ps.setInt(3,Integer.parseInt(id));
					
					int status = ps.executeUpdate();
					
					if(status>0)
					{
						out.println("<script>\r\n"
								+ "    alert(\"Coach PhoneNo & Address Updated\");\r\n"
								+ "</script>\r\n"
								+ "");
						RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
						rd.include(request, response);
					}
					else
					{
						out.println("<script>\r\n"
								+ "    alert(\"Unable to Update \");\r\n"
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
			else
			{
				out.println("<script>\r\n"
						+ "    alert(\"Nothing to Update \");\r\n"
						+ "</script>\r\n"
						+ "");
				RequestDispatcher rd = request.getRequestDispatcher("./CoachPanel");
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
