<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.divesh.entities.Accessories" %>    
<%@ include file="Header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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

<div class="container my-5">
   
	
    <h2 class="text-center mx-auto p-5">Student Shopping Cart</h2>
    
    <div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./OrderRequest">
      <input class="form-control me-2" type="search" placeholder="Enter Accessories ID :" value="<%=aid %>" name="aid" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
  </div>

	<form method="post" action="./ConfirmOrder">
    <!-- Student ID Input -->
    <div class="mb-4 text-center">
        <label for="studentId" class="form-label fw-bold">Enter Student ID:</label>
        <input type="text" id="studentId" name="sid" class="form-control w-50 mx-auto" required>
    </div>
    
<%
	List<Accessories> L = (List<Accessories>) request.getAttribute("listOfAccessories");

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
%>
      
<% 
		for(Accessories S :L)
		{
			int taid = S.getAid();
			String name = S.getName();
			double price = S.getPrice();
			int stock = S.getStock();
			
			String cls = "";
%>    
    
   <div class="form-check">
        <!-- Radio button to select this accessory -->
        <input class="form-check-input" type="radio" name="selectedAccessory" value="<%=taid %>" id="acc<%=taid %>">
        <label class="form-check-label" for="acc<%=taid %>">
            <%=name %> - $<%=price %>
        </label>
        <!-- Quantity input for this accessory -->
    </div>
<%		
		
		}//for
	}// else
%>

      <input type="number" class="form-control w-25 d-inline ms-2" name="quantity" min="0" max="10" value="0">
      <button class="btn btn-outline-success me-2" type="submit" name="sb1" value="">confirm order</button>
    </form>
	
</div>

<p> ${msg} </p>
</body>
</html>