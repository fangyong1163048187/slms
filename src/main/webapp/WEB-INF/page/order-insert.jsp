<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/3
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>新增订单表信息</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-insert.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/order-insert.js"></script>
</head>
<body>
<a href="order-info"><button type="button" class="btn btn-info">返回上一页</button></a>
<form  class="form">
    <%--客户姓名--%>
        <div class="form-group input-group mb-3">
            <div class="input-group-prepend">
                <label for="customer_name"><span class="input-group-text">客户姓名:</span></label>
            </div>
            <select class="custom-select-sm" name="customer_name" id="customer_name" style="height: 38px;width: 175px">
                <c:forEach items="${customers}" var="customer">
                    <option class="cus" value="${customer.customernumber}">${customer.customername}</option>
                </c:forEach>
            </select>
        </div>
    <%--商品名称--%>
        <div class="form-group input-group mb-3">
            <div class="input-group-prepend">
                <label for="product_name"><span class="input-group-text">商品名称:</span></label>
            </div>
            <select class="custom-select-sm" name="product_name" id="product_name" style="height: 38px;width: 175px">
                <c:forEach items="${products}" var="product">
                    <option class="pro" value="${product.productnumber}">${product.productname}</option>
                </c:forEach>
            </select>
        </div>
    <%--经办人--%>
        <div class="form-group input-group mb-3">
            <div class="input-group-prepend">
                <label for="employee_name"><span class="input-group-text">经&nbsp;&nbsp;办&nbsp;&nbsp;人:</span></label>
            </div>
            <select class="custom-select-sm" name="employee_name" id="employee_name" style="height: 38px;width: 174px">
                <c:forEach items="${employees}" var="employee">
                    <option class="emp" value="${employee.employeenumber}">${employee.employeename}</option>
                </c:forEach>
            </select>
        </div>
    <%--商品数量--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="product_amount"><span class="input-group-text">商品数量:</span></label>
        </div>
        <input type="text" name="product_amount" class="form-control" id="product_amount" placeholder="输入商品数量">
    </div>
    <span class="tip" id="product_amount_tip"></span>
    <%--提交--%>
    <button type="button" class="btn btn-success" id="sub">提交</button>
    <button type="reset" class="btn btn-danger button">重置</button>
</form>
</body>
</html>
