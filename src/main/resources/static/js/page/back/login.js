$("#loginForm").submit(function () {
    $.ajax({
        url: Const.domain + "user/loginCheck",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#loginForm")[0]),
        success: function (res) {
            if(res.isLoginSuccess) {
                if(res.role === "ROLE_USER"){
                    warning_noti("该账户不是管理员或教师！");
                }else{
                    location.href = Const.domain + "back/index";
                }

            }
            else{
                error_noti(res.msg);
            }

        },
        error: function () {
            error_noti("出错了！");
        }
    });
    return false;
})
