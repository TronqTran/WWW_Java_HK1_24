package vn.edu.iuh.frontend.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.frontend.entities.Customer;
import vn.edu.iuh.frontend.entities.Employee;
import vn.edu.iuh.frontend.entities.Order;
import vn.edu.iuh.frontend.entities.OrderDetail;
import vn.edu.iuh.frontend.entities.dtos.ProductDTO;
import vn.edu.iuh.frontend.entities.enums.EmployeeStatus;
import vn.edu.iuh.frontend.models.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    @Inject
    private CustomerModel customerModel;
    @Inject
    private EmployeeModel employeeModel;
    @Inject
    private ProductModel productModel;
    @Inject
    private OrderModel orderModel;
    @Inject
    private OrderDetailModel orderDetailModel;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Customer customer;
        Employee employee;
        ProductDTO product;
        Order order;
        OrderDetail orderDetail;
        switch (action) {
            case "addCustomer":
                customer = new Customer();
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                String address = req.getParameter("address");
                customer.setName(name);
                customer.setEmail(email);
                customer.setPhone(phone);
                customer.setAddress(address);
                customerModel.addCustomer(customer);
                req.setAttribute("customerList", customerModel.getAllCustomer());
                req.getRequestDispatcher("customer/customer.jsp").forward(req, resp);
                break;
            case "addEmployee":
                employee = new Employee();
                String nameEmp = req.getParameter("emp_name");
                String emailEmp = req.getParameter("email");
                String phoneEmp = req.getParameter("phone");
                String addressEmp = req.getParameter("address");
                LocalDate dob = LocalDate.parse(req.getParameter("dob"));
                employee.setFullName(nameEmp);
                employee.setEmail(emailEmp);
                employee.setPhone(phoneEmp);
                employee.setAddress(addressEmp);
                employee.setDob(dob);
                employee.setStatus(EmployeeStatus.ACTIVE);
                employeeModel.addEmployee(employee);
                req.setAttribute("employeeList", employeeModel.getAllEmployee());
                req.getRequestDispatcher("employee/employee.jsp").forward(req, resp);
                break;
            case "addProduct":
                product = new ProductDTO();
                String nameProduct = req.getParameter("name");
                String description = req.getParameter("description");
                Double price = Double.parseDouble(req.getParameter("price"));
                String manufacturer = req.getParameter("manufacturer");
                String unit = req.getParameter("unit");
                String imgPath = req.getParameter("imgPath");
                product.setName(nameProduct);
                product.setDescription(description);
                product.setPrice(price);
                product.setManufacturer(manufacturer);
                product.setUnit(unit);
                product.setImgPath(imgPath);
                productModel.addProduct(product);
                req.setAttribute("productList", productModel.getAllProduct());
                req.getRequestDispatcher("product/product.jsp").forward(req, resp);
                break;
            case "addOrder":
                Long customerID = Long.parseLong(req.getParameter("customerId"));
                Long employeeID = Long.parseLong(req.getParameter("employeeId"));
                order = new Order();
                order.setCustomer(customerModel.getCustomerById(customerID));
                order.setEmployee(employeeModel.getEmployeeById(employeeID));
                order.setOrderDate(LocalDateTime.now());
                orderModel.addOrder(order);

                req.setAttribute("customerList", customerModel.getAllCustomer());
                req.setAttribute("employeeList", employeeModel.getAllEmployee());
                req.setAttribute("orderList", orderModel.getAllOrder());
                req.getRequestDispatcher("order/order.jsp").forward(req, resp);

                break;
            case "addProduct2Detail":

                Long orderId = Long.parseLong(req.getParameter("orderId"));
                Long productId = Long.parseLong(req.getParameter("productId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                double priceP = Double.parseDouble(req.getParameter("price"));


                OrderDetail existingOrderDetail = orderDetailModel.getOrderDetailByOrderAndProduct(orderId, productId);
                if (existingOrderDetail != null) {
                    existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + quantity);
                    existingOrderDetail.setPrice(priceP);
                    orderDetailModel.updateOrderDetail(existingOrderDetail);
                } else {
                    OrderDetail newOrderDetail = new OrderDetail();
                    newOrderDetail.setOrder(orderModel.getOrderById(orderId));
                    newOrderDetail.setProduct(productModel.getProductById(productId));
                    newOrderDetail.setQuantity(quantity);
                    newOrderDetail.setPrice(priceP);
                    orderDetailModel.addOrderDetail(newOrderDetail);

                }

                req.setAttribute("orderId", orderId);
                req.setAttribute("employee", employeeModel.getEmployeeById(orderModel.getOrderById(orderId).getEmployee().getId()).getFullName());
                req.setAttribute("customer", customerModel.getCustomerById(orderModel.getOrderById(orderId).getCustomer().getId()).getName());
                req.setAttribute("orderDetail", orderDetailModel.getOrderDetailByOrder(orderId));
                req.setAttribute("availableProducts", productModel.getAllProductActive());

                req.getRequestDispatcher("order/orderDetail.jsp").forward(req, resp);

                break;
            default:
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "getCustomer":
                req.setAttribute("customerList", customerModel.getAllCustomer());
                req.getRequestDispatcher("customer/customer.jsp").forward(req, resp);
                break;
            case "getEmployee":
                req.setAttribute("employeeList", employeeModel.getAllEmployee());
                req.getRequestDispatcher("employee/employee.jsp").forward(req, resp);
                break;
            case "getProduct":
                req.setAttribute("productList", productModel.getAllProduct());
                req.getRequestDispatcher("product/product.jsp").forward(req, resp);
                break;
            case "getOrder":
                req.setAttribute("customerList", customerModel.getAllCustomer());
                req.setAttribute("employeeList", employeeModel.getAllEmployee());
                req.setAttribute("orderList", orderModel.getAllOrder());
                req.getRequestDispatcher("order/order.jsp").forward(req, resp);
                break;
            case "getOrderDetail":
                Long orderId = Long.parseLong(req.getParameter("orderId"));
                req.setAttribute("orderId", orderId);
                req.setAttribute("employee", employeeModel.getEmployeeById(orderModel.getOrderById(orderId).getEmployee().getId()).getFullName());
                req.setAttribute("customer", customerModel.getCustomerById(orderModel.getOrderById(orderId).getCustomer().getId()).getName());
                req.setAttribute("orderDetail", orderDetailModel.getOrderDetailByOrder(orderId));
                req.setAttribute("availableProducts", productModel.getAllProductActive());

                req.getRequestDispatcher("order/orderDetail.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                break;
        }

    }
}
