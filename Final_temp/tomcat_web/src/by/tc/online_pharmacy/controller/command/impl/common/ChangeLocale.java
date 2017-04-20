package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLocale implements Command {

    private final static String RU = "ru";
    private final static String EN = "en";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String locale = request.getParameter(ParameterName.LOCAL);

        if (locale != null) {
            if (locale.equals(RU) || locale.equals(EN)) {
                request.getSession(true).setAttribute(AttributeName.LOCAL, locale);
            }
        }

        String page = JspPageName.START_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}