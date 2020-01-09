<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/4
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改进货单信息</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buy-update.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/buy-update.js"></script>
    <script type="text/javascript">
        $(function () {
            var str = "${page.list[0].createtime}";
            var time = str.substring(0,str.lastIndexOf(":")).replace(/ /,"T");
            $("#date").attr("value",time)
        })
    </script>
</head>
<body>
<a href="buy-info"><button type="button" class="btn btn-info">返回上一页</button></a>
<form  class="form">
    <%--订单编号--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="buyOrderNumber"><span class="input-group-text">订单编号:</span></label>
        </div>
        <input type="text" name="buyOrderNumber" class="form-control" id="buyOrderNumber" placeholder="输入进货单编号"
               value="${page.list[0].ordernumber}" readonly="readonly">
    </div>
    <%--进货商姓名--%>
    <div class="form-group input-group mb-3">
        <div class="input-group-prepend">
            <label for="supplier_name"><span class="input-group-text">客户姓名:</span></label>
        </div>
        <select class="custom-select-sm" name="supplier_name" id="supplier_name" style="height: 38px;width: 175px">
            <option class="cus" value="${page.list[0].supplier.suppliernumber}" selected="selected">${page.list[0].suppliername}</option>
            <c:forEach items="${suppliers}" var="supplier">
                <c:if test="${supplier.id!=page.list[0].supplier.id}">
                    <option class="cus" value="${supplier.suppliernumber}">${supplier.suppliername}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
    <%--商品名称--%>
    <div class="form-group input-group mb-3">
        <div class="input-group-prepend">
            <label for="product_name"><span class="input-group-text">商品名称:</span></label>
        </div>
        <select class="custom-select-sm" name="product_name" id="product_name" style="height: 38px;width: 175px">
            <option class="pro" value="${page.list[0].product.productnumber}" selected="selected">${page.list[0].product.productname}</option>
            <c:forEach items="${products}" var="product">
                <c:if test="${product.id!=page.list[0].product.id}">
                    <option class="pro" value="${product.productnumber}">${product.productname}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
    <%--经办人--%>
    <div class="form-group input-group mb-3">
        <div class="input-group-prepend">
            <label for="employee_name"><span class="input-group-text">经&nbsp;&nbsp;办&nbsp;&nbsp;人:</span></label>
        </div>
        <select class="custom-select-sm" name="employee_name" id="employee_name" style="height: 38px;width: 174px">
            <option class="emp" value="${page.list[0].employee.employeenumber}" selected="selected">${page.list[0].employee.employeename}</option>
            <c:forEach items="${employees}" var="employee">
                <c:if test="${employee.id!=page.list[0].employee.id}">
                    <option class="emp" value="${employee.employeenumber}">${employee.employeename}</option>
                </c:if>
            </c:forEach>
        </select>
    </div>
    <%--商品数量--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="product_amount"><span class="input-group-text">商品数量:</span></label>
        </div>
        <input type="text" name="product_amount" class="form-control" id="product_amount" placeholder="输入商品数量" value="${page.list[0].productamount}">
    </div>
    <span class="tip" id="product_amount_tip"></span>
    <%--日期--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="date"><span class="input-group-text">订货日期:</span></label>
        </div>
        <input type="datetime-local" name="date" class="form-control" id="date" value="">
    </div>
    <%--提交--%>
    <button type="button" class="btn btn-success" id="sub">提交</button>
    <button type="reset" class="btn btn-danger button">重置</button>
</form>
</body>
</html>
