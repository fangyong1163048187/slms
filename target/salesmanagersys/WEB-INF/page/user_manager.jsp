<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/4
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/info.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/user-manager.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#page6").click(function () {
                <c:if test="${page.pageNumber>=page.pageTotal}">
                return false;
                </c:if>
            });
            $("#page2").click(function () {
                <c:if test="${page.pageNumber<=1}">
                return false;
                </c:if>
            });
            $(".tiaozhuan").click(function () {
                var pageNumber = $(".text_page").val();
                //没有输入跳转页面时，按钮无效
                if(pageNumber==="" || pageNumber==null){
                    return false;
                }else{
                    if(pageNumber.match(/^\d*$/) && pageNumber <= ${page.pageTotal} && pageNumber>=1){
                        $(".tiaozhuan").attr("href","user_select?username=${page.username}&role=${page.role}&pageSize=${page.pageSize}&pageNumber="+pageNumber);
                    }else{
                        return false;
                    }
                }
            });
            //在多个相同类中获取发生点击事件的那个按钮类，
            // 并获取那个类的data-number属性，把其放在id为test的div块中
            $(".mybtn").click(function () {
                //用户ID
                var number = $(this).attr("data-number");
                //用户名
                var username = $(this).attr("data-username")
                //用户角色
                var role = $(this).attr("data-role")
                $("#test").attr("data-number",number);
                $("#test").attr("data-username",username);
                $("#test").attr("data-role",role);
            });
            //删除一条数据
            $("#delete_one_data").click(function () {
                var number = $('#test').data('number');
                var username = $('#test').data('username');
                var role = $('#test').data('role');
                $.ajax({
                    url: "user_delete_one",
                    type: "post",
                    data: {"userId": number,"username":username,"role":role},
                    dataType: "json",
                    success: function (data) {
                        if (data.row > 0) {
                            alert("删除用户成功!");
                            window.location.href = "user_select?username=" + "${page.username}"
                                + "&role=" + "${page.role}";
                        } else {
                            alert("删除用户失败!");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body id="all">
<%@ include file="top.jsp"%>
<%@ include file="left.jsp"%>
<div class="right_all">
    <div class="head">
        <span id="logo" class="glyphicon glyphicon-home" style="color: rgb(0, 0, 0);"></span>
        <span id="tip">首页&gt;系统管理&gt;用户管理</span>
    </div>
    <form method="post" action="user_select">
        <div class="search">
            用户昵称:<input type="search" name="username" placeholder="输入用户昵称">&nbsp;&nbsp;
            用户角色:<input type="search" name="userRole" placeholder="输入用户角色" id="userRole">&nbsp;&nbsp;
            <input type="submit" value="查询" class="button" id="sub_select">&nbsp;&nbsp;
            <input type="reset" value="重置" class="button">
            <span style="color: red">小提示:用户角色0-普通用户，2-管理员，4-超级管理员</span>
        </div>
    </form>
    <div class="container table-responsive-lg" id="t">
        <table class="table <%--table-bordered--%> table-hover table-striped" id="mytable">
            <thead class="bg-light text-dark">
            <tr class="row_th">
                <th>用户名</th>
                <th>用户角色</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.list}" var="userManager">
                <tr class="row_td">
                    <td>${userManager.username}</td>
                    <td>${userManager.role}</td>
                        <%--设为普通用户--%>
                    <td><a href="javascript:void(0);" data-role="${userManager.role}" data-username="${userManager.username}" data-number="${userManager.id}" class="alterRole_ordinary"><span style="color: green">设为普通用户</span></a></td>
                        <%--设置为管理员--%>
                    <td><a href="javascript:void(0);" data-role="${userManager.role}" data-username="${userManager.username}" data-number="${userManager.id}" class="alterRole_manager"><span style="color: green">设为管理员</span></a></td>
                        <%--修改密码--%>
                    <td><a href="javascript:void(0);" data-role="${userManager.role}" data-username="${userManager.username}" data-number="${userManager.id}" class="alterPassword"><span style="color: green">重置密码</span></a></td>
                        <%--删除--%>
                    <td><button  data-role="${userManager.role}" data-username="${userManager.username}" data-number="${userManager.id}" class="mybtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><span style="color: red">删除</span></button></td>
                </tr>
                <div id="test"></div>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${page.list.size()==0}">
            <span class="search_tip">没有找到结果！</span>
        </c:if>
    </div>

    <!-- 模态框(删除单个数据) -->
    <div class="modal fade" id="myModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="staticBackdropLabel">
                        提示信息
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    确定要删除该条数据吗，删除后不可恢复
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                    </button>
                    <button id="delete_one_data" type="button" class="btn btn-primary">
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
    <%--分页--%>
    <c:if test="${page.list.size()!=0 && page !=null}">
        <div class="page_block">
            <ul class="pagination pagination-lg" id="page">
                <li class="page-item"><a id="page1" class="page-link" href="user_select?username=${page.username}&role=${page.role}&pageSize=${page.pageSize}&pageNumber=1"><span class="text-dark">首页</span></a></li>
                <li class="page-item"><a id="page2" class="page-link" href="user_select?username=${page.username}&role=${page.role}&pageSize=${page.pageSize}&pageNumber=${page.pageNumber-1}"><span class="text-dark">上一页</span></a></li>
                    <%--                <li class="page-item active"><a id="page3" class="page-link" href="#"><span class="text-dark">1</span></a></li>
                                    <li class="page-item"><a id="page4" class="page-link" href="#"><span class="text-dark">2</span></a></li>
                                    <li class="page-item"><a id="page5" class="page-link" href="#"><span class="text-dark">3</span></a></li>--%>
                <li class="page-item"><a id="page6" class="page-link" href="user_select?username=${page.username}&role=${page.role}&pageSize=${page.pageSize}&pageNumber=${page.pageNumber+1}"><span class="text-dark">下一页</span></a></li>
                <li class="page-item"><a id="page7" class="page-link" href="user_select?username=${page.username}&role=${page.role}&pageSize=${page.pageSize}&pageNumber=${page.pageTotal}"><span class="text-dark">尾页</span></a></li>
                <li class="page-item"><a id="page8" class="page-link" href="#"><span class="text-dark">${page.pageNumber}/${page.pageTotal}页</span></a></li>
                <li class="page-item"><a id="page9" class="page-link" href="#"><span class="text-dark">共找到<span style="color: red">${page.pageCountOfUser}</span>条数据</span></a></li>
                    <%--<li class="page-item" id="gogo"><a id="page10" class="page-link" href="#"><span class="text-dark">Go</span></a></li>--%>
            </ul>
            <span id="page_go">跳至<input type="text" name="text_page" id="text_page" class="text_page">页</span>
            <span class="gogo">
                <a href="#" role="button" class="btn btn-primary tiaozhuan">Go</a>
            </span>
        </div>
    </c:if>
</div>
</body>
</html>
