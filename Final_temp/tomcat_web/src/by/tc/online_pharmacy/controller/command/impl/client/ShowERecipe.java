package by.tc.online_pharmacy.controller.command.impl.client;


import by.tc.online_pharmacy.bean.RecipeDescription;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.ParameterName;
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


public class ShowERecipe implements Command {

    private static final Logger logger = LogManager.getLogger(ShowERecipe.class.getName());

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
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.CLIENT_ORDER_BY_ERECIPE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}