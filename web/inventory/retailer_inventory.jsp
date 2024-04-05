<%-- 
    Document   : retailer_inventory
    Created on : Apr 4, 2024, 8:38:19 p.m.
    Author     : ty_huang
--%>

<%@page import="java.util.List"%>
<%@page import="model.Food"%>
<%@page import="dataaccesslayer.FoodDAOImpl"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Retailer Inventory</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h1>Your Inventory</h1>
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Inventory</th>
                    <th>Price</th>
                    <th>Expiration Date</th>
                    <th>Demand</th>
                    <th>Donation</th>
                    <th>Modification</th>
                </tr>
            </thead>
            <tbody>
                <%
                    User user = (User) session.getAttribute("user");
                    if(user != null) {
                        FoodDAOImpl dao = new FoodDAOImpl();
                        List<Food> foods = dao.getFoodsByRetailerId(user.getId());
                        for(Food food : foods) {
                %>
                <tr>
                    <td><%= food.getName() %></td>
                    <td><%= food.getInventory() %></td>
                    <td>$<%= String.format("%.2f", food.getPrice()) %></td>
                    <td><%= food.getExpirationDate().toString() %></td>
                    <td><%= food.getDemand() %></td>
                    <td><%= food.getIsDonation() ? "Yes" : "No" %></td>
                    <td>
                        <a href="updateFood.jsp?foodId=<%= food.getId() %>">Modify</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                        // User not logged in or session expired
                        response.sendRedirect("login.jsp");
                    }
                %>
            </tbody>
        </table>
            <div class="submit-area">
            <button type="button" onclick="history.back()">Go Back</button>
        </div>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>
