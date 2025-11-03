<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "AdminHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Batch Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            padding: 20px;
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 20px;
        }
        .table-responsive {
            max-height: 600px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
    <div class="container py-5 mt-5">
        <h1 class="text-center mt-5">Batch Panel</h1>

        <div class="d-flex justify-content-around my-4">
            <button id="showDataBtn" class="btn btn-primary"  ><a class="dropdown-item" href="./BatchAdd">Add Batch</a></button>
            <button id="editStudentBtn" class="btn btn-secondary"><a class="dropdown-item" href="./BatchDisplay">Show Batch</a></button>
            <button id="addStudentBtn" class="btn btn-success"><a class="dropdown-item" href="./BatchUpdate">Update Batch</a></button>
            <button id="deleteStudentBtn" class="btn btn-danger"><a class="dropdown-item" href="./BatchDelete">Delete Batch</a></button>
        </div>

        <div id="responseContent" class="mt-4">
            <!-- Dynamic content will load here -->
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
