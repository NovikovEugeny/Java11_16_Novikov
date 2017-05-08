package by.tc.onlinepharmacy.controller.command.impl.page;

import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, which forwards to the sign in page.
 */
public class SignInPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = JspPageName.SIGN_IN_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}