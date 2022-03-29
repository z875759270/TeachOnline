$("#senciForm").submit(function () {
    $.ajax({
        url: Const.domain + "/back/senci/edit",
        type: "POST",
        data:{
            senciList:$("#senci").val()
        },
        success: function (res) {
            if (!res.empty) {
                alert("yes");
            }
        }
    });
    return false;
})
