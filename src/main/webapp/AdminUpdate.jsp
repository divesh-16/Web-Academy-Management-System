<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%@ page import="com.divesh.entities.Admin" %>
<%@ include file = "AdminHeader.jsp" %>
    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    
    <script>
    	
    	function modify(id)
    	{
    		var tr = document.getElementById(id);
    		var td = tr.getElementsByTagName("td");
    		
    		
    		var name = td[1].querySelector("input").value;
    		var phoneno = td[2].querySelector("input").value;
    		var salary =  td[3].querySelector("input").value;
    		var address = td[4].querySelector("input").value;
    		var password = td[5].querySelector("input").value;
    		
    		fetch("http://localhost:8080/Sports-Management-Servlet-JDBC/AdminUpdate",
    				{
    					method:'POST',
    					body : new URLSearchParams({'id':id , 'name':name, 'phoneno':phoneno, 'address':address, 'salary' : salary, 'password':password})
    				})
    		.then(response => response.text())
    		.then(data => 
    					{
    						if(data.trim()=="success")
    						{
    							//alert("Record is Deleted for rno : "+ttno);
    							alert("Record is Updated for ID : "+ id);
    							
    						}
    						
    						if(data.trim()=="failure")
    						{
    							alert("Failed to update ID : "+id);
    						}
    					})
    		.catch(error => console.error("MyError while Updating :",error))
    	}
    
    </script>
    
</head>
<body>
<%
	String id = request.getParameter("id");

	if(id == null)
	{
		id="";
	}
	
	String sbtn = request.getParameter("sbtn");
	
	if(sbtn != null && sbtn.equals("Refresh"))
	{
		id="";
	}
	
%>


<div class="container py-5 mt-5 text-center">
<h2 class="text-center mt-5">Admin Information</h2>

<!-- Search Logic -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./AdminUpdate">
      <input class="form-control me-2" type="search" placeholder="Enter Admin ID :" value="<%=id %>" name="id" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
  </div>


<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">ID</th>
      <th scope="col">NAME</th>
      <th scope="col">PHONENO</th>
      <th scope="col">SALARY</th>
      <th scope="col">ADDRESS</th>
      <th scope="col">PASSWORD</th>
      <th scope="col">ACTION</th>
    </tr>
  </thead>
  
  
<%
	List<Admin> L = (List<Admin>) request.getAttribute("listOfAdmin");

	if(L== null || L.isEmpty())
	{
%>

		<tr>
			<td colspan="5">No Data Found</td>
		</tr>

<%
	}
	else
	{
		for(Admin S :L)
		{
			int tid = S.getId();
			String name = S.getName();
			String address = S.getAddress();
			String phoneno = S.getPhoneNo();
			double salary = S.getSalary();
			String password = S.getPassword();
			//out.println("<tr>");
			//out.println("<td>"+rno+"</td>");
			//out.println("<td>"+name+"</td>");
			//out.println("<td>"+per+"</td>");
			//out.println("</tr>");
			
			String cls = "";
%>
			<tr id="<%=tid %>" class = "<%=cls%>">
		      <td><%=tid %></td>
		      <td> <input type="text" value="<%=name %>" class="form-control me-2 border-danger"/></td>
		       <td> <input type="text" value="<%=phoneno %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=salary %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=address %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=password %>"  class="form-control me-2 border-danger"/></td>
		      <td><input type="button" value="Update" class="btn btn-primary" onclick="modify(<%=tid %>)"/></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>