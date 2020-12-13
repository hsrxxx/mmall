$(function(){
    //计算总价
    var array = $(".qprice");
    var totalCost = 0;
    for(var i = 0;i < array.length;i++){
        var val = parseInt($(".qprice").eq(i).html().substring(1));
        totalCost += val;
    }
    $("#totalprice").html("￥"+totalCost);
    //settlement2使用
    $("#settlement2_totalCost").val(totalCost);
});

//商品数量++
function addQuantity(obj){
    let index = $(".car_btn_2").index(obj);
    let id = $(".id").eq(index).val();
    let stock = parseInt($(".productStock").eq(index).val());
    let price = parseFloat($(".productPrice").eq(index).val());
    let quantity = parseInt($(".car_ipt").eq(index).val());
    if(quantity == stock){
        alert("库存不足！");
        return false;
    }
    quantity++;
    let cost = quantity * price;

    //更新数据库
    $.ajax({
        url:"/cart/updateCart/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        success:function (data) {
            if(data == "success"){
                $(".car_ipt").eq(index).val(quantity);
                $(".qprice").eq(index).text('￥' + cost);
                //重新计算总价
                let array = $(".qprice");
                let totalCost = 0;
                for(let i = 0;i < array.length;i++){
                    let val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("￥"+totalCost);
            }
        }
    });
}

//商品数量--
function subQuantity(obj){
    let index = $(".car_btn_1").index(obj);
    let id = $(".id").eq(index).val();
    let price = parseFloat($(".productPrice").eq(index).val());
    let quantity = parseInt($(".car_ipt").eq(index).val());
    if(quantity == 1){
        alert("至少选择一件商品")
        return false;
    }
    quantity--;
    let cost = quantity * price;

    $.ajax({
        url:"/cart/updateCart/"+id+"/"+quantity+"/"+cost,
        type:"POST",
        success:function (data) {
            if(data == "success"){
                $(".car_ipt").eq(index).val(quantity);
                $(".qprice").eq(index).text('￥' + cost);
                //重新计算总价
                let array = $(".qprice");
                let totalCost = 0;
                for(let i = 0;i < array.length;i++){
                    let val = parseInt($(".qprice").eq(i).html().substring(1));
                    totalCost += val;
                }
                $("#totalprice").html("￥"+totalCost);
            }
        }
    });
}

//移出购物车
function removeCart(obj){
    let index = $(".delete").index(obj);
    let id = $(".id").eq(index).val();
    if(confirm("是否确定要删除？")){
        window.location.href = "/cart/deleteById/"+id;
    }
}

function settlement2() {
    let totalCost = $("#totalprice").text();
    if (totalCost == "￥0"){
        alert("购物车为空，请先添加物品！");
        return false;
    }
    window.location.href = "/cart/settlement2";
}