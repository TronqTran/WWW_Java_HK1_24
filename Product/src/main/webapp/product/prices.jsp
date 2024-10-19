<%@ page import="vn.edu.iuh.frontend.entities.ProductPrice" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 9/30/2024
  Time: 6:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Price</title>
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
<h1 style="text-align: center;">Product Price List</h1>
<table>
    <thead>
    <tr>
        <th>Price_ID</th>
        <th>Apply Date</th>
        <th>Note</th>
        <th>Status</th>
        <th>Price</th>
        <th>Option</th>
    </tr>
    </thead>
    <tbody>
    <!-- Duyệt qua danh sách giá của sản phẩm và hiển thị -->
    <%
        for (ProductPrice price : (List<ProductPrice>) request.getAttribute("priceList")
        ) {
    %>
    <tr>
        <td><%=price.getPriceId()%></td>
        <td><%=price.getApplyDate()%></td>
        <td><%=price.getNote()%></td>
        <td><%=price.getStatus()%></td>
        <td><%=price.getPrice()%></td>
        <td>
            <form action="controller" method="post">
                <input type="hidden" name="action" value="editPrice">
                <input type="hidden" name="id" value="<%=price.getPriceId()%>">
                <input type="submit" value="Edit">
            </form>
        </td>
    </tr>
    <% }%>
    </tbody>
</table>
</body>
</html>
