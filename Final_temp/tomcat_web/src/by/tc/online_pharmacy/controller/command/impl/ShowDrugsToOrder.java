package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;


public class ShowDrugsToOrder implements Command {

    private final static String GROUP = "group";
    private final static String DRUGS = "drugs";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            String group = request.getParameter(GROUP);
            //request.setAttribute(DRUGS, pharmService.takeDrugGroup(group));
            request.setAttribute(DRUGS, pharmService.takeDrugGroupToOrder(group));
            response = JspPageName.CLIENT_DRUG_LIST_TO_ORDER;
        } catch (ServiceException exc) {
            //logger
        }
        return response;
    }
}
