function findSafeQuestion() {
    console.log("finding account...")
    const account = $("#account").val();
    console.log(account);
    var data = {userAccount: account}
    $.ajax({
        url: "http://localhost:8080/vacBook/user/getUserByAccount",
        data: data,
        type: "POST",
        dataType: "text",
        success: function (data) {
            console.log(typeof data)
            if (data === "") {
                console.log("no find");
                document.getElementById('question').value = "No account find, please check your input account!";
                document.getElementById('question').style = "color:red";
                layer.msg("No account find, please check your input account!");
            } else {
                console.log(data);
                document.getElementById('question').value = data;
                document.getElementById('question').style = "color:black";
            }
        },
        error: function () {
            location.href = "login";
        }
    });
}

function check() {
    console.log("checking invalid input..")
    if (document.getElementById('question').value === "No account find, please check your input account!") {
        layer.msg("No account find, please check your input account!");
    } else {
        const account = $("#account").val();
        console.log(account);
        const answer = $("#answer").val();
        console.log(answer);
        var data = {userAccount: account, answer: answer}
        $.ajax({
            url: "http://localhost:8080/vacBook/user/forgetPassword",
            data: data,
            type: "POST",
            dataType: "text",
            success: function (data) {
                console.log(typeof data)
                if (data === "") {
                    console.log(data);
                    layer.msg("Your input answer is not correct, please try again !");
                } else {
                    console.log(data);
                    sessionStorage.setItem("user_id", data)
                    location.href = "http://localhost:8080/vacBook/user/changePassword"
                }
            },
            error: function () {
                location.href = "login";
            }
        });
    }
}
