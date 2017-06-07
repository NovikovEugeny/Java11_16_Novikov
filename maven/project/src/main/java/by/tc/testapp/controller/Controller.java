package by.tc.testapp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Евгений on 05.06.2017.
 */
public class Controller extends HttpServlet {

    private final static String MESSAGE = "message";
    private final static String ENCODING = "UTF-8";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter(MESSAGE);
        System.out.println(message);

        response.setCharacterEncoding(ENCODING);
        response.getWriter().print(message);
    }
}