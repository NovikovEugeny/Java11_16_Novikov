package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.command.URLCommand;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.resource.ParameterName;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes the object-command, the execution of which
 * adds a new drug.
 */

public class AddNewDrug implements Command {

    private static final Logger logger = LogManager.getLogger(AddNewDrug.class.getName());

    /**
     * Drug data extracted from the request is packed into an object of class
     * {@link by.tc.online_pharmacy.bean.Drug Drug} checked on the service layer.
     * If at least some data is not valid, the control passed to the catch block of <tt>ValidatorException</tt>
     * and user stay on the same page and receives corresponding error messages.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, then the page is updated and
     * the fields become empty.
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
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            Drug drug = new Drug();
            drug.setName(request.getParameter(ParameterName.NAME));
            drug.setGroup(request.getParameter(ParameterName.GROUP));
            drug.setForm(request.getParameter(ParameterName.FORM));
            drug.setDrugAmount(request.getParameter(ParameterName.DRUG_AMOUNT));
            drug.setActiveSubstances(request.getParameter(ParameterName.ACTIVE_SUBSTANCES));
            drug.setCountry(request.getParameter(ParameterName.COUNTRY));
            drug.setDispensing(request.getParameter(ParameterName.DISPENSING));
            drug.setPrice(Double.parseDouble(request.getParameter(ParameterName.PRICE)));
            drug.setQuantity(Integer.parseInt(request.getParameter(ParameterName.QUANTITY)));

            pharmacistService.addNewDrug(drug);

            response.sendRedirect(URLCommand.ADD_DRUG_PAGE);

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.ERROR_MAP, exc.getErrors());
            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (NumberFormatException | NullPointerException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}