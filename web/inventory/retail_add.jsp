<%-- 
    Document   : retail_add
    Created on : Apr 4, 2024, 9:11:44 p.m.
    Author     : ty_huang
--%>
<%@ page import="model.Food"%>
<%@ page import="java.util.*,model.Food,dataaccesslayer.FoodDAOImpl"%>
<%@ page import="java.sql.Date"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management</title>
    <link rel="stylesheet" href="../styles.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header class="navbar">
        <div class="logo">Your Logo</div>
        <nav>
            <a href="../index.jsp">Home</a>
            <a href="http://localhost:8080/FWRP/user/registration.jsp">Registration</a>   
            <a href="http://localhost:8080/FWRP/user/login.jsp">Login</a>    
        </nav>
    </header>

    <div class="container">
        <h1>Inventory Management</h1>
        <section id="inventorySection">
            <h2>Add New Item</h2>
            <%
            String successMessage = request.getParameter("success");
            String errorMessage = request.getParameter("error");
            if ("true".equals(successMessage)) {
                out.println("<p>Item added successfully!</p>");
            } else if ("true".equals(errorMessage)) {
                out.println("<p>Error adding item.</p>");
            }
            %>
            <form id="inventoryForm" class="form" method="post" action="AddItemServlet">
                <div class="form-group">
                    <label for="itemName">Item Name:</label>
                    <input type="text" id="itemName" name="itemName" required>
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity:</label>
                    <input type="number" id="quantity" name="quantity" required>
                </div>
                <div class="form-group">
                    <label for="price">Price:</label>
                    <input type="text" id="price" name="price" required>
                </div>
                <div class="form-group">
                    <label for="demand">Demand:</label>
                    <input type="number" id="demand" name="demand" required>
                </div>
                <div class="form-group">
                    <label for="expirationDate">Expiration Date:</label>
                    <input type="date" id="expirationDate" name="expirationDate" required>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name="isDonation"> Is Donation?</label>
                </div>
                <button type="submit">Add Item</button>
                <div class="submit-area">
                <button type="button" onclick="history.back()">Go Back</button>
                </div>
            </form>
        </section>
    </div>
</body>
</html>