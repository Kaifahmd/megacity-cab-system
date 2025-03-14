<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${car == null ? "Create New Car" : "Edit Car"}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to right, #5D5D63FF, #222121FF);
        }
        .car-form-container {
            max-width: 500px;
            width: 100%;
            padding: 30px;
            background: #7d7d80;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
            margin-top: 300px;
            margin-bottom: 200px;
        }
    </style>
</head>
<body>

<div class="car-form-container my-8 " >
    <h2 class="mb-4">${car == null ? "Create New Car" : "Edit Car"}</h2>
    <form action="${car == null ? 'insert' : 'update'}" method="post">
        <input type="hidden" name="carid" value="${car != null ? car.carid : ''}">

        <div class="mb-3 text-start">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="${car != null ? car.name : ''}" required>
        </div>

        <div class="mb-3 text-start">
            <label class="form-label">Category</label>
            <input type="text" name="category" class="form-control" value="${car != null ? car.category : ''}" required>
        </div>

        <div class="mb-3 text-start">
            <label class="form-label">No of Seats</label>
            <input type="text" name="noSeats" class="form-control" value="${car != null ? car.noSeats : ''}" required>
        </div>

        <div class="mb-3 text-start">
            <label class="form-label">Type</label>
            <input type="text" name="type" class="form-control" value="${car != null ? car.type : ''}" required>
        </div>

        <div class="mb-3 text-start">
            <label class="form-label">Amount</label>
            <input type="text" name="rentalAmountForDay" class="form-control" value="${car != null ? car.rentalAmountForDay : ''}" required>
        </div>

        <div class="mb-3 text-start">
            <label class="form-label">Availability</label>
            <input type="text" name="availability" class="form-control" value="${car != null ? car.availability : ''}" required>
        </div>

        <button type="submit" class="btn btn-secondary w-100">${car == null ? "Save" : "Update"}</button>
    </form>

    <a href="${pageContext.request.contextPath}/car/list" class="btn btn-dark mt-3 w-100">Back to Car List</a>
</div>

</body>
</html>
