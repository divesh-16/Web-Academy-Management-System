<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Organization Home Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
  
  <style>
    /* General Page Styling */
    body {
      background-color: #f8f9fa;
      font-family: Arial, sans-serif;
    }

	/*Navbar Starting */
	
    /* Navbar Styling */
    .navbar {
      background-color: #333;
    }

    /* Organization Logo */
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

    /* Dropdown Item Styling */
    .dropdown-item {
      font-weight: bold;
    }

    .dropdown-item:hover {
      background-color: #ff7f50;
      color: #fff;
    }

    /* Social Icon Styling */
    .social-icon {
      color: #fff;
      margin-left: 10px;
      font-size: 1.3em; /* Slightly larger icons */
      transition: color 0.3s;
    }

    .social-icon:hover {
      color: #ff7f50;
    }
    /*Navbar ending*/

    /* Carousel Styling */
    .carousel-item img {
      width: 100%;
      height: 500px;
      object-fit: cover;
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

  <!-- Navbar Section -->
  <nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand ml-3" href="index.html">
      <img src="resources/ISA.jpg" alt="Organization Logo"> <!-- Organization Logo -->
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div>
        <Label style="color: #f8f9fa; text-align: center; font-weight: bolder; font-size: larger;">Invictus Sports Academy</Label>
    </div>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto">
        <!-- Home Link -->
        <li class="nav-item">
          <a class="nav-link" href="index.html">Home</a>
        </li>
        <!-- Dropdown for Login Pages -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="loginDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Login
          </a>
          <div class="dropdown-menu" aria-labelledby="loginDropdown">
            <a class="dropdown-item" href="StudentLogin.html">Student Login</a>
            <a class="dropdown-item" href="CoachLogin.html">Teacher Login</a>
            <a class="dropdown-item" href="AdminLogin.html">Admin Login</a>
          </div>
        </li>
        <!-- About Us Link -->
        <li class="nav-item">
          <a class="nav-link" href="about.html">About Us</a>
        </li>
        <!-- Social Media Icons -->
        <li class="nav-item">
          <a class="nav-link social-icon" href="https://www.instagram.com/invictussportsacademy/?hl=en" target="_blank"><i class="fab fa-instagram"></i></a>
        </li>
        <li class="nav-item">
          <a class="nav-link social-icon" href="https://wa.me/9168991787" target="_blank"><i class="fab fa-whatsapp"></i></a>
        </li>
        <!-- Contact Email -->
        <li class="nav-item">
          <a class="nav-link" href="mailto:invictussportsacademy@gmail.com">invictussportsacademy@gmail.com</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="./OrderRequest">Try Accessories</a>
        </li>
      </ul>
    </div>
  </nav>
  
  
  
  
  
  
  
	  	<!-- Footer Section -->
	  <footer>
	    &copy; 2024 All Rights Reserved by Organization Name.
	  </footer>
	  <!-- Bootstrap JS, jQuery, and Popper.js -->
	  <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" integrity="sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q" crossorigin="anonymous"></script>
  </body>
</html>