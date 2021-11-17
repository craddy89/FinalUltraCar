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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Menu for consideration of applications for rent
 *
 */

@WebServlet(name = "moderation", value="/moderation")
public class ModerationServlet extends HttpServlet {

    HistoryDao historyDao;
    List<History> moderList;
    static String message = "";

    @Override
    public void init() throws ServletException {
        historyDao = new HistoryDaoIml();
        moderList = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        moderList = historyDao.selectModeration("Moderation");
        req.setAttribute("message", message);
        req.setAttribute("moderList", moderList);
        req.getRequestDispatcher("moderator/moderation.jsp").forward(req, resp);
    }
}
