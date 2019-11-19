<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script type="text/javascript">
        // 创建EventSource对象，指定事件源为sse/default请求
        var es = new EventSource("sse/default");
        // 当接收到服务端发送过来的事件时，触发该方法执行
        es.onmessage = function (evt) {
            // 参数一 日志名称
            // 参数二 事件中输出数据
            // 参数三 事件
            console.log("sse-message",evt.data,evt);
            if(evt.data == 9) {
                es.close();
            }
        }
    </script>
</body>
</html>
