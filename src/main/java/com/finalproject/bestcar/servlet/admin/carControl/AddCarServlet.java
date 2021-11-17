package com.finalproject.bestcar.servlet.admin.carControl;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.daoIml.CarDaoIml;
import com.finalproject.bestcar.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for adding a car to the database
 */

@WebServlet(urlPatterns = "/add_car")
public class AddCarServlet extends HttpServlet {

    CarDao carDao;

    @Override
    public void init() throws ServletException {
        carDao = new CarDaoIml();
    }

    /**
     * Getting data from jsp and storing it in the database
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println(Validator.carValid(req));
        if (Validator.carValid(req)) {
            final String brand = req.getParameter("brand");
            final String name = req.getParameter("name");
            final String carClass = req.getParameter("carClass");
            final String color = req.getParameter("color");
            final int price = Integer.parseInt(req.getParameter("price"));
            final String photo = req.getParameter("photo");
            final String description = req.getParameter("description");

            carDao.insertCar(new Car(brand, name, carClass, color, price, photo, description));
            CarsControlServlet.message = "Successfully!";
        } else {
            CarsControlServlet.message = "Data isn't valid!";
        }
        resp.sendRedirect(req.getContextPath() + "/admin_cars");
    }

}
