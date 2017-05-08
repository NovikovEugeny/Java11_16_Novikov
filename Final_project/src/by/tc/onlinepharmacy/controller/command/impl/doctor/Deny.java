package by.tc.onlinepharmacy.controller.command.impl.doctor;

import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.RERDescription;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.DoctorService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, the execution of which rejects the client's application
 * for the recipe renewal and doesn't make any changes to the recipe.
 */
public class Deny implements Command {

    private static final Logger LOGGER = LogManager.getLogger(Deny.class.getName());

    /**
     * If the value of the parameters <tt>id</tt>, <tt>recipeCode</tt> from the request
     * are not valid, then the control is passed to the catch block
     * of <tt>ValidatorException</tt> and forwarding to the page with error 400.
     * <p>
     * Packs request parameters into a transfer object {@link RERDescription RERDescription}
     * and sends them to the services layer.
     * <p>
     * If the command is successful, then the data on the page is updated
     * and the line with the corresponding application for the recipe disappears.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     *
     * @param request  object that contains the request the client has made of the servlet
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

            int id = Integer.parseInt(request.getParameter(ParameterName.ID));
            String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);
            int doctorId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();

            RERDescription description = new RERDescription();
            description.setId(id);
            description.setDoctorId(doctorId);
            description.setRecipeCode(recipeCode);

            doctorService.deny(description);

            response.sendRedirect(URLCommand.SHOW_RECIPE_EXTENSION_REQUESTS);

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException | NumberFormatException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}