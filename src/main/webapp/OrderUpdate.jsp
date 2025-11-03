<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%@ page import="com.divesh.entities.Order" %>
<%@ include file = "Header.jsp" %>
    
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
    		var product =  td[2].querySelector("input").value;
    		var quantity = td[3].querySelector("input").value;
    		var amount = td[4].querySelector("input").value;
    		var payOption =  td[5].querySelector("input").value;
    		var payStatus = td[6].querySelector("input").value;
    		var deliveryStatus = td[7].querySelector("input").value;
    		
    		fetch("http://localhost:8080/Sports-Management-Servlet-JDBC/OrderUpdate",
    				{
    					method:'POST',
    					body : new URLSearchParams({'oid':id , 'product':product, 'quantity':quantity, 'amount' : amount, 'payStatus':payStatus, 'payOption':payOption,'deliveryStatus':deliveryStatus })
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
	String oid = request.getParameter("oid");

	if(oid == null)
	{
		oid="";
	}
	
	String sbtn = request.getParameter("sbtn");
	
	if(sbtn != null && sbtn.equals("Refresh"))
	{
		oid="";
	}
	
%>


<div class="container text-center">
<h2 class="text-center mt-4">Orders Information</h2>

<!-- Search Logic -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="get" action="./OrderUpdate">
      <input class="form-control me-2" type="search" placeholder="Enter Accessories ID :" value="<%=oid %>" name="oid" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
  </div>


<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">OID</th>
      <th scope="col">SID</th>
      <th scope="col">PRODUCT</th>
      <th scope="col">QUANTITY</th>
      <th scope="col">AMOUNT</th>
      <th scope="col">PAY_STATUS</th>
      <th scope="col">PAY_OPTION</th>
      <th scope="col">DELIVERY_STATUS</th>
      <th scope="col">ACTION</th>
    </tr>
  </thead>
  
  
<%
	List<Order> L = (List<Order>) request.getAttribute("listOfOrder");

	if(L== null || L.isEmpty())
	{
%>

		<tr>
			<td colspan="8">No Data Found</td>
		</tr>

<%
	}
	else
	{
		for(Order S :L)
		{
			int toid = S.getOid();
			int sid = S.getSid();
			String product = S.getProductName();
			int quantity = S.getQuantity();
			double amount = S.getAmount();
			String payStatus = S.getPayStatus();
			String payOption = S.getPayOption();
			String deliveryStatus = S.getDeliveryStatus();
			
			
			String cls = "";
%>
			<tr id="<%=toid %>" class = "<%=cls%>">
		      <td><%=toid %></td>
		      <td> <input type="text" value="<%=sid %>" class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=product %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=quantity %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=amount %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=payStatus %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=payOption %>"  class="form-control me-2 border-danger"/></td>
		      <td> <input type="text" value="<%=deliveryStatus %>"  class="form-control me-2 border-danger"/></td>
		      <td><input type="button" value="Update" class="btn btn-primary" onclick="modify(<%=toid %>)"></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>