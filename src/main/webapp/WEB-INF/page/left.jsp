<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/26
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>


<head>
    <title>菜单栏</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!-- Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap337.min.css" <%--integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"--%>>
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme337.min.css" <%--integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"--%>>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar_menu.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <!-- Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap337.min.js" <%--integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"--%>></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/sidebar_menu.js"></script>
</head>

<body>
    <div class="my_aside" id="mynav">
    <!-- 侧边栏标签页 -->
    <ul class="nav nav-pills nav-stacked" id="bar" >
        <!-- 侧边栏选项 -->
        <li role="presentation" class="active" id="bar-1">
            <!-- 选项控制data-target对应的显隐 -->
            <a href="#list1" data-toggle="collapse" id="collapseParent-1" onclick="chevron_toggle()"><i class="glyphicon glyphicon-file"></i>档案管理<span class="pull-right glyphicon glyphicon-chevron-left"></span></a>
            <!-- 隐藏的详细菜单 -->
            <ul id="list1" class="collapse">
                <li class="active">
                    <a id="employee" href="employee-info"><span class="glyphicon glyphicon-hand-right"></span>员工档案</a>
                </li>
                <li class="active">
                    <a id="product" href="product-info"><span class="glyphicon glyphicon-hand-right"></span>商品档案</a>
                </li>
                <li class="active">
                    <a id="customer" href="customer-info"><span class="glyphicon glyphicon-hand-right"></span>顾客档案</a>
                </li>
                <li class="active">
                    <a id="supplier" href="supplier-info"><span class="glyphicon glyphicon-hand-right"></span>货主档案</a>
                </li>
            </ul>
        </li>
        <li role="presentation" class="active" id="bar-2">
            <a href="#list2" data-toggle="collapse" id="collapseParent-2" onclick="chevron_toggle2()"><i class="glyphicon glyphicon-tasks"></i>销售管理<span class="pull-right glyphicon glyphicon-chevron-left"></span></a>
            <ul id="list2" class="collapse">
                <li class="active">
                    <a  id="order" href="order-info"><span class="glyphicon glyphicon-hand-right"></span>订货单</a>
                </li>
            </ul>
        </li>
        <li role="presentation" class="active" id="bar-3">
            <a href="#list3" data-toggle="collapse" id="collapseParent-3" onclick="chevron_toggle3()"><i class="glyphicon glyphicon-folder-open"></i>采购管理<span class="pull-right glyphicon glyphicon-chevron-left"></span></a>
            <ul id="list3" class="collapse">
                <li class="active">
                    <a id="buy" href="buy-info"><span class="glyphicon glyphicon-hand-right"></span>进货单</a>
                </li>
            </ul>
        </li>
        <li role="presentation" class="active" id="bar-4">
            <a href="#list4" data-toggle="collapse" id="collapseParent-4" onclick="chevron_toggle4()"><i class="glyphicon glyphicon-cog"></i>系统管理<span class="pull-right glyphicon glyphicon-chevron-left"></span></a>
            <ul id="list4" class="collapse">
                <li class="active">
                    <a id="user" href="user_manager" onclick="javascript:<c:if test='${sessionScope.user.role<4}'>alert('权限不足'); return false;</c:if>"><span class="glyphicon glyphicon-hand-right"></span>用户管理</a>
                </li>
            </ul>
        </li>
    </ul>
    </div>
</body>
</html>