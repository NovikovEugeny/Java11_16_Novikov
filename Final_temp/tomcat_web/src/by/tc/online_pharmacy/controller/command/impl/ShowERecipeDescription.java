package by.tc.online_pharmacy.controller.command.impl;


import by.tc.online_pharmacy.bean.RecipeDescription;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class ShowERecipeDescription implements Command {

    private final String RECIPE_CODE = "recipeCode";
    private final String RD = "RD";

    @Override
    public String execute(HttpServletRequest request) {
       String response = null;

       try {
           ServiceFactory serviceFactory = ServiceFactory.getInstance();
           PharmService pharmService = serviceFactory.getPharmService();

           String recipeCode = request.getParameter(RECIPE_CODE);

           RecipeDescription recipeDescription =
                   pharmService.takeRecipeDescription(recipeCode);

           request.setAttribute(RD, recipeDescription);
           response = JspPageName.CLIENT_RECIPE_DESCRIPTION;
       } catch (ServiceException exc) {
           //logger
       }
       return response;
    }
}
