package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;


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
    public String execute(HttpServletRequest request) {
        String response = null;

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
            PharmService pharmService = serviceFactory.getPharmService();

            pharmService.addNewDrug(drug);

            response = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
        } catch (ServiceException exc) {
            //logger
        } catch (ValidatorException exc) {
            request.setAttribute(ERROR_MAP, exc.getErrors());
            response = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
        } catch (NumberFormatException exc) {
            request.setAttribute(EMPTY_NUMBER_INPUTS, EMPTY_NUMBER_INPUTS_MESSAGE);
            response = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
        }
        return response;
    }
}
