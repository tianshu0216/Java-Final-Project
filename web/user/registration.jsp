

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Waste Reduction Platform - Registration</title>
    <link rel="stylesheet" href="../styles.css">
</head>

<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h2>Registration</h2>
                 <%
       
        String error = request.getParameter("error");
        if (error != null && error.equals("fail")) {
    %>
        <script>
            alert("Registration failed! Please try again!");
        </script>
    <%
        }
    %>
    <%
     if (error != null && error.equals("email_exists")) {
    %>
        <script>
            alert("Email already being used!");
        </script>
    <%
        }
    %>
    
        <!--<form id="registrationForm" method="POST">-->
        <form id="registrationForm" action="RegistrationFormServlet"  method="POST">

            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="userType">User Type:</label>
                <select id="userType" name="userType" required>
                    <option value="">Select User Type</option>
                    <option value="retailer">Retailer</option>
                    <option value="consumer">Consumer</option>
                    <option value="charitable_organization">Charitable Organization</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="location">Location</label>
                <select id="location" name="location" required>
                    <option value="">Select Location</option>
                    <option value="Alberta">Alberta</option>
                    <option value="British Columbia">British Columbia</option>
                    <option value="Manitoba">Manitoba</option>
                    <option value="New Brunswick">New Brunswick</option>
                    <option value="Newfoundland and Labrador">Newfoundland and Labrador</option>
                    <option value="Nova Scotia">Nova Scotia</option>
                    <option value="Ontario">Ontario</option>
                    <option value="Prince Edward Island">Prince Edward Island</option>
                    <option value="Quebec">Ontario</option>
                    <option value="Saskatchewan">Saskatchewan</option>
                </select>
            </div>
           
            <div class="form-group">
            <label for="subscribed">Subscribed:</label>
            <input type="checkbox" id="subscribed" name="subscribed">    
            </div>
            
            <button type="submit">Register</button>
        </form>
    </div>

    
</body>
</html>
