package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.Recipe2;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 02.04.2017.
 */
public class OrderWithRecipe implements Command {

    private final static String USER = "user";
    private final static String DRUG_ID = "drugId";
    private final static String QUANTITY = "quantity";
    private final static String PRICE = "price";
    private final static String NEW = "new";
    private final static String RECIPE_CODE = "recipeCode";

    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("command");
        String response = null;

        User client = (User) request.getSession().getAttribute(USER);

        Order order = new Order();
        order.setClientId(client.getId());
        order.setDrugId(Integer.parseInt(request.getParameter(DRUG_ID)));
        order.setQuantity(Integer.parseInt(request.getParameter(QUANTITY)));
        order.setCost(Double.parseDouble(request.getParameter(PRICE)) * order.getQuantity());
        order.setStatus(NEW);

        Recipe2 recipe = new Recipe2();
        recipe.setRecipeCode(request.getParameter(RECIPE_CODE));
        recipe.setDrugId(order.getDrugId());
        recipe.setQuantity(order.getQuantity());

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmService pharmService = serviceFactory.getPharmService();

            pharmService.orderWithRecipe(order, recipe);//return string

            //response = JspPageName.DRUG_LIST_TO_ORDER_PAGE;
        } catch (ServiceException e) {
            //response =
        }
        return response;
    }
}
