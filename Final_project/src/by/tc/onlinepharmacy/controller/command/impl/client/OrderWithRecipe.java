package by.tc.onlinepharmacy.controller.command.impl.client;

import by.tc.onlinepharmacy.bean.Order;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.ClientService;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Class describes the object-command, which allows to order
 * a drug, requiring a prescription.
 */
public class OrderWithRecipe implements Command {

    private static final Logger LOGGER = LogManager.getLogger(OrderWithRecipe.class.getName());

    /**
     * The recipe code from the request test on validity. If it is not valid
     * then forwarding to the same page with error message.
     * <p>
     * The quantity of the drug is extracted from the request and compared
     * with the current quantity in the warehouse and if the client ordered
     * more than there is in the warehouse he receives an error message.
     * <p>
     * The cost of the order is extracted from the request and compared
     * with the current user balance. If the current balance is less
     * than the cost of the order, the user receives an error message.
     * <p>
     * If the value of the parameters <tt>drugId</tt>, <tt>quantity</tt>, <tt>cost</tt>
     * from the request are not integer, then the control is passed to the catch block
     * of <tt>NumberFormatException</tt> and forwarding to the page with error 400.
     * <p>
     * If the value of the parameters <tt>quantity</tt>, test on the service layer and
     * if it is not correct(negative or zero), than the control is passed to the catch block
     * of <tt>ValidatorException</tt> and forwarding to the same page with error messages.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, then redirecting to the previous page.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            String recipeCode = request.getParameter(ParameterName.RECIPE_CODE);
            int clientId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();
            int drugId = Integer.parseInt(request.getParameter(ParameterName.DRUG_ID));
            int quantity = Integer.parseInt(request.getParameter(ParameterName.QUANTITY));
            double cost = Double.parseDouble(request.getParameter(ParameterName.COST));

            Date currentDate = new Date();
            Date recipeEndDate = clientService.showRecipeEndDate(recipeCode);

            double currentClientBalance = clientService.showCurrentBalance(clientId);
            int currentDrugQuantity = clientService.showCurrentDrugQuantity(drugId);

            if (currentDate.after(recipeEndDate)) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.TIME_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_ERECIPE + recipeCode).forward(request, response);

            } else if (currentDrugQuantity < quantity) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.QUANTITY_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_ERECIPE + recipeCode).forward(request, response);

            } else if (currentClientBalance < cost) {
                request.setAttribute(AttributeName.EXECUTION, AttributeName.MONEY_ERROR);
                request.getRequestDispatcher(URLCommand.SHOW_ERECIPE + recipeCode).forward(request, response);

            } else {
                Order order = new Order();
                order.setClientId(clientId);
                order.setDrugId(drugId);
                order.setQuantity(quantity);
                order.setCost(cost);
                order.setStatus(ParameterName.STATUS_NEW);

                clientService.orderWithRecipe(order, recipeCode);
                response.sendRedirect(URLCommand.ORDER_BY_ERECIPE);
            }

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
            exc.printStackTrace();

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.CLIENT_EXTEND_RECIPE_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (NumberFormatException | NullPointerException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}