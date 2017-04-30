package by.tc.online_pharmacy.controller.command.impl.common;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.ParameterName;
import by.tc.online_pharmacy.controller.command.URLCommand;
import by.tc.online_pharmacy.service.CommonService;
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

public class SignIn implements Command {

    private static final Logger logger = LogManager.getLogger(SignIn.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            CommonService commonService = serviceFactory.getCommonService();

            String mobilePhone = request.getParameter(ParameterName.MOBILE);
            String password = request.getParameter(ParameterName.PASSWORD);
            User user = commonService.signIn(mobilePhone, password);

            if (user != null) {

                request.getSession(true).setAttribute(AttributeName.USER, user);

                if (user.getPosition().equals(ParameterName.PHARMACIST)) {
                    response.sendRedirect(URLCommand.PHARMACIST_SHOW_ORDER_LIST);
                }
                if (user.getPosition().equals(ParameterName.DOCTOR)) {
                    response.sendRedirect(URLCommand.SHOW_RECIPE_EXTENSION_REQUESTS);
                }
                if (user.getPosition().equals(ParameterName.CLIENT)) {
                    response.sendRedirect(URLCommand.SHOW_MESSAGES);
                }

            } else {
                request.setAttribute(AttributeName.IS_EXISTS, AttributeName.NO);
                page = JspPageName.SIGN_IN_PAGE;
                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.ERROR_MAP, exc.getErrors());
            page = JspPageName.SIGN_IN_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}