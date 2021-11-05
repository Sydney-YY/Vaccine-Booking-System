/**
 * js for the adminVaccineModel
 */


$(document).on("click", ".editModal", function () {
    var myVaccineId = $(this).data('id');
    $(".modal-body #stock").val( myVaccineId );

});

function showAmount(amount, vaccineId) {
    console.log(vaccineId);
    console.log(amount);
    document.getElementById('stock').value = amount;
    document.getElementById('update_id').value = vaccineId;
}

function getDelete(vaccineId) {
    console.log(vaccineId);
    document.getElementById('delete_id').value = vaccineId;
}


function vaccine_del(delete_id) {
    layer.confirm('Are you sure you want to delete this Vaccine in stock?', {
        btn: ["Confirm", "Cancel"],
        icon: 2,
        title: "Delete Vaccine Warning!"
    }, function () {
        //点击确后关闭提示框
        layer.closeAll('dialog');
        vaccineDel(delete_id);
    });
}

function vaccineDel(delete_id) {
    //调ajax
    var data = {
        "delete_id": delete_id,
    }
    var msg = data.msg;
    $.ajax({
        url: "/vacbook/admin/vaccines/delete/",
        data: data,
        type: "post",
        success:function (data){
            $("#id_vac_container").html(data);
            layer.msg('The vaccine has been deleted !')
        },
        error:function (){
            layer.msg('Sorry You can not delete this vaccine Now!<br> Because there are some bookings for this vaccine!')
        }

    });

}

function vaccine_add() {
    layer.prompt({btn: ["Confirm", "Cancel"],title: 'Please input new vaccine type', formType: 3}, function (type, index) {

        layer.close(index);
        layer.prompt({btn: ["Confirm", "Cancel"],title: 'Please input new vaccine name', formType: 3}, function (vaccineName, index) {
            layer.close(index);
            layer.prompt({btn: ["Confirm", "Cancel"],title: 'Please input new vaccine stock', formType: 3}, function (stock, index) {
                const re = /^[0-9]+$/;
                if(!(re.test(stock))){
                    layer.close(index);
                    layer.msg('Please input 0 or Positive integer!');
                }
                else {layer.close(index);
                    layer.msg('Add success' + '<br>New vaccine type：' + type + '<br>New vaccine name：' + vaccineName + '<br>New vaccine stock1：' + stock);

                    var data = {
                        // name, String type, Integer amount
                        "type": type,
                        "name": vaccineName,
                        "amount": stock,
                    }


                    $.ajax({
                        url: "/vacbook/admin/vaccines/add",
                        data: data,
                        type: "post",
                        success:function (data){
                            $("#id_vac_container").html(data); //
                        }

                    });}



            });
        });

    });


}

function vaccine_update(vaccine_amount,update_id) {
    console.log('dd') //test
    //Integer stock, Integer update_id
    layer.prompt({value:vaccine_amount,btn: ["Confirm", "Cancel"],title: 'Please input new vaccine stock', formType: 3}, function (stock, index) {
        const re = /^[0-9]+$/;
        if(!(re.test(stock))){
            layer.close(index);
            layer.msg('Please input 0 or Positive integer!');
        }
        else{
            layer.close(index);
            layer.msg('Update success' + '<br>New vaccine stock：' + stock);
            var data = {
                // name, String type, Integer amount
                "update_id": update_id,
                "stock": stock,
            }
            $.ajax({
                url: "/vacbook/admin/vaccines/update",
                data: data,
                type: "POST",
                success:function (data){
                    $("#id_vac_container").html(data);//
                }
            });
        }




    });

}

