<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.rental.model.CarBooking" %>
<html>
<head>
    <title>Car Bookings</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body {

            background-color: #212529; /* Dark background */
            color: #e0e0e0; /* Light text */
            margin: 0;
            padding: 0;
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
        h2, h3 {
            color: #ffffff; /* White color for headings */
            text-align: center;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #343a40; /* Dark table background */
        }
        th, td {
            border: 1px solid #444444; /* Lighter borders */
            padding: 12px;
            text-align: left;
            color: #ffffff; /* White text */
        }
        th {
            background-color: #495057; /* Darker header background */
            font-size: 18px;
        }
        td {
            background-color: #343a40; /* Slightly lighter rows */
        }
        tr:hover {
            background-color: #495057; /* Row hover effect */
        }
        .no-bookings {
            text-align: center;
            color: #ccc; /* Light grey text for empty states */
            font-style: italic;
        }
        /* Button Style */
        .btn-custom {
            background-color: #17a2b8;
            color: white;
            border-radius: 30px;
            padding: 10px 20px;
            font-weight: 500;
            text-decoration: none;
            transition: 0.3s;
        }
        .btn-custom:hover {
            background-color: #138496;
        }



        /* Navbar */

    </style>
</head>
<body>

<!-- Navbar -->

<header class="sticky-top">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Car Booking</a>
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

<!-- Pending Bookings Table -->
<h3 style="margin-top: 30px">Pending Bookings</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Car ID</th>
        <th>User ID</th>
        <th>Rental Days</th>
        <th>Total Rental</th>
        <th>Permission</th>
    </tr>
    <%
        List<CarBooking> pendingBookings = (List<CarBooking>) request.getAttribute("pendingBookings");
        if (pendingBookings != null && !pendingBookings.isEmpty()) {
            for (CarBooking booking : pendingBookings) {
    %>
    <tr>
        <td><%= booking.getId() %></td>
        <td><%= booking.getCarId() %></td>
        <td><%= booking.getUserId() %></td>
        <td><%= booking.getRentalDays() %></td>
        <td><%= booking.getTotalRental() %></td>
        <td><%= booking.getPermission() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr class="no-bookings">
        <td colspan="6">No pending bookings found.</td>
    </tr>
    <%
        }
    %>
</table>

<!-- Approved Bookings Table -->
<h3>Approved Bookings</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Car ID</th>
        <th>User ID</th>
        <th>Rental Days</th>
        <th>Total Rental</th>
        <th>Permission</th>
    </tr>
    <%
        List<CarBooking> approvedBookings = (List<CarBooking>) request.getAttribute("approvedBookings");
        if (approvedBookings != null && !approvedBookings.isEmpty()) {
            for (CarBooking booking : approvedBookings) {
    %>
    <tr>
        <td><%= booking.getId() %></td>
        <td><%= booking.getCarId() %></td>
        <td><%= booking.getUserId() %></td>
        <td><%= booking.getRentalDays() %></td>
        <td><%= booking.getTotalRental() %></td>
        <td><%= booking.getPermission() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr class="no-bookings">
        <td colspan="6">No approved bookings found.</td>
    </tr>
    <%
        }
    %>
</table>



</body>
</html>
