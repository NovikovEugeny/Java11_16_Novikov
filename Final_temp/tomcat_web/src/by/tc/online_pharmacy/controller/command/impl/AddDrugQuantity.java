package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class AddDrugQuantity implements Command {

    private final static String DRUGS = "drugs";
    private final static String QUANTITY = "quantity";
    private final static String GROUP = "group";
    private final static String ID = "id";

    @Override
    public String execute(HttpServletRequest request) {

        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        PharmService pharmService = serviceFactory.getPharmService();

        try {
            int id = Integer.parseInt(request.getParameter(ID));
            int quantity = Integer.parseInt(request.getParameter(QUANTITY));
            pharmService.addDrugQuantity(id, quantity);

            String group = request.getParameter(GROUP);
            List<Drug> drugs = pharmService.takeDrugGroup(group);
            request.setAttribute(DRUGS, drugs);

            response = JspPageName.PHARMACIST_DRUG_LIST_TO_UPDATE;
        } catch (ServiceException exc) {
            //logger
        }
        return response;
    }
}
