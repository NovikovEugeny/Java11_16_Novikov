package by.tc.online_pharmacy.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface Command {
    String execute(HttpServletRequest request);
}
