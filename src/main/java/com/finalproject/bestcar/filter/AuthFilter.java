package com.finalproject.bestcar.filter;

import com.finalproject.bestcar.dao.UserDao;
import com.finalproject.bestcar.daoIml.UserDaoIml;
import com.finalproject.bestcar.util.PasswordHashing;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for authorization.
 * Checking for the presence of a user in the database,
 * as well as redirecting to pages depending on the role.
 */

@WebFilter("/login")
public class AuthFilter implements Filter {

    UserDao userDao;
    String message = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = new UserDaoIml();
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String phone = req.getParameter("phone");
        final String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        if (session != null &&
            session.getAttribute("phone") != null &&
            session.getAttribute("password") != null &&
            session.getAttribute("id") != null){

            final String role = (String) session.getAttribute("role");
            moveToMenu(req, res, role);


        } else if (password != null || phone != null) {
            if(userDao.userExists(phone, PasswordHashing.hash(password))) {
                if (userDao.getActive(phone)) {
                    final String role = userDao.getUserRole(phone, PasswordHashing.hash(password));
                    final Integer id = userDao.getUserId(phone, PasswordHashing.hash(password));
                    req.getSession().setAttribute("password", PasswordHashing.hash(password));
                    req.getSession().setAttribute("phone", phone);
                    req.getSession().setAttribute("role", role);
                    req.getSession().setAttribute("id", id);
                    moveToMenu(req, res, role);
                }else {
                    message = "Your account has been blocked!";
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("general/login.jsp").forward(req, res);
                }
            }else {
                message = "Data isn't valid!";
                req.setAttribute("message", message);
                moveToMenu(req, res, "Unknown");
            }
        }else
            moveToMenu(req, res, "Unknown");
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final String role)
            throws ServletException, IOException {

        switch (role) {
            case "Admin":
                res.sendRedirect(req.getContextPath() + "/admin");
                break;
            case "User":
                res.sendRedirect(req.getContextPath() + "/user");
                break;
            case "Moderator":
                res.sendRedirect(req.getContextPath() + "/moderator");
                break;
            default:
                req.getRequestDispatcher("general/login.jsp").forward(req, res);
                break;
        }
    }

}
