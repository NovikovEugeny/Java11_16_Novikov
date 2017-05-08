package by.tc.onlinepharmacy.filter;

import by.tc.onlinepharmacy.resource.CommandName;
import by.tc.onlinepharmacy.resource.JspPageName;
import by.tc.onlinepharmacy.resource.ParameterName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This filter checks the request and if such command does not exist,
 * then forwarding to the page with error 404.
 *
 * @see javax.servlet.Filter
 */
public class WrongRequestFilter implements Filter {

    private final static String viewRegExp = "^/(js|css|images).+$";
    private final static String servletRegExp = "^/controller.*$";

    private boolean test(String regExp, String str) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String command = httpRequest.getParameter(ParameterName.COMMAND);
        String URI = httpRequest.getRequestURI();

        if (test(viewRegExp, URI)) {
            filterChain.doFilter(servletRequest, servletResponse);

        } else if (!test(servletRegExp, URI)) {
            httpRequest.getRequestDispatcher(JspPageName.START_PAGE).forward(servletRequest, servletResponse);

        } else if (command != null && !command.isEmpty()) {

            boolean isExists = false;

            for (CommandName c : CommandName.values()) {
                if (command.equals(c.toString().toLowerCase())) {
                    isExists = true;
                    break;
                }
            }

            if (!isExists) {
                httpRequest.getRequestDispatcher(JspPageName.WRONG_REQUEST_PAGE).forward(servletRequest, servletResponse);

            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }

        } else {
            httpRequest.getRequestDispatcher(JspPageName.START_PAGE).forward(servletRequest, servletResponse);
        }
    }
}