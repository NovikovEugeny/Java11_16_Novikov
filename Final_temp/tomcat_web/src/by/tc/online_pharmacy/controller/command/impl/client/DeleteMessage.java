package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.command.URLCommand;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.resource.ParameterName;
import by.tc.online_pharmacy.service.ClientService;
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
 * Class describes the object-command, which deletes the message
 * about the consideration results of the recipe extension request.
 */

public class DeleteMessage implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteMessage.class.getName());

    /**
     * If the command is successful, then the message is removed from the message list
     * by id extracted from the request. The page is updated and
     * the message disappears and is never displayed again.
     * <p>
     * <p>
     * If the value of the parameter <tt>requestId</tt> from the request is not integer,
     * then the control is passed to the catch block of <tt>NumberFormatException</tt>
     * and forwarding to the page with error 400.
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
            ClientService clientService = serviceFactory.getClientService();

            int requestId = Integer.parseInt(request.getParameter(ParameterName.REQUEST_ID));
            clientService.deleteMessage(requestId);

            response.sendRedirect(URLCommand.SHOW_MESSAGES);

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