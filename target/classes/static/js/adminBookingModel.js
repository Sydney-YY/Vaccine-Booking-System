/**
 * js for the adminBookingModel
 */
function del_booking(btn,id) {
    layer.confirm('Are you sure you want to delete this Booking now <br> And send reminder email?', {
        btn: ["Confirm", "Cancel"],
        icon: 2,
        title: "Delete Booking Warning!"
    }, function () {
        //点击确后关闭提示框
        layer.closeAll('dialog');

        /*$.get("/vacbook/booking/reject/"+id, function(result){
            if(result){
                $(btn).parent().parent().remove()
                layer.msg('The booking has been deleted !')
            }
        });*/
        $.ajax({
            url: "/vacbook/booking/reject/"+id,
            type: "get",
            beforeSend: function (){
                sendRejectEmail(id);
                addVaccine(id);
            },
            success:function (result){
                if(result){

                    $(btn).parent().parent().remove()
                    layer.msg('The booking has been deleted!<br>'
                        +"And reminder email has been sent successfully")
                }
            },
        });
    });
}

function sendRejectEmail(id){
    var data = {
        "booking_id": id,
    }
    $.ajax({
        url: "/vacbook/booking/sendRejectEmail/",
        data: data,
        type: "post",
        dataType: "json",
    });

}

function addVaccine(id){
    var data = {
        "bookingID": id,
    };
    $.ajax({
        url: "/vacbook/vaccine/addVaccine",
        data: data,
        type: "post",
        dataType: "json",
    });
}
