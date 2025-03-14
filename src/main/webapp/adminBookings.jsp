<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin - Car Booking Management</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
        .action-links a.view {
            color: #198754;
        }
        .action-links a.manage {
            color: #ffc107;
        }
    </style>
</head>
<body>
<header class="sticky-top">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Admin Panel</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" >
                <div class="d-flex ms-3">

                    <a href="${pageContext.request.contextPath}/admin.jsp" class="nav-link">Back</a>
                </div>
            </div>
        </div>
    </nav>
</header>
<div class="container">


    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-success alert-dismissible fade show">
                ${sessionScope.message}
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
        <% session.removeAttribute("message"); %>
    </c:if>

    <c:if test="${not empty sessionScope.error}">
        <div class="alert alert-danger alert-dismissible fade show">
                ${sessionScope.error}
            <button type="button" class="close" data-dismiss="alert">&times;</button>
        </div>
        <% session.removeAttribute("error"); %>
    </c:if>


    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>Booking ID</th>
                <th>Car ID</th>
                <th>User ID</th>
                <th>Rental Days</th>
                <th>Total Rental</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty bookings}">
                    <tr>
                        <td colspan="7" class="text-center">No bookings found</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="booking" items="${bookings}">
                        <tr>
                            <td>${booking.id}</td>
                            <td>${booking.carId}</td>
                            <td>${booking.userId}</td>
                            <td>${booking.rentalDays}</td>
                            <td>$${booking.totalRental}</td>
                            <td class="${booking.permission eq 'pending' ? 'status-pending' : 'status-approved'}">
                                    ${booking.permission}
                            </td>
                            <td>
                                <c:if test="${booking.permission eq 'pending'}">
                                    <form action="${pageContext.request.contextPath}/approveBooking" method="post">
                                        <input type="hidden" name="bookingId" value="${booking.id}">
                                        <button type="submit" class="btn btn-success btn-sm">Approve</button>
                                    </form>
                                </c:if>
                                <c:if test="${booking.permission eq 'approved'}">
                                    <span class="text-muted">Already Approved</span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
