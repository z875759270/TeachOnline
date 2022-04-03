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
                location.href = Const.domain + "index";
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
