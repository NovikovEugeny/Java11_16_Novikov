package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.resource.ParameterName;
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


/**
 * Class describes the object-command, the execution of which displays all drugs
 * in the selected group to update(increase in quantity or removal from the assortment).
 */

public class ShowDrugsToUpdate implements Command {

    private static final Logger logger = LogManager.getLogger(ShowDrugsToUpdate.class.getName());

    /**
     * If the command is successful, forwarding to the page
     * where drug list is displayed.
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

            page = JspPageName.PHARMACIST_DRUG_LIST_TO_UPDATE;

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}