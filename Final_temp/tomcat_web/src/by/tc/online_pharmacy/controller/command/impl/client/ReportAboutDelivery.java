package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.OrderDescription;
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


public class ReportAboutDelivery implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String USER = "user";
    private final static String ORDER_LIST = "orderList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
            int clientId = ((User)request.getSession().getAttribute(USER)).getId();

            clientService.reportAboutDelivery(orderId);

            List<OrderDescription> orderList = clientService.showSendingMessageList(clientId);
            request.setAttribute(ORDER_LIST, orderList);
            page = JspPageName.CLIENT_HOME_PAGE;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {

        }
    }
}
