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
 *
 * Servlet to update the cars in the database
 *
 */

@WebServlet(name = "admin_update_car", value = "/admin_update_car")
public class UpdateCarServlet extends HttpServlet {

    CarDao carDao;
    String message = "";
    Car car;

    @Override
    public void init() throws ServletException {
        carDao = new CarDaoIml();
        car = new Car();
    }

    /**
     * Collection of new data and vehicle updates
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (Validator.carValid(req)) {
            final Integer id = Integer.parseInt(req.getParameter("id"));
            final String brand = req.getParameter("brand");
            final String name = req.getParameter("name");
            final String carClass = req.getParameter("carClass");
            final String color = req.getParameter("color");
            final Integer price = Integer.parseInt(req.getParameter("price"));
            final String photo = req.getParameter("photo");
            final String description = req.getParameter("description");

            car = new Car();
            car.setId(id);
            car.setBrand(brand);
            car.setName(name);
            car.setCarClass(carClass);
            car.setColor(color);
            car.setPrice(price);
            car.setPhoto(photo);
            car.setDescription(description);

            carDao.updateCar(car);

            resp.sendRedirect(req.getContextPath() + "/admin_cars");
        } else {
            message = "Data isn't valid!";
            doGet(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));

        car = carDao.getCar(id);
        req.setAttribute("car", car);
        req.setAttribute("message", message);
        req.getRequestDispatcher("admin/admin_update_car.jsp").forward(req, resp);
    }

}
