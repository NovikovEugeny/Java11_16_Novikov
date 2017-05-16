package by.tc.onlinepharmacy.controller.command.impl.pharmacist;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.PharmacistService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes the object-command, the execution of which
 * increases the quantity of a certain drug in the warehouse.
 */
public class AddDrugQuantity implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddDrugQuantity.class.getName());

    /**
     * If the value of the parameters <tt>id</tt>, <tt>quantity</tt>
     * from the request are not integer, then the control is passed to the catch block
     * of <tt>NumberFormatException</tt> and forwarding to the page with error 400.
     * <p>
     * Drug quantity extracted from the request is checked on the service layer.
     * If quantity less than or equal to zero, the control passed to the catch block of <tt>ValidatorException</tt>
     * and user stay on the same page where a pop-up window with an error message appears.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, then the page is updated and
     * the drug quantity by id extracted from the query increases.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String group = request.getParameter(ParameterName.GROUP);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            int id = Integer.parseInt(request.getParameter(ParameterName.ID));
            int quantity = Integer.parseInt(request.getParameter(ParameterName.QUANTITY));
            pharmacistService.addDrugQuantity(id, quantity);

            response.sendRedirect(URLCommand.SHOW_DRUGS_TO_UPDATE + group);

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            request.getRequestDispatcher(URLCommand.SHOW_DRUGS_TO_UPDATE + group).forward(request, response);

        } catch (NumberFormatException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}