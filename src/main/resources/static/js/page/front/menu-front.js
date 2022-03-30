function getHotTag(){
    $.ajax({
        url: Const.domain+"courseTag/count",
        type: "POST",
        success:function(res){
            console.log(res);
        }
    })
}


$(function () {
    getHotTag();
})