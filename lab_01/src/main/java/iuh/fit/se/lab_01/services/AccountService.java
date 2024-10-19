package iuh.fit.se.lab_01.services;

import iuh.fit.se.lab_01.enums.AccountStatus;
import iuh.fit.se.lab_01.models.Account;
import iuh.fit.se.lab_01.models.GrantAccess;
import iuh.fit.se.lab_01.models.Log;
import iuh.fit.se.lab_01.repositories.AccountRepository;
import iuh.fit.se.lab_01.repositories.LogRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class AccountService {
    private AccountRepository accountRepositories;
    private LogRepository logRepositories;
    public AccountService() {
        this.accountRepositories = new AccountRepository();
        this.logRepositories = new LogRepository();
    }
    public void addAccount(Account account) {
        accountRepositories.addAccuont(account);
    }
    public void updateAccount(Account account) {
        accountRepositories.updateAccount(account);
    }
    public void deleteAccount(Account account) {
        accountRepositories.deleteAccount(account);
    }
    public Account findById(String account_id) {
        return accountRepositories.findById(account_id);
    }
    public Account findByEmailAndPassword(String email, String password) {
        return accountRepositories.findByEmailAndPassword(email, password);
    }
    public List<Account> getAllAccounts() {
        return accountRepositories.getAllAccounts();
    }
    public void showSignInPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("signin.jsp");
    }
    public void showSignUpPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("signup.jsp");
    }
    public void showHomePage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("index.jsp");
    }
    public void SignIn (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Account account = accountRepositories.findByEmailAndPassword(email, password);
        if (account == null) {
             showHomePage(req, resp);
        } else {
            HttpSession session = req.getSession(false);
            session.setAttribute("account", account);
            req.setAttribute("account", account);
            Log log = new Log(new Timestamp(System.currentTimeMillis()), "Login", account);
            logRepositories.addLog(log);
            session.setAttribute("log", log);
            req.getRequestDispatcher("profile.jsp").forward(req, resp) ;
        }
    }
    public void SignUp (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String account_id = req.getParameter("accountid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        Account account = new Account(account_id, username, password, email, phone, accountStatus);

        req.setAttribute("account", account);
        accountRepositories.addAccuont(account);
        req.getRequestDispatcher("profile.jsp").forward(req, resp);


        HttpSession session = req.getSession(false);
        req.setAttribute("account", account);
        Log log = new Log(new Timestamp(System.currentTimeMillis()), "Login", account);
        logRepositories.addLog(log);
        session.setAttribute("log", log);
    }
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        session.getAttribute("log");
        Log log = (Log) session.getAttribute("log");
        log.setLogout_time(new Timestamp(System.currentTimeMillis()));
        log.setNote("Logout");
        logRepositories.updateLog(log);
        session.removeAttribute("account");
        session.invalidate();
        showHomePage(req, resp);
    }
}
