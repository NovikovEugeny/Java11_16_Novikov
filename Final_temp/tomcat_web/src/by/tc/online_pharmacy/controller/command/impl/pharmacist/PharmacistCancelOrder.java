package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.CommonService;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class PharmacistCancelOrder implements Command {

    private final static String ORDER_ID = "orderId";
    private final static String ORDER_LIST = "orderList";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            CommonService commonService = serviceFactory.getCommonService();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
            commonService.cancelOrder(orderId);

            List<OrderDescription> orderList = pharmacistService.pharmacistShowOrderList();
            request.setAttribute(ORDER_LIST, orderList);
            page = JspPageName.PHARMACIST_HOME_PAGE;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {

        }
    }
}
