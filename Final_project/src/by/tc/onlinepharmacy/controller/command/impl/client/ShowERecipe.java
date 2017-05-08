package by.tc.onlinepharmacy.controller.command.impl.client;


import by.tc.onlinepharmacy.bean.RecipeDescription;
import by.tc.onlinepharmacy.controller.command.Command;
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


/**
 * Class describes the object-command, which displays description of the e-recipe.
 */
public class ShowERecipe implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowERecipe.class.getName());

    /**
     * The recipe code from the request validate on the service layer,
     * and if the recipe code is empty, the control passed to the catch block
     * of <tt>ValidatorException</tt>. Client stays on the same page and receives
     * error message.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, forwarding to the page
     * where recipe description is displayed.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block
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

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);
            RecipeDescription recipeDescription = clientService.showRecipeDescription(recipeCode);
            request.setAttribute(AttributeName.RD, recipeDescription);

            page = JspPageName.CLIENT_RECIPE_DESCRIPTION;

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.CLIENT_ORDER_BY_ERECIPE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}