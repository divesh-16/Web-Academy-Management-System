package com.divesh.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.divesh.util.JDBCUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs1,rs2,rs3;
		
		HttpSession session = request.getSession(false);
	    String user = (session != null) ? (String) session.getAttribute("sid") : null;
	    if (user == null) {
	        response.sendRedirect("StudentLogin.jsp");
	        return;
	    }
		
		try
		{
			con=JDBCUtil.getConnection();
			
			String id = request.getParameter("user");
			String password = request.getParameter("password");
			
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Student Self-Processing Page</title>\r\n"
					+ "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\">\r\n"
					+ "  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css\">\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "    <style>\r\n"
					+ "        body {\r\n"
					+ "            background-color: #f8f9fa;\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        style>\r\n"
					+ "    /* General Page Styling */\r\n"
					+ "    body {\r\n"
					+ "      background-color: #f8f9fa;\r\n"
					+ "      font-family: Arial, sans-serif;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "	/*Navbar Starting */\r\n"
					+ "	\r\n"
					+ "    /* Navbar Styling */\r\n"
					+ "    .navbar {\r\n"
					+ "      background-color: #333;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    /* Organization Logo */\r\n"
					+ "    .navbar-brand img {\r\n"
					+ "      height: 60px; /* Increased height for bigger logo */\r\n"
					+ "      width: auto;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    /* Navbar Links */\r\n"
					+ "    .navbar-nav .nav-link {\r\n"
					+ "      color: #fff;\r\n"
					+ "      font-weight: bold;\r\n"
					+ "      font-size: 1.1em; /* Larger font for bold effect */\r\n"
					+ "      transition: color 0.3s;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    .navbar-nav .nav-link:hover {\r\n"
					+ "      color: #ff7f50;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    /* Dropdown Item Styling */\r\n"
					+ "    .dropdown-item {\r\n"
					+ "      font-weight: bold;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    .dropdown-item:hover {\r\n"
					+ "      background-color: #ff7f50;\r\n"
					+ "      color: #fff;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    /* Social Icon Styling */\r\n"
					+ "    .social-icon {\r\n"
					+ "      color: #fff;\r\n"
					+ "      margin-left: 10px;\r\n"
					+ "      font-size: 1.3em; /* Slightly larger icons */\r\n"
					+ "      transition: color 0.3s;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    .social-icon:hover {\r\n"
					+ "      color: #ff7f50;\r\n"
					+ "    }\r\n"
					+ "    /*Navbar ending*/\r\n"
					+ "\r\n"
					+ "        .btn-group {\r\n"
					+ "            display: flex;\r\n"
					+ "            justify-content: center;\r\n"
					+ "            gap: 1rem;\r\n"
					+ "            margin-bottom: 2rem;\r\n"
					+ "        }\r\n"
					+ "        .table-container {\r\n"
					+ "            max-height: 300px;\r\n"
					+ "            overflow-y: auto;\r\n"
					+ "        }\r\n"
					+ "        .table-responsive {\r\n"
					+ "            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "         /* Footer Styling */\r\n"
					+ "    footer {\r\n"
					+ "      background-color: #333;\r\n"
					+ "      color: #fff;\r\n"
					+ "      padding: 15px;\r\n"
					+ "      text-align: center;\r\n"
					+ "      position: fixed;\r\n"
					+ "      width: 100%;\r\n"
					+ "      bottom: 0;\r\n"
					+ "      font-size: 0.9em;\r\n"
					+ "    }\r\n"
					+ "\r\n"
					+ "    </style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "  <!-- Navbar Section -->\r\n"
					+ "  <nav class=\"navbar navbar-expand-lg navbar-dark\">\r\n"
					+ "    <a class=\"navbar-brand\" href=\"index.html\">\r\n"
					+ "      <img src=\"resources/ISA.jpg\" alt=\"Organization Logo\"> <!-- Organization Logo -->\r\n"
					+ "    </a>\r\n"
					+ "    <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n"
					+ "      <span class=\"navbar-toggler-icon\"></span>\r\n"
					+ "    </button>\r\n"
					+ "    <div>\r\n"
					+ "        <Label style=\"color: #f8f9fa; text-align: center; font-weight: bolder; font-size: larger;\">Invictus Sports Academy</Label>\r\n"
					+ "    </div>\r\n"
					+ "    <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\r\n"
					+ "      <ul class=\"navbar-nav ml-auto\">\r\n"
					+ "        <!-- Home Link -->\r\n"
					+ "        <li class=\"nav-item\">\r\n"
					+ "          <a class=\"nav-link\" href=\"index.html\">Home</a>\r\n"
					+ "        </li>\r\n"
					+ "        <!-- Dropdown for Login Pages -->\r\n"
					+ "        <li class=\"nav-item\">\r\n"
					+ "          <a class=\"nav-link\" href=\"./StudentLogout\">Logout</a>\r\n"
					+ "        <!-- About Us Link -->\r\n"
					+ "        <li class=\"nav-item\">\r\n"
					+ "          <a class=\"nav-link\" href=\"about.html\">About Us</a>\r\n"
					+ "        </li>\r\n"
					+ "        <!-- Social Media Icons -->\r\n"
					+ "        <li class=\"nav-item\">\r\n"
					+ "          <a class=\"nav-link social-icon\" href=\"https://instagram.com/organization\" target=\"_blank\"><i class=\"fab fa-instagram\"></i></a>\r\n"
					+ "        </li>\r\n"
					+ "        <li class=\"nav-item\">\r\n"
					+ "          <a class=\"nav-link social-icon\" href=\"https://wa.me/1234567890\" target=\"_blank\"><i class=\"fab fa-whatsapp\"></i></a>\r\n"
					+ "        </li>\r\n"
					+ "        <!-- Contact Email -->\r\n"
					+ "        <li class=\"nav-item\">\r\n"
					+ "          <a class=\"nav-link\" href=\"mailto:contact@organization.com\">contact@organization.com</a>\r\n"
					+ "        </li>\r\n"
					+ "      </ul>\r\n"
					+ "    </div>\r\n"
					+ "  </nav>\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "\r\n"
					+ "    <div class=\"container py-5\">\r\n"
					+ "        <h1 class=\"text-center mb-4\">Student Self-Processing Page</h1>\r\n"
					+ "\r\n"
					+ "        <!-- Buttons Section -->\r\n"
					+ "        <div class=\"btn-group\">\r\n"
					+ "            <button class=\"btn btn-primary\" onclick=\"showDetails('students')\">Student Details</button>\r\n"
					+ "            <button class=\"btn btn-secondary\" onclick=\"showDetails('batches')\">Batches</button>\r\n"
					+ "            <button class=\"btn btn-success\" onclick=\"showDetails('coaches')\">Coaches</button>\r\n"
					+ "            <button class=\"btn btn-warning\" onclick=\"showDetails('sports')\">Sports</button>\r\n"
					+ "        </div>\r\n"
					+ "\r\n"
					+ "        <form action=\"StudentChangePassword.jsp\" method=\"post\">\r\n"
					+ "          <div class=\"text-center mb-5\"> \r\n"
					+ "            <input  class=\"btn btn-danger\" type=\"submit\" value=\"Change Password\">\r\n"
					+ "          </div>\r\n"
					+ "        </form>      \r\n"
					+ "\r\n"
					+ "        <!-- Table Section -->\r\n"
					+ "        <div class=\"table-responsive table-container\">\r\n"
					+ "            <table class=\"table table-striped table-hover\">\r\n"
					+ "                <thead class=\"table-dark\">\r\n"
					+ "                    <tr id=\"table-header\">\r\n"
					+ "                        <th>Field</th>\r\n"
					+ "                        <th>Details</th>\r\n"
					+ "                    </tr>\r\n"
					+ "                </thead>\r\n"
					+ "                <tbody id=\"table-body\">\r\n"
					+ "                    <!-- Data will be dynamically inserted here -->\r\n"
					+ "                </tbody>\r\n"
					+ "            </table>\r\n"
					+ "        </div>\r\n"
					+ "    </div>");
			
			
			ps = con.prepareStatement("select * from student where sid=? and password=?");
			ps.setInt(1,Integer.parseInt(id));
			ps.setString(2, password);
			
			rs1 = ps.executeQuery();
			rs1.next();
			
			ps = con.prepareStatement("select name,phoneno,game from coach,batch,games where coach.cid=batch.cid and batch.gid=games.gid and sid=?");
			ps.setInt(1,Integer.parseInt(id));
			rs2 = ps.executeQuery();
			rs2.next();
			
			
			
			ps = con.prepareStatement("select name,game from coach,batch,games where coach.cid=batch.cid and batch.gid=games.gid and sid=?");
			ps.setInt(1,Integer.parseInt(id));
			rs3 = ps.executeQuery();
			rs3.next();
			
			
			out.println("<script>\r\n"
					+ "        // Data for the buttons\r\n"
					+ "        const data = {\r\n"
					+ "            students: [\r\n"
					+ "                { field: \"Name\", details: \""+rs1.getString("name")+"\" },\r\n"
					+ "                { field: \"ID\", details: \""+rs1.getInt("sid")+"\" },\r\n"
					+ "                { field: \"Address\", details: \""+rs1.getString("address")+"\" },\r\n"
					+ "                { field: \"Phone NO\", details: \""+rs1.getString("phoneno")+"\" }\r\n"
					+ "            ],\r\n"
					+ "            batches: [\r\n"
					+ "                { field: \"Batch Name\", details: \"Morning Batch\" },\r\n"
					+ "                { field: \"Batch Time\", details: \"6:00 AM - 8:00 AM\" },\r\n"
					+ "                { field: \"Enrolled Students\", details: \"25\" }\r\n"
					+ "            ],\r\n"
					+ "            coaches: [\r\n"
					+ "                { field: \"Coach Name\", details: \""+rs2.getString("name")+"\" },\r\n"
					+ "                { field: \"Experience\", details: \""+rs2.getString("phoneno")+"\" },\r\n"
					+ "                { field: \"Specialization\", details: \""+rs2.getString("game")+"\" }\r\n"
					+ "            ],\r\n"
					+ "            sports: [\r\n"
					+ "                { field: \"Sport\", details: \""+rs3.getString("game")+"\" },\r\n"
					+ "                { field: \"Players\", details: \"10\" },\r\n"
					+ "                { field: \"Coach\", details: \""+rs3.getString("name")+"\" }\r\n"
					+ "            ]\r\n"
					+ "        };\r\n"
					+ "");
			
			
			
			
			
			out.println(" // Function to display data based on the button clicked\r\n"
					+ "        function showDetails(category) {\r\n"
					+ "            const tableBody = document.getElementById(\"table-body\");\r\n"
					+ "            tableBody.innerHTML = \"\"; // Clear previous data\r\n"
					+ "\r\n"
					+ "            data[category].forEach(item => {\r\n"
					+ "                const row = document.createElement(\"tr\");\r\n"
					+ "                row.innerHTML = `<td>${item.field}</td><td>${item.details}</td>`;\r\n"
					+ "                tableBody.appendChild(row);\r\n"
					+ "            });\r\n"
					+ "        }");			
			out.println("</script>");
			
			
			
			
			out.println("  <!-- Footer Section -->\r\n"
					+ "  <footer>\r\n"
					+ "    &copy; 2024 All Rights Reserved by Organization Name.\r\n"
					+ "  </footer>\r\n"
					+ "\r\n"
					+ "   <!-- Bootstrap JS, jQuery, and Popper.js -->\r\n"
					+ "  <script src=\"https://code.jquery.com/jquery-3.5.1.min.js\"></script>\r\n"
					+ "  <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n"
					+ "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\r\n"
					+ "</body>\r\n"
					+ "</html>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
