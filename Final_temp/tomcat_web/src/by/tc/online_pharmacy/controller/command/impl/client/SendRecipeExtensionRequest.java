package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;
import by.tc.online_pharmacy.controller.util.URLCommand;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class SendRecipeExtensionRequest implements Command {

    private static final Logger logger = LogManager.getLogger(SendRecipeExtensionRequest.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);
        int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            Date currentDate = new Date();
            Date endRecipeDate = clientService.showRecipeEndDate(recipeCode);

            if (endRecipeDate == null) {
                request.setAttribute(AttributeName.IS_EXISTS, AttributeName.NO);
                request.getRequestDispatcher(URLCommand.EXTEND_RECIPE_PAGE).forward(request, response);

            } else if (currentDate.before(endRecipeDate)) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.TIME_ERROR);
                request.getRequestDispatcher(URLCommand.EXTEND_RECIPE_PAGE).forward(request, response);

            } else {
                clientService.sendRecipeExtensionRequest(recipeCode, clientId);
                response.sendRedirect(URLCommand.EXTEND_RECIPE_PAGE);
            }

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}