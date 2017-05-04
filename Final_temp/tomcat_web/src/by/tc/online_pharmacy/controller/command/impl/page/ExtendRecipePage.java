package by.tc.online_pharmacy.controller.command.impl.page;

import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.JspPageName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, which forwards to the user page
 * to send an application for renewal of the recipe
 */
public class ExtendRecipePage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}