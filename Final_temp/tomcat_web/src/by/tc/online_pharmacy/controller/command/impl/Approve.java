package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 05.03.2017.
 */
public class Approve implements Command {

    private final static String RECIPE_ID = "recipeId";
    private final static String USER = "user";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            int recipeId = Integer.parseInt(request.getParameter(RECIPE_ID));
            int doctorId = ((User)request.getSession().getAttribute(USER)).getId();

            pharmService.approve(recipeId, doctorId);//return string

            //response = JspPageName.SHOW_ORDER_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }
        return response;
    }
}
