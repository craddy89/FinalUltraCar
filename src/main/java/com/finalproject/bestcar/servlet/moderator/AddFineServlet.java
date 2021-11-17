package com.finalproject.bestcar.servlet.moderator;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.dao.FineDao;
import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.entity.Fine;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.daoIml.CarDaoIml;
import com.finalproject.bestcar.daoIml.FineDaoIml;
import com.finalproject.bestcar.daoIml.HistoryDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Button for issuing a fine to the user
 *
 */

@WebServlet(value = "/pick_up")
public class AddFineServlet extends HttpServlet {

    FineDao fineDao;
    HistoryDao historyDao;
    CarDao carDao;

    @Override
    public void init() throws ServletException {
        fineDao = new FineDaoIml();
        historyDao = new HistoryDaoIml();
        carDao = new CarDaoIml();
    }

    /**
     * If there is no damage to the car, the car will receive the "Returned" status,
     * otherwise the user will be issued a fine, and the car will receive the "Fine" status.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer carId = Integer.parseInt(req.getParameter("carId"));
        carDao.updateCarActive(true, carId);
        if(req.getParameter("damage").equals("No damage")) {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            historyDao.returnCar(new History(id, "Returned"));
        }else {
            final Integer historyId = Integer.parseInt(req.getParameter("id"));
            final Integer userId = Integer.parseInt(req.getParameter("userId"));
            final String damage = req.getParameter("damage");
            final String description = req.getParameter("description");

            Integer sum = 0;

            if (damage.equals("Low"))
                sum = 1000;
            if (damage.equals("Average"))
                sum = 4000;
            if (damage.equals("High"))
                sum = 8000;
            if (damage.equals("Critical"))
                sum = 10000;

            fineDao.insertFine(new Fine(historyId, userId, carId, damage, description, sum));
            historyDao.returnCar(new History(historyId, "Fine"));
        }
        resp.sendRedirect(req.getContextPath() + "/return_cars");
    }

}
