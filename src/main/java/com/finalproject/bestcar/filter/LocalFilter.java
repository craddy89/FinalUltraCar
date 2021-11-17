package com.finalproject.bestcar.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for storing the language selected by the user for translation in the session
 */

@WebFilter(urlPatterns = "/*")
public class LocalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String language = "en";
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if(session.getAttribute("language") != null)
            language = (String) session.getAttribute("language");
        req.getSession().setAttribute("language", language);
        filterChain.doFilter(req, servletResponse);
    }
}
