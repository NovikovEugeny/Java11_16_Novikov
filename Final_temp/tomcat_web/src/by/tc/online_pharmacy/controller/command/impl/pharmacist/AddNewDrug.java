package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;
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


public class AddNewDrug implements Command {

    private static final Logger logger = LogManager.getLogger(AddNewDrug.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
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

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            pharmacistService.addNewDrug(drug);

            page = JspPageName.PHARMACIST_ADD_DRUG_REDIRECT;
            response.sendRedirect(page);
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.ERROR_MAP, exc.getErrors());
            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (NumberFormatException exc) {
            request.setAttribute(AttributeName.IS_VALID, AttributeName.NO);
            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}