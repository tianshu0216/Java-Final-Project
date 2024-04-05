<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <style>
        /* CSS for Header */
        header {
            background-image: url('footer-bg.jpg'); /* 设置背景图片路径 */
            background-size: cover; /* 背景图片适应整个header */
            color: #333333; 
            text-align: center;
            padding: 25px 0;
            width: 100%;
            display: flex; /* 将内容设置为弹性布局，使其在同一行上显示 */
            align-items: center; /* 垂直居中 */
            justify-content: space-between; /* 水平间距 */
        }
           .Title {
            display: flex; /* 将标题设置为弹性布局 */
            align-items: center; /* 垂直居中标题 */
        }

        .Title p {
            font-size: 32px; /* 设置标题字体大小 */
            margin: 0; /* 清除默认的外边距 */
            color: #333333;
        }  

        /* CSS for Navbar */
        .navbar {            
            overflow: hidden;  }

        .navbar a {
            float: left;
            display: block;
            background-color: #4b7a4b;
            color: #fff;
            text-align: center;
            padding: 25px 20px; /* 设置链接的内边距 */
            text-decoration: none; /* 清除链接的下划线 */
            font-size: 17px; /* 设置链接文字大小 */
        }

        .navbar a:hover {
            background-color: #2a8e48;
        }

    </style>
</head>
<body>
    <header>
        <div class="Title">        
            <p> Food Waste Reduction Platform </p></div>
        <div class="navbar">
           <nav>
                <%-- Check if user is logged in --%>
                <% User user = (User)session.getAttribute("user");
                    if (user == null) {
                %>
                        <%-- If user is not logged in, show registration and login links --%>
                        <a href="http://localhost:8080/FWRP/user/registration.jsp">Registration</a>
                        <a href="http://localhost:8080/FWRP/user/login.jsp">Login</a>
                <%
                    } else {
                %>
                        <%-- If user is logged in, show logout link --%>
                        <a href="http://localhost:8080/FWRP/user/LogoutServlet">Logout</a>
                        <a href="http://localhost:8080/FWRP">Home</a>
                        <a href="http://localhost:8080/FWRP/user/dashboard.jsp">Dashboard</a>
                <%
                    }
                %>
            </nav>
        </div>
    </header>
</body>
</html>
