<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 9/21/2024
  Time: 5:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Login</h1>
<form action="controller" method="post">
    <input type="hidden" name="action" value="login"/>
    <input type="email" name="email"/>
    <input type="password" name="password"/>
    <input type="submit" value="Login"/>
</form>

</body>
</html>
