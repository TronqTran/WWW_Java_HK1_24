package iuh.fit.se.lab_01.controllers;

import iuh.fit.se.lab_01.models.Account;
import iuh.fit.se.lab_01.services.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/ControllerServlet", "/controller"})
public class ControllerServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                accountService.SignIn(req, resp);
                break;
            case "logout":
                // do logout
                break;
            case "register":
                // do register
                accountService.SignUp(req, resp);
                break;
            case "update":
                // do update
                break;
            case "delete":
                // do delete
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "login":
                //do login
                accountService.showSignInPage(req, resp);
                break;
            case "logout":
                // do logout
                accountService.logout(req, resp);
                break;
            case "register":
                // do register
                accountService.showSignUpPage(req, resp);
                break;
            case "update":
                // do update
                break;
            case "delete":
                // do delete
                break;
            default:
                break;
        }
    }
}
