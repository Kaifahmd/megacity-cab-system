<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.rental.model.Car" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Your Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #212529;
            color: white;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            margin-top: 50px;
        }

        .card {
            background: #343a40;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            padding: 20px;
            margin-bottom: 30px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 255, 255, 0.3);
        }

        .card h2, .card h3 {
            color: #17a2b8;
        }

        .card p {
            font-size: 1.1rem;
        }

        .btn-primary {
            background-color: #17a2b8;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #138496;
        }

        .back-btn {
            display: block;
            margin: 20px auto;
            text-align: center;
            color: #17a2b8;
            text-decoration: none;
            font-weight: bold;
            transition: 0.3s ease;
        }

        .back-btn:hover {
            color: #0a7b8c;
        }

        .car-details {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .form-group input {
            background-color: #495057;
            color: white;
        }

        .form-group label {
            color: #adb5bd;
        }

        @media (max-width: 768px) {
            .car-details {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="text-center mb-4">Confirm Your Booking</h1>

    <c:if test="${not empty sessionScope.bookedCar}">
        <div class="card">
            <h2>Car Details</h2>

            <div class="car-details text-light">
                <div>
                    <p><strong>Name:</strong> ${sessionScope.bookedCar.name}</p>
                    <p><strong>Category:</strong> ${sessionScope.bookedCar.category}</p>
                    <p><strong>Seats:</strong> ${sessionScope.bookedCar.noSeats}</p>
                </div>
                <div>
                    <p><strong>Type:</strong> ${sessionScope.bookedCar.type}</p>
                    <p><strong>Rental per Day:</strong> $${sessionScope.bookedCar.rentalAmountForDay}</p>
                    <p><strong>Availability:</strong> ${sessionScope.bookedCar.availability}</p>
                </div>
            </div>

            <form action="confirmBooking" method="POST">
                <div class="form-group">
                    <label for="rentalDays">Enter Number of Days:</label>
                    <input type="number" class="form-control" name="rentalDays" id="rentalDays" min="1" required>
                </div>
                <input type="hidden" name="carId" value="${sessionScope.bookedCar.carid}">
                <input type="hidden" name="carName" value="${sessionScope.bookedCar.name}">
                <input type="hidden" name="carType" value="${sessionScope.bookedCar.type}">
                <input type="hidden" name="carCategory" value="${sessionScope.bookedCar.category}">
                <input type="hidden" name="carAvailability" value="${sessionScope.bookedCar.availability}">
                <input type="hidden" name="carRentalAmount" value="${sessionScope.bookedCar.rentalAmountForDay}">
                <button type="submit" class="btn btn-primary mt-4">Confirm Booking</button>
            </form>

            <c:if test="${not empty param.rentalDays}">
                <h3>Total Amount: $${param.rentalDays * sessionScope.bookedCar.rentalAmountForDay}</h3>
            </c:if>
        </div>

        <a href="${pageContext.request.contextPath}/availablecars" class="back-btn">← Back to Booking</a>
    </c:if>
</div>

</body>
</html>
