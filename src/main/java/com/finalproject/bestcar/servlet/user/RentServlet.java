package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.CarDao;
import com.finalproject.bestcar.dao.FineDao;
import com.finalproject.bestcar.dao.HistoryDao;
import com.finalproject.bestcar.dao.PassportDao;
import com.finalproject.bestcar.entity.Car;
import com.finalproject.bestcar.entity.History;
import com.finalproject.bestcar.daoIml.CarDaoIml;
import com.finalproject.bestcar.daoIml.FineDaoIml;
import com.finalproject.bestcar.daoIml.HistoryDaoIml;
import com.finalproject.bestcar.daoIml.PassportDaoIml;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * Car rental with an indication of the driver's option and the number of days
 *
 */

@WebServlet(value = "/rent_car")
public class RentServlet extends HttpServlet {

    CarDao carDao;
    HistoryDao historyDao;
    FineDao fineDao;
    PassportDao passportDao;
    String message = "";
    Integer carId;

    @Override
    public void init() throws ServletException {
        fineDao = new FineDaoIml();
        carDao = new CarDaoIml();
        historyDao = new HistoryDaoIml();
        passportDao = new PassportDaoIml();
    }

    /**
     * Displaying information about the car and the rental cost
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sum = checkRentSum(req);
        carId = Integer.parseInt(req.getParameter("carId"));
        Car car = carDao.getCar(carId);
        req.setAttribute("message", message);
        message = "";
        req.setAttribute("sum", sum);
        req.setAttribute("car", car);
        req.getRequestDispatcher("user/rent_car.jsp").forward(req, resp);
    }

    /**
     * Calculating the cost of rent
     */
    protected Integer checkRentSum(HttpServletRequest req){
        Integer sum = 0;
        Integer driver = 0;
        if (req.getParameter("driver") != null) {
            driver = Integer.parseInt(req.getParameter("driver"));
        }
        if(req.getParameter("days") != null){
            final Integer days = Integer.parseInt(req.getParameter("days"));
            final Integer price = Integer.parseInt(req.getParameter("price"));
            if (days < 1) {
                message = "Days isn't valid!";
            } else {
                sum = (driver + price) * days;
                req.setAttribute("days", days);
                req.setAttribute("driver", driver);
            }
        }
        return sum;
    }

    /**
     * Checking user data, if successful, sending rental data for moderation,
     * otherwise displaying an error message. With unpaid fines, rent is prohibited.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final Integer userId = (Integer) session.getAttribute("id");
        boolean finesExist = fineDao.finesExist(userId);
        boolean passportExist = passportDao.passportExist(userId);
        if(!passportExist) {
            message = "Passport isn't Exist!";
            this.doGet(req, resp);
        }
        if(finesExist) {
            message = "You have unpaid fines";
            this.doGet(req, resp);
        }
        boolean isDriver = false;
        final Integer price = Integer.parseInt(req.getParameter("sum"));
        final Integer days = Integer.parseInt(req.getParameter("days"));
        final Integer carId = Integer.parseInt(req.getParameter("carId"));
        if (req.getParameter("driver") != null) {
            final Integer driver = Integer.parseInt(req.getParameter("driver"));
            if(driver > 0)
                isDriver = true;
        }
        historyDao.insertHistory(new History(userId, carId, isDriver, days, price));
        resp.sendRedirect(req.getContextPath() + "/history");
    }
}
