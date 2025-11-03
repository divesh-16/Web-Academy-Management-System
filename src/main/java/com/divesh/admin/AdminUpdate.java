package com.divesh.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.divesh.entities.Admin;
import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Admin> L  = null;
		
		String qry = "";
		
		
		String tid = request.getParameter("id");
		String sbtn = request.getParameter("sbtn");
		
		if(sbtn == null)
		{
			qry = "select * from admin order by id";
		}
		else if(sbtn.equals("Refresh"))
		{
			qry = "select * from admin order by id";
		}
		else if(sbtn.equals("Search"))
		{
			qry = "select * from admin where id = "+tid;
		}
		
		try
		{
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			
			rs = ps.executeQuery();
			
			L = new ArrayList<>();
			
			while(rs.next())
			{
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String phoneno = rs.getString("phoneno");
				String address = rs.getString("address");
				double salary = rs.getDouble("salary");
				String password = rs.getString("password");
				
				Admin S = new Admin(id, name, phoneno, address, salary, password);
				
				L.add(S);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			L = null;
		}
		finally
		{
			try
			{
				rs.close();
				//con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				L = null;
			}
		}
		
	
		request.setAttribute("listOfAdmin", L);
		RequestDispatcher rd =request.getRequestDispatcher("AdminUpdate.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Update Logic
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phoneno = request.getParameter("phoneno");
		String address = request.getParameter("address");
		String salary = request.getParameter("salary");
		String password = request.getParameter("password");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String qry = "update admin set name=?,phoneno=?,address=?,salary=?,password=? where id="+id;
		
		try
		{
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			ps.setString(1,name);
			ps.setString(2, phoneno);
			ps.setString(3, address);
			ps.setDouble(4,Double.parseDouble(salary));
			ps.setString(5, password);
			ps.executeUpdate();
			out.println("success");
		}
		catch(Exception e)
		{
			out.println("failure");
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				//con.close();
			} catch (Exception e2) 
			{
				out.println("failure");
				e2.printStackTrace();
			}
		}
	}

}
