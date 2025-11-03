package com.divesh.batch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class BatchUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		List<Batch> L  = null;
		
		String qry = "";
		
		
		String bid = request.getParameter("bid");
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
			qry = "select * from batch where bid= " +bid;
		}
		
		try
		{

			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			
			rs = ps.executeQuery();
			
			L = new ArrayList<>();
			
			while(rs.next())
			{
				int id = rs.getInt("bid");
				int rno = rs.getInt("sid");
				int tno = rs.getInt("cid");
				int gid = rs.getInt("gid");
				
				Batch S = new Batch(id, rno, tno, gid);
				
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
		
	
		request.setAttribute("listOfBatch", L);
		RequestDispatcher rd =request.getRequestDispatcher("BatchUpdate.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Update Logic
		
		String bid = request.getParameter("bid");
		String sid = request.getParameter("sid");
		String cid = request.getParameter("cid");
		String gid = request.getParameter("gid");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String qry = "UPDATE batch SET sid="+sid+", cid="+cid+", gid ="+gid+" where bid = "+bid;
		
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
