<%-- 
    Document   : updateFood.jsp
    Created on : Apr 5, 2024, 1:01:50 a.m.
    Author     : ty_huang
--%>

<%@page import="model.Food"%>
<%@page import="dataaccesslayer.FoodDAOImpl"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String foodIdStr = request.getParameter("foodId");
    int foodId = Integer.parseInt(foodIdStr);
    FoodDAOImpl dao = new FoodDAOImpl();
    Food food = dao.getItemById(foodId); 
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Food Item</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    
    <div class="container">
         <h1>Inventory Management</h1>
        <section id="inventorySection">
            <h2>Modify An Item</h2>
        
    <form action="UpdateFoodServlet" method="post">
        <input type="hidden" name="id" value="<%= foodId %>">
        <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" name="name" value="<%= food.getName() %>" required><br>
        </div><!-- comment -->
        <div class="form-group">
        <label for="inventory">Inventory:</label>
        <input type="number" name="inventory" value="<%= food.getInventory() %>" required><br>
        </div>
        <label for="price">Price:</label>
        <input type="text" name="price" value="<%= food.getPrice() %>" required><br>
         <div class="form-group">
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" name="expirationDate" value="<%= food.getExpirationDate().toString() %>" required><br>
         </div>
         <div class="form-group">
        <label for="demand">Demand:</label>
        <input type="number" name="demand" value="<%= food.getDemand() %>" required><br>
         </div>
         <div class="form-group">
        <label for="isDonation">Donation:</label>
        <input type="checkbox" name="isDonation" <%= food.getIsDonation() ? "checked" : "" %>><br>
         </div>
        <button type="submit">Modify An Item</button> 
         <button type="button" onclick="history.back()">Go Back</button>
        
    </form>
    </section>
   </div>
        <jsp:include page="../footer.jsp" />
</body>
</html>
