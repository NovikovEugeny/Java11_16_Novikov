package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
    private final static String ERROR_MAP = "errorMap";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

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
            ClientService clientService = serviceFactory.getClientService();

            int id = clientService.signUp(user);
            user.setId(id);
            request.getSession().setAttribute(USER, user);

            clientService.addAccount(id);

            page = JspPageName.CLIENT_HOME_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        } catch (ServiceException exc) {
            //logger

        } catch (ValidatorException exc) {
            request.setAttribute(ERROR_MESSAGE, exc.getMessage());
            request.setAttribute(ERROR_MAP, exc.getErrors());
            page = JspPageName.SIGN_UP_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
