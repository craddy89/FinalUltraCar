package com.finalproject.bestcar.servlet.user;

import com.finalproject.bestcar.dao.PassportDao;
import com.finalproject.bestcar.entity.Passport;
import com.finalproject.bestcar.daoIml.PassportDaoIml;
import com.finalproject.bestcar.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * Servlet for adding / updating passport data
 *
 */

@WebServlet(urlPatterns = "/add_passport")
public class AddPassportServlet extends HttpServlet {

    PassportDao passportDao;

    @Override
    public void init() throws ServletException {
        passportDao = new PassportDaoIml();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession();
        if (Validator.passportIsValid(req)) {
            final Integer id = (Integer) session.getAttribute("id");
            final String series = req.getParameter("series");
            final Integer number = Integer.parseInt(req.getParameter("number"));
            final String whoMade = req.getParameter("whoMade");
            final String createDate = req.getParameter("regDate");

            if (passportDao.passportExist(id)) {
                passportDao.deletePassport(id);
            }
            passportDao.insertPassport(new Passport(series, number, whoMade, createDate), id);
            ProfileServlet.message = "Successfully!";
        }else
            ProfileServlet.message = "Passport isn't valid!";
        resp.sendRedirect(req.getContextPath() + "/user_profile");
    }

}