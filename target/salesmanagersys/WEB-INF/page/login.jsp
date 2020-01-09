<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
</head>
<body>
<form action="login_submit" method="post">
    <div>
        <span>登录</span>
        <p>用户名:<input type="text" name="username" placeholder="用户名" id="username" class="txt" value="${sessionScope.user.username}"></p>
        <p>密　码:<input type="password" name="password" placeholder="密　码" id="password" class="txt" value="${sessionScope.user.password}"><span></span></p>
        <p id="rem_psd"><label><input type="checkbox" name="rem_psd" value="true"></label>记住密码</p>
        <input type="submit" value="登录" class="button">
        <p id="tip">没有账号?<a href="register">请注册</a></p>
    </div>
</form>
</body>
</html>
