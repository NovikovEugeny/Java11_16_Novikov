package by.tc.online_pharmacy.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LocalizationFilter implements Filter {

    private final static String LOCAL = "local";
    private final static String PARAM_NAME = "defaultLocale";
    private String local;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        local = filterConfig.getInitParameter(PARAM_NAME);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(true);

        if (session.getAttribute(LOCAL) == null) {
            session.setAttribute(LOCAL, local);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}