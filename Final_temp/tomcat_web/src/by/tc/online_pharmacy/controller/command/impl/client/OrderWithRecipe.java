package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.ParameterName;
import by.tc.online_pharmacy.controller.command.URLCommand;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class OrderWithRecipe implements Command {

    private static final Logger logger = LogManager.getLogger(OrderWithRecipe.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();
        String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);

        Order order = new Order();
        order.setClientId(clientId);
        order.setDrugId(Integer.parseInt(request.getParameter(ParameterName.DRUG_ID)));
        order.setQuantity(Integer.parseInt(request.getParameter(ParameterName.QUANTITY)));
        order.setCost(Double.parseDouble(request.getParameter(ParameterName.COST)));
        order.setStatus(ParameterName.STATUS_NEW);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            Date currentDate = new Date();
            Date recipeEndDate = clientService.showRecipeEndDate(recipeCode);

            double currentClientBalance = clientService.showCurrentBalance(clientId);
            int currentDrugQuantity = clientService.showCurrentDrugQuantity(order.getDrugId());

            if (currentDate.after(recipeEndDate)) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.TIME_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_ERECIPE + recipeCode).forward(request, response);

            } else if (currentDrugQuantity < order.getQuantity()) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.QUANTITY_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_ERECIPE + recipeCode).forward(request, response);

            } else if (currentClientBalance < order.getCost()) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.MONEY_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_ERECIPE + recipeCode).forward(request, response);

            } else {
                clientService.orderWithRecipe(order, recipeCode);
                response.sendRedirect(URLCommand.ORDER_BY_ERECIPE);
            }

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
            
        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}