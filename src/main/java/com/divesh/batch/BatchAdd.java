package com.divesh.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class BatchAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher("BatchAdd.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		
		Connection con = null;
		PreparedStatement ps = null;
		
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		int cid = Integer.parseInt(request.getParameter("tno"));
		int gid = Integer.parseInt(request.getParameter("gid"));
		String message = "";
		try
		{
			
			con = JDBCUtil.getConnection();
			
			con.setAutoCommit(false);
			
			ps = con.prepareStatement("insert into batch values(?,?,?)");
			ps.setInt(1, rno);
			ps.setInt(2, cid);
			ps.setInt(3, gid);
			
			int status = ps.executeUpdate();
			con.commit();
			message = ("<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\"> Record inserted Successfully "+rno+ "</div>");
			
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
			message = ("<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\"> Record not inserted Successfully : "+rno+"</div>");
		}
		finally
		{
		
		}
		
		request.setAttribute("msg", message);
		RequestDispatcher rd =request.getRequestDispatcher("BatchAdd.jsp");
		rd.forward(request, response);
	}

}
