function getCategoriesList() {
    var categoriesList = null;
    $.ajax({
        url: AdminConfig.domain + "categories/list/0/10000",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
            if (!res.empty) {
                categoriesList = res.content;
            }
        }
    });
    return categoriesList;
}

function editCategories() {
    let cname = $($(".modal-body > input")[1]).val();
    $.ajax({
        url: AdminConfig.domain + "categories/edit",
        type: "PUT",
        dataType: "json",
        data: {
            categoriesId:$("#hidCId").val(),
            categoriesName: cname
        },
        success: function (res) {
            if (!isNaN(res.categoriesId)) {
                success_noti("修改成功！");
                $("#tb_mgr").bootstrapTable("updateRow", {
                    index: $("#hidCIndex").val(),
                    row: {
                        categoriesId: res.categoriesId,
                        categoriesName: res.categoriesName
                    }
                })
            } else {
                error_noti("修改失败！请联系管理员！");
            }
        }
    })
}

function addCategories() {
    $.ajax({
        url: AdminConfig.domain + "categories/add",
        type: "POST",
        dataType: "json",
        data: {
            categoriesName: $("#addCategoriesName").val()
        },
        success: function (res) {
            if (!isNaN(res.categoriesId)) {
                success_noti("添加成功！");
                $("#tb_mgr").bootstrapTable("append", {
                    categoriesId: res.categoriesId,
                    categoriesName: res.categoriesName
                })
            } else {
                error_noti("添加失败！请联系管理员！");
            }
        }
    })
}

function delCategories() {
    let id=$("#hidDeleteId").val();
    $.ajax({
        url: AdminConfig.domain + "categories/delete/"+id,
        type: "DELETE",
        dataType: "json",
        success: function (res) {
            if (res) {
                success_noti("删除成功！");
                $("#tb_mgr").bootstrapTable("removeByUniqueId", id);
            } else {
                error_noti("添加失败！请联系管理员！");
            }
        }
    })
}


$(function () {
    var aList = getCategoriesList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "categoriesId",
        uniqueId: "categoriesId",
        pagination: true,
        pageList: "[5,10,15,All]",
        classes: 'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        url: "",
        data: aList,
        columns: [{
            field: "categoriesId",
            title: "ID"
        }, {
            field: "categoriesName",
            title: "类别",
        }, {
            field: "action",
            title: "操作",
            events: operateEvents,
            formatter: AddFounction
        }],
    })
})

function AddFounction() {
    return [
        '<button id="tblCategoriesEdit" type="button" style="margin-left: 10px" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">编辑</button>',
        '<button id="tblCategoriesDel" type="button" style="margin-left: 10px" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal">删除</button>'
    ].join("")
}

window.operateEvents = {
    "click #tblCategoriesEdit": function (e, value, row, index) {
        $("#hidCId").val(row["categoriesId"]);
        $("#hidCIndex").val(index);
        $($(".modal-body > input")[1]).val(row["categoriesName"]);
    },
    "click #tblCategoriesDel": function (e, value, row, index) {
        $("#hidDeleteId").val(row["categoriesId"]);
        $($(".modal-body")[2]).html("确认删除[" + row["categoriesName"] + "]吗？");
    }
}