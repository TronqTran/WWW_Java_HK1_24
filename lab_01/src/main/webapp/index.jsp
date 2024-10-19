<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<form action="controller" method="get">
  <input type="hidden" name="action" value="login"/>
  <input type="submit" value="Sign In"/>
</form>
<form action="controller" method="get">
  <input type="hidden" name="action" value="register"/>
  <input type="submit" value="Sign Up"/>
</form>
</body>
</html>