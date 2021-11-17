package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.daoIml.HistoryDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Car return button
 *
 */

@WebServlet(value = "/return_car")
public class ReturnCarServlet extends HttpServlet {

    HistoryDao historyDao;

    @Override
    public void init() throws ServletException {
        historyDao = new HistoryDaoIml();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        historyDao.returnCar(new History(id, "Return"));
        resp.sendRedirect(req.getContextPath() + "/history");
    }
}
