<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/2
  Time: 6:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<html>
<head>
    <title>商品信息修改</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product-update.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/product-update.js"></script>
</head>
<body>
<a href="product-info"><button type="button" class="btn btn-info">返回上一页</button></a>
<form  class="form">
    <%--编号--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="number"><span class="input-group-text">编号:</span></label>
        </div>
        <input type="text" name="number" class="form-control" id="number" placeholder="输入编号" value="${product.productnumber}" readonly="readonly">
    </div>
    <span class="tip" id="number_tip"></span>
    <%--姓名--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="name"><span class="input-group-text">商品名称:</span></label>
        </div>
        <input type="text" name="name" class="form-control" id="name" placeholder="输入姓名" value="${product.productname}">
    </div>
    <span class="tip" id="name_tip"></span>
    <%--商品进货单价--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="buyprice"><span class="input-group-text">进货单价:</span></label>
        </div>
        <input type="text" name="buyprice" class="form-control" id="buyprice" placeholder="输入进货单价" value="${product.productbuyprice}">
    </div>
    <span class="tip" id="buyprice_tip"></span>
    <%--商品售出单价--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="salesprice"><span class="input-group-text">销售单价:</span></label>
        </div>
        <input type="text" name="salesprice" class="form-control" id="salesprice" placeholder="输入销售单价" value="${product.productsalesprice}">
    </div>
    <span class="tip" id="salesprice_tip"></span>
    <%--库存总量--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="stockamount"><span class="input-group-text">库存总量:</span></label>
        </div>
        <input type="text" name="stockamount" class="form-control" id="stockamount" placeholder="输入库存总量" value="${product.stock.stockamount}">
    </div>
    <span class="tip" id="stockamount_tip"></span>
    <%--销售总量--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="salesamount"><span class="input-group-text">销售总量:</span></label>
        </div>
        <input type="text" name="salesamount" class="form-control" id="salesamount" placeholder="输入销售总量" value="${product.stock.salesamount}">
    </div>
    <span class="tip" id="salesamount_tip"></span>
        <input type="text" name="productid" value="${product.stock.productid}" style="display: none">
    <%--提交--%>
    <button type="button" class="btn btn-success" id="sub">提交</button>
    <button type="reset" class="btn btn-danger button">重置</button>
</form>
</body>
</html>
