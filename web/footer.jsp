<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Footer</title>
    <style>
        /* CSS FOR Footer */
        footer {
            background-image: url('footer-bg.jpg'); 
            background-size: cover; 
            color: #333333; 
            text-align: center;
            padding: 15px 0 20px;
            position: relative;
            width: 100%;                       
        }
        .footer-links {
            margin-top: 20px;
        }
        .footer-links a {
            color: #333333; 
            text-decoration: none;
            margin: 0 10px;
        }
        p{
             color: #333333;
        }
    </style>
</head>
<body>
    <main>
        <!-- Main content goes here -->
    </main>
    <footer>
        <p>&copy; <%= new java.util.Date().getYear() + 1900 %>  &nbsp;&nbsp; &nbsp;&nbsp; Food Waste Reduction Platform</p>
        <div class="footer-links">
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Service</a>
            <a href="contactUs.jsp">Contact Us</a>
        </div>
    </footer>
</body>
</html>
