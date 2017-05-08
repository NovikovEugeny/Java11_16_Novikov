package by.tc.onlinepharmacy.controller.command.impl.client;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.service.ClientService;
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
 * Class describes the object-command, which displays current balance of the client.
 */
public class ShowBalance implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowBalance.class.getName());

    /**
     * If the command is successful, forwarding to the page
     * where current balance is displayed.
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
            ClientService clientService = serviceFactory.getClientService();

            int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();
            double balance = clientService.showCurrentBalance(clientId);
            request.setAttribute(AttributeName.BALANCE, balance);

            page = JspPageName.CLIENT_BALANCE;

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}