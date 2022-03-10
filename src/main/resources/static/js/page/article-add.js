function addSubmit() {
    var formData = new FormData($("#addForm")[0]);
    $.ajax({
        type:"POST",
        url:AdminConfig.domain+"article/add",
        dataType:"json",
        contentType: false,
        processData:false,
        data:formData,
        success:function (res) {
            if(!isNaN(res.articleId)){
                success_noti("发布成功！");
            }else{
                error_noti("发布失败！请联系管理员！");
            }
        },
        error:function (){
            error_noti("遇到错误了！请联系管理员！");
        }
    })
    return false;
}