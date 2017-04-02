package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Евгений on 19.02.2017.
 */
public class Search implements Command {

    private final static String DRUG_NAME = "drugName";
    private final static String DRUGS = "drugs";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            String name = request.getParameter(DRUG_NAME);
            List<Drug> drugs = pharmService.takeDrugsByName(name);
            request.setAttribute(DRUGS, drugs);

            response = JspPageName.DRUG_LIST_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }
        return response;
    }
}
