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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.divesh.entities.Supplier;
import com.divesh.util.JDBCUtil;


public class SupplierUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Supplier> L  = null;
		
		String qry = "";
		
		
		String spid = request.getParameter("spid");
		String sbtn = request.getParameter("sbtn");
		
		if(sbtn == null)
		{
			qry = "select * from supplier order by spid";
		}
		else if(sbtn.equals("Refresh"))
		{
			qry = "select * from supplier order by spid";
		}
		else if(sbtn.equals("Search"))
		{
			qry = "select * from supplier where spid = "+spid;
		}
		
		try
		{
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			
			rs = ps.executeQuery();
			
			L = new ArrayList<>();
			
			while(rs.next())
			{
				int tspid = rs.getInt("spid");
				int aid = rs.getInt("aid");
				String name = rs.getString("name");
				String address = rs.getString("address");
				
				Supplier S = new Supplier(tspid, aid, name, address);
				
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
		
	
		request.setAttribute("listOfSupplier", L);
		RequestDispatcher rd =request.getRequestDispatcher("SupplierUpdate.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Update Logic
		
		
		String spid = request.getParameter("spid");
		String aid = request.getParameter("aid");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String qry = "UPDATE supplier SET aid='"+aid+"', name='"+name+"', address ='"+address+"' where spid = "+spid;
		
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
