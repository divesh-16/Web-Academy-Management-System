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
    <title>Display Accessories</title>
    
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
    <form class="d-flex" role="search" method="post" action="./AccessoriesDisplay">
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
    </tr>
  </thead>
  
  
<%
	List<Accessories> L = (List<Accessories>) request.getAttribute("listOfAccessories");

	if(L== null || L.isEmpty())
	{
%>

		<tr>
			<td colspan="3">No Data Found</td>
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
			<tr class = "<%=cls%>">
			  <td><%=taid%></td>
		      <td><%=name %></td>
		      <td><%=stock %></td>
		      <td><%=price %></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>