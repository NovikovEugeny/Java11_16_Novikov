package by.training.parsers.parser.sax;

import by.training.parsers.bean.*;
import by.training.parsers.node_type_enum.Type;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Евгений on 28.01.2017.
 */
public class SaxHandler extends DefaultHandler {
    private StringBuilder text;

    private boolean isFilter;
    private boolean isFilterMapping;
    private boolean isServlet;

    private BeanSet beanSet;
    private DisplayName displayName;
    private WelcomeFileList welcomeFileList;
    private WelcomeFile welcomeFile;
    private Filter filter;
    private InitParam initParam;
    private FilterMapping filterMapping;
    private Listener listener;
    private Servlet servlet;
    private ServletMapping servletMapping;
    private ErrorPage errorPage;


    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {

        Type tagName = Type.valueOf(qName.toUpperCase().replace("-", "_"));
        text = new StringBuilder();
        switch (tagName) {
            case WEB_APP:
                beanSet = new BeanSet();
                break;
            case DISPLAY_NAME:
                displayName = new DisplayName();
                break;
            case WELCOME_FILE_LIST:
                welcomeFileList = new WelcomeFileList();
                break;
            case WELCOME_FILE:
                welcomeFile = new WelcomeFile();
                break;
            case FILTER:
                isFilter = true;
                filter = new Filter();
                break;
            case INIT_PARAM:
                initParam = new InitParam();
                break;
            case FILTER_MAPPING:
                isFilterMapping = true;
                filterMapping = new FilterMapping();
                break;
            case LISTENER:
                listener = new Listener();
                break;
            case SERVLET:
                isServlet = true;
                servlet = new Servlet();
                break;
            case SERVLET_MAPPING:
                servletMapping = new ServletMapping();
                break;
            case ERROR_PAGE:
                errorPage = new ErrorPage();
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        Type tagName = Type.valueOf(qName.toUpperCase().replace("-", "_"));

        switch (tagName) {
            case DISPLAY_NAME:
                displayName.setName(text.toString());
                beanSet.addDisplayName(displayName);
                break;
            case WELCOME_FILE:
                welcomeFile.setName(text.toString());
                welcomeFileList.addElement(welcomeFile);
                break;
            case WELCOME_FILE_LIST:
                beanSet.addWelcomeFileList(welcomeFileList);
                break;
            case FILTER_NAME:
                if (isFilter) {
                    filter.setFilterName(text.toString());
                } else {
                    filterMapping.setFilterName(text.toString());
                }
                break;
            case FILTER_CLASS:
                filter.setFilterClass(text.toString());
                break;
            case PARAM_NAME:
                initParam.setParamName(text.toString());
                break;
            case PARAM_VALUE:
                initParam.setParamValue(text.toString());
                break;
            case INIT_PARAM:
                filter.setInitParam(initParam);
                break;
            case FILTER:
                beanSet.addFilter(filter);
                isFilter = false;
                break;
            case URL_PATTERN:
                if (isFilterMapping) {
                    filterMapping.setUrlPattern(text.toString());
                } else {
                    servletMapping.setUrlPattern(text.toString());
                }
                break;
            case DISPATCHER:
                filterMapping.setDispatcher(text.toString());
                break;
            case FILTER_MAPPING:
                beanSet.addFilterMapping(filterMapping);
                isFilterMapping = false;
                break;
            case LISTENER_CLASS:
                listener.setListenerClass(text.toString());
                break;
            case LISTENER:
                beanSet.addListener(listener);
                break;
            case SERVLET_NAME:
                if (isServlet) {
                    servlet.setServletName(text.toString());
                } else {
                    servletMapping.setServletName(text.toString());
                }
                break;
            case SERVLET_CLASS:
                servlet.setServletClass(text.toString());
                break;
            case SERVLET:
                beanSet.addServlet(servlet);
                isServlet = false;
                break;
            case SERVLET_MAPPING:
                beanSet.addServletMapping(servletMapping);
                break;
            case EXCEPTION_TYPE:
                errorPage.setExceptionType(text.toString());
                break;
            case ERROR_CODE:
                errorPage.setErrorCode(text.toString());
                break;
            case LOCATION:
                errorPage.setLocation(text.toString());
                break;
            case ERROR_PAGE:
                beanSet.addErrorPage(errorPage);
                break;
        }
    }

    public BeanSet getBeanSet() {
        return beanSet;
    }
}
