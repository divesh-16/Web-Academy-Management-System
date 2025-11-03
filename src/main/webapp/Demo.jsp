<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding to Batch</title>
</head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
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
    <div class="container py-5">
      <div class="btn-group mt-5" style="display: flex; justify-content: center; gap: 1rem; margin-bottom: 2rem; flex-wrap: wrap;">
        <button class="btn btn-primary" onclick="showDetails('students')">Student Details</button>
        <button class="btn btn-success" onclick="showDetails('coaches')">Coaches</button>
        <button class="btn btn-warning" onclick="showDetails('sports')">Sports</button>
      </div>
      <form onsubmit="event.preventDefault(); alert('Change Password clicked. Implement logic here.');" class="text-center mb-5">
        <input class="btn btn-danger" type="submit" value="Change Password">
      </form>
      <div class="table-responsive table-container">
        <table class="table table-striped table-hover">
          <thead class="table-dark">
            <tr id="table-header">
              <th>Field</th>
              <th>Details</th>
            </tr>
          </thead>
          <tbody id="table-body">
            <!-- Data will be dynamically inserted here -->
          </tbody>
        </table>
      </div>
      <script>
        const data = {
          students: [
            { field: "Name", details: 'Divesh' },
            { field: "ID", details: '101' },
            { field: "Address", details: 'Pune' },
            { field: "Phone", details: '0123654789' }
          ],
          coaches: [
            { field: "Coach Name", details: 'AAA' },
            { field: "Phone no", details: '78996541230' },
            { field: "Specialization", details: 'football' }
          ],
          sports: [
            { field: "Sport", details: 'football' },
            { field: "Coach", details: 'AAA' }
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

        window.onload = () => showDetails('students');
      </script>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
