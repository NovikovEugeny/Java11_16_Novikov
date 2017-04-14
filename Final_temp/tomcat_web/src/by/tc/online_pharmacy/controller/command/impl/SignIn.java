package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.UserService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private final static String ERROR_MAP = "errorMap";
    private final static String RECIPE_LIST = "recipeList";
    private final static String ORDER_LIST = "orderList";
    private final static String ORDER_MESSAGE_LIST = "orderMessageList";
    private final static String RECIPE_MESSAGE_LIST = "recipeMessageList";


    @Override
    public String execute(HttpServletRequest request) {

        String response = null;

        String mobilePhone = request.getParameter(MOBILE);
        String password = request.getParameter(PASSWORD);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            PharmService pharmService = serviceFactory.getPharmService();

            User user = userService.signIn(mobilePhone, password);

            if (user != null) {
                if (user.getPosition().equals(PHARMACIST)) {
                    List<OrderDescription> orderList = pharmService.pharmacistShowOrderList();
                    request.setAttribute(ORDER_LIST, orderList);
                    response = JspPageName.PHARMACIST_START_PAGE;
                }
                if (user.getPosition().equals(DOCTOR)) {
                    List<RERDescription> recipeList = pharmService.takeRecipeExtensionRequestList();
                    request.setAttribute(RECIPE_LIST, recipeList);
                    response = JspPageName.DOCTOR_PAGE;
                }
                if (user.getPosition().equals(CLIENT)) {
                    List<OrderDescription> orderMessageList =
                            pharmService.takeSendingMessageList(user.getId());
                    List<RERDescription> recipeMessageList =
                            pharmService.takeDoctorResponseMessageList(user.getId());
                    request.setAttribute(ORDER_MESSAGE_LIST, orderMessageList);
                   // request.setAttribute(RECIPE_MESSAGE_LIST, recipeMessageList);
                    response = JspPageName.CLIENT_START_PAGE;
                }
                request.getSession(true).setAttribute(USER, user);
            } else {
                request.setAttribute(ERROR_MESSAGE, ERROR_MESSAGE_CONTENT);
                response = JspPageName.SIGN_IN_PAGE;
            }
        } catch (ServiceException exc) {
            //logger
            //response?
            exc.printStackTrace();
        } catch (ValidatorException exc) {
            request.setAttribute(ERROR_MAP, exc.getErrors());
            response = JspPageName.SIGN_IN_PAGE;
        }
        return response;
    }
}