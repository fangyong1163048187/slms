$(function(){
    var name =false;
    var buyprice = false;
    var salesprice = false;
    var stockamount = false;
    var salesamount = false;
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
    //检验进货单价
    $("#buyprice").bind("mouseover click input",function () {
        if(!$(this).val().match(/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/)){
            $("#buyprice_tip").html("×").css({"color":"red"})
            buyprice = false;
        }else{
            $("#buyprice_tip").html("✔").css({"color":"green"})
            buyprice = true;
        }
    });
    //检验销售单价
    $("#salesprice").bind("mouseover click input",function () {
        if(!$(this).val().match(/(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/)){
            $("#salesprice_tip").html("×").css({"color":"red"})
            salesprice = false;
        }else{
            $("#salesprice_tip").html("✔").css({"color":"green"})
            salesprice = true;
        }
    });
    //检验库存总量
    $("#stockamount").bind("mouseover click input",function () {
        if(!$(this).val().match(/^[123456789]\d{0,11}$/)){
            $("#stockamount_tip").html("×").css({"color":"red"})
            stockamount = false;
        }else{
            $("#stockamount_tip").html("✔").css({"color":"green"})
            stockamount = true;
        }
    });
    //检验销售总量
    $("#salesamount").bind("mouseover click input",function () {
        if(!$(this).val().match(/^([123456789]\d{0,11}|0)$/)){
            $("#salesamount_tip").html("×").css({"color":"red"})
            salesamount = false;
        }else{
            $("#salesamount_tip").html("✔").css({"color":"green"})
            salesamount = true;
        }
    });
    //检验是否可以提交数据
    $("#sub").click(function () {
        if(name===false || buyprice===false || salesprice===false || stockamount===false || salesamount===false){
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"product-insert",
                type:"post",
                data:data,
                dataType:"json",
                success:function (data) {
                    if(data.productNumber===null){
                        alert("插入数据失败")
                    }else{
                        alert("插入数据成功,您的编号为:"+data.productNumber)
                    }
                }
            });
        }
    });
});