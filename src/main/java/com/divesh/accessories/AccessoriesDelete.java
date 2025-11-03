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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.divesh.entities.Accessories;
import com.divesh.util.JDBCUtil;


public class AccessoriesDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Accessories> L  = null;
		
		String qry = "";
		
		
		String taid = request.getParameter("aid");
		String sbtn = request.getParameter("sbtn");
		
		if(sbtn == null)
		{
			qry = "select * from accessories order by aid";
		}
		else if(sbtn.equals("Refresh"))
		{
			qry = "select * from accessories order by aid";
		}
		else if(sbtn.equals("Search"))
		{
			qry = "select * from accessories where aid = "+taid;
		}
		
		try
		{
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			
			rs = ps.executeQuery();
			
			L = new ArrayList<>();
			
			while(rs.next())
			{
				int aid = rs.getInt("aid");
				String name = rs.getString("name");
				int stock = rs.getInt("stock");
				double price = rs.getDouble("price");
				
				Accessories S = new Accessories(aid,name,stock,price);
				
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
		
	
		request.setAttribute("listOfAccessories", L);
		RequestDispatcher rd =request.getRequestDispatcher("AccessoriesDelete.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Deleting Logic
		
		String aid = request.getParameter("aid");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String qry = "delete from accessories where aid = "+aid;
		
		try
		{
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
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
			
		}
	}

}
