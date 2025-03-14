<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(to right, #5D5D63FF, #222121FF);
        }
        .signup-container {
            max-width: 400px;
            width: 100%;
            padding: 30px;
            background: #7d7d80;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
    </style>
</head>
<body>

<div class="signup-container">
    <h2 class="mb-4 ">Sign Up</h2>
    <form action="signup" method="POST">
        <div class="mb-3 text-start">
            <label for="name" class="form-label">Name</label>
            <input type="text" id="name" name="name" class="form-control" required>
        </div>
        <div class="mb-3 text-start">
            <label for="email" class="form-label">Email</label>
            <input type="email" id="email" name="email" class="form-control" required>
        </div>
        <div class="mb-3 text-start">
            <label for="idNo" class="form-label">ID Number</label>
            <input type="text" id="idNo" name="idNo" class="form-control" required>
        </div>
        <div class="mb-3 text-start">
            <label for="password" class="form-label">Password</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-secondary w-100">Sign Up</button>
    </form>
</div>

</body>
</html>
