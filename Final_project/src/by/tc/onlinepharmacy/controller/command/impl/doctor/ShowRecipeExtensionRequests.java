package by.tc.onlinepharmacy.controller.command.impl.doctor;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.RERDescription;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.service.DoctorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class describes the object-command, the execution of which displays a list of received applications
 * for the recipe renewal.
 */
public class ShowRecipeExtensionRequests implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowRecipeExtensionRequests.class.getName());

    /**
     * If the command is successful, forwarding to the doctor page
     * where recipe applications are displayed.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block
     * and forwarding to the server error page.
     *
     * @param request object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            DoctorService doctorService = serviceFactory.getDoctorService();

            List<RERDescription> recipeList = doctorService.showRecipeExtensionRequestList();
            request.setAttribute(AttributeName.RECIPE_LIST, recipeList);

            page = JspPageName.DOCTOR_HOME_PAGE;

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}