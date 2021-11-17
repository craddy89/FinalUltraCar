package com.finalproject.bestcar.util;

import javax.servlet.http.HttpServletRequest;

public class Validator {

    private Validator() {
    }

    /**
     * Validation Car
     */
    public static boolean carValid(HttpServletRequest req) {
        final String brand = req.getParameter("brand");
        final String name = req.getParameter("name");
        final String carClass = req.getParameter("carClass");
        final String color = req.getParameter("color");
        final Integer price = Integer.parseInt(req.getParameter("price"));
        final String photo = req.getParameter("photo");
        final String description = req.getParameter("description");
        return brand != null && brand.length() > 0 &&
               name != null && name.length() > 0 &&
               carClass != null && carClass.length() == 1 &&
               color != null && color.length() > 0 &&
               price > 0 &&
               photo != null && photo.length() > 0 &&
               description != null && description.length() > 0;
    }

    /**
     * Validation User
     */
    public static boolean userValid(HttpServletRequest req) {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String phone = req.getParameter("phone");
        final String password = req.getParameter("password");
        final String password2 = req.getParameter("password2");
        return name != null && name.length() > 0 &&
               surname != null && surname.length() > 0 &&
               phone != null && phone.length() == 9 &&
               password != null && password.length() > 0 &&
               password2 != null && password2.length() > 0 &&
               password.equals(password2);
    }

    /**
     * Validation Add money in profile
     */
    public static boolean sumIsValid(HttpServletRequest req) {
        final String money = req.getParameter("money");
        int sum = 0;
        try {
            sum = Integer.parseInt(money);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return sum > 0;
    }

    /**
     * Validation Add/Update passport in profile
     */
    public static boolean passportIsValid(HttpServletRequest req) {
        final String series = req.getParameter("series");
        final Integer number = Integer.parseInt(req.getParameter("number"));
        final String whoMade = req.getParameter("whoMade");
        final String createDate = req.getParameter("regDate");
        return series != null && series.length() == 2 &&
               number > 99999 && number < 1000000 &&
               whoMade != null && whoMade.length() > 0 &&
               createDate != null && createDate.length() > 0;
    }

}
