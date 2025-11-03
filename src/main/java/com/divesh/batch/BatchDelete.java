package com.divesh.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.divesh.entities.Batch;
import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BatchDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Batch> L = null;
		
		String qry = "";
		
		String id = request.getParameter("id");
		String sbtn = request.getParameter("sbtn");
		
		if(sbtn == null)
		{
			qry = "select * from batch order by bid";
		}
		else if(sbtn.equals("Refresh"))
		{
			qry = "select * from batch order by bid";
		}
		else if(sbtn.equals("Search"))
		{
			qry = "select * from batch where bid="+id;
		}
		
		
		
		
		try
		{
			
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			
			rs = ps.executeQuery();
			
			L = new ArrayList<>();
			
			while(rs.next())
			{
				int bid = rs.getInt("bid");
				int sid = rs.getInt("sid");
				int cid = rs.getInt("cid");
				int gid = rs.getInt("gid");
				
				Batch B = new Batch(bid,sid,cid,gid);
				
				L.add(B);
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
				//rs.close();
				//con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				L = null;
			}
		}
		
	
		request.setAttribute("listOfBatch", L);
		RequestDispatcher rd =request.getRequestDispatcher("BatchDelete.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Deleting Logic
		
		String id = request.getParameter("id");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String qry = "delete from batch where bid = "+id;
		
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
