package by.tc.online_pharmacy.controller.command.impl.page;


import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, which forwards to the user page
 * to select a group of drugs for order.
 */
public class GroupsToOrderPage implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = JspPageName.CLIENT_PHARM_GROUPS;
        request.getRequestDispatcher(page).forward(request, response);
    }
}