

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Check if user information exists in the session
    User user = (User) session.getAttribute("user");
    if (user == null) {
        // If not authenticated, redirect to landing page
        response.sendRedirect("http://localhost:8080/FWRP");
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
            <%
                if (user != null) {
                    out.println("<h2> Welcome, " + user.getName() + "!</h2>");
                //    out.println("<h3>Your user type is: " + user.getType() + ".</h3>");

                    // Convert user type to lowercase for case-insensitive comparison
                    String userType = user.getUserType().toLowerCase();

                    // Show different links based on user type
                    if (userType.equals("retailer")) {
            %>
            <!-- Show links for admin -->

            <a class="dashboard_btn" href="http://localhost:8080/FWRP/inventory/management.html">Inventory Management</a>
            <%
            } else if (userType.equals("consumer")) {
            %>
            <!-- Show links for customer -->


            <a class="dashboard_btn" href="http://localhost:8080/FWRP/customer/ItemListCustomerServlet">Purchase Food</a>

            <%
            } else if (userType.equals("charitable organization")) {
            %>

            <a class="dashboard_btn" href="http://localhost:8080/FWRP/charity/ItemListServlet">Claim Food</a>


            <%       }
                }
            %>
            <a class="dashboard_btn" href="http://localhost:8080/FWRP/user/TransactionServlet">View Transaction</a>
        </div>
        <jsp:include page="../footer.jsp" />
    </body>
</html>