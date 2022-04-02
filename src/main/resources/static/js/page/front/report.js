$("#illegalImgUpload").fileinput({
    language: 'zh',
    theme: 'fas',
    required: true,
    browseOnZoneClick: true,
    showUpload: false,
    uploadUrl: Const.domain + 'illegal/upload',
    allowedFileExtensions: ['jpg', 'jpeg', 'png'],
    allowedFileTypes: ['image'],
    dropZoneTitle: '拖拽图片文件到这里 &hellip;<br>支持jpg/jpeg/png格式',
    dropZoneClickTitle: '<br>(或点击选择文件)',
    maxFileSize: 1024 * 3, //KB
    maxFilesNum: 1,
    fileActionSettings: {
        showUpload: false,
    }
}).on("fileuploaded", function (e, data, previewiId, index) {
    var response = data.response;
    if (response.flag) {
        //将返回的文件名传入input:hidden
        $("#illegalImg").val(response.resUrl);
        $.ajax({
            url: Const.domain + "illegal/add",
            type: "POST",
            dataType: "json",
            contentType: false,
            processData: false,
            data: new FormData($("#reportForm")[0]),
            success: function (res) {
                console.log(res);
                success_noti("举报提交成功，等待管理员处理！")
            },
            error: function () {
                error_noti("举报出错了！");
            }
        });
        return false;
    }
})

$("#reportForm").submit(function () {
    $("#illegalImgUpload").fileinput("upload");
    return false;
})