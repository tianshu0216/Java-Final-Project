<%-- 
    Document   : viewMessages
    Created on : Apr 5, 2024, 1:32:19 a.m.
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
    <title>View Messages</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
   <% 
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    MessagesDAO messagesDAO = new MessagesDAO();
    List<Message> openMessages = messagesDAO.getOpenMessages();
    List<Message> userClaimedMessages = messagesDAO.getMessagesClaimedByUser(user.getId());
    %>
    <div class="container">
        <h2>Available Messages</h2>
    <table>
        <thead>
            <tr>
                <th>Message</th>
                <th>Posted By</th>
                <% if ("charitable organization".equalsIgnoreCase(user.getUserType())) { %>
                    <th>Action</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
            <% for (Message message : openMessages) { %>
                <tr>
                    <td><%= message.getMessage() %></td>
                    <td><%= message.getUserId() %></td>
                    <% if ("charitable organization".equalsIgnoreCase(user.getUserType())) { %>
                        <td>
                            <form action="ClaimMessageServlet" method="get">
                                <input type="hidden" name="messageId" value="<%= message.getId() %>" />
                                <input type="submit" value="Accept" />
                            </form>
                        </td>
                    <% } %>
                </tr>
            <% } %>
        </tbody>
    </table>
            <h2>Messages Claimed By You</h2>
    <table>
        <thead>
            <tr>
                <th>Message</th>
                <th>Posted At</th>
            </tr>
        </thead>
        <tbody>
            <% for (Message message : userClaimedMessages) { %>
                <tr>
                    <td><%= message.getMessage() %></td>
                    <td><%= message.getPostedAt() %></td> 
                </tr>
            <% } %>
        </tbody>
    </table>
    </div>
</body>
</html>
