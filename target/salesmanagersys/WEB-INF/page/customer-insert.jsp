<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/2
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>新增商品信息</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer-insert.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/customer-insert.js"></script>
</head>
<body>
<a href="customer-info"><button type="button" class="btn btn-info">返回上一页</button></a>
<form  class="form">
    <%--姓名--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="name"><span class="input-group-text">顾客名称:</span></label>
        </div>
        <input type="text" name="name" class="form-control" id="name" placeholder="顾客名称">
    </div>
    <span class="tip" id="name_tip"></span>
    <%--联系人--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="linkman"><span class="input-group-text">联系人:</span></label>
        </div>
        <input type="text" name="linkman" class="form-control" id="linkman" placeholder="输入联系人">
    </div>
    <span class="tip" id="linkman_tip"></span>
    <%--联系电话--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="linkphone"><span class="input-group-text">联系电话:</span></label>
        </div>
        <input type="text" name="linkphone" class="form-control" id="linkphone" placeholder="输入联系电话">
    </div>
    <span class="tip" id="linkphone_tip"></span>
    <%--联系地址--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="linkaddress"><span class="input-group-text">联系地址:</span></label>
        </div>
        <input type="text" name="linkaddress" class="form-control" id="linkaddress" placeholder="输入联系地址">
    </div>
    <span class="tip" id="linkaddress_tip"></span>
    <%--提交--%>
    <button type="button" class="btn btn-success" id="sub">提交</button>
    <button type="reset" class="btn btn-danger button">重置</button>
</form>
</body>
</html>
