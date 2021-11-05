function checkLogin() {
    let account = document.getElementById("adminAccount").value;
    let password = document.getElementById("adminPassword").value;

    console.log(account);
    console.log(password);


    if (account.trim() == null || account == "") {
        layer.msg("Please enter your account!");
        return false;
    }

    if(password == ""){
        layer.msg("Please enter your password!");
        return false;
    }



    if (password !="") {
        var data = {
            "account": account,
            "password": password,
        }
        $.ajax({
            url: "/vacbook/admin/login",
            data: data,
            type: "post",
            dataType: "text",
            success: function(data){
                console.log(typeof data)
                console.log(data)
                if(data === "true"){
                    layer.msg("Welcome to management system!" );
                    setTimeout(function(){//两秒后跳转
                        location.href = "/vacbook/admin/base";
                    },2000);
                }else{
                    layer.msg("Your account or password is wrong！",{icon: 5});
                    return false;
                }

            },
            error : function() {
                location.href = "login";
            }

        });


        return true;
    }

}