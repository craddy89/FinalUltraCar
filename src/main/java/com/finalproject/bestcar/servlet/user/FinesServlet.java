package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.FineDao;
import com.finalproject.bestcar.entity.Fine;
import com.finalproject.bestcar.daoIml.FineDaoIml;

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
 * Table with fines
 *
 */

@WebServlet(value = "/fines")
public class FinesServlet extends HttpServlet {

    FineDao fineDao;
    List<Fine> fines;
    static String message = "";

    @Override
    public void init() throws ServletException {
        fineDao = new FineDaoIml();
        fines = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final Integer id = (Integer) session.getAttribute("id");
        fines = fineDao.selectFines(id);
        req.setAttribute("message", message);
        req.setAttribute("fines", fines);
        req.getRequestDispatcher("user/fines.jsp").forward(req, resp);
    }
}
