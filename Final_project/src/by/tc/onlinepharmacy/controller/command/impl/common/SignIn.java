package by.tc.onlinepharmacy.controller.command.impl.common;

import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.service.CommonService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Class describes object-command, which gives the user access to a special section,
 * depending on his rights.
 */
public class SignIn implements Command {

    private static final Logger LOGGER = LogManager.getLogger(SignIn.class.getName());

    /**
     * Mobile and password extracted from the request are checked on the service layer.
     * If fields are empty, the control passed to the catch block of <tt>ValidatorException</tt>
     * and user stay on the same page and get validation error message.
     * <p>
     * If there is no corresponding mobile and password in the database,
     * the user stays on the login page where the corresponding message is displayed
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     * <p>
     * If the command is successful, then redirecting to the page that corresponds
     * to the user's rights(client page, doctor page, pharmacist page), where are displayed
     * data, corresponding to the user's home page.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
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
                if (user.getPosition().equals(ParameterName.ADMIN)) {
                    response.sendRedirect(URLCommand.SHOW_EMPLOYEES);
                }

            } else {
                request.setAttribute(AttributeName.IS_EXISTS, AttributeName.NO);
                page = JspPageName.SIGN_IN_PAGE;
                request.getRequestDispatcher(page).forward(request, response);
            }

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.ERROR_MAP, exc.getErrors());
            page = JspPageName.SIGN_IN_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}