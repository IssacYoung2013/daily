<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <script type="text/javascript">
        // 创建EventSource对象，指定事件源为sse/custom 请求
        var es = new EventSource("sse/custom");
        // 当接收到服务端发送过来的事件时，触发该方法执行
        es.addEventListener("issac",function (evt) {
            console.log("issac-message",evt.data,evt);
            if(evt.data == 9) {
                es.close();
            }
        })

    </script>
</body>
</html>
