function getLogList() {
    var LogList=null;
    $.ajax({
        url: AdminConfig.domain + "log/list/0/10000",
        type: "GET",
        dataType: "json",
        async:false,
        success: function (res) {
            if (!res.empty) {
                LogList = res.content;
            }
        }
    });
    return LogList;
}

$(function () {
    var aList=getLogList();
    $("#tb_mgr").bootstrapTable({
        toolbar: "#toolbar",
        idField: "logId",
        uniqueId: "logId",
        pagination: true,
        classes:'table table-bordered table-hover table-striped',
        search: true,
        clickToSelect: true,
        pageList:"[5,10,15,All]",
        url: "",
        data: aList,
        columns: [{
            field: "id",
            title: "ID",
            sortable: true
        }, {
            field: "userName",
            title: "操作用户",
            sortable: true,
            formatter:function (value){
                return "<a th:href='@{/user/"+value+"}' target='_blank'>"+value+"</a>"
            }
        }, {
            field: "operation",
            title: "操作",
            sortable: true
        },{
            field: "method",
            title: "方法",
            sortable: true
        },{
            field: "params",
            title: "参数"
        },{
            field: "ip",
            title: "IP",
            sortable: true
        },{
            field: "createTime",
            title: "操作时间",
            sortable: true
        }],
    })
})
