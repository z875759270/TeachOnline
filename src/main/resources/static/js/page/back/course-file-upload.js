$("#courseFileUpload").fileinput({
    language: 'zh',
    theme: 'explorer-fas',
    required: true,
    browseOnZoneClick: true,
    showUpload: false,
    uploadAsync:false,
    uploadUrl: Const.domain + 'courseFile/add',
    allowedFileExtensions: ['jpg', 'jpeg', 'png', "doc", "docx", "pdf", "ppt", "pptx", "mp4", "txt"],
    dropZoneTitle: '拖拽文件到这里 &hellip;<br>支持图片、视频、文档',
    dropZoneClickTitle: '<br>(或点击选择文件)',
    maxFileSize: 1024 * 100, //KB
    maxFilesNum: 5,
    maxFileCount:5,
    allowedPreviewTypes:['image','video','text'],
    fileActionSettings: {
        showUpload: false,
    },
    uploadExtraData: {
        courseId: $("#hidCourseId").val()
    }
}).on("fileuploaded", function (e, data, previewiId, index) {
    success_noti("上传成功！")
})

$("#uploadForm").submit(function () {
    $("#courseFileUpload").fileinput("upload");
    return false;
})
