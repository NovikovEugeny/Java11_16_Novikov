package by.tc.online_pharmacy.controller.command;

import javax.servlet.http.HttpServletRequest;


public interface Command {
    String execute(HttpServletRequest request);
}
