package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ClientCancelOrder implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String USER = "user";
    private final static String ORDER_LIST = "orderList";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        int clientId = ((User) request.getSession().getAttribute(USER)).getId();

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
            pharmService.cancelOrder(orderId);

            List<OrderDescription> orderList = pharmService.clientShowOrderList(clientId);
            request.setAttribute(ORDER_LIST, orderList);
            response = JspPageName.CLIENT_CANCEL_ORDER_PAGE;
        } catch (ServiceException exc) {

        }
        return response;
    }
}