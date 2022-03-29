$("#regForm").submit(function () {
    $.ajax({
        url: Const.domain + "user/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#regForm")[0]),
        success: function (res) {
            if (res.userName == null) {
                warning_noti("已存在的用户名！")
            } else {
                success_noti("注册成功！五秒后跳转至登录页");
                setTimeout(function () {
                    location.href = Const.domain + "login";
                }, 5 * 1000)
            }
        },
        error: function () {
            error_noti("出错了！");
        }
    });
    return false;
})
