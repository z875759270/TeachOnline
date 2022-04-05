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
    var bList = getCourseCategoryList();
    console.log(bList)
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
            formatter: function (value,row,index,field) {
                return '<a href="/course/info/' + row["courseId"] + '" target="_blank">' + value + '</a>'
            }
        }, {
            field: "courseCreater",
            title: "创建者",
            sortable: true,
            formatter: function (value) {
                return "<a href='/profile/" + value + "' target='_blank'>" + value + "</a>"
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
            editable: {
                type: "select",
                title: "课程状态",
                source: [
                    {value: 2, text: "待审核"},
                    {value: 1, text: "上架"},
                    {value: 0, text: "下架"}]
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
        }],
    })
    $('[data-bs-toggle="popover"]').popover({
        trigger: 'hover',
        html: true
    });
})

