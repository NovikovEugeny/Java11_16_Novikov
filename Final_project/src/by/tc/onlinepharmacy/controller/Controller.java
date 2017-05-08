package by.tc.onlinepharmacy.controller;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.CommandProvider;
import by.tc.onlinepharmacy.resource.ParameterName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front application controller.
 */
public class Controller extends HttpServlet {

    /**
     * Called main.by the server (via the service method) to allow a servlet to handle a POST request.
     * <p>
     * Delegates the request processing to
     * {@link #processRequest(HttpServletRequest, HttpServletResponse) processRequests} method.
     *
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Called main.by the server (via the service method) to allow a servlet to handle a GET request.
     * <p>
     * Delegates the request processing to
     * {@link #processRequest(HttpServletRequest, HttpServletResponse) processRequest} method.
     *
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Extracts the command name from the request and invoke the method
     * {@link Command#execute(HttpServletRequest, HttpServletResponse) execute} of the corresponding command.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CommandProvider commandProvider = CommandProvider.getInstance();

        String commandName = request.getParameter(ParameterName.COMMAND);

        Command command = commandProvider.getCommand(commandName);

        command.execute(request, response);
    }
}