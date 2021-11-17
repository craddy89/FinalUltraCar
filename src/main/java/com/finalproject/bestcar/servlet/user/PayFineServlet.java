package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.FineDao;
import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.daoIml.FineDaoIml;
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
 * Button for paying fines
 *
 */

@WebServlet(value = "/pay_fine")
public class PayFineServlet extends HttpServlet {

    FineDao fineDao;
    HistoryDao historyDao;
    UserDao userDao;

    @Override
    public void init() throws ServletException {
        fineDao = new FineDaoIml();
        historyDao = new HistoryDaoIml();
        userDao = new UserDaoIml();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer fineId = Integer.parseInt(req.getParameter("id"));
        final Integer userId = Integer.parseInt(req.getParameter("userId"));
        final Integer price = Integer.parseInt(req.getParameter("price"));
        final Integer historyId = Integer.parseInt(req.getParameter("historyId"));
        if(userDao.getUserMoney(userId) > price) {
            userDao.addMoney(userId, userDao.getUserMoney(userId)-price);
            fineDao.deleteFine(fineId);
            historyDao.returnCar(new History(historyId, "Returned"));
        }else {
            FinesServlet.message = "Not enough money, top up your account!";
        }
        resp.sendRedirect(req.getContextPath() + "/fines");
    }
}
