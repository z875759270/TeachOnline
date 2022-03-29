function getCourseList() {
    var courseList = null;
    $.ajax({
        url: Const.domain + "course/list",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
            if (!res.empty) {
                courseList = res.content;
            }
        }
    });
    return courseList;
}

function delCourse() {
    let id = $($("input[name='hidId']")[0]).val()
    $.ajax({
        url: Const.domain + "course/delete/" + id,
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
    var aList = getCourseList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "courseId",
        uniqueId: "courseId",
        pagination: true,
        classes: 'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList: "[5,10,15,All]",
        url: "",
        data: aList,
        columns: [{
            field: "courseId",
            title: "课程号",
            sortable: true
        }, {
            field: "courseName",
            title: "课程名称",
            sortable: true
        }, {
            field: "courseCreater",
            title: "创建者",
            sortable: true,
            formatter: function (value) {
                return "<a href='/profile/" + value + "' target='_blank'>"+value+"</a>"
            }
        }, {
            field: "courseCategoryId",
            title: "分类",
            sortable: true,
            formatter: function (value) {
                let res = "";
                $.ajax({
                    url: "/courseCategory/find/" + value,
                    type: "GET",
                    async:false,
                    success: function (data) {
                        res = data.categoryName;
                    }
                })
                return res;
            }
        }, {
            field: "courseCreateTime",
            title: "创建时间",
            sortable: true,
            formatter:function (value) {
                let time = value.split('.')[0]
                let ab = time.split('T');
                return ab[0]+' '+ab[1];
            }
        }, {
            field: "courseViews",
            title: "浏览量",
            sortable: true,
        }, {
            field: "courseImg",
            title: "封面",
            formatter: function (value) {
                return "<img src='" + Const.oss + "course/img/" + value + "' class=\"product-img-2\">"
            }
        }, {
            field: "courseStatus",
            title: "状态",
            sortable: true,
            formatter: function (value) {
                if (value === 2) {
                    return "<div class=\"badge rounded-pill text-warning bg-light-warning p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>待审核</div>";
                } else if (value === 1) {
                    return "<div class=\"badge rounded-pill text-success bg-light-success p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>正常</div>"
                } else if (value === 0) {
                    return "<div class=\"badge rounded-pill text-danger bg-light-danger p-2 px-3\"><i class=\"bx bxs-circle me-1\"></i>下架</div>"
                }
            }
        }, {
            field: "action",
            title: "操作",
            events: operateEvents,
            formatter: AddFounction
        }],
    })
})

function AddFounction(value, row, index) {
    return [
        '<button id="tblCourseInfo" type="button" class="btn btn-success">查看详情</button>',
        '<button id="tblCourseEdit" type="button" style="margin-left: 10px" class="btn btn-primary">编辑</button>',
        '<button id="tblCourseDel" type="button" style="margin-left: 10px" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal">删除</button>'
    ].join("")
}

window.operateEvents = {
    "click #tblCourseInfo": function (e, value, row, index) {
        location.href = Const.domain + "course/info/" + row["courseId"];
    },
    "click #tblCourseEdit": function (e, value, row, index) {
        location.href = Const.domain + "course/edit/" + row["courseId"];
    },
    "click #tblCourseDel": function (e, value, row, index) {
        $($("input[name='hidId']")[0]).val(row["courseId"]);
        $($(".modal-body")[0]).html("确认删除[" + row["courseId"] + "]吗？");
    }
}