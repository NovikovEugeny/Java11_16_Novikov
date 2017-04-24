package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.URLCommand;
import by.tc.online_pharmacy.controller.util.ParameterName;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RemoveDrug implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveDrug.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String group = request.getParameter(ParameterName.GROUP);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            int id = Integer.parseInt(request.getParameter(ParameterName.ID));
            pharmacistService.removeDrug(id);

            response.sendRedirect(URLCommand.SHOW_DRUGS_TO_UPDATE + group);

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            String page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}