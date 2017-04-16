package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class HideMessage implements Command {

    private final static String REQUEST_ID = "requestId";
    private final static String USER = "user";
    private final static String DOCTOR_RESPONSE_MESSAGES = "doctorResponseMessages";
    private final static String SENDING_MESSAGES = "sendingMessages";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int requestId = Integer.parseInt(request.getParameter(REQUEST_ID));
            int clientId = ((User) request.getSession().getAttribute(USER)).getId();

            clientService.hideMessage(requestId);

            List<OrderDescription> sendingMessages =
                    clientService.showSendingMessageList(clientId);
            List<RERDescription> doctorResponseMessages =
                    clientService.showDoctorResponseMessageList(clientId);

            request.setAttribute(SENDING_MESSAGES, sendingMessages);
            request.setAttribute(DOCTOR_RESPONSE_MESSAGES, doctorResponseMessages);

            page = JspPageName.CLIENT_HOME_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {

        }
    }
}
