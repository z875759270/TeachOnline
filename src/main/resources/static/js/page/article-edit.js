function editSubmit() {
    var formData = new FormData($("#editForm")[0]);
    $.ajax({
        type:"PUT",
        url:AdminConfig.domain+"article/edit",
        dataType:"json",
        contentType: false,
        processData:false,
        data:formData,
        success:function (res) {
            if(!isNaN(res.articleId)){
                success_noti("修改成功！");
            }else{
                error_noti("修改失败！请联系管理员！");
            }
        },
        error:function (){
            error_noti("遇到错误了！请联系管理员！");
        }
    })
    return false;
}