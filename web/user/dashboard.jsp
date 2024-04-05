
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("http://localhost:8080/FWRP/login.jsp");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Dashboard</title>
        <link rel="stylesheet" href="../styles.css">
 </head>
<body>
    <jsp:include page="../header.jsp" />
    <div class="container">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <div class="user-info">
            <p><strong>Email:</strong> <%= user.getEmail() %></p>
            <p><strong>Subscribed:</strong> <%= user.getIsSubscribe() ? "Yes" : "No" %></p>
            <p><strong>User Type:</strong> <%= user.getUserType() %></p>
            <p><strong>Location:</strong> <%= user.getLocation() %></p>
        </div>
        <%-- Link Section --%>
        <div class="link-section">
            <% if (user.getUserType().equalsIgnoreCase("retailer")) { %>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/inventory/AddItemServlet">Inventory Management</a>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/inventory/retailer_inventory.jsp">See All Inventory</a>
            <% } else if (user.getUserType().equalsIgnoreCase("consumer")) { %>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/customer/ItemListCustomerServlet">Purchase Food</a>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/user/TransactionServlet">View Transaction</a>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/messages/postMessage.jsp">Post a Message</a>
            <% } else if (user.getUserType().equalsIgnoreCase("charitable organization")) { %>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/charity/ItemListServlet">Claim Food</a>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/user/TransactionServlet">View Claimed History</a>
                <a class="dashboard_btn" href="http://localhost:8080/FWRP/messages/viewMessages.jsp">View Messages</a>
            <% } %>
        </div>
    </div>
    <jsp:include page="../footer.jsp" />
</body>
</html>