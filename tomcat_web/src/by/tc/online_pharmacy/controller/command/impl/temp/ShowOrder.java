package by.tc.online_pharmacy.controller.command.impl.temp;

import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 26.02.2017.
 */
public class ShowOrder implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String DRUG = "drug";
    private final static String USER = "user";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        /*try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
            int pharmacistId = ((User)request.getSession().getAttribute(USER)).getId();

            /Drug drug = pharmService.showOrder(orderId, pharmacistId);

            request.setAttribute(DRUG, drug);

            request.setAttribute(ORDER_ID, orderId);//??????

            response = JspPageName.SHOW_ORDER_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }*/
        return response;
    }
}
