<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "AdminHeader.jsp" %>

<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("id") == null) {
        response.sendRedirect("AdminLogin.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container py-5 mt-5">
        <h1 class="text-center mb-5 mt-5">Admin Dashboard</h1>
        <div class="row g-4">
            <!-- Buttons/Links -->
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Students</h5>
                    <a href="./AdminController?option=student" class="btn btn-primary mt-3">Manage Students</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Coaches</h5>
                    <a href="./AdminController?option=coach" class="btn btn-secondary mt-3">Manage Coaches</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Accessories</h5>
                    <a href="./AdminController?option=accessories" class="btn btn-success mt-3">Manage Accessories</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Batches</h5>
                    <a href="./AdminController?option=batch" class="btn btn-warning mt-3">Manage Batches</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Suppliers</h5>
                    <a href="./AdminController?option=supplier" class="btn btn-danger mt-3">Manage Suppliers</a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center p-4">
                    <h5>Orders</h5>
                    <a href="./AdminController?option=order" class="btn btn-info mt-3">Manage Orders</a>
                </div>
            </div>
            <!-- Reports Section -->
            <div class="col-md-6">
                <div class="card text-center p-4">
                    <h5>Reports</h5>
                    <a href="./AdminController?option=report" class="btn btn-success mt-3">Generate Reports</a>
                </div>
            </div>
            <!-- Admin Data Section -->
            <div class="col-md-6">
                <div class="card text-center p-4">
                    <h5>Admin Settings</h5>
                    <a href="./AdminController?option=mydata" class="btn btn-dark mt-3">Edit Admin Data</a>
                </div>
            </div>
        </div>
    </div>
<p> ${msg} </p>
</body>
</html>