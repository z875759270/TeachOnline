$("#courseImgUpload").fileinput({
    language: 'zh',
    theme: 'fas',
    required: true,
    browseOnZoneClick: true,
    showUpload: false,
    uploadUrl: Const.domain + 'course/upload',
    allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif'],
    allowedFileTypes: ['image'],
    dropZoneTitle: '拖拽图片文件到这里 &hellip;<br>支持jpg/jpeg/png/gif格式',
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
        $("#courseImg").val(response.resUrl);
        $("#courseVideoUpload").fileinput("upload");

    }
})

$("#courseVideoUpload").fileinput({
    language: 'zh',
    theme: 'fas',
    required: true,
    browseOnZoneClick: true,
    showUpload: false,
    uploadUrl: Const.domain + 'course/upload',
    allowedFileExtensions: ['mp4'],
    dropZoneTitle: '拖拽视频文件到这里 &hellip;<br>支持mp4格式',
    dropZoneClickTitle: '<br>(或点击选择文件)',
    maxFileSize: 1024 * 500, //KB
    maxFilesNum: 1,
    fileActionSettings: {
        showUpload: false,
    }
}).on("fileuploaded", function (e, data, previewiId, index) {
    var response = data.response;
    if (response.flag) {
        //将返回的文件名传入input:hidden
        $("#courseVideo").val(response.resUrl);
        //添加
        $.ajax({
            url: Const.domain + "course/add",
            type: "POST",
            dataType: "json",
            contentType: false,
            processData: false,
            data: new FormData($("#uploadForm")[0]),
            success: function (res) {
                success_noti("成功！");
                let tagsList = $("#tagsInput").tagsinput("items");
                for (let i = 0; i < tagsList.length; i++) {
                    addTag(tagsList[i],res.courseId);
                }
            },
            error: function () {
                error_noti("出错了！");
            }
        });
    }
})



function addTag(tagName,courseId){
    $.ajax({
        url: Const.domain + "tag/add",
        type: "POST",
        dataType: "json",
        async:false,
        data:{
            tagName:tagName
        },
        success: function (res) {
            console.log("标签添加成功！")
            $.ajax({
                url: Const.domain + "courseTag/add",
                type: "POST",
                dataType: "json",
                async:false,
                data:{
                    tagId:res.tagId,
                    courseId:courseId
                },
                success: function (res) {
                    console.log("课程链接标签成功！");
                },
                error: function () {
                    console.log("课程链接标签出错了！");
                }
            });
        },
        error: function () {
            console.log("添加标签出错了！");
        }
    });
}


$("#uploadForm").submit(function () {
    let tagsList = $("#tagsInput").tagsinput("items");
    if(tagsList.length===0){
        warning_noti("请添加合适的标签！");
    }else{
        $("#courseImgUpload").fileinput("upload");
    }

    return false;
})


$(function () {
    $("#tagsInput").tagsinput({
        maxTags: 3,
        maxChars: 10,
        trimValue: true
    })
})