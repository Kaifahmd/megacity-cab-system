<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body {
            background: #212529;
            color: white;
            font-family: Arial, sans-serif;
        }

        .navbar {
            background: #343a40;
            padding: 15px 30px;
        }
        .navbar-brand, .nav-link {
            color: white !important;
        }
        .nav-link:hover {
            color: #17a2b8 !important;
        }


        .page-title {
            text-align: center;
            margin: 30px 0;
            font-size: 2rem;
            font-weight: bold;
        }

        .card-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .car-card {
            background: #343a40;
            border-radius: 10px;
            padding: 20px;
            width: 300px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            transition: transform 0.3s, box-shadow 0.3s;
            text-align: center;
        }

        .car-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 8px 16px rgba(0, 255, 255, 0.3);
        }

        .car-card h4 {
            color: #17a2b8;
            margin-bottom: 10px;
        }

        .car-info {
            font-size: 1rem;
            margin-bottom: 10px;
        }

        .book-btn {
            background: #17a2b8;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: 0.3s;
        }

        .book-btn:hover {
            background: #138496;
        }


        .back-btn {
            display: block;
            margin: 20px auto;
            text-align: center;
            color: #17a2b8;
            text-decoration: none;
            font-weight: bold;
            transition: 0.3s;
        }

        .back-btn:hover {
            color: #0a7b8c;
        }
    </style>
</head>
<body>


<header class="sticky-top">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Mega City Cab</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="booking.jsp">Back</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<!-- Page Title -->
<h1 class="page-title">Available Cars</h1>

<!-- Car List (Card Layout) -->
<div class="container">
    <div class="card-container">
        <c:forEach var="car" items="${availableListCar}">
            <div class="car-card">
                <h4>${car.name}</h4>
                <p class="car-info"><strong>Category:</strong> ${car.category}</p>
                <p class="car-info"><strong>Seats:</strong> ${car.noSeats}</p>
                <p class="car-info"><strong>Type:</strong> ${car.type}</p>
                <p class="car-info"><strong>Rental (per day):</strong> $${car.rentalAmountForDay}</p>
                <p class="car-info"><strong>Availability:</strong> ${car.availability}</p>

                <!-- Book Form -->
                <form action="availablecars" method="POST">
                    <input type="hidden" name="carId" value="${car.carid}">
                    <input type="hidden" name="carName" value="${car.name}">
                    <input type="hidden" name="carCategory" value="${car.category}">
                    <input type="hidden" name="carSeats" value="${car.noSeats}">
                    <input type="hidden" name="carType" value="${car.type}">
                    <input type="hidden" name="carRental" value="${car.rentalAmountForDay}">
                    <input type="hidden" name="carAvailability" value="${car.availability}">
                    <input type="hidden" name="action" value="book">
                    <button type="submit" class="book-btn">Book Now</button>
                </form>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Back Button -->
<a href="booking.jsp" class="back-btn">← Back to Booking</a>

</body>
</html>
