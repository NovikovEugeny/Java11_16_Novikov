package by.training.parsers.parser.stax;

import by.training.parsers.bean.*;
import by.training.parsers.parser.Parser;
import by.training.parsers.parser.exception.ParserException;
import by.training.parsers.node_type_enum.Type;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Stax implements Parser {

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
    public void startParsing(String filePath) throws ParserException {

        filePath = filePath.trim();
        if (!filePath.substring(filePath.length()-3,
                filePath.length()).equals("xml")) {
            throw new ParserException("wrong file type");
        }

        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(filePath);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

            Type tag = null;

            while (reader.hasNext()) {
                int type = reader.next();

                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        tag = Type.valueOf(reader.getLocalName().toUpperCase().
                                replace("-", "_"));

                        switch (tag) {
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
                        break;


                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText().trim();

                        if (text.isEmpty()) {
                            break;
                        }

                        switch (tag) {
                            case DISPLAY_NAME:
                                displayName.setName(text.toString());
                                break;
                            case WELCOME_FILE:
                                welcomeFile.setName(text.toString());
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
                            case LISTENER_CLASS:
                                listener.setListenerClass(text.toString());
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
                            case EXCEPTION_TYPE:
                                errorPage.setExceptionType(text.toString());
                                break;
                            case ERROR_CODE:
                                errorPage.setErrorCode(text.toString());
                                break;
                            case LOCATION:
                                errorPage.setLocation(text.toString());
                                break;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        tag = Type.valueOf(reader.getLocalName().toUpperCase().
                                replace("-", "_"));

                        switch (tag) {
                            case DISPLAY_NAME:
                                beanSet.addDisplayName(displayName);
                                break;
                            case WELCOME_FILE_LIST:
                                beanSet.addWelcomeFileList(welcomeFileList);
                                break;
                            case WELCOME_FILE:
                                welcomeFileList.addElement(welcomeFile);
                                break;
                            case FILTER:
                                beanSet.addFilter(filter);
                                isFilter = false;
                                break;
                            case INIT_PARAM:
                                filter.setInitParam(initParam);
                                break;
                            case FILTER_MAPPING:
                                beanSet.addFilterMapping(filterMapping);
                                isFilterMapping = false;
                                break;
                            case LISTENER:
                                beanSet.addListener(listener);
                                break;
                            case SERVLET:
                                beanSet.addServlet(servlet);
                                isServlet = false;
                                break;
                            case SERVLET_MAPPING:
                                beanSet.addServletMapping(servletMapping);
                                break;
                            case ERROR_PAGE:
                                beanSet.addErrorPage(errorPage);
                                break;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException exc) {
            throw new ParserException(exc);
        } catch (XMLStreamException exc) {
            throw new ParserException(exc);
        }

    }

    @Override
    public BeanSet takeBeanSet() {
        return beanSet;
    }

}