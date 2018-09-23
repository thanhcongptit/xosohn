function editOrder(medicamentOrderId,url){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/medicament-order/edit-ajax/index.jsp",
        data: {
            action : 'getListByOrderId',
            order_id: medicamentOrderId
        },
        success: function(result){
            window.location.href = url;
        }
    });
}

function removeDetail(orderId,detailId){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/medicament-order/edit-ajax/index.jsp",
        data: {
            action : 'removeDetail',
            order_id: orderId,
            medicamentOrderDetailId: detailId
        },
        success: function(result){
        }
    });
}

function changeQuantity(orderId,detailId,quantity){
    $.ajax({
        type: "POST",
        url: contextPath+"/admin/medicament-order/edit-ajax/index.jsp",
        data: {
            action : 'changeQuantity',
            order_id: orderId,
            medicamentOrderDetailId: detailId,
            quantity: quantity
        },
        success: function(result){
        }
    });
}

function addProduct(orderId,param){
    var url;
    if (param != "") {
       url =  contextPath + "/admin/medicament-order/edit-ajax/index.jsp?" + param;
    }
    $.ajax({
        type: "POST",
        url: url,
        data: {
            action : 'addProduct',
            order_id: orderId
        },
        success: function(result){
            $(".tclose", parent.document).click();
            $("#refreshPage", parent.document).submit();
        }
    });
}