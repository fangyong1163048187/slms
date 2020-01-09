$(function () {
    var product_amount = true;
    //检验商品数量
    $("#product_amount").bind("mouseover click input",function () {
        //用户输入的商品数量
        var amount = $(this).val();
        //用户选择的商品的编号
        var number = $(".pro:selected").val();
        if(!amount.match(/^[123456789]\d*$/)){
            $("#product_amount_tip").html("×").css({"color":"red"})
            product_amount = false;
        }else{
            $("#product_amount_tip").html("✔").css({"color":"green"})
            product_amount = true;
        }
    });
    //检验是否可以提交数据
    $("#sub").click(function () {
        if(product_amount===false){
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"buy-update",
                type:"post",
                data:data,
                dataType:"json",
                success:function (data) {
                    if(data.row===null){
                        alert("修改数据失败")
                    }else{
                        alert("修改数据成功")
                    }
                }
            });
        }
    });
});