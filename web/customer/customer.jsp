<%-- 
    Document   : consumers
    Created on : April 1, 2024
    Author     : Tianshu&Feiling
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Food" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Food Waste Reduction Platform - Customer</title>
        <link rel="stylesheet" href="../styles.css">
    </head>

    <body>
        <jsp:include page="../header.jsp" />
        <div class="container">
            <h2>Available Items for Purchase</h2>
            <form action="orderReview.jsp" method="post" id="customerpurchaseForm">
                <% String errorMessage = (String) session.getAttribute("customererror");
                    if (errorMessage != null) {
                %>
                <script>alert("<%= errorMessage%>");</script>
                <%
                        session.removeAttribute("customererror"); // Remove the message after displaying
                    }
                %>
                <div class="item-header">
                    <span class="item-name-header">Name</span>
                
                    <span class="item-quantity-header">Quantity</span>
                </div>
                <div id="itemsList">
                    <%
                        List<Food> items = (List<Food>) request.getAttribute("items");
                        if (items != null && !items.isEmpty()) {
                            for (Food item : items) {
                    %>
                    <div class="item-row">
                        <span class="item-name"><strong><%= item.getName()%></strong>  $<%= item.getPrice()%></span>
                       
                        <div class="quantity-area">
                            <input type="number" class="item-quantity" id="quantity_<%= item.getId()%>" name="quantity_<%= item.getId()%>" value="0" min="0" max="<%= item.getInventory()- (int)(item.getDemand()*1.2)%>">
                            <span class="availability">Available: <%= item.getInventory()- (int)(item.getDemand()*1.2) %></span>
                        </div>
                    </div>

                    <%
                        }
                    } else {
                    %>
                    <p>No items available for purchase.</p>
                    <%
                        }
                    %>
                </div>
                <div class="submit-area">
                    <button type="submit">Purchase </button>
                    <br></br>
                    <button type="button" onclick="history.back()">Go Back</button>
                </div>
               
            </form>
        </div>
        <jsp:include page="../footer.jsp" />

    </body>
</html>

