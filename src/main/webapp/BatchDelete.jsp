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
    		fetch("http://localhost:8080/Sports-Management-Servlet-JDBC/BatchDelete",
    				{
    					method:'POST',
    					body : new URLSearchParams({'id':id})
    				})
    		.then(response => response.text())
    		.then(data => 
    					{
    						if(data.trim()=="success")
    						{
    							//alert("Record is Deleted for rno : "+trno);
    							alert("Record is Deleted for ID : "+ id);
    							var tr = document.getElementById(id);
    							tr.remove();
    						}
    						
    						if(data.trim()=="failure")
    						{
    							alert("Record not Deleted for tno : "+id);
    						}
    					})
    		.catch(error => console.error("MyError while Deleting :",error))
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
<h2 class="text-center mt-5">Batch Information</h2>

<!-- Search Logic for Student -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./BatchDelete">
      <input class="form-control me-2" type="search" placeholder="Enter Batch ID  : " value="<%=id %>" name="id" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
 </div>


<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">ID</th>
      <th scope="col">RNO</th>
      <th scope="col">TNO</th>
      <th scope="col">Game</th>
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
		for(Batch T :L)
		{
			int bid = T.getBid();
			int rno = T.getRno();
			int tno = T.getTno();
			int gid= T.getGid();
			
			//out.println("<tr>");
			//out.println("<td>"+rno+"</td>");
			//out.println("<td>"+name+"</td>");
			//out.println("<td>"+per+"</td>");
			//out.println("</tr>");
			
			String cls = "";
			
%>
			<tr id="<%=bid %>" class = "<%=cls%>">
		      <td><%=bid %></td>
		      <td><%=rno %></td>
		      <td><%=tno %></td>
		      <td><%=gid %></td>
		      <td><input type="button" value="Delete" class="btn btn-danger" onclick="modify(<%=bid %>)"></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>