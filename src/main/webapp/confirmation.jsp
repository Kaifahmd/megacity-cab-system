<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.rental.model.Car" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Booking Confirmation</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    body {
      background: #212529;
      color: white;
      padding: 30px;
    }

    .container {
      max-width: 900px;
      margin: 0 auto;
      background-color: #343a40;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 2px 10px rgba(255, 255, 255, 0.1);
    }

    h1, h2, h3 {
      color: #17a2b8;
      text-align: center;
    }

    p {
      font-size: 16px;
      color: #ccc;
    }

    .card-details {
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 20px;
      margin-bottom: 20px;
    }

    .button-container {
      text-align: center;
      margin-top: 30px;
    }

    button {
      background-color: #17a2b8;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 5px;
      font-size: 18px;
      cursor: pointer;
      transition: 0.3s ease;
    }

    button:hover {
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
  </style>
</head>
<body>

<h1>Booking Confirmation</h1>

<div class="container">
  <c:if test="${not empty requestScope.bookedCar}">


    <div class="card-details">
      <p><strong>Name:</strong> ${requestScope.bookedCar.name}</p>
      <p><strong>Category:</strong> ${requestScope.bookedCar.category}</p>
      <p><strong>Seats:</strong> ${requestScope.bookedCar.noSeats}</p>
      <p><strong>Type:</strong> ${requestScope.bookedCar.type}</p>
      <p><strong>Rental per Day:</strong> $${requestScope.bookedCar.rentalAmountForDay}</p>
      <p><strong>Availability:</strong> ${requestScope.bookedCar.availability}</p>
    </div>

    <h3>Total Rental Cost for ${requestScope.rentalDays} days: $${requestScope.totalRental}</h3>

    <div class="button-container">
      <form action="confirmBooking" method="post">
        <input type="hidden" name="carId" value="${requestScope.bookedCar.carid}" />
        <input type="hidden" name="rentalDays" value="${requestScope.rentalDays}" />
        <input type="hidden" name="totalRental" value="${requestScope.totalRental}" />
        <button type="submit">Confirm Booking</button>
      </form>
    </div>
  </c:if>

  <c:if test="${not empty requestScope.error}">
    <p style="color:red; text-align: center;">${requestScope.error}</p>
  </c:if>

  <div class="button-container">
    <a href="${pageContext.request.contextPath}/booking.jsp" class="back-btn">Back to Home</a>
  </div>
</div>

</body>
</html>