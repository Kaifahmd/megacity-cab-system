<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Booking - Home</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body{
            background: #212529;
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

            color: white;
            text-align: center;
            padding: 120px 20px;
        }
        .hero h1 {
            font-size: 3rem;
            font-weight: bold;
        }
        .hero p {
            font-size: 1.2rem;
            margin-bottom: 20px;
        }


        .feature-icon {
            font-size: 40px;
            color: #17a2b8;
        }


        .cta {
            background: #17a2b8;
            color: white;
            text-align: center;
            padding: 50px 20px;
            color: white;
        }
    </style>
</head>
<body>

<header class="sticky-top">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="#">Mega City Cab</a>
            <div class="d-flex">
                <a class="btn btn-outline-light btn-custom me-3" href="signup.jsp">Sign Up</a>
                <a class="btn btn-outline-light btn-custom" href="login.jsp">Login</a>
            </div>
        </div>
    </nav>
</header>

<!-- Hero Section -->
<section class="hero">
    <div class="container">
        <h1>Rent Your Dream Car Today</h1>
        <p>Find the perfect car for your journey at unbeatable prices.</p>
        <a href="signup.jsp" class="btn btn-light btn-lg btn-custom">Get Started</a>
    </div>
</section>

<!-- Features Section -->
<section class="container text-center py-5 text-light">
    <h2 class="mb-4">Why Choose Us?</h2>
    <div class="row">
        <div class="col-md-4">

            <h4>Wide Selection</h4>
            <p>Choose from a variety of cars to fit your needs and budget.</p>
        </div>
        <div class="col-md-4">

            <h4>Affordable Rates</h4>
            <p>Get the best deals on car rentals without hidden fees.</p>
        </div>
        <div class="col-md-4">

            <h4>Fast & Easy Booking</h4>
            <p>Book your car in minutes with our seamless process.</p>
        </div>
    </div>
</section>

<!-- CTA Section -->
<section class="cta">
    <div class="container">
        <h2>Ready to Book Your Car?</h2>
        <p>Sign up now and start your journey with ease.</p>
        <a href="signup.jsp" class="btn btn-light btn-lg btn-custom">Sign Up Now</a>
    </div>
</section>

</body>
</html>
