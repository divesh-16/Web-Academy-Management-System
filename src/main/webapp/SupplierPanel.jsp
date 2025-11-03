<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "AdminHeader.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Supplier Panel</title>
</head>
<body>
    <div class="container py-5 mt-5">
        
		<h1 class="text-center mt-5">Supplier Panel</h1>
        <div class="d-flex justify-content-around my-4">
            <button id="showDataBtn" class="btn btn-primary"><a class="dropdown-item" href="./SupplierAdd">Add Supplier</a></button>
            <button id="editStudentBtn" class="btn btn-secondary"><a class="dropdown-item" href="./SupplierDisplay">Show Supplier</a></button>
            <button id="addStudentBtn" class="btn btn-success"><a class="dropdown-item" href="./SupplierUpdate">Update Supplier</a></button>
            <button id="deleteStudentBtn" class="btn btn-danger"><a class="dropdown-item" href="./SupplierDelete">Delete Supplier</a></button>
        </div>

        <div id="responseContent" class="mt-4">
            <!-- Dynamic content will load here -->
        </div>
    </div>

</body>
</html>
