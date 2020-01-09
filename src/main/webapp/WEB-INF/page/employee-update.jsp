<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/31
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>修改</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee-update.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/employee-update.js"></script>
    <script type="text/javascript">
        $(function () {
            var time = "${employee.employeebirthday}".concat("T00:00");
            $("#birthday").attr("value",time)
        })
    </script>
</head>
<body>
<a href="employee-info"><button type="button" class="btn btn-info">返回上一页</button></a>
<form  class="form">
    <%--编号--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="number"><span class="input-group-text">编号:</span></label>
        </div>
        <input type="text" name="number" class="form-control" id="number" placeholder="输入编号" value="${employee.employeenumber}" readonly="readonly">
    </div>
    <span class="tip" id="number_tip"></span>
    <%--姓名--%>
    <div class="form-group input-group mb-3" style="width:20%" id="div_name">
        <div class="input-group-prepend">
            <label for="name"><span class="input-group-text">姓名:</span></label>
        </div>
        <input type="text" name="name" class="form-control" id="name" placeholder="输入姓名" value="${employee.employeename}">
    </div>
    <span class="tip" id="name_tip"></span>
    <%--电话--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="phone"><span class="input-group-text">电话:</span></label>
        </div>
        <input type="text" name="phone" class="form-control" id="phone" placeholder="输入手机号" value="${employee.employeephone}">
    </div>
    <span class="tip" id="phone_tip"></span>
    <%--邮箱--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="email"><span class="input-group-text">邮箱:</span></label>
        </div>
        <input type="text" name="email" class="form-control" id="email" placeholder="输入邮箱" value="${employee.employeeemail}">
    </div>
    <span class="tip" id="email_tip"></span>
    <%--地址--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="address"><span class="input-group-text">地址:</span></label>
        </div>
        <input type="text" name="address" class="form-control" id="address" placeholder="输入详细地址" value="${employee.employeeaddress}">
    </div>
    <span class="tip" id="address_tip"></span>
    <%--性别--%>
    <div class="form-group input-group mb-3">
        <div class="input-group-prepend">
            <label for="sex"><span class="input-group-text">性别:</span></label>
        </div>
        <select class="custom-select-sm" name="sex" id="sex" style="height: 38px">
            <option selected value="男">男</option>
            <option value="女">女</option>
        </select>
    </div>
    <%--日期--%>
    <div class="form-group input-group mb-3" style="width:20%">
        <div class="input-group-prepend">
            <label for="birthday"><span class="input-group-text">出生日期:</span></label>
        </div>
        <input type="datetime-local" name="birthday" class="form-control" id="birthday" value="">
    </div>
    <%--提交--%>
    <button type="button" class="btn btn-success" id="sub">提交</button>
    <button type="reset" class="btn btn-danger button">重置</button>
</form>
</body>
</html>
