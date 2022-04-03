$("#courseImgUpload").fileinput({
    language: 'zh',
    theme: 'fas',
    browseOnZoneClick: true,
    showUpload: false,
    uploadUrl: Const.domain + 'course/edit/upload',
    allowedFileExtensions: ['jpg', 'jpeg', 'png', 'gif'],
    allowedFileTypes: ['image'],
    dropZoneTitle: '拖拽图片文件到这里 &hellip;<br>支持jpg/jpeg/png/gif格式',
    dropZoneClickTitle: '<br>(或点击选择文件)',
    maxFileSize: 1024 * 3, //KB
    maxFilesNum: 1,
    fileActionSettings: {
        showUpload: false,
    },
    uploadExtraData: {
        courseId:$("#courseId").val()
    }
}).on("fileuploaded", function (e, data, previewiId, index) {
    var response = data.response;
    if (response.flag) {
        //将返回的文件名传入input:hidden
        $("#courseImg").val(response.resUrl);
    }
})

$("#courseVideoUpload").fileinput({
    language: 'zh',
    theme: 'fas',
    browseOnZoneClick: true,
    showUpload: false,
    uploadUrl: Const.domain + 'course/edit/upload',
    allowedFileExtensions: ['mp4'],
    dropZoneTitle: '拖拽视频文件到这里 &hellip;<br>支持mp4格式',
    dropZoneClickTitle: '<br>(或点击选择文件)',
    maxFileSize: 1024 * 500, //KB
    maxFilesNum: 1,
    fileActionSettings: {
        showUpload: false,
    },
    uploadExtraData: {
        courseId:$("#courseId").val()
    }
}).on("fileuploaded", function (e, data, previewiId, index) {
    var response = data.response;
    if (response.flag) {
        //将返回的文件名传入input:hidden
        $("#courseVideo").val(response.resUrl);
        //添加

    }
})



$("#editForm").submit(function () {
        $.ajax({
            url: Const.domain + "course/edit",
            type: "PUT",
            dataType: "json",
            contentType: false,
            processData: false,
            data: new FormData($("#editForm")[0]),
            success: function (res) {
                success_noti("成功！");
                let imgCount = $('#courseImgUpload').fileinput('getFilesCount');
                let videoCount = $('#courseVideoUpload').fileinput('getFilesCount');
                console.log("=="+imgCount+"=="+videoCount+"==")
                if (imgCount > 0) {
                    $("#courseImgUpload").fileinput("upload");
                }
                if (videoCount > 0) {
                    $("#courseVideoUpload").fileinput("upload");
                }


            },
            error: function () {
                error_noti("出错了！");
            }
        });
    return false;
})


$(function () {
    $("#tagsInput").tagsinput({
        maxTags: 3,
        maxChars: 10,
        trimValue: true
    })
    $('#tagsInput').on('itemAdded', function(event) {
        $.ajax({
            url: Const.domain + "tag/add",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                tagName: event.item
            },
            success: function (res) {
                $.ajax({
                    url: Const.domain + "courseTag/add",
                    type: "POST",
                    dataType: "json",
                    async: false,
                    data: {
                        tagId: res.tagId,
                        courseId: $("#courseId").val()
                    },
                    success: function (res) {
                        console.log("课程链接标签成功！");
                    },
                    error: function () {
                        console.log("课程链接标签出错了！");
                    }
                });
            }
        });
    });

    $('#tagsInput').on('beforeItemRemove', function(event) {
        $.ajax({
            url: Const.domain +"tag/list",
            type: "GET",
            data: {
                tagName:event.item
            },
            success:function(res){
                let content=res.content;
                $.ajax({
                    url: Const.domain +"courseTag/delete",
                    type: "DELETE",
                    data: {
                        tagId: content[0].tagId,
                        courseId: $("#courseId").val()
                    },
                    success:function(res){
                        console.log(res);
                    }
                })
            }
        })

    });
})