package com.finalproject.bestcar.servlet.admin.userControl;

import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.User;
import com.finalproject.bestcar.daoIml.UserDaoIml;
import com.finalproject.bestcar.util.PasswordHashing;
import com.finalproject.bestcar.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Servlet for adding a moderator to the database
 *
 */

@WebServlet(urlPatterns = "/add_user")
public class AddUserServlet extends HttpServlet {

    UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoIml();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (Validator.userValid(req)) {
            if(!phoneExists(req)) {
                final String phone = req.getParameter("phone");
                final String name = req.getParameter("name");
                final String surname = req.getParameter("surname");
                final String password = req.getParameter("password");

                userDao.insertModerator(new User(name, surname, phone, PasswordHashing.hash(password), "Moderator"));
                UsersControlServlet.message = "Successfully!";
            }else
                UsersControlServlet.message = "Phone exist!";
        } else {
            UsersControlServlet.message = "Data isn't valid!";
        }
        resp.sendRedirect(req.getContextPath() + "/admin_users");
    }

    /**
     * Validation
     */
    private boolean phoneExists(HttpServletRequest req){
        final String phone = req.getParameter("phone");
        return userDao.phoneExists(phone);
    }

}
