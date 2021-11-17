package com.finalproject.bestcar.servlet.admin.carControl;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.daoIml.CarDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for removing a car from the database
 */

@WebServlet(urlPatterns = "/deleteCar")
public class DeleteCarServlet extends HttpServlet {

    CarDao carDao;

    @Override
    public void init() throws ServletException {
        carDao = new CarDaoIml();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        carDao.deleteCar(id);
        resp.sendRedirect(req.getContextPath() + "/admin_cars");
    }
}
