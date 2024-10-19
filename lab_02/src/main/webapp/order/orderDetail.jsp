<%@ page import="java.util.List"%>
<%@ page import="vn.edu.iuh.frontend.entities.Order"%>
<%@ page import="vn.edu.iuh.frontend.entities.Product"%>
<%@ page import="vn.edu.iuh.frontend.entities.Employee"%>
<%@ page import="vn.edu.iuh.frontend.entities.Customer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/17/2024
  Time: 7:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Order Detail</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
                background-color: #f4f4f4;
            }
            h1, h2 {
                text-align: center;
            }
            .container {
                margin: 20px auto;
                padding: 20px;
                background-color: #ffffff;
                border-radius: 8px;
                max-width: 1024px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .details {
                margin-bottom: 20px;
            }
            .details label {
                font-weight: bold;
                display: block;
                margin-bottom: 5px;
            }
            .details span {
                display: block;
                margin-bottom: 10px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .options button {
                text-decoration: none;
                color: white;
                background-color: #4CAF50;
                padding: 6px 10px;
                border-radius: 4px;
                border: none;
                cursor: pointer;
            }

            .options button:hover {
                background-color: #45a049;
            }

            .home-btn {
                display: block;
                margin: 10px auto;
                padding: 10px 20px;
                background-color: #333;
                color: white;
                text-align: center;
                border-radius: 5px;
                text-decoration: none;
            }
            .home-btn:hover {
                background-color: #555;
            }
        </style>
  </head>
  <body>
  <h1>Order Detail</h1>

      <a class="home-btn" href="index.jsp">Home</a>

      <div class="container">
          <!-- Order Details -->
           <div class="details">
              <label>Employee:</label>
              <span>${employee}</span>
              <label>Customer:</label>
              <span>${customer}</span>
          </div>
          <!-- Products in the Order -->
          <h2>Products in the Order</h2>
          <table>
              <thead>
                  <tr>
                      <th>Product Name</th>
                      <th>Description</th>
                      <th>Price</th>
                      <th>Quantity</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach var="orderProduct" items="${orderDetail}">
                      <tr>
                          <td>${orderProduct.product.name}</td>
                          <td>${orderProduct.product.description}</td>
                          <td>${orderProduct.price}</td>
                          <td>${orderProduct.quantity}</td>
                      </tr>
                  </c:forEach>
              </tbody>
          </table>

          <!-- Add New Products to Order -->
          <h2>Add More Products</h2>
              <table>
                  <thead>
                      <tr>
                          <th>Product Name</th>
                          <th>Description</th>
                          <th>Price</th>
                          <th>Quantity</th>
                          <th>Action</th>
                      </tr>
                  </thead>
                  <tbody>
                      <c:forEach var="product" items="${availableProducts}">
                          <tr>
                          <form action="controller" method="post">
                              <td>${product.name}</td>
                              <td>${product.description}</td>
                              <td>${product.price}</td>
                              <td>

                                  <input type="number" name="quantity" value="1" min="1" style="width: 60px;">
                              </td>
                              <td class="options">
                                       <input type="hidden" name="action" value="addProduct2Detail">
                                       <input type="hidden" name="productId" value="${product.product_id}">
                                        <input type="hidden" name="orderId" value="${orderId}">
                                        <input type="hidden" name="price" value="${product.price}">
                                       <button type="submit">Add</button>

                              </td>
                          </form>
                          </tr>
                      </c:forEach>
                  </tbody>
              </table>

      </div>
  </body>
</html>
