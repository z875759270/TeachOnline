var socket;
var currentUser = $("#currentUser").val();
var targetUser = $("#targetUser").html();

function openSocket() {
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        var socketUrl = "ws://localhost:8091/webSocket/" + currentUser;
        console.log(socketUrl);
        if (socket != null) {
            socket.close();
            socket = null;
        }
        socket = new WebSocket(socketUrl);
        //打开事件
        socket.onopen = function () {
            console.log("websocket已打开");
            //socket.send("这是来自客户端的消息" + location.href + new Date());
        };
        //获得消息事件
        socket.onmessage = function (msg) {
            toCmd(msg)
        };
        //关闭事件
        socket.onclose = function () {
            console.log("websocket已关闭");
        };
        //发生了错误事件
        socket.onerror = function () {
            console.log("websocket发生了错误");
        }
    }
}


function sendMessage() {
    if (typeof (WebSocket) == "undefined") {
        console.log("您的浏览器不支持WebSocket");
    } else {
        // console.log("您的浏览器支持WebSocket");
        var fromUser = currentUser;
        var toUser = targetUser;
        var data = $("#msgInput").val()
        var sendObj = {
            fromUser,toUser,
            data,
            cmd: "log"
        }
        sendHtml(new Date().toLocaleString(),data);
        socket.send(JSON.stringify(sendObj));
    }
}

/*解析返回数据的cmd*/
function toCmd(value) {
    var {cmd, data} = JSON.parse(value.data)
    switch (cmd) {
        case "userList":
            toUserList(data)
            break
        case "log":
            toUserMessage(value.data)
            break
    }
}

/*收到消息后的操作*/
function toUserMessage(data) {
    let obj=JSON.parse(data);
    receiveHtml(obj.data.fromUser+" "+new Date().toLocaleString(),obj.data.data);
}

/*渲染用户列表*/
function toUserList(data) {
    if(data.indexOf(targetUser)>-1){
        $("#isOnline").html('<label class="list-inline-item d-flex align-items-center text-secondary"><small class=\'bx bxs-circle me-1 chart-online\'></small>在线</label>')
    }else{
        $("#isOnline").html('<label class="list-inline-item d-flex align-items-center text-secondary"><small class=\'bx bxs-circle me-1 chart-offline\'></small>离线</label>')
    }
}

//发送信息渲染
function sendHtml(time,msg){
    let htmlStr='<div class="chat-content-rightside">\n' +
        '            <div class="d-flex ms-auto">\n' +
        '                <div class="flex-grow-1 me-2">\n' +
        '                    <p class="mb-0 chat-time text-end">'+time+'</p>\n' +
        '                    <p class="chat-right-msg">'+msg+'</p>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>';
    $("#chatContent").append(htmlStr);
}

//收到信息渲染
function receiveHtml(time,msg){
    let htmlStr='<div class="chat-content-leftside">\n' +
        '            <div class="d-flex">\n' +
        '                <div class="flex-grow-1 ms-2">\n' +
        '                    <p class="mb-0 chat-time">'+time+'</p>\n' +
        '                    <p class="chat-left-msg">'+msg+'</p>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>';
    $("#chatContent").append(htmlStr);
}

/*时间戳转字符串*/
Date.prototype.toLocaleString = function () {
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ':' + this.getMinutes() + ':' + this.getSeconds();
};

$(function () {
    openSocket();
})