<%-- 
    Document   : charity
    Created on : Mar 25, 2024, 11:27:28 PM
    Author     : Qina&Kaiwen
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Food" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Charity Claim Food</title>
        <link rel="stylesheet" href="../styles.css">
    </head>

    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
            <h2>Available Items for Claim</h2>
            <form action="PurchaseServlet" method="post" id="purchaseForm">
                 <% String errorMessage = (String) session.getAttribute("error");
                    if (errorMessage != null) {
                %>
                <script>alert("<%= errorMessage%>");</script>
                <%
                        session.removeAttribute("error"); // Remove the message after displaying
                    }
                %>
                <div class="item-header">
                    <span class="item-name-header">Item</span>
                    <span class="item-quantity-header">Quantity</span>
                </div>
                <div id="itemsList">
                    <%
                        List<Food> items = (List<Food>) request.getAttribute("items");
                        if (items != null && !items.isEmpty()) {
                            for (Food item : items) {
                    %>
                    <div class="item-row">
                        <span class="item-name"><%= item.getName() %></span>
                        <div class="quantity-area">
                            <input type="number" class="item-quantity" id="quantity_<%= item.getId() %>" name="quantity_<%= item.getId() %>" value="0" min="0" max="<%= item.getInventory()- item.getDemand()*1.2 %>">
                            <span class="availability">Available: <%= item.getInventory()- item.getDemand()*1.2 %></span>
                        </div>
                    </div>

                    <%
                            }
                        } else {
                    %>
                    <p>No items available for claim.</p>
                    <%
                        }
                    %>
                </div>
                <div class="submit-area">
                    <button type="submit">Claim Selected Items</button>
                </div>
            </form>
        </div>
        <jsp:include page="../footer.jsp" />

    </body>
</html>
