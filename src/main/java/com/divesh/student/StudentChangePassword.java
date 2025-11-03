package com.divesh.student;

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


public class StudentChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		
		RequestDispatcher rd = request.getRequestDispatcher("./StudentChangePassword.jsp");
		rd.include(request, response);
		
		String id = request.getParameter("sid");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		out.println("");
		
		if(!newPassword.equals(confirmPassword))
		{
			out.println("<script> alert('New passwords do not match!'); </script>");
		}
		
		try
		{
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement("select password from student where sid=?");
			ps.setInt(1,Integer.parseInt(id));
			rs = ps.executeQuery();
			rs.next();
			String password = rs.getString("password");
			
			if(!currentPassword.equals(password))
			{
				out.println("<script> alert('Current passwords do not match!'); </script>");
			}
			else
			{
				ps = con.prepareStatement("update student set password=? where sid=?");
				ps.setString(1,newPassword);
				ps.setInt(2,Integer.parseInt(id));
				
				int status = ps.executeUpdate();
				if(status>0)
				{
					out.println("<script> alert('Password Changed Successfully !'); </script>");
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
