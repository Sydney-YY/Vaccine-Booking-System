function checkRegister() {
    let locationId = document.getElementById("locationId").value;
    let password = document.getElementById("password").value;
    let passwordSecond = document.getElementById("passwordSecond").value;
    let adminName = document.getElementById("adminName").value;
    let account = document.getElementById("account").value;
    <!--Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters-->
    let reg= /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
    let checkPwd = reg.test(password);

    console.log(password);




    if (password != passwordSecond) {
        layer.msg("Different password.");
        return false;
    }

    if (adminName =="") {
        layer.msg("Provider can not be empty.");
        return false;
    }

    if (account== null || account== "") {
        layer.msg("Account can't be empty");
        return false;
    }

    if (password == null || password == "") {
        layer.msg("Password can't be empty");
        return false;
    }

    if(checkPwd == false){
        layer.msg("The password is invalid! The password MUST contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters");
        return false;
    }



    if (passwordSecond == null || passwordSecond == "") {
        layer.msg("Password confirm can't be empty");
        return false;
    }



    if ( locationId == "") {
        layer.msg("Please select your address!")
        return false;
    }


    if (password == passwordSecond) {
        console.log(password);
        var data = {
            "adminName": adminName,
            "adminAccount": account,
            "adminPassword": password,
            "locationId": locationId,
        }
        $.ajax({
            url: "/vacbook/admin/register/",
            data: data,
            type: "post",
            dataType: "text",



            success: function(data){
                var code = data.code;
                console.log(code);
                console.log(data.status+"2");
                layer.msg("Congratulations on your registration, jump in one second." );
                setTimeout(function(){//两秒后跳转
                    location.href = "index";
                },2000);
            },
            error : function() {
                console.log(data.status+"1");
                layer.msg("Your account or email or phone has been registered！",{icon: 5});
            }

        });


        return true;
    }
}