package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogOut implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getSession().removeAttribute(AttributeName.USER);

        String page = JspPageName.START_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}