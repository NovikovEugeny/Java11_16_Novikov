package by.training.parsers.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 29.01.2017.
 */
public class BeanSet implements Serializable {
    private List<DisplayName> displayNameList = new ArrayList<>();
    private List<WelcomeFileList> welcomeFileLists = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();
    private List<FilterMapping> filterMappings = new ArrayList<>();
    private List<Listener> listeners = new ArrayList<>();
    private List<Servlet> servlets = new ArrayList<>();
    private List<ServletMapping> servletMappings = new ArrayList<>();
    private List<ErrorPage> errorPages = new ArrayList<>();

    public BeanSet() {}

    public void addDisplayName(DisplayName d) {
        displayNameList.add(d);
    }

    public void addWelcomeFileList(WelcomeFileList w) {
        welcomeFileLists.add(w);
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public void addFilterMapping(FilterMapping fm) {
        filterMappings.add(fm);
    }

    public void addListener(Listener l) {
        listeners.add(l);
    }

    public void addServlet(Servlet s) {
        servlets.add(s);
    }

    public void addServletMapping(ServletMapping sm) {
        servletMappings.add(sm);
    }

    public void addErrorPage(ErrorPage e) {
        errorPages.add(e);
    }

    public List<DisplayName> getDisplayNameList() {
        return displayNameList;
    }

    public List<WelcomeFileList> getWelcomeFileLists() {
        return welcomeFileLists;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public List<FilterMapping> getFilterMappings() {
        return filterMappings;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public List<Servlet> getServlets() {
        return servlets;
    }

    public List<ServletMapping> getServletMappings() {
        return servletMappings;
    }

    public List<ErrorPage> getErrorPages() {
        return errorPages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        BeanSet beanSet = (BeanSet) obj;
        if (displayNameList.size() != beanSet.displayNameList.size()) {
            return false;
        }
        for (int i = 0; i < displayNameList.size(); i++) {
            if (!displayNameList.get(i).equals(beanSet.displayNameList.get(i))) {
                return false;
            }
        }

        if (welcomeFileLists.size() != beanSet.welcomeFileLists.size()) {
            return false;
        }
        for (int i = 0; i < welcomeFileLists.size(); i++) {
            if (!welcomeFileLists.get(i).equals(beanSet.welcomeFileLists.get(i))) {
                return false;
            }
        }

        if (filters.size() != beanSet.filters.size()) {
            return false;
        }
        for (int i = 0; i < filters.size(); i++) {
            if (!filters.get(i).equals(beanSet.filters.get(i))) {
                return false;
            }
        }

        if (filterMappings.size() != beanSet.filterMappings.size()) {
            return false;
        }
        for (int i = 0; i < filterMappings.size(); i++) {
            if (!filterMappings.get(i).equals(beanSet.filterMappings.get(i))) {
                return false;
            }
        }

        if (listeners.size() != beanSet.listeners.size()) {
            return false;
        }
        for (int i = 0; i < listeners.size(); i++) {
            if (!listeners.get(i).equals(beanSet.listeners.get(i))) {
                return false;
            }
        }

        if (servlets.size() != beanSet.servlets.size()) {
            return false;
        }
        for (int i = 0; i < servlets.size(); i++) {
            if (!servlets.get(i).equals(beanSet.servlets.get(i))) {
                return false;
            }
        }

        if (servletMappings.size() != beanSet.servletMappings.size()) {
            return false;
        }
        for (int i = 0; i < servletMappings.size(); i++) {
            if (!servletMappings.get(i).equals(beanSet.servletMappings.get(i))) {
                return false;
            }
        }

        if (errorPages.size() != beanSet.errorPages.size()) {
            return false;
        }
        for (int i = 0; i < errorPages.size(); i++) {
            if (!errorPages.get(i).equals(beanSet.errorPages.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 73 + displayNameList.hashCode() + welcomeFileLists.hashCode() +
                filters.hashCode() + filterMappings.hashCode() +
                listeners.hashCode() + servlets.hashCode() +
                servletMappings.hashCode() + errorPages.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + displayNameList.toString() +
                " " + welcomeFileLists.toString() + " " + filters.toString() +
                filterMappings.toString() + " " + listeners.toString() + " " +
                servlets.toString() + " " + servletMappings.toString() + " " +
                errorPages.toString();
    }
}