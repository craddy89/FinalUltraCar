package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.User;
import com.finalproject.bestcar.daoIml.UserDaoIml;
import com.finalproject.bestcar.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * Servlet to fund user account
 *
 */

@WebServlet(urlPatterns = "/add_money")
public class AddMoneyServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoIml();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        if (Validator.sumIsValid(req)) {
            final Integer id = (Integer) session.getAttribute("id");
            User user = userDao.getUser(id);
            final Integer money = Integer.parseInt(req.getParameter("money"));
            int sum = user.getMoney() + money;
            userDao.addMoney(id, sum);
            ProfileServlet.message = "Successfully!";
        }else
            ProfileServlet.message = "Money isn't valid!";
        resp.sendRedirect(req.getContextPath() + "/user_profile");
    }

}