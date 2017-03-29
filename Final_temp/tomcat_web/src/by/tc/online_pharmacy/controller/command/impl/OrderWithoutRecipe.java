package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 24.02.2017.
 */
public class OrderWithoutRecipe implements Command {

    private final static String USER = "user";
    private final static String DRUG_ID = "drugId";
    private final static String QUANTITY = "quantity";
    private final static String PRICE = "price";
    private final static String DELIVERY_ADDRESS = "deliveryAddress";
    private final static String NEW = "new";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        User client = (User) request.getSession().getAttribute(USER);

        Order order = new Order();
        order.setClientId(client.getId());
        order.setDrugId(Integer.parseInt(request.getParameter(DRUG_ID)));
        order.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));
        order.setCost(Double.parseDouble(request.getParameter(PRICE)) * order.getQuantity());
        order.setDeliveryAddress(request.getParameter(DELIVERY_ADDRESS));
        order.setStatus(NEW);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            pharmService.orderWithoutRecipe(order);//return string

            //response = JspPageName.DRUG_LIST_TO_ORDER_PAGE;
        } catch (ServiceException e) {
            //response =
        }
        return response;
    }
}
