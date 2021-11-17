package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.daoIml.CarDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 *
 * Main user menu with car list, navigation and sorting
 *
 */

@WebServlet(name = "user", value = "/user")
public class UserMenuServlet extends HttpServlet {

    List<Car> cars;
    CarDao carDao;
    String orderBy = "id";
    String brand = "";
    String carClass = "";

    @Override
    public void init() throws ServletException {
        carDao = new CarDaoIml();
        cars = new ArrayList<>();
    }


    protected void doGetTest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/user_menu.jsp").forward(req, resp);
    }

    /**
     * The method displays all active cars with the ability to navigate and sort by brand, price, color
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 6;
        if(req.getParameter("page") != null || req.getParameter("pageSize") != null) {
            page = Integer.parseInt(req.getParameter("page"));
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        if(req.getParameter("orderBy") != null) {
            orderBy = req.getParameter("orderBy");
        }
        if(req.getParameter("brand") != null) {
            brand = req.getParameter("brand");
        }
        if(req.getParameter("carClass") != null) {
            carClass = req.getParameter("carClass");
        }
        cars = carDao.getAllCars(brand, carClass, orderBy, page, pageSize);
        Integer carCount = carDao.getCarCount();
        Integer maxPage = (int)Math.ceil((double) carCount / pageSize);
        List<String> brands = carDao.getAllBrands();
        List<String> carClasses = carDao.getAllClasses();
        req.setAttribute("sort", orderBy);
        req.setAttribute("brand", brand);
        req.setAttribute("carClass", carClass);
        req.setAttribute("brands", brands);
        req.setAttribute("carClasses", carClasses);
        req.setAttribute("page", page);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("maxPage", maxPage);
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("user/user_menu.jsp").forward(req, resp);
    }

    /**
     * Reset navigation parameters
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("orderByX") != null) {
            orderBy = "id";
        }
        if(req.getParameter("brandX") != null) {
            brand = "";
        }
        if(req.getParameter("carClassX") != null) {
            carClass = "";
        }
        resp.sendRedirect("/user");
    }
}
