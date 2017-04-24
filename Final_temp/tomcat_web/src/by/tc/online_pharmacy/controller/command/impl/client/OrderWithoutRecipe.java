package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.URLCommand;
import by.tc.online_pharmacy.controller.util.ParameterName;
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


public class OrderWithoutRecipe implements Command {

    private static final Logger logger = LogManager.getLogger(OrderWithoutRecipe.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String group = request.getParameter(ParameterName.GROUP);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();

            Order order = new Order();
            order.setClientId(clientId);
            order.setDrugId(Integer.parseInt(request.getParameter(ParameterName.DRUG_ID)));
            order.setQuantity(Integer.parseInt(request.getParameter(ParameterName.QUANTITY)));
            order.setCost(Double.parseDouble(request.getParameter(ParameterName.PRICE)) * order.getQuantity());
            order.setStatus(ParameterName.STATUS_NEW);

            double currentClientBalance = clientService.showCurrentBalance(clientId);
            int currentDrugQuantity = clientService.showCurrentDrugQuantity(order.getDrugId());

            if (currentDrugQuantity < order.getQuantity()) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.QUANTITY_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_DRUGS_TO_ORDER + group).forward(request, response);

            }
             else if (currentClientBalance < order.getCost()) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.MONEY_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_DRUGS_TO_ORDER + group).forward(request, response);

            } else {
                clientService.orderWithoutRecipe(order);
                response.sendRedirect(URLCommand.SHOW_DRUGS_TO_ORDER + group);
            }

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            String page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException | NumberFormatException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            request.getRequestDispatcher(URLCommand.SHOW_DRUGS_TO_ORDER + group).forward(request, response);
        }
    }
}