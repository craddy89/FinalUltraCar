package com.finalproject.bestcar.servlet.general;

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
 * Registration Servlet
 *
 */

@WebServlet(name = "registration", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    UserDao userDao;
    String message = "";

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoIml();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("general/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Validator.userValid(req) && !phoneExists(req)){
            final String name = req.getParameter("name");
            final String surname = req.getParameter("surname");
            final String phone = req.getParameter("phone");
            final String password = req.getParameter("password");
            userDao.insertUser(new User(name, surname, phone, PasswordHashing.hash(password)));
            message = "Successfully!";
        }else if(!Validator.userValid(req)){
            message = "User isn't valid!";
        }else if(phoneExists(req)) {
            message = "Phone exists!";
        }else
            message = "Unknown error!";
        doGet(req, resp);
    }


    /**
     * Validation
     */
    private boolean phoneExists(final HttpServletRequest req){
        final String phone = req.getParameter("phone");
        return userDao.phoneExists(phone);
    }

}
