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
    
</head>
<body>
<%
	String tsid = request.getParameter("sid");
	String tcid = request.getParameter("cid");

	if(tsid == null)
	{
		tsid="";
	}
	
	if(tcid == null)
	{
		tcid="";
	}
	
	String sbtn = request.getParameter("sbtn");
	
	if(sbtn != null && sbtn.equals("Refresh"))
	{
		tsid="";
		tcid="";
	}
	
	
%>


<div class="container py-5 mt-5 text-center">
<h2 class="text-center mt-5">Batch Information</h2>

<!-- Search Logic for Student -->
<div class="container-fluid mt-3 d-flex justify-content-end">
    <form class="d-flex" role="search" method="post" action="./BatchDisplay">
      <input class="form-control me-2" type="search" placeholder="Enter Student Roll no" value="<%=tsid %>" name="sid" aria-label="Search"/>
      <input class="form-control me-2" type="search" placeholder="Enter Teacher ID : " value="<%=tcid %>" name="cid" aria-label="Search"/>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Search">Search</button>
      <button class="btn btn-outline-success me-2" type="submit" name="sbtn" value="Refresh">Refresh</button>
    </form>
 </div>
 

<!-- Table Logic -->
<table class="table table-striped table-bordered table-hover mt-5">
<thead>
    <tr class="table-dark">
      <th scope="col">BID</th>
      <th scope="col">SID</th>
      <th scope="col">CID</th>
      <th scope="col">GID</th>
    </tr>
  </thead>
  
  
<%
	List<Batch> L = (List<Batch>) request.getAttribute("listOfBatch");

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
		for(Batch S :L)
		{
			int bid = S.getBid();
			int rno = S.getRno();
			int tno = S.getTno();
			int gid = S.getGid();
			//out.println("<tr>");
			//out.println("<td>"+rno+"</td>");
			//out.println("<td>"+name+"</td>");
			//out.println("<td>"+per+"</td>");
			//out.println("</tr>");
			
			String cls = "";
			
%>
			<tr class = "<%=cls%>">
			  <td><%=bid %></td>
		      <td><%=rno %></td>
		      <td><%=tno %></td>
		      <td><%=gid %></td>
	    	</tr>
<%		
		
		}//for
	}// else
%>
  
    
</table>    
</div>    

</body>
</html>