<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 9/22/2024
  Time: 12:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile</title>
</head>
<body>
<h1>Hi, ${account.username}</h1>
<p>${account.username}</p>
<p>${account.email}</p>
<p>${account.phone}</p>
<p>${account.status}</p>
<form action="controller" method="get">
    <input type="hidden" name="action" value="logout"/>
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
