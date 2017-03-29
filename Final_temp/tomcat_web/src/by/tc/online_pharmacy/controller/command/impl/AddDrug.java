package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 22.02.2017.
 */
public class AddDrug implements Command {

    private final static String NAME = "name";
    private final static String GROUP = "group";
    private final static String FORM = "form";
    private final static String DRUG_AMOUNT = "drugAmount";
    private final static String ACTIVE_SUBSTANCES = "activeSubstances";
    private final static String COUNTRY = "country";
    private final static String DISPENSING = "dispensing";
    private final static String PRICE = "price";
    private final static String QUANTITY = "quantity";



    @Override
    public String execute(HttpServletRequest request) {
        String response = null;

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

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            pharmService.addDrug(drug);//sucssess or failed

            response = JspPageName.PHARMACIST_ADD_DRUG_PAGE;
        } catch (ServiceException exc) {
            //response = exc.getMessage();
        }
        return response;
    }
}
