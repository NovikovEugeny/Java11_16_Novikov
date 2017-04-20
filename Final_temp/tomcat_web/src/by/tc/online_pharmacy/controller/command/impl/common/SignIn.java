package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.CommonService;
import by.tc.online_pharmacy.service.DoctorService;
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
import java.util.List;

public class SignIn implements Command {

    private static final Logger logger = LogManager.getLogger(SignIn.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        String mobilePhone = request.getParameter(ParameterName.MOBILE);
        String password = request.getParameter(ParameterName.PASSWORD);

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            CommonService commonService = serviceFactory.getCommonService();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();
            DoctorService doctorService = serviceFactory.getDoctorService();
            ClientService clientService = serviceFactory.getClientService();

            User user = commonService.signIn(mobilePhone, password);

            if (user != null) {
                if (user.getPosition().equals(ParameterName.PHARMACIST)) {
                    List<OrderDescription> orderList = pharmacistService.pharmacistShowOrderList();
                    request.setAttribute(AttributeName.ORDER_LIST, orderList);
                    page = JspPageName.PHARMACIST_HOME_PAGE;
                }
                if (user.getPosition().equals(ParameterName.DOCTOR)) {
                    List<RERDescription> recipeList = doctorService.showRecipeExtensionRequestList();
                    request.setAttribute(AttributeName.RECIPE_LIST, recipeList);
                    page = JspPageName.DOCTOR_HOME_PAGE;
                }
                if (user.getPosition().equals(ParameterName.CLIENT)) {
                    List<OrderDescription> sendingMessages = clientService.showSendingMessageList(user.getId());
                    List<RERDescription> doctorResponseMessages = clientService.showDoctorResponseMessageList(user.getId());
                    request.setAttribute(AttributeName.SENDING_MESSAGES, sendingMessages);
                    request.setAttribute(AttributeName.DOCTOR_RESPONSE_MESSAGES, doctorResponseMessages);
                    page = JspPageName.CLIENT_HOME_PAGE;
                }

                request.getSession(true).setAttribute(AttributeName.USER, user);
            } else {
                request.setAttribute(AttributeName.IS_EXISTS, AttributeName.NO);
                page = JspPageName.SIGN_IN_PAGE;
            }

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            exc.printStackTrace();
            page = JspPageName.SERVER_ERROR_PAGE;
        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.ERROR_MAP, exc.getErrors());
            page = JspPageName.SIGN_IN_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}