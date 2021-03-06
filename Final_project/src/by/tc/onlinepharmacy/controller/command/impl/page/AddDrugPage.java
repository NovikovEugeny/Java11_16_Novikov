package by.tc.onlinepharmacy.controller.command.impl.page;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.JspPageName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, which forwards to the pharmacist page
 * to add a new drug.
 */
public class AddDrugPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}