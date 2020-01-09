<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/27
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>顾客档案库</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-4.3.1.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/info.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1-jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#page6").click(function () {
                <c:if test="${customer.pageNumber>=customer.pageTotal}">
                return false;
                </c:if>
            });
            $("#page2").click(function () {
                <c:if test="${customer.pageNumber<=1}">
                return false;
                </c:if>
            });
            $(".tiaozhuan").click(function () {
                var pageNumber = $(".text_page").val();
                //没有输入跳转页面时，按钮无效
                if(pageNumber==="" || pageNumber==null){
                    return false;
                }else{
                    if(pageNumber.match(/^\d*$/) && pageNumber <= ${customer.pageTotal} && pageNumber>=1){
                        $(".tiaozhuan").attr("href","customer_select?customer_id=${customer.customerNumber}&customer_name=${customer.customerName}&pageSize=${customer.pageSize}&pageNumber="+pageNumber);
                    }else{
                        return false;
                    }
                }
            });
            //在多个相同类中获取发生点击事件的那个按钮类，
            // 并获取那个类的data-number属性，把其放在id为test的div块中
            $(".mybtn").click(function () {
                var cus_number = $(this).attr("data-number");
                var cus_customerid = $(this).attr("data-customerid");
                $("#test").attr("data-number",cus_number);
                $("#test").attr("data-customerid",cus_customerid);
            });
            //删除一条数据
            $("#delete_one_data").click(function () {
                if(${sessionScope.user.role<2}){
                    alert("对不起，您没有相应的权限!")
                }else{
                    var cus_number = $('#test').data('number');
                    var cus_customerid = $('#test').data('customerid');
                    $.ajax({
                        url:"customer_delete_one",
                        type:"post",
                        data:{"customerNumber":cus_number,"salesOrderCustomerID":cus_customerid},
                        dataType:"json",
                        success:function (data) {
                            if(data.row!=null){
                                alert("删除数据成功!");
                                window.location.href="customer_select?customer_id="+"${customer.customerNumber}"
                                    +"&customer_name="+"${customer.customerName}";
                            }else{
                                alert("删除数据失败!");
                            }
                        }
                    });
                }
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
        <span id="tip">首页&gt;档案管理&gt;客户档案</span>
    </div>
    <form method="post" action="customer_select">
        <div class="search">
            客户编号:<input type="search" name="customer_id" placeholder="输入客户编号">&nbsp;&nbsp;
            客户名称:<input type="search" name="customer_name" placeholder="输入客户姓名">&nbsp;&nbsp;
            <input type="submit" value="查询" class="button">&nbsp;&nbsp;
            <a href="customer-insert-page" onclick="javascript: if(${sessionScope.user.role<2}){ alert('权限不足'); return false;}" id="insert" class="button">新增</a>&nbsp;&nbsp;
            <input type="reset" value="重置" class="button">
            <button type="button" id="truncate" class="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">清空数据</button>
        </div>
    </form>
    <div class="container table-responsive-lg" id="t">
        <table class="table <%--table-bordered--%> table-hover table-striped" id="mytable">
            <col width="25%"/>
            <col width="24%"/>
            <col width="10%"/>
            <col width="10%"/>
            <col width="20%"/>
            <col width="6%"/>
            <col width="6%"/>
            <thead class="bg-light text-dark">
            <tr class="row_th">
                <th>客户编号</th>
                <th>客户名称</th>
                <th>联系人</th>
                <th>联系电话</th>
                <th>联系地址</th>
                <th>修改</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customer.list}" var="customer">
                <tr class="row_td">
                    <td>${customer.customernumber}</td>
                    <td>${customer.customername}</td>
                    <td>${customer.linkman}</td>
                    <td>${customer.linkphone}</td>
                    <td>${customer.linkaddress}</td>
                    <td><a href="customer-update?customerId=${customer.id}&customerNumber=${customer.customernumber}&customerName=${customer.customername}&linkman=${customer.linkman}&linkphone=${customer.linkphone}&linkaddress=${customer.linkaddress}" onclick="javascript:if(${sessionScope.user.role<2}){alert('权限不足!'); return false;}"><span class="glyphicon glyphicon-pencil" style="color: rgb(255, 140, 60);"></span></a>
                    </td>
                        <%--每个button按钮都加上一个属性data-number--%>
                    <td id="delete"><button data-customerid="${customer.id}" data-number="${customer.customernumber}" class="mybtn" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-minus" style="color: rgb(255, 0, 0);"></span></button></td>
                </tr>
                <div id="test" <%--data-number="${employee.employeenumber}"--%>></div>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${customer.list.size()==0}">
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
    <!-- 模态框(清空所有数据) -->
    <div class="modal fade" id="myModal2" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel2"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="staticBackdropLabel2">
                        提示信息
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    确定要清空所有数据吗，清空后不可恢复
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">取消
                    </button>
                    <a href="customer_truncate" onclick="javascript:if(${sessionScope.user.role<2}){ alert('权限不足'); return false;}"><button id="trun" type="button" class="btn btn-primary">
                        确认
                    </button></a>
                </div>
            </div>
        </div>
    </div>
    <%--分页--%>
    <c:if test="${customer.list.size()!=0 && customer !=null}">
        <div class="page_block">
            <ul class="pagination pagination-lg" id="page">
                <li class="page-item"><a id="page1" class="page-link" href="customer_select?customer_id=${customer.customerNumber}&customer_name=${customer.customerName}&pageSize=${customer.pageSize}&pageNumber=1"><span class="text-dark">首页</span></a></li>
                <li class="page-item"><a id="page2" class="page-link" href="customer_select?customer_id=${customer.customerNumber}&customer_name=${customer.customerName}&pageSize=${customer.pageSize}&pageNumber=${customer.pageNumber-1}"><span class="text-dark">上一页</span></a></li>
                    <%--                <li class="page-item active"><a id="page3" class="page-link" href="#"><span class="text-dark">1</span></a></li>
                                    <li class="page-item"><a id="page4" class="page-link" href="#"><span class="text-dark">2</span></a></li>
                                    <li class="page-item"><a id="page5" class="page-link" href="#"><span class="text-dark">3</span></a></li>--%>
                <li class="page-item"><a id="page6" class="page-link" href="customer_select?customer_id=${customer.customerNumber}&customer_name=${customer.customerName}&pageSize=${customer.pageSize}&pageNumber=${customer.pageNumber+1}"><span class="text-dark">下一页</span></a></li>
                <li class="page-item"><a id="page7" class="page-link" href="customer_select?customer_id=${customer.customerNumber}&customer_name=${customer.customerName}&pageSize=${customer.pageSize}&pageNumber=${customer.pageTotal}"><span class="text-dark">尾页</span></a></li>
                <li class="page-item"><a id="page8" class="page-link" href="#"><span class="text-dark">${customer.pageNumber}/${customer.pageTotal}页</span></a></li>
                <li class="page-item"><a id="page9" class="page-link" href="#"><span class="text-dark">共找到<span style="color: red">${customer.pageCountOfCustomer}</span>条数据</span></a></li>
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
