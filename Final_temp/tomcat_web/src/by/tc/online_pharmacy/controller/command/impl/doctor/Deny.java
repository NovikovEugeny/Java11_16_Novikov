package by.tc.online_pharmacy.controller.command.impl.doctor;

import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;
import by.tc.online_pharmacy.service.DoctorService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class Deny implements Command {

    private static final Logger logger = LogManager.getLogger(Deny.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        int id = Integer.parseInt(request.getParameter(ParameterName.ID));
        String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);
        int doctorId = ((User) request.getSession().getAttribute(ParameterName.USER)).getId();

        RERDescription rerDescription = new RERDescription();
        rerDescription.setId(id);
        rerDescription.setDoctorId(doctorId);
        rerDescription.setRecipeCode(recipeCode);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DoctorService doctorService = serviceFactory.getDoctorService();

            doctorService.deny(rerDescription);

            List<RERDescription> recipeList = doctorService.showRecipeExtensionRequestList();
            request.setAttribute(AttributeName.RECIPE_LIST, recipeList);

            page = JspPageName.DOCTOR_HOME_PAGE;
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}