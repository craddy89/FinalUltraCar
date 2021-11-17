package com.finalproject.bestcar.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * Filter for checking moderator role on moderator pages
 *
 * If the role does not match,
 * a redirect to the page with an error is made
 *
 */

@WebFilter(urlPatterns = {"/search_info", "/return_cars", "/moderator", "/moderation", "/ban_rent", "/pick_up", "/allow_rent"})
public class ModeratorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;
        final HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if(role == null || !role.equals("Moderator"))
            res.sendRedirect(req.getContextPath() + "/error");
        else
            filterChain.doFilter(req, res);
    }

}
