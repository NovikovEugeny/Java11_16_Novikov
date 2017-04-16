package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
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


public class RemoveDrug implements Command {

    private final static String DRUGS = "drugs";
    private final static String GROUP = "group";
    private final static String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();
            CommonService commonService = serviceFactory.getCommonService();

            int id = Integer.parseInt(request.getParameter(ID));
            pharmacistService.removeDrug(id);

            String group = request.getParameter(GROUP);
            List<Drug> drugs = commonService.showDrugGroup(group);
            request.setAttribute(DRUGS, drugs);

            page = JspPageName.PHARMACIST_DRUG_LIST_TO_UPDATE;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {

        }
    }
}
