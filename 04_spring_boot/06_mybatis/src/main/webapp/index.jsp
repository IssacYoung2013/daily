<%--
  Created by IntelliJ IDEA.
  User: Issac
  Date: 2019/11/2
  Time: 下午8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="test/register" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="注册"><br>
</form>
<hr>
<form action="test/find" method="post">
    id：<input type="text" name="id"><br>
    <input type="submit" value="查询"><br>
</form>
<hr>

<a href="test/count">查询员工总数</a>
</body>
</html>
