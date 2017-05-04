package by.tc.online_pharmacy.controller.command.impl.page;

import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, which forwards to the start page.
 */
public class StartPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = JspPageName.START_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}