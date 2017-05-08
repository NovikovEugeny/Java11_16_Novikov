package by.tc.onlinepharmacy.controller.command.impl.client;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.ClientService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * Class describes the object-command, which allows to send
 * an application for renewal of the recipe.
 */
public class SendRecipeExtensionRequest implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SendRecipeExtensionRequest.class.getName());

    /**
     * The recipe code verified on the service layer, and it empty,
     * then the control passed to the catch block of <tt>ValidatorException</tt>,
     * and client stays on the same page and receives a message about it.
     * <p>
     * If the recipe with the code extracted from the request does not exist,
     * the user stays on the same page and receives a pop-up window with an error message.
     * <p>
     * If a such recipe exists, but its validity has expired, the user stays on the same page
     * and receives a pop-up window with an error message.
     * <p>
     * If the application has already been sent, then the user stays on the same page
     * and receives a pop-up window with an error message.
     * <p>
     * If the command is successful, then the page is updated
     * and the client stays on the same page.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
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
            boolean isDuplicate = clientService.isDuplicateApplication(recipeCode);

            if (endRecipeDate == null) {
                request.setAttribute(AttributeName.IS_EXISTS, AttributeName.NO);
                request.getRequestDispatcher(URLCommand.EXTEND_RECIPE_PAGE).forward(request, response);

            } else if (currentDate.before(endRecipeDate)) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.TIME_ERROR);
                request.getRequestDispatcher(URLCommand.EXTEND_RECIPE_PAGE).forward(request, response);

            } else if (isDuplicate) {
                request.setAttribute(AttributeName.IS_DUPLICATE, AttributeName.YES);
                request.getRequestDispatcher(URLCommand.EXTEND_RECIPE_PAGE).forward(request, response);

            } else {
                clientService.sendRecipeExtensionRequest(recipeCode, clientId);
                response.sendRedirect(URLCommand.EXTEND_RECIPE_PAGE);
            }

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}