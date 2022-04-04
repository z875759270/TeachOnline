function getTopicList() {
    var topicList = null;
    $.ajax({
        url: Const.domain + "topic/list/0/10000",
        type: "GET",
        dataType: "json",
        async: false,
        success: function (res) {
            if (!res.empty) {
                topicList = res.content;
            }
        }
    });
    return topicList;
}

$(function () {
    var aList = getTopicList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "topicId",
        uniqueId: "topicId",
        pagination: true,
        classes: 'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList: "[5,10,15,All]",
        url: "",
        data: aList,
        onEditableSave: function (field, row, oldValue, $el) {
            $.ajax({
                url: Const.domain + "topic/edit",
                type: "PUT",
                data: {
                    topicId: row['topicId'],
                    topicStatus: row['topicStatus']
                },
                success: function (res) {
                    success_noti("操作成功！");
                }
            })
        },
        columns: [{
            field: "topicId",
            title: "ID",
            sortable: true
        }, {
            field: "topicTitle",
            title: "标题",
            sortable: true,
            formatter: function (value, row) {
                return '<a href="/topic/info/' + row["topicId"] + '" target="_blank">' + value + '</a>'
            }
        }, {
            field: "topicDetail",
            title: "详情",
            formatter: function (value) {
                return "<button type=\"button\" class=\"btn btn-primary\" " +
                "data-bs-toggle=\"popover\" title=\"详情\" data-bs-placement=\"top\"" +
                "data-bs-content=\"" + value.substring(0, 100) + (value.length > 50 ? "..." : "") + "\">点击查看</button>"
            }
        }, {
            field: "topicViews",
            title: "浏览量",
            sortable: true
        }, {
            field: "topicCreateTime",
            title: "创建时间",
            sortable: true,
            formatter: function (value) {
                let time = value.split('.')[0]
                let ab = time.split('T');
                return ab[0] + ' ' + ab[1];
            }
        }, {
            field: "topicStatus",
            title: "话题状态",
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

    $('[data-bs-toggle="popover"]').popover({
        trigger: 'hover',
        html: true
    });
})
