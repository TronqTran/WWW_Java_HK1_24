<%--
  Created by IntelliJ IDEA.
  User: trong
  Date: 10/13/2024
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Lab 02</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
      }

      h1 {
        text-align: center;
        color: #333;
        font-size: 2.5em;
        margin-bottom: 20px;
      }

      .container {
        background-color: #ffffff;
        padding: 20px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        width: 300px;
        text-align: center;
      }

      form {
        margin-bottom: 15px;
      }

      input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 10px 20px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 4px;
        width: 100%;
        transition: background-color 0.3s ease;
      }

      input[type="submit"]:hover {
        background-color: #45a049;
      }

      input[type="submit"]:active {
        background-color: #3e8e41;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Lab 02</h1>
      <form action="controller" method="get">
        <input type="hidden" name="action" value="getOrder"/>
        <input type="submit" value="Order"/>
      </form>
      <form action="controller" method="get">
        <input type="hidden" name="action" value="getCustomer"/>
        <input type="submit" value="Customer"/>
      </form>
      <form action="controller" method="get">
        <input type="hidden" name="action" value="getEmployee"/>
        <input type="submit" value="Employee"/>
      </form>
      <form action="controller" method="get">
        <input type="hidden" name="action" value="getProduct"/>
        <input type="submit" value="Product"/>
      </form>
    </div>
  </body>
</html>
