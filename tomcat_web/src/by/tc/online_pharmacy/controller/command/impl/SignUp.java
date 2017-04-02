package by.tc.online_pharmacy.controller.command.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.UserService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Евгений on 17.02.2017.
 */
public class SignUp implements Command {

    private final static String CLIENT = "client";
    private final static String SURNAME = "surname";
    private final static String NAME = "name";
    private final static String PATRONYMIC = "patronymic";
    private final static String MOBILE = "mobile";
    private final static String PASSWORD = "password";
    private final static String CONFIRM = "confirm";
    private final static String USER = "user";
    private final static String ERROR_MESSAGE = "errorMessage";

    @Override
    public String execute(HttpServletRequest request) {

        String response = null;

        User user = new User();
        user.setPosition(CLIENT);
        user.setSurname(request.getParameter(SURNAME));
        user.setName(request.getParameter(NAME));
        user.setPatronymic(request.getParameter(PATRONYMIC));
        user.setMobilePhone(request.getParameter(MOBILE));
        user.setPassword(request.getParameter(PASSWORD));
        user.setConfirmPassword(request.getParameter(CONFIRM));

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();

            int id = userService.signUp(user);
            user.setId(id);
            request.getSession().setAttribute(USER, user);

            response = JspPageName.CLIENT_START_PAGE;
        } catch (ServiceException exc) {
            //logger
        } catch (ValidatorException exc) {
            response = JspPageName.SIGN_UP_PAGE;
            request.setAttribute(ERROR_MESSAGE, exc.getMessage());
        }
        return response;
    }
}
