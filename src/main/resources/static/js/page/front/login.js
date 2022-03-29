$("#loginForm").submit(function () {
    $.ajax({
        url: Const.domain + "user/loginCheck",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#loginForm")[0]),
        success: function (res) {
            if(res) {
                location.href = Const.domain + "index";
            }
            else{
                error_noti("账号或密码错误，请重试！");
            }

        },
        error: function () {
            error_noti("出错了！");
        }
    });
    return false;
})
