function getCourseList() {
    var courseList = null;
    $.ajax({
        url: Const.domain + "course/list",
        type: "GET",
        dataType: "json",
        async: false,
        data: {
            courseCreater: document.getElementById('courseMineScript').getAttribute('data')
        },
        success: function (res) {
            if (!res.empty) {
                courseList = res.content;
            }
        }
    });
    return courseList;
}

function getCourseCategoryList() {
    var categoryList = [];
    $.ajax({
        url: Const.domain + "courseCategory/list",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
            if (!res.empty) {
                let content = res.content;
                console.log(content);
                for (let i = 0; i < content.length; i++) {
                    categoryList.push(
                        {value: content[i].categoryId, text: content[i].categoryName}
                    )
                }
            }
        }
    });
    return categoryList;
}

function getTagList() {
    var tagList = null;
    $.ajax({
        url: Const.domain + "tag/list",
        type: "GET",
        dataType: "json",
        async: false,
        data: {
            courseCreater: document.getElementById('courseMineScript').getAttribute('data')
        },
        success: function (res) {
            if (!res.empty) {
                tagList = res.content;
            }
        }
    });
    return tagList;
}

function delCourse() {
    let id = $($("input[name='hidId']")[0]).val()
    $.ajax({
        url: Const.domain + "course/edit",
        type: "PUT",
        dataType: "json",
        data: {
            courseId: id,
            courseStatus: 0
        },
        success: function (res) {
            if (res) {
                success_noti("下架成功！");
                setTimeout(function () {
                    location.reload();
                }, 5 * 1000)
            } else {
                error_noti("下架失败！请联系管理员！");
            }
        }
    })
}

function setNotice() {
    let id = $($("input[name='hidId']")[0]).val()
    $.ajax({
        url: Const.domain + "course/edit",
        type: "PUT",
        dataType: "json",
        data: {
            courseId: id,
            courseNotice: $("#courseNotice").val()
        },
        success: function (res) {
            if (res) {
                success_noti("发布成功！");
            } else {
                error_noti("发布失败！请联系管理员！");
            }
        }
    })
}

function addHomeWork() {
    for (let i = 0; i < 5; i++) {
        $.ajax({
            url: Const.domain + "/homework/add",
            type: "POST",
            async:false,
            dataType: "json",
            contentType: false,
            processData: false,
            data: new FormData($($("form[name='radioForm']")[i])[0]),
            success: function (res) {
            },
            error: function () {
                error_noti("出错了！");
            }
        });
    }

    for (let i = 0; i < 5; i++) {
        $.ajax({
            url: Const.domain + "/homework/add",
            type: "POST",
            dataType: "json",
            async:false,
            contentType: false,
            processData: false,
            data: new FormData($($("form[name='checkBoxForm']")[i])[0]),
            success: function (res) {
            },
            error: function () {
                error_noti("出错了！");
            }
        });
    }
    success_noti("成功！");
}



function getCourseUser() {
    $("#userModalBody").html("");
    $.ajax({
        url: Const.domain + "courseUser/list",
        type: "GET",
        data: {
            courseId: $($("input[name='hidId']")[0]).val()
        },
        success: function (res) {
            if (res.numberOfElements === 0) {
                $("#userModalBody").html("暂无用户学习该课程！");
                return;
            }
            let content = res.content;
            for (let i = 0; i < content.length; i++) {
                let userName = content[i].userName;
                $.ajax({
                    url: Const.domain + "user/find/" + userName,
                    type: "GET",
                    success: function (res) {
                        let tempStr = $("#userModalBody").html();
                        let appendStr = '<div class="chip">\n' +
                            '<a href="/profile/' + res.userName + '" target="_blank">' +
                            '<img src="/media/user/img/' + res.userImg + '">' + res.userName + '</a></div>'
                        $("#userModalBody").html(tempStr + appendStr);
                    }
                })
            }
        }
    })
}

$(function () {
    var aList = getCourseList();
    var bList = getCourseCategoryList();
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
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                url: Const.domain + "course/edit",
                type: "PUT",
                data: {
                    courseId: row['courseId'],
                    courseCategoryId: row['courseCategoryId'],
                    courseStatus: row['courseStatus']
                },
                success: function (res) {
                    success_noti("操作成功！");
                }
            })
        },
        columns: [{
            field: "courseId",
            title: "课程号",
            sortable: true
        }, {
            field: "courseName",
            title: "课程名称",
            sortable: true,
            formatter: function (value, row, index, field) {
                return '<a href="/course/info/' + row["courseId"] + '" target="_blank">' + value + '</a>'
            }
        }, {
            field: "courseCategoryId",
            title: "分类",
            sortable: true,
            editable: {
                type: "select",
                title: "分类",
                source: bList
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
            field: "courseViews",
            title: "浏览量",
            sortable: true,
        }, {
            field: "courseImg",
            title: "封面",
            formatter: function (value) {
                return "<img src='" + "/media/" + "course/img/" + value + "' class=\"product-img-2\" " +
                    "data-bs-toggle=\"popover\" data-bs-container=\"body\" data-bs-placement=\"top\" " +
                    "data-bs-content=\"<img src='/media/course/img/" + value + "' class='img-fluid'>\">"
            }
        }, {
            field: "courseCreateTime",
            title: "创建时间",
            sortable: true,
            formatter: function (value) {
                let time = value.split('.')[0]
                let ab = time.split('T');
                return ab[0] + ' ' + ab[1];
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

    for (let i = 0; i < 5; i++) {
        $($("form[name='radioForm']")[i]).submit(function () {return false;})
    }

    for (let i = 0; i < 5; i++) {
        $($("form[name='checkBoxForm']")[i]).submit(function () {return false;})
    }

})

function AddFounction(value, row, index) {
    let hasWork=false;
    $.ajax({
        url: Const.domain+"homework/list",
        type: "GET",
        async:false,
        data: {
            courseId:row['courseId']
        },
        success:function(res){
            if (res.numberOfElements>0){
                hasWork=true;
            }
        }
    })
    return [
        '<button id="tblCourseNotice" type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#noticeModal">发布公告</button>',
        '<button id="tblCourseUser" type="button" style="margin-left: 10px" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#userModal">成员列表</button>',
        (!hasWork)?'<button id="tblCourseWorkAdd" type="button" style="margin-left: 10px" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#workModal">添加作业</button>':'',
        '<button id="tblCourseFileAdd" type="button" style="margin-left: 10px" class="btn btn-success">上传课件</button>',
        '<button id="tblCourseEdit" type="button" style="margin-left: 10px" class="btn btn-primary">编辑</button>',
        '<button id="tblCourseDel" type="button" style="margin-left: 10px" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal">下架</button>'
    ].join("")
}

window.operateEvents = {
    "click #tblCourseNotice": function (e, value, row, index) {
        $($("input[name='hidId']")[0]).val(row["courseId"]);
        $("#courseNotice").val(row["courseNotice"]);
    },
    "click #tblCourseUser": function (e, value, row, index) {
        $($("input[name='hidId']")[0]).val(row["courseId"]);
        getCourseUser();
    },
    "click #tblCourseWorkAdd": function (e, value, row, index) {
        $("input[name='courseId']").val(row["courseId"]);
    },
    "click #tblCourseFileAdd": function (e, value, row, index) {
        location.href = "/back/course/file/upload/" + row["courseId"];
    },
    "click #tblCourseEdit": function (e, value, row, index) {
        location.href = "/back/course/edit/" + row["courseId"]
    },
    "click #tblCourseDel": function (e, value, row, index) {
        $($("input[name='hidId']")[0]).val(row["courseId"]);
        $("#delModalBody").html("确认删除[" + row["courseName"] + "]吗？该操作不可撤销！");
    }
}