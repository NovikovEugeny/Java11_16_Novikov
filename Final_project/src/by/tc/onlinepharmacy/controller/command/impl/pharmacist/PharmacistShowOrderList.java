package by.tc.onlinepharmacy.controller.command.impl.pharmacist;

import by.tc.onlinepharmacy.bean.OrderDescription;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.service.PharmacistService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.resource.AttributeName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Class describes object-command, which displays full list of orders.
 */
public class PharmacistShowOrderList implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PharmacistShowOrderList.class.getName());

    /**
     * If the command is successful, forwarding to the pharmacist home page
     * where orders are displayed.
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
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            List<OrderDescription> orderList = pharmacistService.pharmacistShowOrderList();
            request.setAttribute(AttributeName.ORDER_LIST, orderList);

            page = JspPageName.PHARMACIST_HOME_PAGE;

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}