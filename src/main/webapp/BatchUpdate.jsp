<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%@ page import="com.divesh.entities.Batch" %>
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
    		
    		
    		var sid = td[1].querySelector("input").value;
    		var cid =  td[2].querySelector("input").value;
    		var gid = td[3].querySelector("input").value;
    		
    		fetch("http://localhost:8080/Sports-Management-Servlet-JDBC/BatchUpdate",
    				{
    					method:'POST',
    					body : new URLSearchParams({'bid':id , 'sid':sid, 'cid':cid, 'gid' : gid})
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
	String bid = request.getParameter("id");

	if(bid == null)
	{
		bid="";
	}
	
	String sbtn = request.getParameter("sbtn");
	
	if(sbtn != null && sbtn.equals("Refresh"))
	{
		bid="";
	}
	
%>


<div class="container py-5 mt-5 text-center">
<h2 class="text-center mt-5">Batch Update</h2>

<!-- Search Logic -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./BatchUpdate">
      <input class="form-control me-2" type="search" placeholder="Enter Batch ID no" value="<%=bid %>" name="bid" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
  </div>


<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">BID</th>
      <th scope="col">Student ID</th>
      <th scope="col">Teacher ID</th>
      <th scope="col">GID</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  
  
<%
	List<Batch> L = (List<Batch>) request.getAttribute("listOfBatch");

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
		for(Batch S :L)
		{
			int id = S.getBid();
			int rno = S.getRno();
			int tno = S.getTno();
			int gid = S.getGid();
 			
			
			String cls = "";
			
%>
			<tr id="<%=id %>" class = "<%=cls%>">
		      <td><%=id %></td>
		      <td> <input type="text" value="<%=rno %>" class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=tno %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=gid %>"  class="form-control me-2 border-danger"/></td>
		      <td><input type="button" value="Update" class="btn btn-primary" onclick="modify(<%=id %>)"></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>