<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>

<%
    session = request.getSession(false);
    if (session == null || session.getAttribute("sid") == null) {
        response.sendRedirect("StudentLogin.jsp");
        return;
    }

    Map<String, String> student = (Map<String, String>) request.getAttribute("student");
    Map<String, String> coach = (Map<String, String>) request.getAttribute("coach");
    Map<String, String> sport = (Map<String, String>) request.getAttribute("sport");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Student Dashboard</title>

<!-- ✅ Bootstrap 5 only -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<style>
    body { background-color: #f8f9fa; }
    .navbar { background-color: #333; }
    .navbar-brand img { height: 60px; width: auto; }
    .navbar-nav .nav-link { color: #fff; font-weight: bold; }
    .navbar-nav .nav-link:hover { color: #ff7f50; }
    .btn-group { display: flex; justify-content: center; gap: 1rem; margin-bottom: 2rem; flex-wrap: wrap; }
    footer {
        background-color: #333; color: #fff; padding: 15px;
        text-align: center; position: fixed; width: 100%; bottom: 0;
    }
    .table td, .table th {
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        max-width: 200px;
    }
    .table-container {
        max-height: 300px;
        overflow-y: auto;
    }
    .table-responsive {
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }
</style>
</head>

<body>
    <!-- ✅ Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.html">
                <img src="resources/ISA.jpg" alt="Logo">
            </a>
            <span class="navbar-text text-white fw-bold ms-2">Invictus Sports Academy</span>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li><a class="nav-link" href="index.html">Home</a></li>
                    <li><a class="nav-link" href="./StudentLogout">Logout</a></li>
                    <li><a class="nav-link" href="about.html">About</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- ✅ Main content -->
    <div class="container py-5 mb-5">
        <h1 class="text-center mb-4">Welcome, <%= student.get("name") %></h1>
        
        <div class="position-relative ">
        	
        	<div class="card ms-5" style="width: 18rem; display: inline-block;">
			  <div class="card-header">
			    Student Details :
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item"><%= student.get("name") %></li>
			    <li class="list-group-item"><%= student.get("sid") %></li>
			    <li class="list-group-item"><%= student.get("address") %></li>
			    <li class="list-group-item"><%= student.get("phoneno") %></li>
			  </ul>
			</div>
			
			<div class="card ms-5" style="width: 18rem; display: inline-block;">
			  <div class="card-header">
			    Coach Details :
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item"><%= coach.get("name") %></li>
			    <li class="list-group-item"><%= coach.get("phoneno") %></li>
			    <li class="list-group-item"><%= coach.get("game") %></li>
			  </ul>
			</div>
			
			<div class="card ms-5" style="width: 18rem; display: inline-block;">
			  <div class="card-header">
			    Sports Details :
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item"><%= sport.get("name") %></li>
			    <li class="list-group-item"><%= sport.get("game") %></li>
			  </ul>
			</div>
        
        
        </div>

        
        <form action="StudentChangePassword.jsp" method="post" class="text-center mb-5">
            <input class="btn btn-danger" type="submit" value="Change Password">
        </form>

 

    <!-- ✅ JS Section 
    <script>
        const data = {
            students: [
                { field: "Name", details: '<%= student.get("name") %>' },
                { field: "ID", details: '<%= student.get("sid") %>' },
                { field: "Address", details: '<%= student.get("address") %>' },
                { field: "Phone", details: '<%= student.get("phoneno") %>' }
            ],
            coaches: [
                { field: "Coach Name", details: '<%= (coach != null ? coach.get("name") : "N/A") %>' },
                { field: "Phone no", details: '<%= (coach != null ? coach.get("phoneno") : "N/A") %>' },
                { field: "Specialization", details: '<%= (coach != null ? coach.get("game") : "N/A") %>' }
            ],
            sports: [
                { field: "Sport", details: '<%= (sport != null ? sport.get("name") : "N/A") %>' },
                { field: "Coach", details: '<%= (sport != null ? sport.get("game") : "N/A") %>' }
            ]
        };

        function showDetails(category) {
            const tableBody = document.getElementById("table-body");
            tableBody.innerHTML = "";
            data[category].forEach(item => {
                const row = document.createElement("tr");
                row.innerHTML = `<td>${item.field}</td><td>${item.details}</td>`;
                tableBody.appendChild(row);
            });
        }

        // ✅ Load student details by default
        window.onload = () => showDetails('students');
        
        
    </script>
    -->

    <!-- ✅ Bootstrap 5 JS Bundle only -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</div> <!-- Container -->

    <footer>
        &copy; 2025 Invictus Sports Academy. All Rights Reserved.
    </footer>
</body>
</html>
