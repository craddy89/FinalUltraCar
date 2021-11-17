package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.daoIml.HistoryDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Table with rent history
 *
 */

@WebServlet(value = "/history")
public class HistoryServlet extends HttpServlet {

    HistoryDao historyDao;
    List<History> stories;
    String message = "";

    @Override
    public void init() throws ServletException {
        historyDao = new HistoryDaoIml();
        stories = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final Integer id = (Integer) session.getAttribute("id");
        stories = historyDao.selectHistory(id);
        if(stories == null)
            message = "History is empty";
        else
            req.setAttribute("stories", stories);
        req.getRequestDispatcher("user/history.jsp").forward(req, resp);
    }

}
