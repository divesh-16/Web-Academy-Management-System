<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%@ page import="com.divesh.entities.Accessories" %>
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
    		var stock =  td[2].querySelector("input").value;
    		var price = td[3].querySelector("input").value;
    		
    		fetch("http://localhost:8080/Sports-Management-Servlet-JDBC/AccessoriesUpdate",
    				{
    					method:'POST',
    					body : new URLSearchParams({'aid':id , 'name':name, 'stock':stock, 'price' : price})
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
	String aid = request.getParameter("aid");

	if(aid == null)
	{
		aid="";
	}
	
	String sbtn = request.getParameter("sbtn");
	
	if(sbtn != null && sbtn.equals("Refresh"))
	{
		aid="";
	}
	
%>


<div class="container py-5 mt-5 text-center">
<h2 class="text-center mt-5">Accessories Information</h2>

<!-- Search Logic -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./AccessoriesUpdate">
      <input class="form-control me-2" type="search" placeholder="Enter Accessories ID :" value="<%=aid %>" name="aid" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
  </div>


<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">AID</th>
      <th scope="col">NAME</th>
      <th scope="col">STOCK</th>
      <th scope="col">PRICE</th>
      <th scope="col">ACTION</th>
    </tr>
  </thead>
  
  
<%
	List<Accessories> L = (List<Accessories>) request.getAttribute("listOfAccessories");

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
		for(Accessories S :L)
		{
			int taid = S.getAid();
			String name = S.getName();
			int stock = S.getStock();
			double price = S.getPrice();
			//out.println("<tr>");
			//out.println("<td>"+rno+"</td>");
			//out.println("<td>"+name+"</td>");
			//out.println("<td>"+per+"</td>");
			//out.println("</tr>");
			
			String cls = "";
%>
			<tr id="<%=taid %>" class = "<%=cls%>">
		      <td><%=taid %></td>
		      <td> <input type="text" value="<%=name %>" class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=stock %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=price %>"  class="form-control me-2 border-danger"/></td>
		      <td><input type="button" value="Update" class="btn btn-primary" onclick="modify(<%=taid %>)"></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>