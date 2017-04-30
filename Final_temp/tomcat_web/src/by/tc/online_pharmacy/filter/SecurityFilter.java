package by.tc.online_pharmacy.filter;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.JspPageName;
import by.tc.online_pharmacy.resource.ParameterName;
import by.tc.online_pharmacy.security.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(true);
        User user = (User) session.getAttribute(AttributeName.USER);

        String command = httpRequest.getParameter(ParameterName.COMMAND);
        String page = null;

        if (command != null && !command.isEmpty()) {

            if (user != null) {

                if (user.getPosition().equals(ParameterName.CLIENT)) {
                    if (!Security.isAllowedToClient(command)) {
                        page = JspPageName.FORBIDDEN_PAGE;
                    }
                }
                if (user.getPosition().equals(ParameterName.DOCTOR)) {
                    if (!Security.isAllowedToDoctor(command)) {
                        page = JspPageName.FORBIDDEN_PAGE;
                    }
                }
                if (user.getPosition().equals(ParameterName.PHARMACIST)) {
                    if (!Security.isAllowedToPharmacist(command)) {
                        page = JspPageName.FORBIDDEN_PAGE;
                    }
                }

            } else if (!Security.isAllowedToGuest(command)) {
                page = JspPageName.FORBIDDEN_PAGE;
            }

            if (page != null) {
                httpRequest.getRequestDispatcher(JspPageName.FORBIDDEN_PAGE).forward(servletRequest, servletResponse);

            } else {
                filterChain.doFilter(servletRequest, servletResponse);

            }

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}