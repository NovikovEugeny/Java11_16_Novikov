package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.UserService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class SignIn implements Command {

    private final static String MOBILE = "mobile";
    private final static String PASSWORD = "password";
    private final static String PHARMACIST = "pharmacist";
    private final static String DOCTOR = "doctor";
    private final static String CLIENT = "client";
    private final static String USER = "user";
    private final static String ERROR_MESSAGE = "errorMessage";
    private final static String ERROR_MESSAGE_CONTENT = "incorrect login or password";
    private final static String RECIPE_LIST = "recipeList";
    private final static String ORDER_LIST = "orderList";


    @Override
    public String execute(HttpServletRequest request) {

        String response = null;

        try {
            String mobilePhone = request.getParameter(MOBILE);
            String password = request.getParameter(PASSWORD);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();

            User user = userService.signIn(mobilePhone, password);
//!!!!!!!!!
            if(user != null) {
                if (user.getPosition().equals(PHARMACIST)) {
                    PharmService pharmService = serviceFactory.getPharmService();
                    Map<Order, Drug> orderList = pharmService.showOrderList();
                    request.setAttribute(ORDER_LIST, orderList);
                    response = JspPageName.PHARMACIST_START_PAGE;
                }
                if (user.getPosition().equals(DOCTOR)) {
                    PharmService pharmService = serviceFactory.getPharmService();
                    Map<Recipe, Drug> recipeList = pharmService.showRecipeList();
                    request.setAttribute(RECIPE_LIST, recipeList);
                    response = JspPageName.DOCTOR_PAGE;
                }
                if (user.getPosition().equals(CLIENT)) {
                    response = JspPageName.CLIENT_START_PAGE;
                }
                request.getSession(true).setAttribute(USER, user);
            } else {
                request.setAttribute(ERROR_MESSAGE, ERROR_MESSAGE_CONTENT);
                response = JspPageName.SIGN_IN_PAGE;
            }
        } catch (ServiceException exc) {
            response = JspPageName.SIGN_IN_PAGE;///????
            request.setAttribute(ERROR_MESSAGE, exc.getMessage());
        }
        return response;
    }
}