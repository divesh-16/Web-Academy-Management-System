package com.divesh.coach;

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

import com.divesh.util.JDBCUtil;

public class CoachPanel extends HttpServlet {
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
					+ "            <button id=\"showDataBtn\" class=\"btn btn-primary\">Show Coaches Data</button>\r\n"
					+ "            <button id=\"editStudentBtn\" class=\"btn btn-secondary\">Edit Coach Details</button>\r\n"
					+ "			   <button id=\"addStudentBtn\" class=\"btn btn-success\">Add Coach</button>\r\n"
					+ "            <button id=\"searchStudentBtn\" class=\"btn btn-info\">Search Coach</button>\r\n"
					+ "            <button id=\"deleteStudentBtn\" class=\"btn btn-danger\">Delete Coach</button>\r\n"
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
			
			ps = con.prepareStatement("select * from coach order by cid asc");
			rs = ps.executeQuery();
			while(rs.next())
			{
				String id = rs.getString("cid");
				String name=rs.getString("name");
				String phone=rs.getString("phoneno");
				String address=rs.getString("address");
				String salary=rs.getString("salary");
				
				out.println("{cid: \""+id+"\", name: \""+name+"\", phone: \""+phone+"\", address: \""+address+"\", salary: \""+salary+"\" },");
			}
			
			out.println("            ];");
			
			out.println("let tableHTML = '<div class=\"table-responsive\"><table class=\"table table-striped\"><thead class=\"table-dark\"><tr><th>ID</th><th>Name</th><th>Phone No</th><th>Address</th><th>Salary</th></tr></thead><tbody>';\r\n"
					+ "\r\n"
					+ "            students.forEach(student => {\r\n"
					+ "                tableHTML += `<tr><td>${student.cid}</td><td>${student.name}</td><td>${student.phone}</td><td>${student.address}</td><td>${student.salary}</td></tr>`;\r\n"
					+ "            });\r\n"
					+ "\r\n"
					+ "            tableHTML += '</tbody></table>';\r\n"
					+ "\r\n"
					+ "            document.getElementById('responseContent').innerHTML = tableHTML;\r\n"
					+ "        }");
			out.println("document.getElementById('showDataBtn').addEventListener('click', loadStudentsData);");
			
			out.println("document.getElementById('editStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form method=\"post\" action=\"./CoachUpdate\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"coachId\" class=\"form-label\">Student ID</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"coachId\" name=\"cid\" placeholder=\"Enter Coach ID\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"coachName\" class=\"form-label\">Name</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"coachName\" name=\"cName\" placeholder=\"Enter Coach name\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"coachAge\" class=\"form-label\">Phone NO</label>\r\n"
					+ "                        <input type=\"number\" class=\"form-control\" id=\"coachPhone\" name=\"cPhoneno\" placeholder=\"Enter Coach Phone NO\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"coachClass\" class=\"form-label\">Address</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"coachAddress\" name=\"cAddress\" placeholder=\"Enter Coach Address\">\r\n"
					+ "                    </div>\r\n"
					+ "					   <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"coachSalary\" class=\"form-label\">Salary</label>\r\n"
					+ "                        <input type=\"number\" class=\"form-control\" id=\"coachSalary\" name=\"cSalary\" placeholder=\"Enter Coach Salary\">\r\n"
					+ "                    </div>\r\n"
					+ "                    <button type=\"submit\" class=\"btn btn-success\">Update</button>\r\n"
					+ "                </form>\r\n"
					+ "            `;\r\n"
					+ "        });");
				
			
			out.println("document.getElementById('addStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form method=\"post\" action=\"./CoachAdd\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newCoachName\" class=\"form-label\">Name</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"newCoachName\" name=\"cName\" placeholder=\"Enter Coach name\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newCoachPhone\" class=\"form-label\">Phone Number</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"newCoachPhone\" name=\"cPhone\" placeholder=\"Enter Coach phone number\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newCoachAddress\" class=\"form-label\">Address</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"newCoachAddress\" name=\"cAddress\" placeholder=\"Enter Coach address\" required>\r\n"
					+ "                    </div>\r\n"
					+ "					   <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"newCoachSalary\" class=\"form-label\">Salary</label>\r\n"
					+ "                        <input type=\"number\" class=\"form-control\" id=\"newCoachSalary\" name=\"cSalary\" placeholder=\"Enter Coach Salary\" required>\r\n"
					+ "                    </div>\r\n"
					+ "                    <button type=\"submit\" class=\"btn btn-success\">Add Coach</button>\r\n"
					+ "                </form>\r\n"
					+ "            `;\r\n"
					+ "        });");
			
			
			
			out.println(" document.getElementById('searchStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form id=\"searchForm\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"searchName\" class=\"form-label\">Search by Name</label>\r\n"
					+ "                        <input type=\"text\" class=\"form-control\" id=\"searchName\" name=\"searchName\" placeholder=\"Enter Coach name\">\r\n"
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
			ps = con.prepareStatement("select * from coach");
			rs1 = ps.executeQuery();
			while(rs1.next())
            {
				String id = rs1.getString("cid");
                String name=rs1.getString("name");
                String phone=rs1.getString("phoneno");
                String address=rs1.getString("address");
                String salary=rs1.getString("salary");
                
                out.println("{cid: \""+id+"\", name: \""+name+"\", phone: \""+phone+"\", address: \""+address+"\", salary: \""+salary+"\" },");
            }
			
			out.println("];\r\n"
					+ "\r\n"
					+ "            const filteredStudents = students.filter(student => student.name.toLowerCase() === name.toLowerCase());\r\n"
					+ "\r\n"
					+ "            let tableHTML = '<div class=\"table-responsive\"><table class=\"table table-striped\"><thead class=\"table-dark\"><tr><th>ID</th><th>Name</th><th>Phone No</th><th>Address</th><th>Salary</th></tr></thead><tbody>';\r\n"
					+ "\r\n"
					+ "            filteredStudents.forEach(student => {\r\n"
					+ "                tableHTML += `<tr><td>${student.cid}</td><td>${student.name}</td><td>${student.phone}</td><td>${student.address}</td><td>${student.salary}</td></tr>`;\r\n"
					+ "            });\r\n"
					+ "\r\n"
					+ "            tableHTML += '</tbody></table></div>';\r\n"
					+ "\r\n"
					+ "            document.getElementById('responseContent').innerHTML = tableHTML;\r\n"
					+ "        }");
						
			
			
			out.println(" document.getElementById('deleteStudentBtn').addEventListener('click', () => {\r\n"
					+ "            document.getElementById('responseContent').innerHTML = `\r\n"
					+ "                <form method=\"post\" action=\"./CoachDelete\">\r\n"
					+ "                    <div class=\"mb-3\">\r\n"
					+ "                        <label for=\"deleteId\" class=\"form-label\">Delete by ID</label>\r\n"
					+ "                        <input type=\"number\" class=\"form-control\" id=\"deleteId\" name=\"id\" placeholder=\"Enter Coach ID : \" required>\r\n"
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
