package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class SendRecipeExtensionRequest implements Command {

    private final static String RECIPE_CODE = "recipeCode";
    private final static String USER = "user";
    private final static String EXECUTE_MESSAGE = "executeMessage";

    private String messageContent;

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        String recipeCode = request.getParameter(RECIPE_CODE);
        int clientId = ((User)request.getSession().getAttribute(USER)).getId();

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            Date currentDate = new Date();
            Date endRecipeDate = pharmService.takeRecipeEndDate(recipeCode);

            if (endRecipeDate == null) {
                messageContent = "no such recipe";
            } else if (currentDate.getTime() < endRecipeDate.getTime()) {
                messageContent = "it is not expired";
            } else {
                pharmService.sendRecipeExtensionRequest(recipeCode, clientId);
                messageContent = "sent";
            }

            request.setAttribute(EXECUTE_MESSAGE, messageContent);

            response = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
        } catch (ServiceException exc) {

        }
        return response;
    }
}
