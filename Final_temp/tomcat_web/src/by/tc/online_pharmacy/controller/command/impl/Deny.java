package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 07.04.2017.
 */
public class Deny implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
