$("#commentForm").submit(function () {
    $.ajax({
        url: Const.domain + "courseFirstComment/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#commentForm")[0]),
        success: function (res) {
            success_noti("评论成功！")
            //异步刷新
            $("#comment_area").load(Const.domain + "courseFirstComment/comment", {
                courseId: $("#hidId").val()
            }, function (res, stat, xhr) {
                $("#commentContent").val("");
            })
        },
        error: function () {
            error_noti("评论失败，出错了！");
        }
    });
    return false;
})

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
        url: Const.domain + "courseSecondComment/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#replyForm")[0]),
        success: function (res) {
            $('#exampleModal').modal('hide');
            success_noti("回复成功！");

            $("#comment_area").load(Const.domain + "courseFirstComment/comment", {
                courseId: $("#hidId").val()
            }, function (res, stat, xhr) {
                $("#replyArea").val("");
            })
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

function RangeOnChange() {
    let val = $("#score").val();
    $("#scoreLabel p strong").html(val);
}

function addCollection() {
    $.ajax({
        url: Const.domain + "course/collection/add",
        type: "POST",
        data: {
            courseId: $("#courseInfoScript").attr("data")
        },
        success: function () {
            success_noti("收藏成功！")
            $("#collectionDiv").html("<li class=\"list-inline-item\">\n" +
                "                        <button type=\"button\" class=\"btn btn-info px-5 radius-30\" onclick=\"delCollection()\">\n" +
                "                            <i class=\"bx bx-heart mr-1\"></i>已收藏\n" +
                "                        </button>\n" +
                "                    </li>")
        }
    })
}

function delCollection() {
    $.ajax({
        url: Const.domain + "course/collection/delete",
        type: "DELETE",
        data: {
            courseId: $("#courseInfoScript").attr("data")
        },
        success: function () {
            success_noti("取消收藏成功！");
            $("#collectionDiv").html("<li class=\"list-inline-item\" th:if=\"${!isCollection}\">\n" +
                "                        <button type=\"button\" class=\"btn btn-primary px-5 radius-30\" onclick=\"addCollection()\">\n" +
                "                            <i class=\"bx bx-heart mr-1\"></i>收藏\n" +
                "                        </button>\n" +
                "                    </li>")
        }
    })
}

function workSubmit() {

    //为真时说明填写不完整
    let radioCheck =($("input[name='workAnswer']")[0].validity.valueMissing) || ($("input[name='workAnswer']")[4].validity.valueMissing)
        || ($("input[name='workAnswer']")[8].validity.valueMissing) || ($("input[name='workAnswer']")[12].validity.valueMissing)
        || ($("input[name='workAnswer']")[16].validity.valueMissing)
    //为真时说明填写完整了
    let checkboxCheck=true;
    for (let i = 0; i < 5; i++) {
        let tmpCheck=false;
        for (let j = 0; j < 4; j++) {
            tmpCheck = tmpCheck || $("input[type='checkbox']")[i*4+j].checked;
        }
        checkboxCheck = checkboxCheck && tmpCheck;
    }
    if(!radioCheck && checkboxCheck){
        for (let i = 0; i < 10; i++) {
            $.ajax({
                url: Const.domain + "homeworkAnswer/add",
                type: "POST",
                dataType: "json",
                async: false,
                contentType: false,
                processData: false,
                data: new FormData($("form[name='workForm']")[i]),
                success: function (res) {

                },
                error: function () {
                    error_noti("出错了！");
                }
            });
        }
        success_noti("成功！");
        $("#homework_area").load(Const.domain + "homework/homework", {
            courseId: $("#hidId").val()
        },function (res,stat,xhr) {
            $("#commentContent").val("");
        })
    }else{
        warning_noti("请完整作答！");
    }

}


$("#rateForm").submit(function () {
    $.ajax({
        url: Const.domain + "rate/add",
        type: "POST",
        dataType: "json",
        contentType: false,
        processData: false,
        data: new FormData($("#rateForm")[0]),
        success: function (res) {
            console.log(res);
            success_noti("评分成功！");
            $("input[type='range']").attr("disabled", "true");
            $("#btnDiv").html('<button type="button" class="btn btn-info px-5 radius-30"\n' +
                '                                    disabled><i\n' +
                '                                    class="bx bx-star mr-1"></i>已评分\n' +
                '                            </button>');
            setTimeout(function () {
                location.reload();
            }, 5 * 1000)
        },
        error: function () {
            error_noti("评分出错了！");
        }
    });
    return false;
})


$(function () {
    for (let i = 0; i < 10; i++) {
        $($("form[name='workForm']")[i]).submit(function () {
            return false;
        })
    }
})
