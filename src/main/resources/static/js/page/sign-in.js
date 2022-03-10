function loginSubmit() {
    $.ajax({
        url: AdminConfig.domain + "user/loginCheck",
        type: "post",
        dataType: "json",
        data:{
            userName:$("#userName").val(),
            userPwd:$("#userPwd").val()
        },
        success: function (res) {
            if(res===true){
                console.log
                location.href=AdminConfig.domain;
            }else{
                alert("=_=||");
            }
        }
    })
    return false;
}