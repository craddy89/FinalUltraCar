package com.finalproject.bestcar.servlet.user;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class UserMenuTest {

    private final static String path = "user/user_menu.jsp";

    @Test
    public void whenCallDoPostThenServletRedirectHistoryServlet() throws ServletException, IOException, NumberFormatException {

        final UserMenuServlet servlet = new UserMenuServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);

        servlet.doGetTest(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);

    }

}
