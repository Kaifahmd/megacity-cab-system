<%@ page import="com.rental.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard - Car Booking</title>

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


        .hero {
            text-align: center;
            padding: 100px 20px;
        }
        .hero h1 {
            font-size: 2.5rem;
            font-weight: bold;
        }
        .hero p {
            font-size: 1.2rem;
            margin-bottom: 20px;
        }
        .name-cus{
            color: #17a2b8;
        }


        .admin-section {
            padding: 50px 0;
            text-align: center;
        }
        .admin-box {
            background: #343a40;
            padding: 20px;
            border-radius: 10px;
            margin: 10px;
        }
    </style>
</head>
<body>


<header class="sticky-top">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Mega City Cab</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/availablecars">Book</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/status">Status</a>
                    </li>
                </ul>
                <div class="d-flex ms-3">
                    <button class="btn btn-outline-light btn-custom" onclick="logout()">Logout</button>
                </div>
            </div>
        </div>
    </nav>
</header>


<section class="hero">
    <div class="container name-cus">
        <h1>Hello, <%= session.getAttribute("user") != null ? ((User) session.getAttribute("user")).getName() : "Admin" %>!</h1>
        <p>Welcome to your Dashboard. Book car with ease.</p>
    </div>
</section>


<section class="container text-center py-5 text-light">
    <h2 class="mb-4">Why Choose Us?</h2>
    <div class="row">
        <div class="col-md-4">

            <h4 class="name-cus">Wide Selection</h4>
            <p>Choose from a variety of cars to fit your needs and budget.</p>
        </div>
        <div class="col-md-4">

            <h4 class="name-cus">Affordable Rates</h4>
            <p>Get the best deals on car rentals without hidden fees.</p>
        </div>
        <div class="col-md-4">

            <h4 class="name-cus">Fast & Easy Booking</h4>
            <p>Book your car in minutes with our seamless process.</p>
        </div>
    </div>
</section>

<script>
    function logout() {
        sessionStorage.removeItem("user");
        window.location.href = "${pageContext.request.contextPath}/user/logout";
    }
</script>

</body>
</html>
