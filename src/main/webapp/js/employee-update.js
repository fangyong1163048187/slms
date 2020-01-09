$(function(){
    var number = true;
    var name =true;
    var phone = true;
    var email = true;
    var address = true;
    //检验编号
    $("#number").bind("mouseover click input",function () {
        if(!$(this).val().match(/^ED\d{18}$/)){
            $("#number_tip").html("×").css({"color":"red"})
            number = false;
        }else{
            $("#number_tip").html("✔").css({"color":"green"})
            number = true;
        }
    });
    //检验姓名
    $("#name").bind("mouseover click input",function () {
        if(!$(this).val().match(/^.{1,20}$/)){
            $("#name_tip").html("×").css({"color":"red"})
            name = false;
        }else{
            $("#name_tip").html("✔").css({"color":"green"})
            name = true;
        }
    });
    //检验手机号
    $("#phone").bind("mouseover click input",function () {
        if(!$(this).val().match(/^1[3456789]\d{9}$/)){
            $("#phone_tip").html("×").css({"color":"red"})
            phone = false;
        }else{
            $("#phone_tip").html("✔").css({"color":"green"})
            phone = true;
        }
    });
    //检验邮箱
    $("#email").bind("mouseover click input",function () {
        if(!$(this).val().match(/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/)){
            $("#email_tip").html("×").css({"color":"red"})
            email = false;
        }else{
            $("#email_tip").html("✔").css({"color":"green"})
            email = true;
        }
    });
    //检验地址
    $("#address").bind("mouseover click input",function () {
        if(!$(this).val().match(/^.{1,100}$/)){
            $("#address_tip").html("×").css({"color":"red"})
            address = false;
        }else{
            $("#address_tip").html("✔").css({"color":"green"})
            address = true;
        }
    });
    //检验是否可以提交数据
    $("#sub").click(function () {
        if(number===false || name===false || phone===false || email===false || address===false){
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"employee-update-after",
                type:"post",
                data:data,
                dataType:"text",
                success:function (data) {
                    if(data>0){
                        alert("更改数据成功");
                        var number = $("#number").val();
                        var name = $("#name").val();
                        window.location.href="employee_select?employee_id="+number+"&employee_name="+name;
                    }else{
                        alert("更改数据失败");
                    }
                }
            });
        }
    });
});