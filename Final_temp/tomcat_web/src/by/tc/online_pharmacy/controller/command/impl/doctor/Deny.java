package by.tc.online_pharmacy.controller.command.impl.doctor;

import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.DoctorService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class Deny implements Command {

    private final static String RECIPE_CODE = "recipeCode";
    private final static String ID = "id";
    private final static String USER = "user";
    private final static String RECIPE_LIST = "recipeList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        int id = Integer.parseInt(request.getParameter(ID));
        String recipeCode = request.getParameter(RECIPE_CODE);
        int doctorId = ((User)request.getSession().getAttribute(USER)).getId();

        RERDescription rerDescription = new RERDescription();
        rerDescription.setId(id);
        rerDescription.setDoctorId(doctorId);
        rerDescription.setRecipeCode(recipeCode);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DoctorService doctorService = serviceFactory.getDoctorService();

            doctorService.deny(rerDescription);

            List<RERDescription> recipeList = doctorService.showRecipeExtensionRequestList();
            request.setAttribute(RECIPE_LIST, recipeList);
            page = JspPageName.DOCTOR_HOME_PAGE;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {
            //logger
        }
    }
}
