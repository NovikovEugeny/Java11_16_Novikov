package by.tc.onlinepharmacy.controller.command.impl.common;

import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.service.CommonService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class describes object-command, which displays full list of drugs by group.
 */
public class ShowDrugs implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ShowDrugs.class.getName());

    /**
     * If the command is successful, then forwarding to the drug list page
     * where displays information about all of drug from pharmacological group,
     * which name extracted from the request.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            CommonService commonService = serviceFactory.getCommonService();

            String group = request.getParameter(ParameterName.GROUP);
            List<Drug> drugs = commonService.showDrugGroup(group);
            request.setAttribute(AttributeName.DRUGS, drugs);

            page = JspPageName.DRUG_LIST_PAGE;
        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}