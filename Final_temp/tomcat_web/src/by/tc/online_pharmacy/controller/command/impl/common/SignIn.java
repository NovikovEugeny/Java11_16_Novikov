package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.*;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private final static String SENDING_MESSAGES = "sendingMessages";
    private final static String DOCTOR_RESPONSE_MESSAGES = "doctorResponseMessages";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String mobilePhone = request.getParameter(MOBILE);
        String password = request.getParameter(PASSWORD);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            CommonService commonService = serviceFactory.getCommonService();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();
            DoctorService doctorService = serviceFactory.getDoctorService();
            ClientService clientService = serviceFactory.getClientService();

            User user = commonService.signIn(mobilePhone, password);

            if (user != null) {
                if (user.getPosition().equals(PHARMACIST)) {
                    List<OrderDescription> orderList = pharmacistService.pharmacistShowOrderList();
                    request.setAttribute(ORDER_LIST, orderList);
                    page = JspPageName.PHARMACIST_HOME_PAGE;
                }
                if (user.getPosition().equals(DOCTOR)) {
                    List<RERDescription> recipeList = doctorService.showRecipeExtensionRequestList();
                    request.setAttribute(RECIPE_LIST, recipeList);
                    page = JspPageName.DOCTOR_HOME_PAGE;
                }
                if (user.getPosition().equals(CLIENT)) {
                    List<OrderDescription> sendingMessages =
                            clientService.showSendingMessageList(user.getId());
                    List<RERDescription> doctorResponseMessages =
                            clientService.showDoctorResponseMessageList(user.getId());
                    request.setAttribute(SENDING_MESSAGES, sendingMessages);
                    request.setAttribute(DOCTOR_RESPONSE_MESSAGES, doctorResponseMessages);
                    page = JspPageName.CLIENT_HOME_PAGE;
                }
                request.getSession(true).setAttribute(USER, user);
            } else {
                request.setAttribute(ERROR_MESSAGE, ERROR_MESSAGE_CONTENT);
                page = JspPageName.SIGN_IN_PAGE;
            }

            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {
            //logger
            //response?
        } catch (ValidatorException exc) {
            request.setAttribute(ERROR_MAP, exc.getErrors());
            page = JspPageName.SIGN_IN_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}