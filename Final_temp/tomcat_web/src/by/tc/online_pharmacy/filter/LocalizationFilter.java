package by.tc.online_pharmacy.filter;


import by.tc.online_pharmacy.resource.AttributeName;
import by.tc.online_pharmacy.resource.ParameterName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LocalizationFilter implements Filter {

    private String local;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        local = filterConfig.getInitParameter(ParameterName.DEFAULT_LOCALE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(true);

        if (session.getAttribute(AttributeName.LOCAL) == null) {
            session.setAttribute(AttributeName.LOCAL, local);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}