package by.tc.onlinepharmacy.controller.command.impl.client;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.OrderDescription;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.service.ClientService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Class describes object-command, which displays full list of orders,
 * which the client can cancel.
 */
public class ClientShowOrderList implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ClientShowOrderList.class.getName());

    /**
     * If the command is successful, forwarding to the page
     * where orders are displayed.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block
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
            ClientService clientService = serviceFactory.getClientService();

            int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();
            List<OrderDescription> orderList = clientService.clientShowOrderList(clientId);
            request.setAttribute(AttributeName.ORDER_LIST, orderList);

            page = JspPageName.CLIENT_CANCEL_ORDER_PAGE;

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}