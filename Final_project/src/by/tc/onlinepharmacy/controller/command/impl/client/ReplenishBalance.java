package by.tc.onlinepharmacy.controller.command.impl.client;

import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.ClientService;
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
 * Class describes the object-command, which replenish the balance
 * on the client card.
 */
public class ReplenishBalance implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ReplenishBalance.class.getName());

    /**
     * If money amount extracted from the request is not number or null,
     * then control passed to the catch block of <tt>NumberFormatException</tt>
     * and <tt>NullPointerException</tt> and client forwarding to the page with
     * error 400.
     * <p>
     * Money amount extracted from the request, validate on the service layer.
     * If it not valid, then the control passed to the catch block of
     * <tt>ValidatorException</tt> and client receives an error message.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, then the page is updated and balance updates.
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
            double amount = Double.parseDouble(request.getParameter(ParameterName.MONEY_AMOUNT));
            clientService.replenishBalance(clientId, amount);

            response.sendRedirect(URLCommand.SHOW_BALANCE);

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            request.getRequestDispatcher(URLCommand.SHOW_BALANCE).forward(request, response);

        } catch (NumberFormatException | NullPointerException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}