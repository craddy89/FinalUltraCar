package com.finalproject.bestcar.servlet.moderator;

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
 * Button for banning a rental request with a reason
 *
 */

@WebServlet(value = "/ban_rent")
public class BanStatusServlet extends HttpServlet {

    HistoryDao historyDao;

    @Override
    public void init() throws ServletException {
        historyDao = new HistoryDaoIml();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final Integer id = Integer.parseInt(req.getParameter("id"));
        final String description = req.getParameter("description");

        historyDao.banUpdate(new History(id, description, "Ban"));

        resp.sendRedirect(req.getContextPath() + "/moderation");
    }

}
