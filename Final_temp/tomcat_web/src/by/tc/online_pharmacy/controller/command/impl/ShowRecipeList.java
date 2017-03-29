package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Евгений on 03.03.2017.
 */
public class ShowRecipeList implements Command {

    private final static String RECIPE_LIST = "recipeList";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            Map<Recipe, Drug> recipeList = pharmService.showRecipeList();

            request.setAttribute(RECIPE_LIST, recipeList);
            response = JspPageName.DOCTOR_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }
        return response;
    }
}
