$("#topicAddForm").submit(function () {
    $.ajax({
        url: Const.domain + "topic/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#topicAddForm")[0]),
        success: function (res) {
            console.log(res);
            success_noti("成功！")
        },
        error: function () {
            error_noti("出错了！");
        }
    });
    return false;
})


$(function () {
    var testEditor = editormd("test-editormd", {
        width: "100%",
        height: 640,
        syncScrolling: "single",
        path: "/plugins/editormd/lib/",
        htmlDecode: "style,script,iframe",
        imageUpload: false,
        placeholder:"在此输入讨论详情...（支持MarkDown语法）",
        tocm: true,         // Using [TOCM]
        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart: true,             // 开启流程图支持，默认关闭
        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭
    });
})