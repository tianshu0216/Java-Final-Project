<%-- 
    Document   : orderReview
    Created on : April 2, 2024
    Author     : Tianshu&Feiling
--%>

<%@page import="java.util.List"%>
<%@page import="dataaccesslayer.FoodDAOImpl"%>
<%@page import="dataaccesslayer.FoodDAO"%>
<%@page import="model.Food"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@page import="java.math.BigDecimal"%>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // If not authenticated, redirect to landing page
        response.sendRedirect("http://localhost:8080/FWRP");
    }
%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Review</title>
        <link rel="stylesheet" href="../styles.css">
        <style>
            .submit-area {
                display: flex;
                justify-content: space-between;
                padding: 50px;
                margin-bottom: 100px;
                margin-top: 30px;
            }
            .submit-area button {
                flex: 1;
                margin: 0 10px;
                padding: 10px 20px;
                font-size: 1em;
                height: 50px;
            }

            .submit-area button:first-child {
                margin-right: 20px;
            }

            /* Specifically target the last button if needed */
            .submit-area button:last-child {
                margin-left: 20px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
            <h1>Order Information</h1>
            <form action="CustomerPurchaseServlet" method="post" onsubmit="return confirmPurchase();">

                <div class="table-container">
                    <table>
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Subtotal</th>

                        </tr>

                        <%
                            double totalPrice = 0;
                            int totalQuantity = 0;
                            FoodDAO itemDAO = new FoodDAOImpl();
                            Map<Integer, Integer> quantities = new HashMap<>();

                            List<Food> items = itemDAO.getSurplusItems();
                            for (Food item : items) {
                                String quantityParam = "quantity_" + item.getId();
                                String quantityString = request.getParameter(quantityParam);
                                if (quantityString != null && !quantityString.isEmpty()) {
                                    int quantity = Integer.parseInt(quantityString);
                                    // Add to the map if the quantity is greater than 0
                                    if (quantity > 0) {
                                        quantities.put(item.getId(), quantity);

                                        System.out.println("Item ID: " + item.getId() + ", Quantity: " + quantity);
                                    }
                                }
                            }

                            boolean anyQuantitySelected = quantities.values().stream().anyMatch(qty -> qty > 0);
                            if (!anyQuantitySelected) {
                                System.out.println("error not selected");
                                request.getSession().setAttribute("customererror", "You have not selected any items.");
                                response.sendRedirect("ItemListCustomerServlet");
                            }

                            for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
                                int itemId = entry.getKey();
                                int requestedQuantity = entry.getValue();
                                // Fetch the current stock from the database
                                Food item = itemDAO.getItemById(itemId);
                                if (item == null || item.getInventory()< requestedQuantity) {
                                    System.out.println("Insufficient stock for Item ID: " + itemId);
                                    break;
                                } else {
                                    double lineTotal = item.getPrice()*entry.getValue();
                                    totalQuantity += entry.getValue();
                                    totalPrice = totalPrice+ lineTotal;
                        %>
                        <tr>
                            <td><%= item.getName()%></td>
                            <td>$<%= item.getPrice()%></td>
                            <td>
                                <%= entry.getValue()%>
                                <!-- Hidden field to send the quantity to the CustomerPurchaseServlet -->
                                <input type="hidden" name="quantity_<%= item.getId()%>" value="<%= entry.getValue()%>" />
                            </td>
                            <td><%= lineTotal%></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        <!-- Totals row -->
                        <tr>
                            <td colspan="2" style="text-align:right;"><strong>Total</strong></td>
                            <td><strong><%= totalQuantity%></strong></td>
                            <td><strong>$<%= totalPrice%></strong></td>
                        </tr>
                        
                    </table>
                </div>
                <div class="submit-area">
                    <button type="button" onclick="history.back()">Go Back</button>
                    <button type="submit">Proceed and Pay</button>
                </div>
        </div>
    </form>
    <jsp:include page="../footer.jsp" />
</body>

<script>
    function confirmPurchase() {
        var totalCost = <%= totalPrice%>; 
        var confirmationMessage = "You are going to buy " + <%= totalQuantity%> + " item(s) with a total cost of $" + totalCost.toFixed(2) + ". Are you sure?";
        return confirm(confirmationMessage);
    }
</script>
</html>