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
    Food food = dao.getItemById(foodId); // 假设你已经实现了这个方法
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Food Item</title>
</head>
<body>
    <form action="UpdateFoodServlet" method="post">
        <input type="hidden" name="id" value="<%= foodId %>">
        <label for="name">Name:</label>
        <input type="text" name="name" value="<%= food.getName() %>" required><br>
        <label for="inventory">Inventory:</label>
        <input type="number" name="inventory" value="<%= food.getInventory() %>" required><br>
        <label for="price">Price:</label>
        <input type="text" name="price" value="<%= food.getPrice() %>" required><br>
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" name="expirationDate" value="<%= food.getExpirationDate().toString() %>" required><br>
        <label for="demand">Demand:</label>
        <input type="number" name="demand" value="<%= food.getDemand() %>" required><br>
        <label for="isDonation">Donation:</label>
        <input type="checkbox" name="isDonation" <%= food.getIsDonation() ? "checked" : "" %>><br>
        <input type="submit" value="Update">
    </form>
</body>
</html>
