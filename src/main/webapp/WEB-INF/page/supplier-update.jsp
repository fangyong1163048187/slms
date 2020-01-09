<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/3
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>供应商信息修改</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/supplier-update.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/supplier-update.js"></script>
</head>
<body>
<a href="supplier-info"><button type="button" class="btn btn-info">返回上一页</button></a>
<form  class="form">
    <%--编号--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="number"><span class="input-group-text">编号:</span></label>
        </div>
        <input type="text" name="number" class="form-control" id="number" placeholder="输入编号" value="${supplier.suppliernumber}" readonly="readonly">
    </div>
    <span class="tip" id="number_tip"></span>
    <%--姓名--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="name"><span class="input-group-text">供应商姓名:</span></label>
        </div>
        <input type="text" name="name" class="form-control" id="name" placeholder="输入姓名" value="${supplier.suppliername}">
    </div>
    <span class="tip" id="name_tip"></span>
    <%--联系人--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="linkman"><span class="input-group-text">联系人:</span></label>
        </div>
        <input type="text" name="linkman" class="form-control" id="linkman" placeholder="联系人" value="${supplier.linkman}">
    </div>
    <span class="tip" id="linkman_tip"></span>
    <%--联系电话--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="linkphone"><span class="input-group-text">联系电话:</span></label>
        </div>
        <input type="text" name="linkphone" class="form-control" id="linkphone" placeholder="输入联系电话" value="${supplier.linkphone}">
    </div>
    <span class="tip" id="linkphone_tip"></span>
    <%--联系地址--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="linkaddress"><span class="input-group-text">联系地址:</span></label>
        </div>
        <input type="text" name="linkaddress" class="form-control" id="linkaddress" placeholder="输入联系地址" value="${supplier.linkaddress}">
    </div>
    <span class="tip" id="linkaddress_tip"></span>
    <%--id--%>
    <input type="text" name="supplierid" value="${supplier.id}" style="display: none">
    <%--提交--%>
    <button type="button" class="btn btn-success" id="sub">提交</button>
    <button type="reset" class="btn btn-danger button">重置</button>
</form>
</body>
</html>
