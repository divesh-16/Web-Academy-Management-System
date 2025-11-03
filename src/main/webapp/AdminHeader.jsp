<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Organization Home Page</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  
<style type="">

.navbar {
      background-color: #333;
    }
.navbar-brand img {
      height: 60px; /* Increased height for bigger logo */
      width: auto;
    }
    
    
    /* Navbar Links */
    .navbar-nav .nav-link {
      color: #fff;
      font-weight: bold;
      font-size: 1.1em; /* Larger font for bold effect */
      transition: color 0.3s;
    }

    .navbar-nav .nav-link:hover {
      color: #ff7f50;
    }
    
   
    

 /* Footer Styling */
    footer {
      background-color: #333;
      color: #fff;
      padding: 15px;
      text-align: center;
      position: fixed;
      width: 100%;
      bottom: 0;
      font-size: 0.9em;
    }

</style>

  
</head>
<body>

  
 <nav class="navbar navbar-dark fixed-top">
  <div class="container-fluid">
     <a class="navbar-brand" href="#">
      <img src="resources/ISA.jpg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
      Invictus Sports Academy
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="offcanvas bg-dark offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
      <div class="offcanvas-header bg-light">
        <h5 class="offcanvas-title bg-light" id="offcanvasNavbarLabel">ISA</h5>
        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
      </div>
      <div class="offcanvas-body">
        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="index.html">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./AdminLogout">Logout</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="AdminWindow.jsp">Admin Dashboard</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="./StudentPanel">Student</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="./CoachPanel">Coach</a>
          </li>
          
           <li class="nav-item">
            <a class="nav-link" href="./GenerateReports">Reports</a>
          </li>
          
          <li class="nav-item">
            <a class="nav-link" href="./AdminUpdate">AdminUpdate</a>
          </li>
         
          
          <!-- Accessories Drop Down -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Accessories
            </a>
            <ul class="dropdown-menu dropdown-menu-dark">
              <li><a class="dropdown-item text-danger" href="./AccessoriesAdd">Add Accessories</a></li>
              <li><a class="dropdown-item" href="./AccessoriesDisplay">Display Accessories</a></li>
              <li><a class="dropdown-item" href="./AccessoriesUpdate">Update Accessories</a></li>
              <li><a class="dropdown-item" href="./AccessoriesDelete">Delete Accessories</a></li>
            </ul>
          </li>
          
          <!-- Batch Drop Down -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Batch
            </a>
            <ul class="dropdown-menu dropdown-menu-dark">
              <li><a class="dropdown-item text-danger" href="./BatchAdd">Add Batch</a></li>
              <li><a class="dropdown-item" href="./BatchDisplay">Display Batch</a></li>
              <li><a class="dropdown-item" href="./BatchUpdate">Update Batch</a></li>
              <li><a class="dropdown-item" href="./BatchDelete">Delete Batch</a></li>
            </ul>
          </li>
          
          <!-- Supplier Drop Down -->
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Supplier
            </a>
            <ul class="dropdown-menu dropdown-menu-dark">
              <li><a class="dropdown-item text-danger" href="./SupplierAdd">Add Supplier</a></li>
              <li><a class="dropdown-item" href="./SupplierDisplay">Display Supplier</a></li>
              <li><a class="dropdown-item" href="./SupplierUpdate">Update Supplier</a></li>
              <li><a class="dropdown-item" href="./SupplierDelete">Delete Supplier</a></li>
            </ul>
          </li>
        
        </ul>
        
      </div>
    </div>
  </div>
</nav>
  
  
	  	<!-- Footer Section -->
	  <footer>
	    &copy; 2024 All Rights Reserved by Organization Name.
	  </footer>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
</body>
</html>