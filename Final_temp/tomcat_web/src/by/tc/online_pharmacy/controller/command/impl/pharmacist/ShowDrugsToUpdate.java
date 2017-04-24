package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;
import by.tc.online_pharmacy.service.CommonService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ShowDrugsToUpdate implements Command {

    private static final Logger logger = LogManager.getLogger(ShowDrugsToUpdate.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            CommonService commonService = serviceFactory.getCommonService();

            String group = request.getParameter(ParameterName.GROUP);
            List<Drug> drugs = commonService.showDrugGroup(group);
            request.setAttribute(AttributeName.DRUGS, drugs);

            page = JspPageName.PHARMACIST_DRUG_LIST_TO_UPDATE;

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}