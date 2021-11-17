package com.finalproject.bestcar.servlet.moderator;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.daoIml.CarDaoIml;
import com.finalproject.bestcar.daoIml.HistoryDaoIml;
import com.finalproject.bestcar.daoIml.UserDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Car rental approval button
 *
 * Writing off money from the account,
 * in case of a shortage, notifies the moderator
 *
 */

@WebServlet(value = "/allow_rent")
public class ActiveStatusServlet extends HttpServlet {

    HistoryDao historyDao;
    UserDao userDao;
    CarDao carDao;

    @Override
    public void init() throws ServletException {
        historyDao = new HistoryDaoIml();
        userDao = new UserDaoIml();
        carDao = new CarDaoIml();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final Integer userId = Integer.parseInt(req.getParameter("userId"));
        final Integer carId = Integer.parseInt(req.getParameter("carId"));
        final Integer price = Integer.parseInt(req.getParameter("price"));
        final String dateStart = req.getParameter("dateStart");
        final String dateFinish = req.getParameter("dateFinish");
        if(userDao.getUserMoney(userId) > price) {
            userDao.addMoney(userId, userDao.getUserMoney(userId)-price);
            historyDao.activeUpdate(new History(id, dateStart, dateFinish, "Active"));
            carDao.updateCarActive(false, carId);
        }else {
            ModerationServlet.message = "The user does not have enough money in the account!";
        }

        resp.sendRedirect(req.getContextPath() + "/moderation");
    }

}
