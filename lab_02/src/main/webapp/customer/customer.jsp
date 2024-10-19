<%@ page import="java.util.List" %>
    <%@ page import="vn.edu.iuh.frontend.entities.Customer" %>
        <%-- Created by IntelliJ IDEA. User: trong Date: 10/16/2024 Time: 7:29 PM To change this template use File |
            Settings | File Templates. --%>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                <html>

                <head>
                    <title>Customer Management</title>
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
                            width: 800px;
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
                        <h1>Customer</h1>
                        <form action="controller" method="post">
                            <input type="hidden" name="action" value="addCustomer">
                            <input type="text" name="name" placeholder="Customer Name" required>
                            <input type="email" name="email" placeholder="Email" required>
                            <input type="tel" name="phone" placeholder="Phone Number" required>
                            <input type="text" name="address" placeholder="Address" required>
                            <input type="submit" value="Add Customer">
                        </form>

                        <h2>Customer List</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Customer Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th>Option</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Example rows, you can dynamically populate this table with customer data from your backend -->
                                <% for (Customer customer : (List<Customer>) request.getAttribute("customerList")) {
                                    %>
                                    <tr>
                                        <td>
                                            <%=customer.getName()%>
                                        </td>
                                        <td>
                                            <%=customer.getEmail()%>
                                        </td>
                                        <td>
                                            <%=customer.getPhone()%>
                                        </td>
                                        <td>
                                            <%=customer.getAddress()%>
                                        </td>
                                        <td class="actions">
                                            <button name="action" value="editCustomer">Edit</button>
                                            <button class="delete">Delete</button>
                                        </td>
                                    </tr>
                                    <% } %>
                            </tbody>
                        </table>
                    </div>
                </body>

                </html>