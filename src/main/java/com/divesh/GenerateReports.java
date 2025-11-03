package com.divesh;

import java.io.File;
import java.io.FileWriter;
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




public class GenerateReports extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		FileWriter fw = null;
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String message = "";
		
		try
		{
			con = JDBCUtil.getConnection();
			
			//student
			ps = con.prepareStatement("select * from student order by sid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\student.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getInt("sid")+","+rs.getString("name")+","+rs.getString("address")+","+rs.getString("phoneno")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			//coach
			ps = con.prepareStatement("select * from coach order by cid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\coach.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getInt("cid")+","+rs.getString("name")+","+rs.getString("address")+","+rs.getString("phoneno")+","+rs.getDouble("salary")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			//admin
			ps = con.prepareStatement("select * from admin order by id");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\admin.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getInt("id")+","+rs.getString("name")+","+rs.getString("address")+","+rs.getString("phoneno")+","+rs.getDouble("salary")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			
			//accessories
			ps = con.prepareStatement("select * from accessories order by aid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\accessories.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getInt("aid")+","+rs.getString("name")+","+rs.getString("stock")+","+rs.getString("price")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			//games
			ps = con.prepareStatement("select * from games order by gid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\games.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getInt("gid")+","+rs.getString("game")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			
			
			//supplier 
			ps = con.prepareStatement("select * from supplier order by aid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\supplier.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getInt("spid")+","+rs.getString("aid")+","+rs.getString("name")+","+rs.getString("address")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			
			// batch
			ps = con.prepareStatement("select bid,student.sid,student.name as sname,coach.cid,coach.name as cname,games.gid,games.game from student,coach,games,batch where student.sid = batch.sid and coach.cid=batch.cid and batch.gid=games.gid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\batch.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getString("bid")+","+rs.getString("sid")+","+rs.getString("sname")+","+rs.getString("cid")+","+rs.getString("cname")+","+rs.getString("gid")+","+rs.getString("game")+"\n";
				fw.write(str);
			}
			
			fw.close();
			
			
			// order
			ps = con.prepareStatement("select oid,name,phoneno,address,product_name,quantity,amount,pay_option,pay_status,delivery_status from oorder,student where student.sid = oorder.sid");
			fw = new FileWriter("D:\\Study\\Java\\Advance Java\\Web Projects\\Sports-Management-Servlet-JDBC\\src\\main\\resources\\order.txt");
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				String str = rs.getString("oid")+","+rs.getString("name")+","+rs.getString("phoneno")+","+rs.getString("address")+","+rs.getString("product_name")+","+rs.getString("quantity")+","+rs.getString("amount")+","+rs.getString("pay_option")+","+rs.getString("pay_status")+","+rs.getString("delivery_status")+"\n";
				fw.write(str);
			}
			
			
			fw.close();
			
			message = ("<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\">Reports Generated Successfully</div>");
			
			
			
			
		}
		catch(Exception e)
		{
			fw.close();
			e.printStackTrace();
			message = ("<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">Reports Generated Successfully</div>");
			
		}
		request.setAttribute("msg", message);
		RequestDispatcher rd = request.getRequestDispatcher("AdminWindow.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
