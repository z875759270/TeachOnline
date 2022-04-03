function getUserList() {
    var userList = null;
    $.ajax({
        url: Const.domain + "user/list",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
            if (!res.empty) {
                userList = res.content;
            }
        }
    });
    return userList;
}

$(function () {

    var aList = getUserList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "userName",
        uniqueId: "userName",
        pagination: true,
        classes: 'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList: "[5,10,15,All]",
        url: "",
        data: aList,
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                url: Const.domain + "user/edit",
                type: "POST",
                data: {
                    userName: row['userName'],
                    userRole: row['userRole'],
                    userStatus: row['userStatus']
                },
                success: function (res) {
                    success_noti("操作成功！");
                }
            })
        },
        columns: [{
            field: "userName",
            title: "用户名",
            sortable: true,

        }, {
            field: "userRole",
            title: "角色",
            sortable: true,
            editable: {
                type: "select",
                title: "用户角色",
                source: [
                    {value: "ROLE_ADMIN", text: "管理员"},
                    {value: "ROLE_USER", text: "普通用户"},
                    {value: "ROLE_TEACHER", text: "教师"}]
            }
        }, {
            field: "userStatus",
            title: "用户状态",
            sortable: true,
            editable: {
                type: "select",
                title: "用户角色",
                source: [
                    {value: 2, text: "审核中"},
                    {value: 1, text: "正常"},
                    {value: 0, text: "冻结中"}]
            }
        }],
    })
})

