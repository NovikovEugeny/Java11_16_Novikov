package by.tc.online_pharmacy.controller;

import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandName = request.getParameter(ParameterName.COMMAND);

        Command command = provider.getCommand(commandName);

        command.execute(request, response);
    }

}