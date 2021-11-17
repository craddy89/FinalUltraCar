package com.finalproject.bestcar.servlet.admin.userControl;

import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.User;
import com.finalproject.bestcar.daoIml.UserDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Servlet to control all users
 *
 */

@WebServlet(name = "admin_users", value="/admin_users")
public class UsersControlServlet extends HttpServlet {

    private List<User> users;
    UserDao userDao;
    static String message = "";

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
        userDao = new UserDaoIml();
        users = userDao.getAllUsers();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        users = userDao.getAllUsers();
        req.setAttribute("message", message);
        req.setAttribute("users", users);
        req.getRequestDispatcher("admin/admin_users.jsp").forward(req, resp);
    }
}
