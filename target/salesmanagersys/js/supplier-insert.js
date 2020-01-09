$(function(){
    var name =false;
    var linkman = false;
    var linkphone = false;
    var linkaddress = false;
    //检验姓名
    $("#name").bind("mouseover click input",function () {
        if($(this).val()==="" || $(this).val()===null){
            $("#name_tip").html("×").css({"color":"red"})
            name = false;
        }else{
            $("#name_tip").html("✔").css({"color":"green"})
            name = true;
        }
    });
    //检验联系人
    $("#linkman").bind("mouseover click input",function () {
        if($(this).val()==="" || $(this).val()===null){
            $("#linkman_tip").html("×").css({"color":"red"})
            linkman = false;
        }else{
            $("#linkman_tip").html("✔").css({"color":"green"})
            linkman = true;
        }
    });
    //检验联系电话
    $("#linkphone").bind("mouseover click input",function () {
        if(!$(this).val().match(/^1[3456789]\d{9}$/)){
            $("#linkphone_tip").html("×").css({"color":"red"})
            linkphone = false;
        }else{
            $("#linkphone_tip").html("✔").css({"color":"green"})
            linkphone = true;
        }
    });
    //检验联系地址
    $("#linkaddress").bind("mouseover click input",function () {
        if(!$(this).val().match(/^.{1,100}$/)){
            $("#linkaddress_tip").html("×").css({"color":"red"})
            linkaddress = false;
        }else{
            $("#linkaddress_tip").html("✔").css({"color":"green"})
            linkaddress = true;
        }
    });
    //检验是否可以提交数据
    $("#sub").click(function () {
        if(name===false || linkman===false || linkphone===false || linkaddress===false){
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"supplier-insert",
                type:"post",
                data:data,
                dataType:"json",
                success:function (data) {
                    if(data.supplierNumber===null){
                        alert("插入数据失败")
                    }else{
                        alert("插入数据成功,您的编号为:"+data.supplierNumber)
                    }
                }
            });
        }
    });
});