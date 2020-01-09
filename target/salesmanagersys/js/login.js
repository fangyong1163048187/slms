$(function () {
    var check=false;
    /*当发生点击提交事件的时候判断用户输入是否合法*/
    $(":submit").click(function () {
        /*当用户名和密码都有输入值时*/
        if($(":text:eq(0)").val()!=="" && $(":password:eq(0)").val()!==""){
            /*发送ajax异步请求，判断用户名和密码是否匹配*/
            $.ajax({
                url:"login_ck_name_psd",
                async: false,
                type:"post",
                data:{"username":$(":text").val(),"password":$(":password").val()},
                datatype:"json",
                success:function (data) {
                    /*用户名或密码错误*/
                    if(data===""){
                        check=false;
                        $(":password").next().css("color","red").html("用户名或密码错误");
                    }else{
                        /*登陆成功*/
                        check=true;
                    }
                }
            });
            if(check===false){
                return false;
            }
        }else{
            /*用户名或密码为空时，提示用户输入用户名和密码*/
            /*check=false;*/
            $(":password").next().css("color","red").html("用户名或密码错误");
            return false;
        }
        /*/!*如果check为false，则提交事件失效*!/
        if(check===false){
            return false;
        }*/
    });
});