package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class OrderWithRecipe implements Command {

    private final static String USER = "user";
    private final static String DRUG_ID = "drugId";
    private final static String QUANTITY = "quantity";
    private final static String COST = "cost";
    private final static String NEW = "new";
    private final static String RECIPE_CODE = "recipeCode";
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
        order.setCost(Double.parseDouble(request.getParameter(COST)));
        order.setStatus(NEW);

        String recipeCode = request.getParameter(RECIPE_CODE);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            Date currentDate = new Date();
            Date recipeEndDate = pharmService.takeRecipeEndDate(recipeCode);

            double currentClientBalance = pharmService.showCurrentBalance(clientId);
            int currentDrugQuantity = pharmService.showCurrentDrugQuantity(order.getDrugId());

            if (currentDate.getTime() > recipeEndDate.getTime()) {
                messageContent = "expired";
            } else if (currentClientBalance < order.getCost()) {
                messageContent = "is not enough money";
            } else if (currentDrugQuantity < order.getQuantity()) {
                messageContent = "is not enough drugs";
            } else {
                pharmService.orderWithRecipe(order, recipeCode);
                messageContent = "successfully";
            }

            request.setAttribute(EXECUTE_MESSAGE, messageContent);

            response = JspPageName.CLIENT_ORDER_BY_ER_RECIPE;
        } catch (ServiceException exc) {
            //logger
        }
        return response;
    }
}
