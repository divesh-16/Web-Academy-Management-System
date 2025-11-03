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
    <title>Display Supplier</title>
    
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
    <form class="d-flex" role="search" method="post" action="./SupplierDisplay">
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
			<tr class = "<%=cls%>">
			  <td><%=tspid%></td>
		      <td><%=aid %></td>
		      <td><%=name %></td>
		      <td><%=address %></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>