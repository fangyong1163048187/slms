<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/25
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="registersubmit">
    <div>
        <span>注册</span><br/>
        <p>用&nbsp;&nbsp;户&nbsp;&nbsp;名:<input type="text" name="username" placeholder="用&nbsp;&nbsp;户&nbsp;&nbsp;名" id="username" class="txt"><span></span></p>
        <p>密　　码:<input type="password" name="password" placeholder="密　　码" id="password" class="txt"><span></span></p>
        <p>确认密码:<input type="password" name="confirmpassword" placeholder="确认密码" class="txt"><span></span></p>
        <p>您的头像:<input type="file" name="file" class="txt"><span></span></p>
        <input type="submit" value="注册" class="button"/>
        <input type="reset" value="重置" class="button"/>
    </div>
</form>
</body>
</html>
