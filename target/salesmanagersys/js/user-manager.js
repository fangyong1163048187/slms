$(function () {
    var userRole=false;
    $("#userRole").bind("blur", function () {
        if ($(this).val().match(/^([024]|\s*)$/)) {
            userRole = true;
        } else {
            userRole = false;
        }
    });
    $("#sub_select").click(function () {
        if($("#userRole").val()===""){
            userRole = true;
        }
        if(userRole===false){
            alert("用户角色输入错误!");
            return false;
        }
    });
    $(".alterRole_ordinary").click(function () {
        //当前元素
        var $s = $(this);
        //用户名
        var username = $(this).attr("data-username");
        //用户角色
        var role = $(this).attr("data-role");
        //用户ID
        var id = $(this).attr("data-number");
        $.ajax({
            url: "alter_role",
            type: "post",
            data: {"username": username, "role": role, "id": id,"alterRole":"0"},
            dataType: "json",
            success: function (data) {
                if(data.row===0){
                    alert("更改用户权限失败!");
                }else{
                    alert("更改用户权限成功!");
                    $s.parent().prev().html("0");
                }
            }
        });
    });
    $(".alterRole_manager").click(function () {
        //当前元素
        var $s = $(this);
        //用户名
        var username = $(this).attr("data-username");
        //用户角色
        var role = $(this).attr("data-role");
        //用户ID
        var id = $(this).attr("data-number");
        $.ajax({
            url: "alter_role",
            type: "post",
            data: {"username": username, "role": role, "id": id,"alterRole":"2"},
            dataType: "json",
            success: function (data) {
                if(data.row===0){
                    alert("更改用户权限失败!");
                }else{
                    alert("更改用户权限成功!");
                    $s.parent().prev().prev().html("2");
                }
            }
        });
    });
    $(".alterPassword").click(function () {
        //用户名
        var username = $(this).attr("data-username");
        //用户角色
        var role = $(this).attr("data-role");
        //用户ID
        var id = $(this).attr("data-number");
        $.ajax({
            url: "alter_password",
            type: "post",
            data: {"username": username, "id": id,"alterPsd":"123456"},
            dataType: "json",
            success: function (data) {
                if(data.row===0){
                    alert("重置密码失败!");
                }else{
                    alert("重置密码成功:123456");
                }
            }
        });
    });
});