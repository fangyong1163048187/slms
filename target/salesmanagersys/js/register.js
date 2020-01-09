$(function () {
        var username = false;
        var password = false;
        var confirm_password = false;
        var file = false;
        $(":text").bind("input",function () {
            <!--如果用户名为空，则提示用户输入用户名-->
            if($(":text").val()===""){
                username = false;
                $(this).next().css({"color":"red","font-size":"16px","height":"10px"}).html("用户名不能为空");
            }else{
                <!--ajax异步请求，当用户输入用户名时检查是否已存在该用户名-->
                $.ajax({
                    url:"register_check_username",
                    type:"post",
                    data:{"username":$(":text").val()},
                    datatype:"json",
                    success:function (data) {
                        <!--json字符串时为""，表示用户名没有重复,该用户名可以使用-->
                        if(data===""){
                            username = true;
                            $(":text").next().css({"color":"green","font-size":"16px","height":"10px"}).html("用户名可以使用");
                        }else{
                            username = false;
                            $(":text").next().css({"color":"red","font-size":"16px","height":"10px"}).html("用户名已存在");
                        }
                    }
                });
            }
        });
        <!--密码规定为6-12位数字-->
        $(":password:eq(0)").bind("input",function () {
            if ($(":password:eq(0)").val().match(/^\d{6,12}$/)){
                password = true;
                $(this).next().css({"color":"green","font-size":"16px","height":"10px"}).html("密码可以使用");
            }else{
                password = false;
                $(this).next().css({"color":"red","font-size":"16px","height":"10px"}).html("密码必须为6-12位数字");
            }
        });
        <!--两次密码输入要一致-->
        $(":password:eq(1)").bind("input",function () {
            if ($(":password:eq(1)").val()===$(":password:eq(0)").val()){
                confirm_password = true;
                $(this).next().css({"color":"green","font-size":"16px","height":"10px"}).html("两次密码输入一致");
            }else{
                confirm_password = false;
                $(this).next().css({"color":"red","font-size":"16px","height":"10px"}).html("两次密码输入不一致");
            }
        });
        <!--以上所有条件只要有一个不符合或者用户没有上传头像，则点击按钮失效-->
        $(":submit").click(function () {
            if($(":file").val()===""){
                <!--若用户没有上传头像，提示用户上传头像-->
                file = false;
                $(":file").next().css({"color":"red","font-size":"16px","height":"10px"}).html("请上传您的头像");
            }else{
                <!--检查用户上传的头像是否符合格式要求-->
                var file_suffix = $(":file").val().substring($(":file").val().lastIndexOf(".")).toUpperCase();
                <!--图片格式为png,jpg,jpeg,gif时符合要求-->
                if(file_suffix===".PNG" || file_suffix===".JPG" || file_suffix===".JPEG" || file_suffix===".GIF"){
                    file = true;
                    $(":file").next().css({"color":"green","font-size":"16px","height":"10px"}).html("您的头像已上传");
                }else{
                    file = false;
                    $(":file").next().css({"color":"red","font-size":"16px","height":"10px"}).html("图片格式不符合要求");
                }
            }
            if(username===false || password===false || confirm_password===false || file===false){
                return false;
            }
        });
    });