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


public class BatchDisplay extends HttpServlet {
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
		
		
		String sid = request.getParameter("sid");
		String cid = request.getParameter("cid");
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
			
			if(sid.length()>0 && cid.length()>0)
			{
				qry = "select * from batch where sid ="+sid+" and cid="+cid;
			}
			else if(sid.length()>0 && cid.equals(""))
			{
				qry = "select * from batch where sid="+sid;
			}
			else if(sid.length()>0 && cid.equals(""))
			{
				qry = "select * from batch where cid="+cid;
			}
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
				int tsid = rs.getInt("sid");
				int tcid = rs.getInt("cid");
				int gid = rs.getInt("gid");
				
				Batch B = new Batch(bid,tsid,tcid,gid);
				
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
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				L = null;
			}
		}
		
	
		request.setAttribute("listOfBatch", L);
		RequestDispatcher rd =request.getRequestDispatcher("BatchDisplay.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
