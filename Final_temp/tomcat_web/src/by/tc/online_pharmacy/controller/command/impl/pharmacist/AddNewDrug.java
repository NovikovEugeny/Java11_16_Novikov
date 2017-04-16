package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddNewDrug implements Command {

    private final static String NAME = "name";
    private final static String GROUP = "group";
    private final static String FORM = "form";
    private final static String DRUG_AMOUNT = "drugAmount";
    private final static String ACTIVE_SUBSTANCES = "activeSubstances";
    private final static String COUNTRY = "country";
    private final static String DISPENSING = "dispensing";
    private final static String PRICE = "price";
    private final static String QUANTITY = "quantity";
    private final static String ERROR_MAP = "errorMap";
    private final static String EMPTY_NUMBER_INPUTS = "ENI";
    private final static String EMPTY_NUMBER_INPUTS_MESSAGE = "*can not be empty";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            Drug drug = new Drug();
            drug.setName(request.getParameter(NAME));
            drug.setGroup(request.getParameter(GROUP));
            drug.setForm(request.getParameter(FORM));
            drug.setDrugAmount(request.getParameter(DRUG_AMOUNT));
            drug.setActiveSubstances(request.getParameter(ACTIVE_SUBSTANCES));
            drug.setCountry(request.getParameter(COUNTRY));
            drug.setDispensing(request.getParameter(DISPENSING));
            drug.setPrice(Double.parseDouble(request.getParameter(PRICE)));
            drug.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            pharmacistService.addNewDrug(drug);

            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;

            request.getRequestDispatcher(page).forward(request,response);
        } catch (ServiceException exc) {
            //logger
        } catch (ValidatorException exc) {
            request.setAttribute(ERROR_MAP, exc.getErrors());
            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (NumberFormatException exc) {
            request.setAttribute(EMPTY_NUMBER_INPUTS, EMPTY_NUMBER_INPUTS_MESSAGE);
            page = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
