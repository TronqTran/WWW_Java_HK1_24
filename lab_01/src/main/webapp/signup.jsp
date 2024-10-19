<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 9/22/2024
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="controller" method="post">
    <input type="hidden" name="action" value="register"/>
    <input type="text" name="accountid" placeholder="AccountId"/> <br/>
    <input type="text" name="username" placeholder="Username"/> <br/>
    <input type="email" name="email" placeholder="Email"/> <br/>
    <input type="password" name="password" placeholder="Password"/> <br/>
    <input type="text" name="phone" placeholder="Phone"/> <br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
