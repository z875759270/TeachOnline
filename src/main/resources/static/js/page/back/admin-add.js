$("#regForm").submit(function () {
    $.ajax({
        url: Const.domain + "user/add/admin",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#regForm")[0]),
        success: function (res) {
            if(res.isSuccess){
                success_noti(res.msg);
            }else{
                error_noti(res.msg);
            }
        },
        error: function () {
            error_noti("出错了！");
        }
    });
    return false;
})
