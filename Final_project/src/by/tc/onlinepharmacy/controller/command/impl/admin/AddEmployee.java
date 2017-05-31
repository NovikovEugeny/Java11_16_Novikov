package by.tc.onlinepharmacy.controller.command.impl.admin;

import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.controller.command.Command;
import by.tc.onlinepharmacy.controller.command.URLCommand;
import by.tc.onlinepharmacy.resource.AttributeName;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;
import by.tc.onlinepharmacy.service.ClientService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes the object-command, the execution of which
 * adds a new employee.
 */
public class AddEmployee implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddEmployee.class.getName());

    /**
     * If the command is successful, then the page is updated
     * and a new employee record is displayed.
     * <p>
     * The user's parameters, extracted from the request are packed
     * into a transfer object {@link User User} and
     * validates on the service layer. If the data is not correct,
     * then the control passed to the catch block of <tt>ValidatorException</tt>
     * and forwarding to the bad request page.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block of <tt>ServiceException</tt>
     * and forwarding to the server error page.
     *
     * @param request  object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page = null;

        User user = new User();
        user.setPosition(request.getParameter(ParameterName.POSITION));
        user.setSurname(request.getParameter(ParameterName.SURNAME));
        user.setName(request.getParameter(ParameterName.NAME));
        user.setPatronymic(request.getParameter(ParameterName.PATRONYMIC));
        user.setMobilePhone(request.getParameter(ParameterName.MOBILE));
        user.setPassword(request.getParameter(ParameterName.PASSWORD));
        user.setConfirmPassword(request.getParameter(ParameterName.CONFIRM));

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ClientService clientService = serviceFactory.getClientService();

            clientService.signUp(user);

            response.sendRedirect(URLCommand.SHOW_EMPLOYEES);

        } catch (ServiceException exc) {
            LOGGER.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
            request.getRequestDispatcher(page).forward(request, response);

        } catch (ValidatorException exc) {
            page = JspPageName.BAD_REQUEST_PAGE;
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}