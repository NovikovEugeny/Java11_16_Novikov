package by.tc.online_pharmacy.controller.command.impl.temp;

import by.tc.online_pharmacy.controller.command.Command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 04.03.2017.
 */
public class ShowRecipe implements Command {

    private final static String RECIPE_ID = "recipeId";
    private final static String USER = "user";
    private final static String NAME = "name";
    private final static String GROUP = "group";
    private final static String FORM = "form";
    private final static String DRUG_AMOUNT = "drugAmount";
    private final static String ACTIVE_SUBSTANCES = "activeSubstances";
    private final static String COUNTRY = "country";
    private final static String QUANTITY = "quantity";
    private final static String RECIPE_CODE = "recipeCode";


    @Override
    public String execute(HttpServletRequest request) {
        String response = null;
/*
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            int recipeId = Integer.parseInt(request.getParameter(RECIPE_ID));
            int doctorId = ((User)request.getSession().getAttribute(USER)).getId();

            Map<String, String> map = pharmService.showRecipe(recipeId, doctorId);
            request.setAttribute(NAME, map.get(NAME));
            request.setAttribute(GROUP, map.get(GROUP));
            request.setAttribute(FORM, map.get(FORM));
            request.setAttribute(DRUG_AMOUNT, map.get(DRUG_AMOUNT));
            request.setAttribute(ACTIVE_SUBSTANCES, map.get(ACTIVE_SUBSTANCES));
            request.setAttribute(COUNTRY, map.get(COUNTRY));
            request.setAttribute(QUANTITY, map.get(QUANTITY));
            request.setAttribute(RECIPE_CODE, map.get(RECIPE_CODE));

            request.setAttribute(RECIPE_ID, recipeId);//??????

            response = JspPageName.SHOW_RECIPE_PAGE;
        } catch (ServiceException exc) {
            response = exc.getMessage();
        }*/
        return response;
    }
}
