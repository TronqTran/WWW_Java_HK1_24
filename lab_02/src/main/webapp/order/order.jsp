<%@ page import="java.util.List"%>
<%@ page import="vn.edu.iuh.frontend.entities.Order"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Order Management</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 0;
                    padding: 20px;
                    background-color: #f4f4f4;
                }
                h1 {
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
                .form-row {
                    display: flex;
                    justify-content: space-between;
                }
                .form-group {
                    width: 48%;
                }
                form label {
                    display: block;
                    margin-bottom: 5px;
                }
                form select, form input {
                    width: 100%;
                    padding: 10px;
                    margin-bottom: 15px;
                    border-radius: 4px;
                    border: 1px solid #ccc;
                }
                form input[type="submit"] {
                    background-color: #4CAF50;
                    color: white;
                    border: none;
                    padding: 10px;
                    cursor: pointer;
                    width: 100%;
                    margin-top: 10px;
                }
                form input[type="submit"]:hover {
                    background-color: #45a049;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin: 20px 0;
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

                .options button.delete {
                    background-color: #f44336;
                }

                .options button:hover {
                    background-color: #45a049;
                }

                .options button.delete:hover {
                    background-color: #d32f2f;
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
  <h1>Order Management</h1>

    <a class="home-btn" href="index.jsp">Home</a>

    <div class="container">
      <form action="controller" method="post">
            <input type="hidden" name="action" value="addOrder">
          <h2>Create New Order</h2>
          <div class="form-row">
              <div class="form-group">
                  <label for="customer">Select Customer:</label>
                  <select name="customerId" required>
                      <!-- Populate with customer list -->
                      <c:forEach var="customer" items="${customerList}">
                              <option value="${customer.id}">${customer.name}</option>
                      </c:forEach>
                  </select>
              </div>

              <div class="form-group">
                  <label for="employee">Select Employee:</label>
                  <select name="employeeId" required>
                      <!-- Populate with employee list -->
                      <c:forEach var="employee" items="${employeeList}">
                          <option value="${employee.id}">${employee.fullName}</option>
                      </c:forEach>
                  </select>
              </div>
          </div>

          <input type="submit" value="Create Order">
      </form>

      <h2>Order List</h2>

          <table>
              <thead>
              <tr>
                  <th>Order ID</th>
                  <th>Customer</th>
                  <th>Employee</th>
                  <th>Order Date</th>
                  <th>Options</th>
              </tr>
              </thead>
              <tbody>
              <!-- Populate order list -->
              <c:forEach var="order" items="${orderList}">
                      <tr>
                          <td>${order.order_id}</td>
                          <td>${order.customer.name}</td>
                          <td>${order.employee.fullName}</td>
                          <td>${order.orderDate}</td>
                          <td class="options">
                              <form action="controller" method="get" style="display:inline;">
                                      <input type="hidden" name="action" value="getOrderDetail">
                                      <input type="hidden" name="orderId" value="${order.order_id}">
                                      <button type="submit">View</button>
                                  </form>
                                  <form action="OrderController" method="post" style="display:inline;">
                                      <input type="hidden" name="action" value="delete">
                                      <input type="hidden" name="id" value="${order.order_id}">
                                      <button type="submit" class="delete">Delete</button>
                                  </form>
                          </td>
                      </tr>
                  </c:forEach>
              </tbody>
          </table>
    </div>

  </body>
</html>
