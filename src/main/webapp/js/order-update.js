$(function () {
    var product_amount = true;
    var stock_amount=true;
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
            //ajax异步请求判断输入的善品数量是否超过库存数量
            $.ajax({
                url:"judgeStockAmount",
                type:"post",
                data:{"amount":amount,"number":number},
                dataType:"json",
                success:function (data) {
                    //返回true表示库存充足
                    if(data.isSuccess===true){
                        $("#product_amount_tip").html("✔").css({"color":"green"})
                        stock_amount = true;
                        product_amount = true;
                    }else{
                        $("#product_amount_tip").html("×").css({"color":"red"})
                        product_amount = false;
                        stock_amount = false;
                    }
                }
            });
        }
    });
    //检验是否可以提交数据
    $("#sub").click(function () {
        if(product_amount===false){
            if(stock_amount===false){
                alert("库存不足");
            }
            return false;
        }else{
            var data = $(".form").serializeArray();
            $.ajax({
                url:"order-update",
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