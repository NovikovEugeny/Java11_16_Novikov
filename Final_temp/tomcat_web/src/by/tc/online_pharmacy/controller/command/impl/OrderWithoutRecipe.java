package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class OrderWithoutRecipe implements Command {

    private final static String USER = "user";
    private final static String DRUG_ID = "drugId";
    private final static String QUANTITY = "quantity";
    private final static String PRICE = "price";
    private final static String NEW = "new";
    private final static String DRUGS = "drugs";
    private final static String GROUP = "group";
    private final static String EXECUTE_MESSAGE = "executeMessage";

    private String messageContent;


    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        int clientId = ((User) request.getSession().getAttribute(USER)).getId();

        Order order = new Order();
        order.setClientId(clientId);
        order.setDrugId(Integer.parseInt(request.getParameter(DRUG_ID)));
        order.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));
        order.setCost(Double.parseDouble(request.getParameter(PRICE)) * order.getQuantity());
        order.setStatus(NEW);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            double currentClientBalance = pharmService.showCurrentBalance(clientId);
            int currentDrugQuantity = pharmService.showCurrentDrugQuantity(order.getDrugId());

            if (currentClientBalance < order.getCost()) {
                messageContent = "not enough money";
            } else if (currentDrugQuantity < order.getQuantity()) {
                messageContent = "not enough drugs";
            } else {
                pharmService.orderWithoutRecipe(order);
                messageContent = "successfully";
            }

            request.setAttribute(EXECUTE_MESSAGE, messageContent);

            String group = request.getParameter(GROUP);
            List<Drug> drugs = pharmService.takeDrugGroup(group);
            request.setAttribute(DRUGS, drugs);

            response = JspPageName.CLIENT_DRUG_LIST_TO_ORDER;
        } catch (ServiceException exc) {
            //response =
        }
        return response;
    }
}
