package by.tc.online_pharmacy.controller.command.impl.pharmacist;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.controller.command.Command;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Class describes the object-command, the execution of which displays
 * sales report sorted by date.
 */

public class ShowSalesReport implements Command {

    private static final Logger logger = LogManager.getLogger(ShowSalesReport.class.getName());

    /**
     * If the command is successful, forwarding to the page
     * where sales report is displayed.
     * <p>
     * If an error occurred during the command execution,
     * then the control is passed to the catch block
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

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            PharmacistService pharmacistService = serviceFactory.getPharmacistService();

            Map<Date, List<Drug>> salesReport = pharmacistService.showSalesReport();
            request.setAttribute(AttributeName.SALES_REPORT, salesReport);

            page = JspPageName.PHARMACIST_SALES_REPORT;

        } catch (ServiceException exc) {
            logger.log(Level.ERROR, exc);
            page = JspPageName.SERVER_ERROR_PAGE;
        }
        request.getRequestDispatcher(page).forward(request, response);
    }
}