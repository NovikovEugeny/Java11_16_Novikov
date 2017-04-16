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

public class ShowShoppingList implements Command {

    private final static String USER = "user";
    private final static String SHOPPING_LIST = "shoppingList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int clientId = ((User) request.getSession().getAttribute(USER)).getId();

            List<OrderDescription> shoppingList = clientService.showShoppingList(clientId);
            request.setAttribute(SHOPPING_LIST, shoppingList);
            page = JspPageName.CLIENT_SHOPPING_LIST;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {

        }
    }
}
