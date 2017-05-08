package by.tc.onlinepharmacy.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Sets the request encoding in utf-8.
 *
 * @see javax.servlet.Filter
 */
public class EncodingFilter implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String codeRequest = servletRequest.getCharacterEncoding();

        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletRequest.setCharacterEncoding(code);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}