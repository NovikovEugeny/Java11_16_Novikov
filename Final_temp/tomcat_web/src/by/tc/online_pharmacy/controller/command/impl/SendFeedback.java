package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 12.03.2017.
 */
public class SendFeedback implements Command {

    private final static String RECIPE_ID = "recipeId";
    private final static String FEEDBACK = "feedback";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            int recipeId = Integer.parseInt(request.getParameter(RECIPE_ID));
            String feedback = request.getParameter(FEEDBACK);

            pharmService.sendFeedback(recipeId, feedback);

            //response = JspPageName.RECIPE_FEEDBACK_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }
        return response;
    }
}
