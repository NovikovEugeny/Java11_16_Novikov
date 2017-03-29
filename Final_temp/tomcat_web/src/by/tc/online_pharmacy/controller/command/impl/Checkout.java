package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 03.03.2017.
 */
public class Checkout implements Command {

    private final static String DRUG_ID = "drugId";
    private final static String PRICE = "price";
    private final static String DISPENSING = "dispensing";
    private final static String WITHOUT_PRESCRIPTION = "without prescription";


    @Override
    public String execute(HttpServletRequest request) {

        int drugId = Integer.parseInt(request.getParameter(DRUG_ID));
        double price = Double.parseDouble(request.getParameter(PRICE));
        String dispensing = request.getParameter(DISPENSING);

        request.setAttribute(DRUG_ID, drugId);
        request.setAttribute(PRICE, price);

        if (dispensing.equals(WITHOUT_PRESCRIPTION)) {
            return JspPageName.ORDER_FORM_WITHOUT_RECIPE_PAGE;
        } else {
            return JspPageName.ORDER_FORM_WITH_RECIPE_PAGE;
        }
    }
}
