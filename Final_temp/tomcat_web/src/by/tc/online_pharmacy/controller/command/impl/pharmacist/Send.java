package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.controller.command.URLCommand;
import by.tc.online_pharmacy.resource.ParameterName;
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


/**
 * Class describes the object-command, the execution of which sends the drug
 * to the client.
 */

public class Send implements Command {

    private static final Logger logger = LogManager.getLogger(Send.class.getName());

    /**
     * If the command is successful, then the drug is sent to user.
     * The page is updated and the line with the order disappears.
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
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            int orderId = Integer.parseInt(request.getParameter(ParameterName.ORDER_ID));
            int pharmacistId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();

            pharmacistService.send(orderId, pharmacistId);

            response.sendRedirect(URLCommand.PHARMACIST_SHOW_ORDER_LIST);

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (NumberFormatException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}