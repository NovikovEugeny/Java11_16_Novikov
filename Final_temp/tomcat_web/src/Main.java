import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private final static String viewRegExp = "^/(js|css|images).*$";
    private final static String servletRegExp = "^/controller.*$";

    private static boolean test(String regExp, String str) {
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) {
/*
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(true);
        User user = (User) session.getAttribute(AttributeName.USER);

        String command = httpRequest.getParameter(ParameterName.COMMAND);
        String URI = httpRequest.getRequestURI();
        String page = null;

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
            } else if (user != null) {

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
                httpRequest.getRequestDispatcher(page).forward(servletRequest, servletResponse);
            }

        }


        System.out.println("security");
        System.out.println(httpRequest.getRequestURL());
        System.out.println(httpRequest.getRequestURI());
        System.out.println(httpRequest.getQueryString());
        filterChain.doFilter(servletRequest, servletResponse);
*/
        String page = null;


/*

        User user = new User();
        user.setPosition("client");

        String command = "groups_to_update_page";


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
                System.out.println("forbidden page");
            } else {
                System.out.println("chain1");
            }

        } else {
            System.out.println("chain2");
        }
*/

    }

}
