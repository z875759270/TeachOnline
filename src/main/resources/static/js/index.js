//获取热门标签
function getHotTags() {
    let resList = null;
    $.ajax({
        url: Const.domain + "courseTag/count",
        type: "POST",
        async: false,
        success: function (data) {
            resList = (data);
        }
    })
    return resList;
}

//获取分类
function getCategories() {
    let categoryList = null;
    $.ajax({
        url: Const.domain + "courseCategory/list",
        type: "GET",
        async: false,
        success: function (res) {
            categoryList = res.content;
        }
    })
    for (let i = 0; i < categoryList.length; i++) {
        let countCourse = 0;
        $.ajax({
            url: Const.domain + "course/list",
            type: "GET",
            async: false,
            data: {
                courseCategoryId: categoryList[i].categoryId
            },
            success: function (res) {
                countCourse = res.numberOfElements;
            }
        })
        categoryList[i]['courseNum'] = countCourse;
    }
    console.log()
    return categoryList;
}

//chart添加数据
function addData(chart,datasetIndex, label, data) {
    chart.data.labels.push(label);
    chart.data.datasets[datasetIndex].data.push(data);
    chart.update();
}

//chart删除数据
function removeData(chart,datasetIndex) {
    chart.data.labels.shift();
    chart.data.datasets[datasetIndex].data.shift();
    chart.update();
}

$(function () {
    "use strict";
    getHotTags();
    let categories = getCategories();
    let categoriesName = []
    let categoriesNum = []
    for (let i = 0; i < categories.length; i++) {
        categoriesName.push(categories[i]["categoryName"]);
        categoriesNum.push(categories[i]["courseNum"]);

        let htmlStr = $("#hotTagsUl").html();
        $("#hotTagsUl").html(htmlStr + '<li class="list-group-item d-flex bg-transparent justify-content-between align-items-center">\n' +
            '                                ' + categories[i]["categoryName"] + ' <span class="badge bg-secondary rounded-pill">' + categories[i]["courseNum"] + '</span>');
    }


// chart 1

    var ctx = document.getElementById("chart1").getContext('2d');

    var gradientStroke1 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke1.addColorStop(0, '#17c5ea');
    gradientStroke1.addColorStop(1, '#17c5ea');

    var gradientStroke2 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke2.addColorStop(0, '#8e54e9');
    gradientStroke2.addColorStop(1, '#8e54e9');


    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['','','','','','','','','',''],
            datasets: [{
                label: 'CPU占用',
                data: [20,10,20,30,40,20,30,50,30,25],
                borderColor: gradientStroke1,
                backgroundColor: gradientStroke1,
                hoverBackgroundColor: gradientStroke1,
                fill: false,
            }
            , {
                label: '磁盘占用',
                data: [0,0,0,0,0,0,0,0,0,0],
                borderColor: gradientStroke2,
                backgroundColor: gradientStroke2,
                hoverBackgroundColor: gradientStroke2,
                fill: false,
            }
            ]
        },

        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                yAxes : [{
                    ticks : {
                        max : 100,
                        min : 0
                    }
                }]
            },
            hover: {
                animationDuration: 0  // 防止鼠标移上去，数字闪烁
            },

        }
    });
    setInterval(function () {
        $.ajax({
            url: "http://localhost:8092/actuator/metrics/system.cpu.usage",
            type: "GET",
            async: false,
            success: function (res) {
                let percent = res.measurements[0].value;
                percent = percent.toFixed(3);
                addData(myChart,0,'',percent*100);
                $.ajax({
                    url: "http://localhost:8092/actuator/health",
                    type: "GET",
                    async: false,
                    success: function (res) {
                        let total = res.components.diskSpace.details.total;
                        let free = res.components.diskSpace.details.free;
                        let percent = ((total-free)/total).toFixed(3);
                        addData(myChart,1,'',percent*100);
                        if(myChart.data.datasets[0].data.length>=10){
                            removeData(myChart,0);
                        }
                        if(myChart.data.datasets[1].data.length>=10){
                            removeData(myChart,1);
                        }
                    }
                });
            }
        });
    }, 800)


// chart 2

    var ctx = document.getElementById("chart2").getContext('2d');

    var gradientStroke1 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke1.addColorStop(0, '#fc4a1a');
    gradientStroke1.addColorStop(1, '#f7b733');

    var gradientStroke2 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke2.addColorStop(0, '#4776e6');
    gradientStroke2.addColorStop(1, '#8e54e9');


    var gradientStroke3 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke3.addColorStop(0, '#ee0979');
    gradientStroke3.addColorStop(1, '#ff6a00');

    var gradientStroke4 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke4.addColorStop(0, '#42e695');
    gradientStroke4.addColorStop(1, '#3bb2b8');

    var gradientStroke5 = ctx.createLinearGradient(0, 0, 0, 300);
    gradientStroke5.addColorStop(0, '#0d6efd');
    gradientStroke5.addColorStop(1, '#0d6efd');

    var myChart2 = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: categoriesName,
            datasets: [{
                backgroundColor: [
                    gradientStroke1,
                    gradientStroke2,
                    gradientStroke3,
                    gradientStroke4,
                    gradientStroke5
                ],
                hoverBackgroundColor: [
                    gradientStroke1,
                    gradientStroke2,
                    gradientStroke3,
                    gradientStroke4,
                    gradientStroke5
                ],
                data: categoriesNum,
                borderWidth: [1, 1, 1, 1]
            }]
        },
        options: {
            maintainAspectRatio: false,
            cutoutPercentage: 75,
            legend: {
                position: 'bottom',
                display: false,
                labels: {
                    boxWidth: 8
                }
            },
            tooltips: {
                displayColors: false,
            }
        }
    });



});
   