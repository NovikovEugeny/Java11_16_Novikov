package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ShowDrugsToOrder implements Command {

    private final static String GROUP = "group";
    private final static String DRUGS = "drugs";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            String group = request.getParameter(GROUP);
            List<Drug> drugs = clientService.showDrugGroupToOrder(group);
            request.setAttribute(DRUGS, drugs);
            page = JspPageName.CLIENT_DRUG_LIST_TO_ORDER;

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {
            //logger
        }
    }
}
