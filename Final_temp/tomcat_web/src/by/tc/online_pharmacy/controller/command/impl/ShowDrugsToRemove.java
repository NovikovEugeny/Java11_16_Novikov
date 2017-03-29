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
 * Created by Евгений on 08.03.2017.
 */
public class ShowDrugsToRemove implements Command {

    private final static String GROUP = "group";
    private final static String DRUGS = "drugs";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            String group = request.getParameter(GROUP);
            List<Drug> drugs = pharmService.takeDrugGroup(group);
            request.setAttribute(DRUGS, drugs);

            response = JspPageName.PHARMACIST_DRUG_LIST_TO_REMOVE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }
        return response;
    }
}
