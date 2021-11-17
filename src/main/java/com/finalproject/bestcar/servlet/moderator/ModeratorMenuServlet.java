package com.finalproject.bestcar.servlet.moderator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * Welcome window
 *
 */

@WebServlet(value = "/moderator")
public class ModeratorMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("moderator/moderator_menu.jsp").forward(req, resp);
    }

}