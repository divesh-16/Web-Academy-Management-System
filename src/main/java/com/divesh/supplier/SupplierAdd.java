package com.divesh.supplier;

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
import java.sql.SQLException;

import com.divesh.util.JDBCUtil;


public class SupplierAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("SupplierAdd.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		
		int aid = Integer.parseInt(request.getParameter("aid"));
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		String message = "";
		try
		{
			
			con = JDBCUtil.getConnection();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("insert into supplier values(default,?,?,?)");
			ps.setInt(1, aid);
			ps.setString(2, name);
			ps.setString(3, address);
			
			int status = ps.executeUpdate();
			con.commit();
			message = ("<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\"> Record inserted Successfully "+aid+ "</div>");
			
		}
		catch(Exception e)
		{
			try 
			{
				con.rollback();
			} catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			//e.printStackTrace();
			message = ("<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\"> Record not inserted Successfully : </div>");
		}
		finally
		{
		
		}
		
		request.setAttribute("msg", message);
		RequestDispatcher rd =request.getRequestDispatcher("SupplierAdd.jsp");
		rd.forward(request, response);
	}

}
