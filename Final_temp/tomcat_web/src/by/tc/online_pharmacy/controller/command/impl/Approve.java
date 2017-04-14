package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public class Approve implements Command {

    private final static String RECIPE_CODE = "recipeCode";
    private final static String ID = "id";
    private final static String USER = "user";
    private final static String RECIPE_LIST = "recipeList";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        int id = Integer.parseInt(request.getParameter(ID));
        String recipeCode = request.getParameter(RECIPE_CODE);
        int doctorId = ((User)request.getSession().getAttribute(USER)).getId();

        RERDescription rerDescription = new RERDescription();
        rerDescription.setId(id);
        rerDescription.setDoctorId(doctorId);
        rerDescription.setRecipeCode(recipeCode);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            pharmService.approve(rerDescription);

            List<RERDescription> recipeList = pharmService.takeRecipeExtensionRequestList();
            request.setAttribute(RECIPE_LIST, recipeList);
            response = JspPageName.DOCTOR_PAGE;
        } catch (ServiceException exc) {
            //logger
        }
        return response;
    }
}
