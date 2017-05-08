package by.tc.onlinepharmacy.controller.command.impl.pharmacist;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.CommonService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes the object-command, the execution of which
 * cancels an order. This command is performed main.by the pharmacist
 * in the event of a client failure.
 */
public class PharmacistCancelOrder implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PharmacistCancelOrder.class.getName());

    /**
     * If the value of the parameter <tt>orderId</tt> from the request are not numbers,
     * then the control is passed to the catch block of <tt>NumberFormatException</tt>
     * and forwarding to the page with error 400.
     *
     * If the command is successful, then the page is updated
     * and the line with the corresponding order disappears.
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
            CommonService commonService = serviceFactory.getCommonService();

            int orderId = Integer.parseInt(request.getParameter(ParameterName.ORDER_ID));
            commonService.cancelOrder(orderId);

            response.sendRedirect(URLCommand.PHARMACIST_SHOW_ORDER_LIST);

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (NumberFormatException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}