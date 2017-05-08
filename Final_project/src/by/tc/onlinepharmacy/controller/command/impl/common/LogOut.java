package by.tc.onlinepharmacy.controller.command.impl.common;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.AttributeName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes object-command, execution of which leads to the user's exit from the system
 * and forwarding to the start page.
 */
public class LogOut implements Command {

    /**
     * The user is deleted from the session, and the user forwards to the start page.
     *
     * @param request object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().removeAttribute(AttributeName.USER);

        String page = JspPageName.START_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}