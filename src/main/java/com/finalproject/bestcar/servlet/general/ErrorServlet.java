package com.finalproject.bestcar.servlet.general;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Error page. Users get to this page
 * when they try to access a prohibited
 */

@WebServlet(value = "/error")
public class ErrorServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ErrorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("An attempt to enter an inaccessible page");
        req.getRequestDispatcher("general/error.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
