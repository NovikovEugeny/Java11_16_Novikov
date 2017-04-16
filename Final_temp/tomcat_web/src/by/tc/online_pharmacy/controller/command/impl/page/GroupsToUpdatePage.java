package by.tc.online_pharmacy.controller.command.impl.page;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GroupsToUpdatePage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = JspPageName.PHARMACIST_GROUPS_TO_UPDATE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}
