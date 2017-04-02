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
public class RemoveDrug implements Command {

    private final static String DRUGS = "drugs";
    private final static String GROUP = "group";
    private final static String ID = "id";

    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            int id = Integer.parseInt(request.getParameter(ID));
            pharmService.removeDrug(id);

            String group = request.getParameter(GROUP);
            List<Drug> drugs = pharmService.takeDrugGroup(group);
            request.setAttribute(DRUGS, drugs);

            response = JspPageName.PHARMACIST_DRUG_LIST_TO_UPDATE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }
        return response;
    }
}
