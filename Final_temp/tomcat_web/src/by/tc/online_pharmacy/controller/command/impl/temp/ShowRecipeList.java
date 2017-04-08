package by.tc.online_pharmacy.controller.command.impl.temp;

import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 03.03.2017.
 */
public class ShowRecipeList implements Command {

    private final static String RECIPE_LIST = "recipeList";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;
/*
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            Map<Recipe, Drug> recipeList = pharmService.showRecipeList();

            request.setAttribute(RECIPE_LIST, recipeList);
            response = JspPageName.DOCTOR_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }*/
        return response;
    }
}
