<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car List - Car Booking</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body {
            background: #212529;
            color: white;
        }

        .navbar {
            background: #212529;
            padding: 15px 30px;
        }
        .navbar-brand {
            font-size: 24px;
            font-weight: bold;
            color: white;
        }
        .navbar-brand:hover {
            color: #17a2b8;
        }
        .nav-link {
            color: white;
            font-weight: 500;
            transition: 0.3s;
        }
        .nav-link:hover {
            color: #17a2b8;
        }
        .btn-custom {
            border-radius: 30px;
            padding: 10px 20px;
            font-weight: 500;
            transition: 0.3s;
        }
        .btn-outline-light:hover {
            background: #17a2b8;
            border-color: #17a2b8;
        }

        .container {
            margin-top: 50px;
        }
        h1 {
            color: #17a2b8;
            text-align: center;
            margin-bottom: 30px;
        }
        .table {
            background: #343a40;
            color: white;
            border-radius: 10px;
            overflow: hidden;
        }
        .table thead {
            background: #17a2b8;
        }
        .action-links a {
            margin-right: 10px;
            text-decoration: none;
        }
        .action-links a.edit {
            color: #0d6efd;
        }
        .action-links a.delete {
            color: #dc3545;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">CarBooking</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">

                <!-- Added Buttons -->
                <div class="d-flex ms-3">
                    <a href="new" class="nav-link me-4">Add Car</a>
                    <a href="${pageContext.request.contextPath}/admin.jsp" class="nav-link">Back</a>
                </div>
            </div>
        </div>
    </nav>
</header>


<!-- Car List Section -->
<div class="container">
    <h1>Car List</h1>



    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Car ID</th>
                <th>Name</th>
                <th>Category</th>
                <th>Seats</th>
                <th>Type</th>
                <th>Rental (per day)</th>
                <th>Availability</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${listCar}">
                <tr>
                    <td>${car.carid}</td>
                    <td>${car.name}</td>
                    <td>${car.category}</td>
                    <td>${car.noSeats}</td>
                    <td>${car.type}</td>
                    <td>$${car.rentalAmountForDay}</td>
                    <td>
                        <span class="badge ${car.availability == 'Available' ? 'bg-success' : 'bg-danger'}">
                                ${car.availability}
                        </span>
                    </td>
                    <td class="action-links">
                        <a href="edit?carid=${car.carid}" class="edit">Edit</a>
                        <a href="delete?carid=${car.carid}" class="delete"
                           onclick="return confirm('Are you sure you want to delete this car?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



</body>
</html>
