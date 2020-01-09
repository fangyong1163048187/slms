<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/26
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>标题</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/top.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
</head>
<body>
<div class="top">
    <img src="${pageContext.request.contextPath}/images/xiaosongshu.png" id="timg">
    <h1>进&nbsp;&nbsp;销&nbsp;&nbsp;存&nbsp;&nbsp;管&nbsp;&nbsp;理&nbsp;&nbsp;系&nbsp;&nbsp;统</h1>
    <div class="info">
        <a href="userinfo"><img src="get_user_photo" id="headimg"><span class="user" id="username"><%--${sessionScope.user.username}--%></span></a>
        <a href="exit"><img src="${pageContext.request.contextPath}/images/exit.png" id="exitimg"><span class="user" id="exit"></span></a>
    </div>
</div>
</body>
</html>
