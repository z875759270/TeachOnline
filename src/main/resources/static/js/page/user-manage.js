function getUserList() {
    var userList=null;
    $.ajax({
        url: AdminConfig.domain + "user/list/0/10000",
        type: "GET",
        dataType: "json",
        async:false,
        success: function (res) {
            if (!res.empty) {
                userList = res.content;
            }
        }
    });
    return userList;
}

function delUser() {
    let id=$($("input[name='hidArticleId']")[0]).val()
    $.ajax({
        url:AdminConfig.domain+"user/delete/"+id,
        type:"DELETE",
        dataType:"json",
        success: function (res) {
            if(res){
                success_noti("删除成功！");
                $("#tb_mgr").bootstrapTable('removeByUniqueId', id);
            }else{
                error_noti("删除失败！请联系管理员！");
            }
        }
    })


}

$(function () {
    var aList=getUserList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "userName",
        uniqueId: "userName",
        pagination: true,
        classes:'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList:"[5,10,15,All]",
        url: "",
        data: aList,
        columns: [{
            field: "userName",
            title: "用户名",
            sortable: true
        }, {
            field: "userRole",
            title: "角色",
            sortable: true,
            formatter:function (value){
                if(value === "ROLE_ADMIN"){
                    return "管理员";
                }else if(value === "ROLE_USER"){
                    return "普通用户"
                }else if(value === "ROLE_TEACHER"){
                    return "教师"
                }
            }
        }, {
            field: "userStatus",
            title: "用户状态",
            sortable: true,
            formatter: function (value) {
                if(value === 2){
                    return "审核中";
                }else if(value === 1){
                    return "正常"
                }else if(value === 0){
                    return "冻结中"
                }
            }
        },{
            field: "action",
            title: "操作",
            events: operateEvents,
            formatter: AddFounction
        }],
    })
})

function AddFounction(value, row, index) {
    return [
        '<button id="tblUserInfo" type="button" class="btn btn-success">查看详情</button>',
        '<button id="tblUserEdit" type="button" style="margin-left: 10px" class="btn btn-primary">编辑</button>',
        '<button id="tblUserDel" type="button" style="margin-left: 10px" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal">删除</button>'
    ].join("")
}

window.operateEvents = {
    "click #tblArticleInfo": function (e, value, row, index) {
        location.href=BlogConfig.domain+"article/info/"+row["articleId"];
    },
    "click #tblArticleEdit": function (e, value, row, index) {
        location.href=AdminConfig.domain+"article/edit/"+row["articleId"];
    },
    "click #tblArticleDel": function (e, value, row, index){
        $($("input[name='hidArticleId']")[0]).val(row["articleId"]);
        $($(".modal-body")[0]).html("确认删除["+row["articleTitle"]+"]吗？");
    }
}