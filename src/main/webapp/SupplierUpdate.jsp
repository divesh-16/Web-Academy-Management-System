<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%@ page import="com.divesh.entities.Supplier" %>
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
    		
    		
    		var aid = td[1].querySelector("input").value;
    		var name =  td[2].querySelector("input").value;
    		var address = td[3].querySelector("input").value;
    		
    		fetch("http://localhost:8080/Sports-Management-Servlet-JDBC/SupplierUpdate",
    				{
    					method:'POST',
    					body : new URLSearchParams({'spid':id , 'aid':aid, 'name':name, 'address' : address})
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
	String spid = request.getParameter("spid");

	if(spid == null)
	{
		spid="";
	}
	
	String sbtn = request.getParameter("sbtn");
	
	if(sbtn != null && sbtn.equals("Refresh"))
	{
		spid="";
	}
	
%>


<div class="container py-5 mt-5 text-center">
<h2 class="text-center mt-5">Supplier Information</h2>

<!-- Search Logic -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./SupplierUpdate">
      <input class="form-control me-2" type="search" placeholder="Enter Supplier ID :" value="<%=spid %>" name="spid" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
  </div>


<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">SPID</th>
      <th scope="col">AID</th>
      <th scope="col">NAME</th>
      <th scope="col">Address</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  
  
<%
	List<Supplier> L = (List<Supplier>) request.getAttribute("listOfSupplier");

	if(L== null || L.isEmpty())
	{
%>

		<tr>
			<td colspan="4">No Data Found</td>
		</tr>

<%
	}
	else
	{
		for(Supplier S :L)
		{
			int tspid = S.getSpid();
			int aid = S.getAid();
			String name = S.getName();
			String address = S.getAddress();
			
			String cls = "";
%>
			<tr id="<%=tspid %>" class = "<%=cls%>">
		      <td><%=tspid %></td>
		      <td> <input type="text" value="<%=aid %>" class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=name %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=address %>"  class="form-control me-2 border-danger"/></td>
		      <td><input type="button" value="Update" class="btn btn-primary" onclick="modify(<%=tspid %>)"></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>