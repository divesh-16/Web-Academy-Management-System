<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "Header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background: linear-gradient(to right, #00c6ff, #0072ff);
      height: 100vh;
    }
    .login-box {
      background: #fff;
      border-radius: 12px;
      padding: 30px;
      box-shadow: 0 4px 10px rgba(0,0,0,0.3);
      width: 350px;
      margin: auto;
      margin-top: 100px;
    }
  </style>
</head>
<body>
  <div class="login-box text-center">
    <h3 class="mb-3 text-primary">User Login</h3>
    <form action="./StudentPage" method="post">
      <div class="form-group mb-3">
        <input type="text" name="user" class="form-control" placeholder="Enter User ID" required>
      </div>
      <div class="form-group mb-3">
        <input type="password" name="password" class="form-control" placeholder="Enter Password" required>
      </div>
      <button type="submit" class="btn btn-primary w-100">Login</button>
    </form>

    <%
        String msg = (String) request.getAttribute("errorMessage");
        if (msg != null) {
    %>
        <div class="alert alert-danger mt-3"><%= msg %></div>
    <% } %>
  </div>
</body>
</html>