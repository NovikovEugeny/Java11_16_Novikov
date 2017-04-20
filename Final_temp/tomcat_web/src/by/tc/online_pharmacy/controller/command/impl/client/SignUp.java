package by.tc.online_pharmacy.controller.command.impl.client;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.controller.JspPageName;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.controller.command.impl.doctor.Approve;
import by.tc.online_pharmacy.controller.util.AttributeName;
import by.tc.online_pharmacy.controller.util.ParameterName;
import by.tc.online_pharmacy.service.ClientService;
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


public class SignUp implements Command {

    private static final Logger logger = LogManager.getLogger(SignUp.class.getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        User user = new User();
        user.setPosition(ParameterName.CLIENT);
        user.setSurname(request.getParameter(ParameterName.SURNAME));
        user.setName(request.getParameter(ParameterName.NAME));
        user.setPatronymic(request.getParameter(ParameterName.PATRONYMIC));
        user.setMobilePhone(request.getParameter(ParameterName.MOBILE));
        user.setPassword(request.getParameter(ParameterName.PASSWORD));
        user.setConfirmPassword(request.getParameter(ParameterName.CONFIRM));

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            int id = clientService.signUp(user);
            user.setId(id);
            request.getSession().setAttribute(AttributeName.USER, user);

            clientService.addAccount(id);

            page = JspPageName.CLIENT_HOME_PAGE;
        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        } catch (ValidatorException exc) {
            request.setAttribute(AttributeName.ALREADY_EXISTS,
                    (exc.getMessage() == null ? AttributeName.NO : AttributeName.YES));
            request.setAttribute(AttributeName.ERROR_MAP, exc.getErrors());
            page = JspPageName.SIGN_UP_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}