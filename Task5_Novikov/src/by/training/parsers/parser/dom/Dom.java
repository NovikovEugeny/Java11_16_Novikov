package by.training.parsers.parser.dom;

import by.training.parsers.bean.*;
import by.training.parsers.parser.Parser;
import by.training.parsers.parser.exception.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Dom implements Parser {

    private BeanSet beanSet = new BeanSet();

    @Override
    public void startParsing(String filePath) throws ParserException {

        filePath = filePath.trim();
        if (!filePath.substring(filePath.length()-3,
                filePath.length()).equals("xml")) {
            throw new ParserException("wrong file type");
        }

        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList displayNames = document.getElementsByTagName("display-name");
            displayNameInit(displayNames);

            NodeList welcomeFileLists =
                    document.getElementsByTagName("welcome-file-list");
            welcomeFileListInit(welcomeFileLists);

            NodeList filters = document.getElementsByTagName("filter");
            filterInit(filters);

            NodeList filterMappings =
                    document.getElementsByTagName("filter-mapping");
            filterMappingInit(filterMappings);

            NodeList listeners = document.getElementsByTagName("listener");
            listenerInit(listeners);

            NodeList servlets = document.getElementsByTagName("servlet");
            servletInit(servlets);

            NodeList servletMappings =
                    document.getElementsByTagName("servlet-mapping");
            servletMappingInit(servletMappings);

            NodeList errorPages = document.getElementsByTagName("error-page");
            errorPageInit(errorPages);
        } catch (ParserConfigurationException exc) {
            throw new ParserException(exc);
        } catch (SAXException exc) {
            throw new ParserException(exc);
        } catch (IOException exc) {
            throw new ParserException(exc);
        }
    }

    @Override
    public BeanSet takeBeanSet() {
        return beanSet;
    }

    private void displayNameInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            DisplayName displayName = new DisplayName();
            String name = nodeList.item(i).getTextContent();
            displayName.setName(name);
            beanSet.addDisplayName(displayName);
        }
    }

    private void welcomeFileListInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            WelcomeFileList welcomeFileList = new WelcomeFileList();
            int length = nodeList.item(i).getChildNodes().getLength();
            for (int j = 0; j < length; j++) {

                if (nodeList.item(i).getChildNodes().item(j).
                        getNodeName().toString().equals("welcome-file")) {

                    WelcomeFile welcomeFile = new WelcomeFile();

                    String name = nodeList.item(i).getChildNodes().
                            item(j).getTextContent();

                    welcomeFile.setName(name);
                    welcomeFileList.addElement(welcomeFile);
                }
            }
            beanSet.addWelcomeFileList(welcomeFileList);
        }
    }

    private void filterInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String filterName =
                    nodeList.item(i).getChildNodes().item(1).getTextContent();
            String filterClass =
                    nodeList.item(i).getChildNodes().item(3).getTextContent();
            Filter filter = new Filter();
            filter.setFilterName(filterName);
            filter.setFilterClass(filterClass);

            InitParam initParam = new InitParam();
            String paramName = nodeList.item(i).getChildNodes().
                    item(5).getChildNodes().item(1).getTextContent();
            String paramValue = nodeList.item(i).getChildNodes().
                    item(5).getChildNodes().item(3).getTextContent();
            initParam.setParamName(paramName);
            initParam.setParamValue(paramValue);
            filter.setInitParam(initParam);
            beanSet.addFilter(filter);
        }
    }

    private void filterMappingInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String filterName = nodeList.item(i).getChildNodes().
                    item(1).getTextContent();
            String urlPattern = nodeList.item(i).getChildNodes().
                    item(3).getTextContent();
            String dispatcher = nodeList.item(i).getChildNodes().
                    item(5).getTextContent();

            FilterMapping filterMapping = new FilterMapping();
            filterMapping.setFilterName(filterName);
            filterMapping.setUrlPattern(urlPattern);
            filterMapping.setDispatcher(dispatcher);
            beanSet.addFilterMapping(filterMapping);
        }
    }

    private void listenerInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String listenerClass = nodeList.item(i).getChildNodes().
                    item(1).getTextContent();
            Listener listener = new Listener();
            listener.setListenerClass(listenerClass);
            beanSet.addListener(listener);
        }
    }

    private void servletInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String servletName = nodeList.item(i).getChildNodes().
                    item(1).getTextContent();
            String servletClass = nodeList.item(i).getChildNodes().
                    item(3).getTextContent();

            Servlet servlet = new Servlet();
            servlet.setServletName(servletName);
            servlet.setServletClass(servletClass);
            beanSet.addServlet(servlet);
        }
    }

    private void servletMappingInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String servletName = nodeList.item(i).getChildNodes().
                    item(1).getTextContent();
            String urlPattern = nodeList.item(i).getChildNodes().
                    item(3).getTextContent();

            ServletMapping servletMapping = new ServletMapping();
            servletMapping.setServletName(servletName);
            servletMapping.setUrlPattern(urlPattern);
            beanSet.addServletMapping(servletMapping);
        }
    }

    private void errorPageInit(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            String exceptionType = null;
            String errorCode = null;
            String location = nodeList.item(i).getChildNodes().
                    item(3).getTextContent();
            String tmp = nodeList.item(i).getChildNodes().item(1).getNodeName();
            String text = nodeList.item(i).getChildNodes().
                    item(1).getTextContent();
            if (tmp.equals("exception-type")) {
                exceptionType = text;
            } else {
                errorCode = text;
            }

            ErrorPage errorPage = new ErrorPage();
            errorPage.setExceptionType(exceptionType);
            errorPage.setErrorCode(errorCode);
            errorPage.setLocation(location);
            beanSet.addErrorPage(errorPage);
        }
    }

}



