<%-- 
    Document   : viewMessages_consumer
    Created on : Apr 5, 2024, 2:08:16 a.m.
    Author     : ty_huang
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="model.Message"%>
<%@ page import="dataaccesslayer.MessagesDAO"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
    <title>Your Messages</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <jsp:include page="../header.jsp" />
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        MessagesDAO dao = new MessagesDAO();
        List<Message> postedMessages = dao.getMessagesPostedByUser(user.getId());
        List<Message> claimedMessages = dao.getClaimedMessagesPostedByUser(user.getId());
    %>
    <div class="container">
        <h2>Your Posted Messages</h2>
        <table>
            <thead>
                <tr>
                    <th>Message</th>
                    <th>Posted At</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% for (Message message : postedMessages) { %>
                    <tr>
                        <td><%= message.getMessage() %></td>
                        <td><%= message.getPostedAt()%></td>
                        <td><%= message.getStatus() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <h2>Messages Claimed By Others</h2>
        <table>
            <thead>
                <tr>
                    <th>Message</th>
                    <th>Claimed By</th>
                    <th>Address for Donation</th>
                </tr>
            </thead>
            <tbody>
                 <% for (Message message : claimedMessages) { %>
            <tr>
                <td><%= message.getMessage() %></td>
                <td><%= message.getClaimedBy() %></td>
                <td>2fake, Ontario</td> <!-- Assuming this is a static address -->
            </tr>
        <% } %>
            </tbody>
        </table>
    </div>
            <jsp:include page="../footer.jsp" />
</body>
</html>
