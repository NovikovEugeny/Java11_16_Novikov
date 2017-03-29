package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 20.02.2017.
 */
public class ChangeLocale implements Command {

    private final static String LOCAL = "local";

    @Override
    public String execute(HttpServletRequest request) {
        request.getSession(true).setAttribute(LOCAL, request.getParameter(LOCAL));
        return JspPageName.START_PAGE;
    }
}
