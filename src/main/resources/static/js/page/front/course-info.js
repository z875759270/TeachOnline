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
            location.reload();
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

})
