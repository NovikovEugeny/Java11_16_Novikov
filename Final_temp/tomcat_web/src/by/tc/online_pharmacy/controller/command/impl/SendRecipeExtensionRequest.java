package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;


public class SendRecipeExtensionRequest implements Command {

    private final static String RECIPE_CODE = "recipeCode";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        String recipeCode = request.getParameter(RECIPE_CODE);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();
            pharmService.addRecipeExtensionRequest(recipeCode);

            response = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
        } catch (ServiceException exc) {

        }
        return response;
    }
}
