<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      .table-container {
        max-height: 300px;
        overflow-y: auto;
      }
      .table-responsive {
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="btn-group mt-5" style="display: flex; justify-content: center; gap: 1rem; margin-bottom: 2rem; flex-wrap: wrap;">
        <button class="btn btn-primary" onclick="showDetails('students')">Student Details</button>
        <button class="btn btn-success" onclick="showDetails('coaches')">Coaches</button>
        <button class="btn btn-warning" onclick="showDetails('sports')">Sports</button>
      </div>

      <form action="StudentChangePassword.jsp" method="post" class="text-center mb-5">
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

      <%
        // Example data creation (use actual database/service code in production)
        Map<String, String> student = new LinkedHashMap<String, String>();
        student.put("Name", "Divesh Shewale");
        student.put("ID", "101");
        student.put("Address", "Pune");
        student.put("Phone", "0123654789");

        Map<String, String> coach = new LinkedHashMap<String, String>();
        coach.put("Coach Name", "AAA");
        coach.put("Phone no", "78996541230");
        coach.put("Specialization", "football");

        Map<String, String> sport = new LinkedHashMap<String, String>();
        sport.put("Sport", "football");
        sport.put("Coach", "AAA");
      %>

      <script>
        // Read data from JSP variables and pass to JS for interactive table
        const data = {
          students: [
            <% for(Map.Entry<String,String> entry : student.entrySet()) { %>
              { field: "<%= entry.getKey() %>", details: "<%= entry.getValue() %>" },
            <% } %>
          ],
          coaches: [
            <% for(Map.Entry<String,String> entry : coach.entrySet()) { %>
              { field: "<%= entry.getKey() %>", details: "<%= entry.getValue() %>" },
            <% } %>
          ],
          sports: [
            <% for(Map.Entry<String,String> entry : sport.entrySet()) { %>
              { field: "<%= entry.getKey() %>", details: "<%= entry.getValue() %>" },
            <% } %>
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
