package vn.edu.iuh.frontend.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.edu.iuh.frontend.dtos.ProductDTO;
import vn.edu.iuh.frontend.models.PriceModel;
import vn.edu.iuh.frontend.models.ProductModel;


import java.io.IOException;
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    @Inject
    private ProductModel productModel;
    @Inject
    private PriceModel priceModel;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id;
        String name;
        String description;
        String imgPath;
        String price;
        ProductDTO productDTO;

        switch (action) {
            case "addProduct":
                name = req.getParameter("name");
                description = req.getParameter("description");
                imgPath = req.getParameter("img_path");
                price = req.getParameter("price");

                productDTO = new ProductDTO();
                productDTO.setName(name);
                productDTO.setDescription(description);
                productDTO.setImgPath(imgPath);
                productDTO.setPrice(Double.parseDouble(price));

                productModel.add(productDTO);

                req.setAttribute("productList", productModel.getAll());
                req.getRequestDispatcher("product/products.jsp").forward(req, resp);
                break;

            case "updateProduct":
                id = Integer.parseInt(req.getParameter("id"));
                name = req.getParameter("name");
                description = req.getParameter("description");
                imgPath = req.getParameter("imgPath");
                price = req.getParameter("price");

                productDTO = new ProductDTO();
                productDTO.setId(id);
                productDTO.setName(name);
                productDTO.setDescription(description);
                productDTO.setImgPath(imgPath);
                productDTO.setPrice(Double.parseDouble(price));

                productModel.updateProductDTO(productDTO);

                req.setAttribute("productList", productModel.getAll());
                req.getRequestDispatcher("product/products.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id;
        switch (action) {
            case "products":
                req.setAttribute("productList", productModel.getAll());
                req.getRequestDispatcher("product/products.jsp").forward(req, resp);
                break;
            case "detailPrice":
                id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("priceList", priceModel.getPriceByProductId(id));
                req.getRequestDispatcher("product/prices.jsp").forward(req, resp);
                break;
            case "editProduct":
                id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                String imgPath = req.getParameter("imgPath");
                String price = req.getParameter("price");

                req.setAttribute("id", id);
                req.setAttribute("name", name);
                req.setAttribute("description", description);
                req.setAttribute("imgPath", imgPath);
                req.setAttribute("price", price);

                req.getRequestDispatcher("product/updateProduct.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }
}
