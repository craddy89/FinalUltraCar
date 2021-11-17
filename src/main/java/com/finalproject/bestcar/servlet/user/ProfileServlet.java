package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.PassportDao;
import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.Passport;
import com.finalproject.bestcar.entity.User;
import com.finalproject.bestcar.daoIml.PassportDaoIml;
import com.finalproject.bestcar.daoIml.UserDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * User profile with his data
 *
 */

@WebServlet(name = "UserProfile", value="/user_profile")
public class ProfileServlet extends HttpServlet {

    UserDao userDao;
    PassportDao passportDao;
    static String message = "";

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoIml();
        passportDao = new PassportDaoIml();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final Integer id = (Integer) session.getAttribute("id");
        if (id != null) {
            User user = userDao.getUser(id);
            req.setAttribute("user", user);
            Passport passport = passportDao.getPassport(id);
            req.setAttribute("passport", passport);
            req.setAttribute("message", message);
        }
        req.getRequestDispatcher("user/user_profile.jsp").forward(req, resp);
    }

}
