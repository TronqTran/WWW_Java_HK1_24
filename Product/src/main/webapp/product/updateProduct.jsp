<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/11/2024
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            width: 710px;
            gap: 20px;

            & form {
                display: flex;
                flex-direction: column;
                gap: 5px;

                & label {
                    font-weight: bold;
                }

                & input {
                    width: 100%;
                    height: 20px;
                    border: 1px solid lightgray;
                }
            }
        }
    </style>
</head>
<body>
<h1>Update Product</h1>
<form action="controller" method="post">
    <input type="hidden" name="action" value="updateProduct">
    <input type="hidden" name="id" value="<%=request.getAttribute("id")%>">
    <label>Name: </label>
    <input type="text" name="name" value="<%=request.getAttribute("name")%>"> </br>
    <label>Description: </label>
    <input type="text" name="description" value="<%=request.getAttribute("description")%>"> </br>
    <label>Image: </label>
    <input type="text" name="imgPath" value="<%=request.getAttribute("imgPath")%>"> </br>
    <label>Price: </label>
    <input type="text" name="price" value="<%=request.getAttribute("price")%>"> </br>
    <input type="submit" value="UPDATE">
</form>
</body>
</html>
