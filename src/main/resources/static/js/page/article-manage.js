function getArticleList() {
    var articleList=null;
    $.ajax({
        url: AdminConfig.domain + "article/list/0/10000",
        type: "GET",
        dataType: "json",
        async:false,
        success: function (res) {
            if (!res.empty) {
                articleList = res.content;
            }
        }
    });
    return articleList;
}

function delArticle() {
    let id=$($("input[name='hidArticleId']")[0]).val()
    $.ajax({
        url:AdminConfig.domain+"article/delete/"+id,
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
    var aList=getArticleList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "articleId",
        uniqueId: "articleId",
        pagination: true,
        classes:'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList:"[5,10,15,All]",
        url: "",
        data: aList,
        columns: [{
            field: "articleId",
            title: "ID"
        }, {
            field: "articleTitle",
            title: "标题"
        }, {
            field: "categoriesId",
            title: "类别",
            formatter:function (value){
                let cname=value;
                $.ajax({
                    url:AdminConfig.domain+"categories/find/"+value,
                    type:"GET",
                    async:false,
                    dataType: "json",
                    success:function (res){
                        cname=res.categoriesName;
                    }
                })
                return cname;
            }
        }, {
            field: "articleAuthor",
            title: "作者",
        }, {
            field: "articleImg",
            title: "封面图",
            formatter: function (value) {
                return '<img src="'+AdminConfig.domain+value+'" height="50">'
            }
        }, {
            field: "articleView",
            title: "浏览量"
        }, {
            field: "articleLike",
            title: "点赞数"
        }, {
            field: "articleTime",
            title: "发布时间",
            formatter:function (value) {
                let time = value.split('.')[0]
                let ab = time.split('T');
                return ab[0]+' '+ab[1];
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
        '<button id="tblArticleInfo" type="button" class="btn btn-success">查看详情</button>',
        '<button id="tblArticleEdit" type="button" style="margin-left: 10px" class="btn btn-primary">编辑</button>',
        '<button id="tblArticleDel" type="button" style="margin-left: 10px" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delModal">删除</button>'
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