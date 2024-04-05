<%-- 
    Document   : postMessage
    Created on : Apr 5, 2024, 1:22:28 a.m.
    Author     : ty_huang
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
    <title>Post a Message</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user == null || !"consumer".equalsIgnoreCase(user.getUserType())) {
            // 如果用户未登录或不是消费者类型，重定向到登录页面
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div class="container">
        <h2>Post Your Message</h2>
        <form action="PostMessageServlet" method="post">
            <div class="form-group">
                <label for="message">Message:</label>
                <textarea id="message" name="message" rows="5" required></textarea>
            </div>
            <div class="form-group">
                <input type="submit" value="Post Message">
            </div>
        </form>
    </div>
</body>
</html>


