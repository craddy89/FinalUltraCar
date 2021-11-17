package com.finalproject.bestcar.servlet.admin.carControl;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.daoIml.CarDaoIml;

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
 * Servlet to control all cars
 *
 */

@WebServlet(name = "admin_cars", value="/admin_cars")
public class CarsControlServlet extends HttpServlet {

    private List<Car> cars;
    CarDao carDao;
    static String message = "";

    @Override
    public void init() throws ServletException {
        cars = new ArrayList<>();
        carDao = new CarDaoIml();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        cars = carDao.getAllCars();
        req.setAttribute("message", message);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("admin/admin_cars.jsp").forward(req, resp);
    }
}
