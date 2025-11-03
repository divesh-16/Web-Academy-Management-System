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


public class StudentPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs,rs1;
		
		try
		{
			con = JDBCUtil.getConnection();
			
			
			
			
			out.println("<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Admin Panel</title>\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n"
					+ "    <style>\r\n"
					+ "        body {\r\n"
					+ "            padding: 20px;\r\n"
					+ "            background-color: #f8f9fa;\r\n"
					+ "        }\r\n"
					+ "        .container {\r\n"
					+ "            margin-top: 20px;\r\n"
					+ "        }"
					+ " 		.table-responsive {\r\n"
					+ "            max-height: 600px;\r\n"
					+ "            overflow-y: auto;\r\n"
					+ "        }\r\n"
					+ "    </style>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"container\">\r\n"
					+ "        <h1 class=\"text-center\">Admin Panel</h1>\r\n"
					+ "\r\n"
					+ "        <div class=\"d-flex justify-content-around my-4\">\r\n"
					+ "            <button id=\"showDataBtn\" class=\"btn btn-primary\">Show Students' Data</button>\r\n"
					+ "            <button id=\"editStudentBtn\" class=\"btn btn-secondary\">Edit Student Details</button>\r\n"
					+ "			   <button id=\"addStudentBtn\" class=\"btn btn-success\">Add Student</button>\r\n"
					+ "            <button id=\"searchStudentBtn\" class=\"btn btn-info\">Search Student</button>\r\n"
					+ "            <button id=\"deleteStudentBtn\" class=\"btn btn-danger\">Delete Student</button>\r\n"
					+ "        </div>\r\n"
					+ "\r\n"
					+ "        <div id=\"responseContent\" class=\"mt-4\">\r\n"
					+ "            <!-- Dynamic content will load here -->\r\n"
					+ "        </div>\r\n"
					+ "    </div>");
			
			
			
			
			out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\"></script>\r\n"
					+ "    <script>\r\n"
					+ "        function loadStudentsData() {\r\n"
					+ "            const students = [");
			
			ps = con.prepareStatement("select * from student order by sid asc");
			rs = ps.executeQuery();
			while(rs.next())
			{
				String id = rs.getString("sid");
				String name=rs.getString("name");
				String phone=rs.getString("phoneno");
				String address=rs.getString("address");
				
				out.println("{sid: \""+id+"\", name: \""+name+"\", phone: \""+phone+"\", address: \""+address+"\" },");
			}
			
			out.println("            ];");
			
			out.println("let tableHTML = '<div class=\"table-responsive\"><table class=\"table table-striped\"><thead class=\"table-dark\"><tr><th>ID</th><th>Name</th><th>Phone No</th><th>Address</th></tr></thead><tbody>';\r\n"
					+ "\r\n"
					+ "            students.forEach(student => {\r\n"
					+ "                tableHTML += `<tr><td>${student.sid}</td><td>${student.name}</td><td>${student.phone}</td><td>${student.address}</td></tr>`;\r\n"
					+ "            });\r\n"
					+ "\r\n"
					+ "            tableHTML += '</tbody></table>';\r\n"
					+ "\r\n"
					+ "            document.getElementById('responseContent').innerHTML = tableHTML;\r\n"
					+ "        }");
			out.println("document.getElementById('showDataBtn').addEventListener('click', loadStudentsData);");
			
			out.println("document.getElementById('editStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form method=\"post\" action=\"./StudentUpdate\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"studentId\" class=\"form-label\">Student ID</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"studentId\" name=\"sid\" placeholder=\"Enter student ID\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"studentName\" class=\"form-label\">Name</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"studentName\" name=\"sName\" placeholder=\"Enter student name\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"studentAge\" class=\"form-label\">Phone NO</label>\r\n"
					+ "                        <input type=\"number\" class=\"form-control\" id=\"studentPhone\" name=\"sPhoneno\" placeholder=\"Enter student Phone NO\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"studentClass\" class=\"form-label\">Address</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"studentAddress\" name=\"sAddress\" placeholder=\"Enter student Address\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <button type=\"submit\" class=\"btn btn-success\">Update</button>\r\n"
					+ "                </form>\r\n"
					+ "            `;\r\n"
					+ "        });");
				
			
			out.println("document.getElementById('addStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form method=\"post\" action=\"./StudentAdd\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newStudentName\" class=\"form-label\">Name</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"newStudentName\" name=\"sName\" placeholder=\"Enter student name\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newStudentPhone\" class=\"form-label\">Phone Number</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"newStudentPhone\" name=\"sPhone\" placeholder=\"Enter student phone number\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newStudentAddress\" class=\"form-label\">Address</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"newStudentAddress\" name=\"sAddress\" placeholder=\"Enter student address\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <button type=\"submit\" class=\"btn btn-success\">Add Student</button>\r\n"
					+ "                </form>\r\n"
					+ "            `;\r\n"
					+ "        });");
			
			
			
			out.println(" document.getElementById('searchStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form id=\"searchForm\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"searchName\" class=\"form-label\">Search by Name</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"searchName\" name=\"searchName\" placeholder=\"Enter student name\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <button type=\"button\" class=\"btn btn-primary\" onclick=\"handleSearch()\">Search</button>\r\n"
					+ "                </form>\r\n"
					+ "            `;\r\n"
					+ "        });\r\n"
					+ "\r\n"
					+ "        function handleSearch() {\r\n"
					+ "            const name = document.getElementById('searchName').value;\r\n"
					+ "            if (name) {\r\n"
					+ "                searchStudentData(name);\r\n"
					+ "            } else {\r\n"
					+ "                alert('Please enter a name to search.');\r\n"
					+ "            }\r\n"
					+ "        }\r\n"
					+ "\r\n"
					+ "        function searchStudentData(name) {\r\n"
					+ "            const students = [");
			ps = con.prepareStatement("select * from student");
			rs1 = ps.executeQuery();
			while(rs1.next())
            {
				String id = rs1.getString("sid");
                String name=rs1.getString("name");
                String phone=rs1.getString("phoneno");
                String address=rs1.getString("address");
                out.println("{id: \""+id+"\", name: \""+name+"\", phone: \""+phone+"\", address: \""+address+"\" },");
            }
			
			out.println("];\r\n"
					+ "\r\n"
					+ "            const filteredStudents = students.filter(student => student.name.toLowerCase() === name.toLowerCase());\r\n"
					+ "\r\n"
					+ "            let tableHTML = '<div class=\"table-responsive\"><table class=\"table table-striped\"><thead class=\"table-dark\"><tr><th>ID</th><th>Name</th><th>Phone No</th><th>Address</th></tr></thead><tbody>';\r\n"
					+ "\r\n"
					+ "            filteredStudents.forEach(student => {\r\n"
					+ "                tableHTML += `<tr><td>${student.id}</td><td>${student.name}</td><td>${student.phone}</td><td>${student.address}</td></tr>`;\r\n"
					+ "            });\r\n"
					+ "\r\n"
					+ "            tableHTML += '</tbody></table></div>';\r\n"
					+ "\r\n"
					+ "            document.getElementById('responseContent').innerHTML = tableHTML;\r\n"
					+ "        }");
						
			
			
			out.println(" document.getElementById('deleteStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form method=\"post\" action=\"./StudentDelete\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"deleteId\" class=\"form-label\">Delete by ID</label>\r\n"
					+ "                        <input type=\"number\" class=\"form-control\" id=\"deleteId\" name=\"id\" placeholder=\"Enter student ID : \" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <button type=\"submit\" class=\"btn btn-danger\">Delete</button>\r\n"
					+ "                </form>\r\n"
					+ "            `;\r\n"
					+ "        });\r\n"
					+ "    </script>\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n"
					+ "");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
