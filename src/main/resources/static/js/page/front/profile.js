$("#updateProfileForm").submit(function () {
    $.ajax({
        url: Const.domain + "user/edit",
        type: "PUT",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#updateProfileForm")[0]),
        success: function (res) {
            console.log(res);
            success_noti("修改成功！");
            setTimeout(function () {
                $("#updateProfileModal").modal("hide");
                location.reload();
            }, 5 * 1000)
        },
        error: function () {
            error_noti("修改失败！");
        }
    });
    return false;
})

function profileSubmit() {
    $("#updateProfileForm").submit();
}
