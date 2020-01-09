$(function(){
    var number = true;
    var name =true;
    var linkman = true;
    var linkphone = true;
    var linkaddress = true;
    //检验编号
    $("#number").bind("mouseover click input",function () {
        if(!$(this).val().match(/^CD\d{18}$/)){
            $("#number_tip").html("×").css({"color":"red"})
            number = false;
        }else{
            $("#number_tip").html("✔").css({"color":"green"})
            number = true;
        }
    });
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
    //检验销售总量
    $("#salesamount").bind("mouseover click input",function () {
        if(!$(this).val().match(/^[123456789]\d{0,11}$/)){
            $("#salesamount_tip").html("×").css({"color":"red"})
            salesamount = false;
        }else{
            $("#salesamount_tip").html("✔").css({"color":"green"})
            salesamount = true;
        }
    });
    //检验是否可以提交数据
    $("#sub").click(function () {
        if(number===false || name===false || linkman===false || linkphone===false || linkaddress===false){
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"supplier-update-after",
                type:"post",
                data:data,
                dataType:"json",
                success:function (data) {
                    if(data.isSuccess===true){
                        alert("更改数据成功");
                        var number = $("#number").val();
                        var name = $("#name").val();
                        window.location.href="supplier_select?supplier_id="+number+"&supplier_name="+name;
                    }else{
                        alert("更改数据失败");
                    }
                }
            });
        }
    });
});