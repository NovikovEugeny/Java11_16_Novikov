package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLocale implements Command {

    private final static String LOCAL = "local";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));

        String page = JspPageName.START_PAGE;

        request.getRequestDispatcher(page).forward(request, response);
    }
}