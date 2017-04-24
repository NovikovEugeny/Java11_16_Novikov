package by.tc.online_pharmacy.controller.command.impl.doctor;

import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.URLCommand;
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


public class Deny implements Command {

    private static final Logger logger = LogManager.getLogger(Deny.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DoctorService doctorService = serviceFactory.getDoctorService();

            int id = Integer.parseInt(request.getParameter(ParameterName.ID));
            String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);
            int doctorId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();

            RERDescription rerDescription = new RERDescription();
            rerDescription.setId(id);
            rerDescription.setDoctorId(doctorId);
            rerDescription.setRecipeCode(recipeCode);

            doctorService.deny(rerDescription);

            response.sendRedirect(URLCommand.SHOW_RECIPE_EXTENSION_REQUESTS);

        } catch (ServiceException | NumberFormatException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}