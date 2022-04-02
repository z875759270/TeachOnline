$("#commentForm").submit(function () {
    $.ajax({
        url: Const.domain + "topicFirstComment/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#commentForm")[0]),
        success: function (res) {
            success_noti("评论成功！")
            location.reload();
        },
        error: function () {
            error_noti("评论失败，出错了！");
        }
    });
    return false;
})

function addLike() {
    $.ajax({
        url: Const.domain + "topic/like/add",
        type: "POST",
        data: {
            topicId: $("#topicInfoScript").attr("data")
        },
        success: function () {
            $("#likeDiv").html("<li class=\"list-inline-item\">\n" +
                "                        <button type=\"button\" class=\"btn btn-info px-5 radius-30\" onclick=\"cancelLike()\">\n" +
                "                            <i class=\"bx bx-like mr-1\"></i>已点赞\n" +
                "                        </button>\n" +
                "                    </li>")
        }
    })
}

function cancelLike() {
    $.ajax({
        url: Const.domain + "topic/like/delete",
        type: "DELETE",
        data: {
            topicId: $("#topicInfoScript").attr("data")
        },
        success: function () {
            $("#likeDiv").html("<li class=\"list-inline-item\" th:if=\"${!isCollection}\">\n" +
                "                        <button type=\"button\" class=\"btn btn-primary px-5 radius-30\" onclick=\"addLike()\">\n" +
                "                            <i class=\"bx bx-like mr-1\"></i>点赞\n" +
                "                        </button>\n" +
                "                    </li>")
        }
    })
}

$('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var commentId = button.data('whatever') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this);
    modal.find('.modal-title').text('回复用户');
    modal.find('.modal-body input').val(commentId);
})

$("#replyForm").submit(function () {
    $.ajax({
        url: Const.domain + "topicSecondComment/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#replyForm")[0]),
        success: function (res) {
            $('#exampleModal').modal('hide');
            success_noti("回复成功！");
            setTimeout(function () {
                location.reload();
            }, 5 * 1000)
        },
        error: function () {
            warning_noti("回复失败，不可重复回复！");
            setTimeout(function () {
                $('#exampleModal').modal('hide');
            }, 5 * 1000)
        }
    });
    return false;
})


function ConfirmReply() {
    $("#replyForm").submit()
}

$(function () {
    var testEditor = editormd.markdownToHTML("test-editor", {
        htmlDecode: "style,script,iframe",
        path: "/plugins/editormd/lib/",
        tocm: true,         // Using [TOCM]
        tex: true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart: true,             // 开启流程图支持，默认关闭
        sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭
    });
})