<%-- 
    Document   : retail_manage
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
  <jsp:include page="../header.jsp" />

    <div class="container">
        <h1>Inventory Management</h1>
        <section id="inventorySection">
            <h2>Add New Item</h2>
            <% 
                // Assume "user" session attribute contains the current user's object
                model.User user = (model.User) session.getAttribute("user");
                if("POST".equalsIgnoreCase(request.getMethod()) && user != null) {
                    String itemName = request.getParameter("itemName");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    int demand = Integer.parseInt(request.getParameter("demand"));
                    Date expirationDate = Date.valueOf(request.getParameter("expirationDate")); // Input format is YYYY-MM-DD
                    boolean isDonation = request.getParameter("isDonation") != null;
                    
                    // Calculate isSurplus
                    boolean isSurplus = quantity > 1.2 * demand;

                    Food food = new Food();
                    food.setName(itemName);
                    food.setInventory(quantity);
                    food.setPrice(price);
                    food.setDemand(demand);
                    food.setExpirationDate(expirationDate);
                    food.setIsDonation(isDonation);
                 //   food.setIsSurplus(isSurplus);
                    food.setRetailerId(user.getId());
                    
                    FoodDAOImpl foodDao = new FoodDAOImpl();
                    if(foodDao.addItem(food)) {
                        out.print("<p>Item added successfully!</p>");
                    } else {
                        out.print("<p>Error adding item.</p>");
                    }
                }
            %>
            <form id="inventoryForm" class="form" method="post">
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
                    <label><input type="checkbox" name="isDonation"> Is this for Donation?</label>
                </div>
                <button type="submit">Add Item</button>
            </form>
        </section>
    </div>
             <jsp:include page="../footer.jsp" />
</body>
</html>
