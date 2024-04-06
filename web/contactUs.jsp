<%-- 
    Document   : contact us
    Created on : Apr 5, 2024, 11:39:26 p.m.
    Author     : Sahou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contact Us</title>
    <link rel="stylesheet" href="styles.css">
    
    <style>
        /* CSS for centering the form and styling input fields */
        form {
            max-width: 500px; /* Limit the form width */
            margin: 0 auto; /* Center the form horizontally */
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type=text], input[type=email] {
            width: 50%; /* Make these fields smaller */
            padding: 5px; /* Smaller padding */
            margin: 5px 0; /* Smaller margin */
        }

        textarea {
            width: 100%; /* Make textarea larger */
            height: 200px; /* Set a fixed height for the textarea */
            padding: 10px; /* Larger padding */
            margin: 10px 0; /* Maintain margin */
        }
    </style>
</head>
<body>
    <h1>Contact Us</h1>
    <form action="contact" method="POST">
        <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" required><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br>

        <label for="message">Message:</label><br>
        <textarea id="message" name="message" required></textarea><br>

        <input type="submit" value="Send">
    </form>
   <!-- Button to go back to the home page -->
    <button onclick="window.location.href='index.jsp';">Home</button>
</body>
</html>
