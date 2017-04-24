package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
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
import java.util.List;


public class ShowMessages implements Command {

    private static final Logger logger = LogManager.getLogger(ShowMessages.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();
            List<OrderDescription> sendingMessages = clientService.showSendingMessageList(clientId);
            List<RERDescription> doctorResponseMessages = clientService.showDoctorResponseMessageList(clientId);

            request.setAttribute(AttributeName.SENDING_MESSAGES, sendingMessages);
            request.setAttribute(AttributeName.DOCTOR_RESPONSE_MESSAGES, doctorResponseMessages);

            page = JspPageName.CLIENT_HOME_PAGE;

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}