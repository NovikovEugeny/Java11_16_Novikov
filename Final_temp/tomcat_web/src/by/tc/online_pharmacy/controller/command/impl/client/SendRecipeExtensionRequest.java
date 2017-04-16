package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class SendRecipeExtensionRequest implements Command {

    private final static String RECIPE_CODE = "recipeCode";
    private final static String USER = "user";
    private final static String EXECUTE_MESSAGE = "executeMessage";

    private String messageContent;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String recipeCode = request.getParameter(RECIPE_CODE);
        int clientId = ((User)request.getSession().getAttribute(USER)).getId();

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            Date currentDate = new Date();
            Date endRecipeDate = clientService.showRecipeEndDate(recipeCode);

            if (endRecipeDate == null) {
                messageContent = "no such recipe";
            } else if (currentDate.before(endRecipeDate)) {
                messageContent = "it is not expired";
            } else {
                clientService.sendRecipeExtensionRequest(recipeCode, clientId);
                messageContent = "sent";
            }

            request.setAttribute(EXECUTE_MESSAGE, messageContent);

            page = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {

        }
    }
}
