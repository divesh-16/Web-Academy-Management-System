package com.divesh.accessories;

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


public class AccessoriesAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("AccessoriesAdd.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		
		String name = request.getParameter("name");
		int stock = Integer.parseInt(request.getParameter("stock"));
		double price = Double.parseDouble(request.getParameter("price"));
		
		String message = "";
		try
		{
			
			con = JDBCUtil.getConnection();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("insert into accessories values(default,?,?,?)");
			ps.setString(1, name);
			ps.setInt(2, stock);
			ps.setDouble(3, price);
			
			int status = ps.executeUpdate();
			con.commit();
			message = ("<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\"> Record inserted Successfully "+status+ "</div>");
			
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
		RequestDispatcher rd =request.getRequestDispatcher("AccessoriesAdd.jsp");
		rd.forward(request, response);
	}

}
