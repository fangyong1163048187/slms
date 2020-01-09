$(function(){
    /*var $name = $("#name");
    var $newDom = $("<span href='#'></span>");
    var $div = $("#div_name");
    var divWidth = $div.width();
    $name.bind("mouseover click input",function () {
        if(!$(this).val().match(/^.{1,20}$/)){
            $newDom.html("×").css({"color":"red","font-size":"25px"})
        }else{
            $newDom.html("✔").css({"color":"green","font-size":"25px"})
        }
        $div.append($newDom).width(divWidth+$newDom.width());
    });*/
    var name =false;
    var phone = false;
    var email = false;
    var address = false;
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
        if(!$(this).val().match(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/)){
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
        if(name===false || phone===false || email===false || address===false){
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"employee-insert",
                type:"post",
                data:data,
                dataType:"json",
                success:function (data) {
                    if(data.employeeNumber===null){
                        alert("插入数据失败")
                    }else{
                        alert("插入数据成功,您的编号为:"+data.employeeNumber)
                    }
                }
            });
        }
    });
});