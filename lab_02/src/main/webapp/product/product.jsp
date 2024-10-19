<%@ page import="java.util.List"%>
<%@ page import="vn.edu.iuh.frontend.entities.dtos.ProductDTO"%>
<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/16/2024
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product Management</title>
                            <style>
                                body {
                                    font-family: Arial, sans-serif;
                                    background-color: #f4f4f9;
                                    margin: 0;
                                    padding: 20px;
                                }

                                h1 {
                                    text-align: center;
                                    color: #333;
                                }

                                .container {
                                    width: 1024px;
                                    margin: 0 auto;
                                    background-color: #fff;
                                    padding: 20px;
                                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                                    border-radius: 8px;
                                }

                                .home-button {
                                    display: block;
                                    margin: 0 auto 20px auto;
                                    padding: 10px 20px;
                                    background-color: #007BFF;
                                    color: white;
                                    text-align: center;
                                    border: none;
                                    border-radius: 5px;
                                    cursor: pointer;
                                    text-decoration: none;
                                    font-size: 16px;
                                }

                                .home-button:hover {
                                    background-color: #0056b3;
                                }

                                form {
                                    display: grid;
                                    grid-template-columns: 1fr 1fr;
                                    gap: 15px;
                                    margin-bottom: 20px;
                                }

                                form input[type="text"],
                                form input[type="email"],
                                form input[type="tel"],
                                form input[type="number"],
                                form input[type="submit"] {
                                    padding: 10px;
                                    font-size: 16px;
                                    border: 1px solid #ccc;
                                    border-radius: 4px;
                                }

                                form input[type="submit"] {
                                    background-color: #4CAF50;
                                    color: white;
                                    border: none;
                                    cursor: pointer;
                                    grid-column: span 2;
                                }

                                form input[type="submit"]:hover {
                                    background-color: #45a049;
                                }

                                table {
                                    width: 100%;
                                    border-collapse: collapse;
                                }

                                table,
                                th,
                                td {
                                    border: 1px solid #ccc;
                                }

                                th,
                                td {
                                    padding: 10px;
                                    text-align: left;
                                }

                                th {
                                    background-color: #f2f2f2;
                                }

                                .actions button {
                                    padding: 5px 10px;
                                    margin: 0 5px;
                                    background-color: #007BFF;
                                    color: white;
                                    border: none;
                                    border-radius: 4px;
                                    cursor: pointer;
                                }

                                .actions button.delete {
                                    background-color: #FF0000;
                                }

                                .actions button:hover {
                                    opacity: 0.8;
                                }
                            </style>
  </head>
  <body>
  <div class="container">
                              <a href="index.jsp" class="home-button">Home</a>
                              <h1>Product</h1>
                              <form action="controller" method="post">
                                  <input type="hidden" name="action" value="addProduct">
                                  <input type="text" name="name" placeholder="Product Name" required>
                                  <input type="text" name="description" placeholder="Description" required>
                                  <input type="number" name="price" placeholder="Price" required>
                                  <input type="text" name="unit" placeholder="Unit" required>
                                  <input type="text" name="manufacturer" placeholder="Manufacturer" required>
                                  <input type="text" name="imgPath" placeholder="Img Path" required>
                                  <input type="submit" value="Add Product">
                              </form>

                              <h2>Employee List</h2>
                              <table>
                                  <thead>
                                      <tr>
                                          <th>Name</th>
                                          <th>Description</th>
                                          <th>Price</th>
                                          <th>Unit</th>
                                          <th>Manufacturer</th>
                                          <th>Img Path</th>
                                          <th>Status</th>
                                          <th>Option</th>
                                      </tr>
                                  </thead>
                                  <tbody>
                                      <!-- Example rows, you can dynamically populate this table with product data from your backend -->
                                      <% for (ProductDTO product : (List<ProductDTO>) request.getAttribute("productList")) {
                                          %>
                                          <tr>
                                              <td>
                                                  <%=product.getName()%>
                                              </td>
                                              <td>
                                                  <%=product.getDescription()%>
                                              </td>
                                              <td>
                                                  <%=product.getPrice()%>
                                              </td>
                                              <td>
                                                  <%=product.getUnit()%>
                                              </td>
                                              <td>
                                                  <%=product.getManufacturer()%>
                                              </td>
                                              <td>
                                                  <%=product.getImgPath()%>
                                              </td>
                                              <td>
                                                  <%=product.getStatus()%>
                                              </td>
                                              <td class="actions">
                                                  <button name="action" value="editEmployee">Edit</button>
                                                  <button class="delete">Delete</button>
                                              </td>
                                          </tr>
                                          <% } %>
                                  </tbody>
                              </table>
                          </div>
  </body>
</html>
