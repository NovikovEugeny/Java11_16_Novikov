package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.Order;
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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

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
            ClientService clientService = serviceFactory.getClientService();

            Date currentDate = new Date();
            Date recipeEndDate = clientService.showRecipeEndDate(recipeCode);

            double currentClientBalance = clientService.showCurrentBalance(clientId);
            int currentDrugQuantity = clientService.showCurrentDrugQuantity(order.getDrugId());

            if (currentDate.after(recipeEndDate)) {
                messageContent = "expired";
            } else if (currentClientBalance < order.getCost()) {
                messageContent = "is not enough money";
            } else if (currentDrugQuantity < order.getQuantity()) {
                messageContent = "is not enough drugs";
            } else {
                clientService.orderWithRecipe(order, recipeCode);
                messageContent = "successfully";
            }

            request.setAttribute(EXECUTE_MESSAGE, messageContent);

            page = JspPageName.CLIENT_ORDER_BY_ERECIPE;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {
            //logger
        }
    }
}
