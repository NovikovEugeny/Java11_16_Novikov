package by.tc.online_pharmacy.controller.command.impl.client;


import by.tc.online_pharmacy.bean.RecipeDescription;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ShowERecipe implements Command {

    private final String RECIPE_CODE = "recipeCode";
    private final String RD = "RD";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String page = null;

       try {
           ServiceFactory serviceFactory = ServiceFactory.getInstance();
           ClientService clientService = serviceFactory.getClientService();

           String recipeCode = request.getParameter(RECIPE_CODE);

           RecipeDescription recipeDescription = clientService.showRecipeDescription(recipeCode);

           request.setAttribute(RD, recipeDescription);

           page = JspPageName.CLIENT_RECIPE_DESCRIPTION;

           request.getRequestDispatcher(page).forward(request, response);
       } catch (ServiceException exc) {
           //logger
       }
    }
}
