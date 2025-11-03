<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e9ecef;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .change-password-card {
            background: #343a40;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            color: #ffffff;
        }
        .btn-custom {
            background-color: #0056b3;
            color: white;
        }
        .btn-custom:hover {
            background-color: #003d80;
        }
        .form-control {
            background-color: #495057;
            color: #ffffff;
            border: none;
        }
        .form-control:focus {
            border-color: #0056b3;
            box-shadow: 0 0 5px rgba(0, 86, 179, 0.5);
        }
        label {
            color: #ced4da;
        }
    </style>
</head>
<body>

<div class="change-password-card">
    <h3 class="text-center">Change Password</h3>
    <form action="./StudentChangePassword" method="post">
    	<div class="form-group">
            <label for="sid">StudentID</label>
            <input name="sid" type="number" class="form-control" id="currentPassword" placeholder="Enter current password" required>
        </div>
        <div class="form-group">
            <label for="currentPassword">Current Password</label>
            <input name="currentPassword" type="password" class="form-control" id="currentPassword" placeholder="Enter current password" required>
        </div>
        <div class="form-group">
            <label for="newPassword">New Password</label>
            <input name="newPassword" type="password" class="form-control" id="newPassword" placeholder="Enter new password" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm New Password</label>
            <input name="confirmPassword" type="text" class="form-control" id="confirmPassword" placeholder="Confirm new password" required>
        </div>
        <button type="submit" class="btn btn-custom btn-block">Update Password</button>
    </form>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
