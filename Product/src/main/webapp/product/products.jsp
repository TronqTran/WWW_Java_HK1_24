<%@ page import="vn.edu.iuh.frontend.dtos.ProductDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 9/30/2024
  Time: 8:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>12W
    <title>Product List</title>
    <style>

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        img {
            width: 100px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Product List</h1>
<p>Product</p>
<form action="controller" method="post" class="add">
    <input type="hidden" name="action" value="addProduct">
    <input type="text" name="name" placeholder="Name">
    <input type="text" name="description" placeholder="Description">
    <input type="text" name="img_path" placeholder="Image">
    <input type="text" name="price" placeholder="Price">
    <input type="submit" value="ADD">
</form>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Image</th>
        <th>Price</th>
        <th>Option</th>
    </tr>
    </thead>
    <tbody>
    <!-- Duyệt qua danh sách sản phẩm và hiển thị -->
    <%
        for (ProductDTO product : (List<ProductDTO>) request.getAttribute("productList")
             ) {
            %>
          <tr>
            <td><%=product.getId()%></td>
            <td><%=product.getName()%></td>
            <td><%=product.getDescription()%></td>
            <td><%=product.getImgPath()%></td>
            <td><%=product.getPrice()%></td>
            <td>
                <form action="controller" method="get">
                    <input type="hidden" name="action" value="detailPrice">
                    <input type="hidden" name="id" value="<%=product.getId()%>">
                    <input type="submit" value="Detail">
                </form>
                <form action="controller" method="get">
                    <input type="hidden" name="action" value="editProduct">
                    <input type="hidden" name="id" value="<%=product.getId()%>">
                    <input type="hidden" name="name" value="<%=product.getName()%>">
                    <input type="hidden" name="description" value="<%=product.getDescription()%>">
                    <input type="hidden" name="imgPath" value="<%=product.getImgPath()%>">
                    <input type="hidden" name="price" value="<%=product.getPrice()%>">
                    <input type="submit" value="Edit">
                </form>
            </td>
        </tr>
    <%}%>
    </tbody>
</table>
</body>
</html>

