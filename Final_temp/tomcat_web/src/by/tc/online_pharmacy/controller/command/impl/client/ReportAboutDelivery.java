package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.URLCommand;
import by.tc.online_pharmacy.controller.util.ParameterName;
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


public class ReportAboutDelivery implements Command {

    private static final Logger logger = LogManager.getLogger(ReportAboutDelivery.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int orderId = Integer.parseInt(request.getParameter(ParameterName.ORDER_ID));
            clientService.reportAboutDelivery(orderId);

            response.sendRedirect(URLCommand.SHOW_MESSAGES);

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            String page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}