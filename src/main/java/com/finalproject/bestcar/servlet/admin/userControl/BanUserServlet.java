package com.finalproject.bestcar.servlet.admin.userControl;

import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.daoIml.UserDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for banning / unbanning users
 */

@WebServlet(urlPatterns = "/blockUser")
public class BanUserServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoIml();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String phone = req.getParameter("phone");
        boolean active = Boolean.parseBoolean(req.getParameter("active"));
        userDao.updateActive(!active, phone);
        resp.sendRedirect(req.getContextPath() + "/admin_users");
    }
}
