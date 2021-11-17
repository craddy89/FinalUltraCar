package com.finalproject.bestcar.servlet.general;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ErrorServletTest {

    private final static String path = "general/error.jsp";
    private final static String path2 = "/home";

    @Test
    public void whenCallDoGetThenServletReturnErrorPage() throws ServletException, IOException {

        final ErrorServlet servlet = new ErrorServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void whenCallDoPostThenServletRedirectHomeServlet() throws ServletException, IOException {

        final ErrorServlet servlet = new ErrorServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);

        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect(request.getContextPath() + path2);
    }

}
