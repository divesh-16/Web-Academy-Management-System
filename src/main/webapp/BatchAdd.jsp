<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.divesh.entities.Student" %>
<%@ page import="com.divesh.entities.Coach" %>
<%@ include file = "AdminHeader.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding to Batch</title>
</head>
<body>
<div class="container py-5 mt-5" style=" margin-top :100px; width :500px" >
	 <h2 class="text-center mt-5">Adding to Batch</h2>  
	 
<form class="mt-5" method="post" action="./BatchAdd">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Roll Number of Student</label>
    <input type="text" name="rno" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
  </div>
  
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Teacher ID </label>
    <input type="text" name="tno" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
  </div>
  
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Game ID </label>
    <input type="text" name="gid" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
  </div>
  
  <div class="d-grid gap-2">
  	<button type="submit" class="btn btn-primary">Save</button>
  </div>
</form>

</div>
<p> ${msg} </p>
</body> 
</body>
</html>