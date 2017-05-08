package by.tc.onlinepharmacy.controller.command.impl.client;

import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.ClientService;
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
 * Class describes the object-command, the execution of which displays all drugs
 * in the selected group, that client can order.
 */
public class ShowDrugsToOrder implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowDrugsToOrder.class.getName());

    /**
     * If the command is successful, forwarding to the page
     * where drug list is displayed.
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

            String group = request.getParameter(ParameterName.GROUP);
            List<Drug> drugs = clientService.showDrugGroupToOrder(group);
            request.setAttribute(AttributeName.DRUGS, drugs);

            page = JspPageName.CLIENT_DRUG_LIST_TO_ORDER;

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}