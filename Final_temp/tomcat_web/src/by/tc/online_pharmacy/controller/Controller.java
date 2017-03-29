package by.tc.online_pharmacy.controller;

import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Евгений on 17.02.2017.
 */
public class Controller extends HttpServlet {

    private final static String COMMAND = "command";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandProvider provider = new CommandProvider();

        String commandName = request.getParameter(COMMAND);

        Command command = provider.getCommand(commandName);

        String page = command.execute(request);

        request.getRequestDispatcher(page).forward(request, response);
    }

}
