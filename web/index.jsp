<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Waste Reduction Platform - Registration</title>
    <style>
        /* CSS for Content */
        .content {
            display: flex;
            justify-content: space-between; /* 水平空间均匀分配 */
            align-items: center;
            background-color: #f6fff2; /* 背景颜色为浅绿色 */
            color: #333333; 
            padding: 50px 10px; /* 增加上下内边距 */
            margin: 25px 15px;
        }
        .content-left,
        .content-right {
            flex-shrink: 0; /* 不缩小 */
        }
        .content img {
            width: 180px; /* 图片宽度增大 */
            height: auto; /* 自适应高度 */
        }
        .menu-btn-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        .menu-btn {
            background-color: #4b7a4b; /* 按钮背景颜色 */
            color: #fff; /* 按钮字体颜色 */
            padding: 10px; /* 按钮内边距 */
            border-radius: 2px; /* 圆角 */
            text-transform: uppercase; /* 文字转换为大写 */
            font-weight: 600; /* 字体粗细 */
            font-size: 16px; /* 字体大小增大 */
            width: 200px; /* 按钮宽度 */
            height: 40px; /* 按钮高度 */
            margin: 15px 10px; /* 按钮间距 */
            display: block; /* 将按钮转换为块级元素，使得换行生效 */
            text-align: center; /* 文字居中对齐 */
            text-decoration: none; /* 去除下划线 */
            line-height: 40px; /* 设置行高，使文字垂直居中 */
        }
        .menu-btn:hover {
            background-color: #2a8e48; /* 按钮鼠标悬停时背景颜色 */
        }
        h1 {
            color: #263a4f; 
            font-size: 32px; 
        }
        h2{
            color: #666666; 
            font-size: 24px; 
        }
    </style>
</head>
<body>
    <jsp:include page="header.jsp" />
    
    <div class="content">
        <div class="content-left">
            <img src="food-2.png" alt="img"> 
        </div>
        <div class="content-center">
            
            <h2>The Food Waste Reduction Platform aims to address the global issue of food waste by providing a comprehensive solution that connects food retailers, consumers, and charitable organizations.
                    A food waste reduction platform plays a vital role in promoting sustainability, reducing hunger, and building more resilient food ecosystems. 
                    It encourages collaboration among stakeholders across the food supply chain and encourages collective action to address one of the most important challenges of our time.</h2> 
            
            <div class="menu-btn-container">
                <a class="menu-btn btn-white" href="http://localhost:8080/FWRP/user/login.jsp">LOGIN</a>
                <a class="menu-btn btn-white" href="http://localhost:8080/FWRP/user/registration.jsp">REGISTRATION</a>
            </div>
        </div>
        <div class="content-right">
            <img src="food-1.png" alt="img"> 
        </div>
    </div>
    
    <jsp:include page="footer.jsp" />
</body>
</html>
