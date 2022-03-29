function getUserList() {
    var userList=null;
    $.ajax({
        url: Const.domain + "user/list",
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
    let id=$($("input[name='hidId']")[0]).val()
    $.ajax({
        url:Const.domain+"user/delete/"+id,
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
                    return "<div class=\"badge rounded-pill text-warning bg-light-warning p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>审核中</div>";
                }else if(value === 1){
                    return "<div class=\"badge rounded-pill text-success bg-light-success p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>正常</div>";
                }else if(value === 0){
                    return "<div class=\"badge rounded-pill text-danger bg-light-danger p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>冻结中</div>"
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
    "click #tblUserInfo": function (e, value, row, index) {
        location.href=Const.domain+"user/info/"+row["userName"];
    },
    "click #tblUserEdit": function (e, value, row, index) {
        location.href=Const.domain+"user/edit/"+row["userName"];
    },
    "click #tblUserDel": function (e, value, row, index){
        $($("input[name='hidId']")[0]).val(row["userName"]);
        $($(".modal-body")[0]).html("确认删除["+row["userName"]+"]吗？");
    }
}