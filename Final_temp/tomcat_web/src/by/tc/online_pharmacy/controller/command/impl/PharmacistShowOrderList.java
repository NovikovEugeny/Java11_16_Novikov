package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public class PharmacistShowOrderList implements Command {

    private final static String ORDER_LIST = "orderList";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            List<OrderDescription> orderList = pharmService.pharmacistShowOrderList();
            request.setAttribute(ORDER_LIST, orderList);

            response = JspPageName.PHARMACIST_START_PAGE;
        } catch (ServiceException exc) {

        }
        return response;
    }
}
