package com.divesh.orders;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.divesh.util.JDBCUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("OrderRequest.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String aid = request.getParameter("selectedAccessory");
		String quantity = request.getParameter("quantity");
		String sid = request.getParameter("sid");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try
		{
			con = JDBCUtil.getConnection();
			
			ps = con.prepareStatement("select * from student where sid="+sid);
			
			rs = ps.executeQuery();
			
			String message = "";
			if(rs.next())
			{
				out.println("<script>\r\n"
						+ "    alert(\"Found\");\r\n"
						+ "  </script>");
				
				ps = con.prepareStatement("select * from accessories where aid="+aid);
				
				rs = ps.executeQuery();
				rs.next();
				int stock = rs.getInt("stock");
				String aname = rs.getString("name");
				double price = rs.getDouble("price");
				
				if(stock>=Integer.parseInt(quantity)); // stock available
				{
					ps = con.prepareStatement("update accessories set stock=stock-"+quantity+" where aid ="+aid);
					int status = ps.executeUpdate();
					
					if(status > 0) // update successfully
					{
						// create order
						
						ps = con.prepareStatement("insert into oorder values(default,?,?,?,?,default,default,default) returning oid",Statement.RETURN_GENERATED_KEYS);
						ps.setInt(1,Integer.parseInt(sid));
						ps.setString(2,aname);
						ps.setInt(3, Integer.parseInt(quantity));
						ps.setDouble(4,Integer.parseInt(quantity)*price);
						
						int inStatus = ps.executeUpdate();
						if(inStatus>0)
						{
							rs = ps.getGeneratedKeys();
							if(rs.next())
							{
								int oid=rs.getInt("oid");
								
								ps = con.prepareStatement("select student.name,student.phoneno,student.address,product_name,quantity,amount,pay_option from oorder,student where student.sid = oorder.sid and oid ="+oid);
								rs = ps.executeQuery();
								rs.next();
								String name = rs.getString("name");
								String phone = rs.getString("phoneno");
								String address = rs.getString("address");
								String p = rs.getString("product_name");
								String q = rs.getString("quantity");
								String a = rs.getString("amount");
								String pay = rs.getString("pay_option");
								
								message = "<div class=\"badge text-bg-primary text-wrap text-center position-absolute top-50 start-50 translate-middle\" style=\"width: 6rem;\">\r\n"
										+ "    <p class=\"text-start\">Hooray! Order Placed !!<br>Order Summary</p>\r\n"
										+ "    <p class=\"text-start\">Name     :"+name+"</p>\r\n"
										+ "    <p class=\"text-start\">PhoneNo  :"+phone+" </p>\r\n"
										+ "    <p class=\"text-start\">Address  :"+address+" </p>\r\n"
										+ "    <p class=\"text-start\">Order Id :"+oid+"</p>\r\n"
										+ "    <p class=\"text-start\">Product  :"+p+" </p>\r\n"
										+ "    <p class=\"text-start\">Quantity :"+q+" </p>\r\n"
										+ "    <p class=\"text-start\">Amount   :"+a+" </p>\r\n"
										+ "    <p class=\"text-start\">Payment  :"+pay+"</p>\r\n"
										+ "  </div>";
							}
							else
							{
								message = "<div class=\"badge text-bg-primary text-wrap\" style=\"width: 6rem;\">\r\n"
										+ "    <p class=\"text-start\">Order not Placed,Unable to process</p> </div>";
							}
										
						}
						else
						{
							message = "<div class=\"badge text-bg-primary text-wrap\" style=\"width: 6rem;\">\r\n"
									+ "    <p class=\"text-start\">Order not Placed,Unable to process</p> </div>";
						}
						
						
					}
					else
					{
						message = "<div class=\"badge text-bg-primary text-wrap\" style=\"width: 6rem;\">\r\n"
								+ "    <p class=\"text-start\">Order not Placed,Unable to process</p> </div>";
					}
				}
				
			}
			else
			{
				
				message = "<div class=\"badge text-bg-primary text-wrap\" style=\"width: 6rem;\">\r\n"
						+ "    <p class=\"text-start\">Order not Placed,Student ID not found</p> </div>";
			}
			
			request.setAttribute("msg", message);
			RequestDispatcher rd =request.getRequestDispatcher("OrderRequest.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
