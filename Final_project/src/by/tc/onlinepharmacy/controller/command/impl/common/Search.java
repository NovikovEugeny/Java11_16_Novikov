package by.tc.onlinepharmacy.controller.command.impl.common;

import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.CommonService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Class describes object-command, which searches for the description of the drug main.by name.
 */
public class Search implements Command {

    private static final Logger LOGGER = LogManager.getLogger(Search.class.getName());

    /**
     * The value of the parameters <tt>name</tt> from the request, test on the service layer and
     * if it is not valid, than the control is passed to the catch block
     * of <tt>ValidatorException</tt> and forwarding to the same page with error messages.
     * <p>
     * The drug name, extracted from the request is checked on the service layer.
     * If name is not valid the control is passed to the catch block of <tt>ValidatorException</tt>
     * and user stay on the same page and get validation error message.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, then forwarding to the drug list page
     * where displays drug information if such drug exists.
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

            String drugName = request.getParameter(ParameterName.DRUG_NAME);
            List<Drug> drugs = commonService.showDrugsByName(drugName);
            request.setAttribute(AttributeName.DRUGS, drugs);

            page = JspPageName.DRUG_LIST_PAGE;
        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.START_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}