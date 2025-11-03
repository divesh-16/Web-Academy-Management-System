package com.divesh.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.divesh.entities.Accessories;
import com.divesh.entities.Order;
import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class OrderUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Order> L  = null;
		
		String qry = "";
		
		
		String toid = request.getParameter("oid");
		String sbtn = request.getParameter("sbtn");
		
		if(sbtn == null)
		{
			qry = "select * from oorder order by oid";
		}
		else if(sbtn.equals("Refresh"))
		{
			qry = "select * from oorder order by oid";
		}
		else if(sbtn.equals("Search"))
		{
			qry = "select * from oorder where oid = "+toid;
		}
		
		try
		{
			
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement(qry);
			
			rs = ps.executeQuery();
			
			L = new ArrayList<>();
			
			while(rs.next())
			{
				int oid = rs.getInt("oid");
				int sid = rs.getInt("sid");
				String product = rs.getString("product_name");
				int quantity = rs.getInt("quantity");
				double amount = rs.getDouble("amount");
				String payStatus = rs.getString("pay_status");
				String payOption = rs.getString("pay_option");
				String deliveryStatus = rs.getString("delivery_status");
				
				Order S = new Order(oid, sid, product, quantity, amount, payOption, payStatus, deliveryStatus);
				
				
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
		
	
		request.setAttribute("listOfOrder", L);
		RequestDispatcher rd =request.getRequestDispatcher("OrderUpdate.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Update Logic
		
		
		
		String oid = request.getParameter("oid");
		String sid = request.getParameter("sid");
		String product = request.getParameter("product");
		String quantity = request.getParameter("quantity");
		String amount = request.getParameter("amount");
		String payStatus = request.getParameter("payStatus");
		String payOption = request.getParameter("payOption");
		String deliveryStatus = request.getParameter("deliveryStatus");
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String qry = "UPDATE oorder SET sid="+sid+", product_name='"+product+"', quantity ="+quantity+", amount="+amount+", pay_option='"+payOption+"', pay_status='"+payStatus+"', delivery_status='"+deliveryStatus+"' where oid = "+oid;
		
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
