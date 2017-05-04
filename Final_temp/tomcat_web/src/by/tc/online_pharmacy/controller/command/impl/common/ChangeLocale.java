package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes object-command, execution of which changes the application language.
 */

public class ChangeLocale implements Command {

    private final static String RU = "ru";
    private final static String EN = "en";

    /**
     * If the user's locale extracted from the query is ru or en,
     * then the locale changes, otherwise the locale remains the same.
     *
     * @param request object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String locale = request.getParameter(ParameterName.LOCAL);

        if (locale != null && !locale.isEmpty()) {
            if (locale.equals(RU) || locale.equals(EN)) {
                request.getSession(true).setAttribute(AttributeName.LOCAL, locale);
            }
        }

        String page = JspPageName.START_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}