package com.finalproject.bestcar.servlet.moderator;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.dao.PassportDao;
import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.entity.Passport;
import com.finalproject.bestcar.entity.User;
import com.finalproject.bestcar.daoIml.CarDaoIml;
import com.finalproject.bestcar.daoIml.PassportDaoIml;
import com.finalproject.bestcar.daoIml.UserDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Menu for searching user and car information
 *
 */

@WebServlet(value = "/search_info")
public class SearchInfoServlet extends HttpServlet {

    UserDao userDao;
    PassportDao passportDao;
    CarDao carDao;
    String message = "";
    User user;
    Passport passport;
    Car car;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoIml();
        passportDao = new PassportDaoIml();
        carDao = new CarDaoIml();
    }

    /**
     * Data output
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(user != null)
            req.setAttribute("user", user);
        if(car != null)
            req.setAttribute("car", car);
        if(passport != null)
            req.setAttribute("passport", passport);
        req.setAttribute("message", message);
        req.getRequestDispatcher("moderator/search_info.jsp").forward(req, resp);
    }

    /**
     * Data search
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("userId") != null) {
            final Integer userId = Integer.parseInt(req.getParameter("userId"));
            message = "";
            if(userId > 0) {
                user = userDao.getUser(userId);
                passport = passportDao.getPassport(userId);
            }else message = "UserID isn't valid!";
        }
        if (req.getParameter("carId") != null) {
            final Integer carId = Integer.parseInt(req.getParameter("carId"));
            message = "";
            if(carId > 0)
                car = carDao.getCar(carId);
            else message = "CarID isn't valid!";
        }
        resp.sendRedirect(req.getContextPath() + "/search_info");
    }
}
