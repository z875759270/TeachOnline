function getIllegalList() {
    var illegalList = null;
    $.ajax({
        url: Const.domain + "illegal/list/0/10000",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
            if (!res.empty) {
                illegalList = res.content;
            }
        }
    });
    return illegalList;
}

function delIllegal() {
    let id = $($("input[name='hidId']")[0]).val()
    $.ajax({
        url: Const.domain + "illegal/delete/" + id,
        type: "DELETE",
        dataType: "json",
        success: function (res) {
            if (res) {
                success_noti("删除成功！");
                $("#tb_mgr").bootstrapTable('removeByUniqueId', id);
            } else {
                error_noti("删除失败！请联系管理员！");
            }
        }
    })


}

$(function () {


    var aList = getIllegalList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "illegalId",
        uniqueId: "illegalId",
        pagination: true,
        classes: 'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList: "[5,10,15,All]",
        url: "",
        data: aList,
        columns: [{
            field: "illegalId",
            title: "ID",
            sortable: true
        }, {
            field: "illegalType",
            title: "违规类型",
            sortable: true,
            formatter: function (value) {
                if (value === "topic") {
                    return "讨论";
                } else if (value === "course") {
                    return "课程"
                } else if (value === "user") {
                    return "用户"
                }
            }
        }, {
            field: "illegalImg",
            title: "违规截图",
            sortable: true,
            formatter: function (value) {
                return "<img src='" + "/media/" + "illegal/img/" + value + "' class=\"product-img-2\" " +
                    "data-bs-toggle=\"popover\" data-bs-container=\"body\" data-bs-placement=\"top\" " +
                    "data-bs-content=\"<img src='/media/illegal/img/" + value + "' class='img-fluid'>\">"
            }
        }, {
            field: "illegalDetail",
            title: "违规原因描述",
            formatter: function (value) {
                return "<button type=\"button\" class=\"btn btn-primary\" " +
                    "data-bs-toggle=\"popover\" title=\"违规原因描述\" data-bs-placement=\"top\"" +
                    "data-bs-content=\"" + value + "\">点击查看</button>"
            }
        }, {
            field: "userName",
            title: "举报者",
            sortable: true,
            formatter: function (value) {
                return "<a href='/profile/" + value + "' target='_blank'>" + value + "</a>"
            }
        }, {
            field: "illegalTime",
            title: "举报时间",
            sortable: true,
            formatter: function (value) {
                let time = value.split('.')[0]
                let ab = time.split('T');
                return ab[0] + ' ' + ab[1];
            }
        }, {
            field: "illegalStatus",
            title: "用户状态",
            sortable: true,
            formatter: function (value) {
                if (value === 2) {
                    return "<div class=\"badge rounded-pill text-warning bg-light-warning p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>待审核</div>";
                } else if (value === 1) {
                    return "<div class=\"badge rounded-pill text-success bg-light-success p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>未违规</div>"
                } else if (value === 0) {
                    return "<div class=\"badge rounded-pill text-danger bg-light-danger p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>违规，已处理</div>"
                }
            }
        }, {
            field: "action",
            title: "操作",
            events: operateEvents,
            formatter: AddFounction
        }],
    })

    $('[data-bs-toggle="popover"]').popover({
        trigger: 'hover',
        html: true
    });
})

function AddFounction(value, row, index) {
    return [
        '<button id="tblIllegalInfo" type="button" class="btn btn-success">查看详情</button>',
        '<button id="tblIllegalEdit" type="button" style="margin-left: 10px" class="btn btn-primary">编辑</button>',
        '<button id="tblIllegalDel" type="button" style="margin-left: 10px" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal">删除</button>'
    ].join("")
}

window.operateEvents = {
    "click #tblIllegalInfo": function (e, value, row, index) {
        location.href = Const.domain + "illegal/info/" + row["illegalId"];
    },
    "click #tblIllegalEdit": function (e, value, row, index) {
        location.href = Const.domain + "illegal/edit/" + row["illegalId"];
    },
    "click #tblIllegalDel": function (e, value, row, index) {
        $($("input[name='hidId']")[0]).val(row["illegalId"]);
        $($(".modal-body")[0]).html("确认删除[" + row["illegalId"] + "]吗？");
    }
}