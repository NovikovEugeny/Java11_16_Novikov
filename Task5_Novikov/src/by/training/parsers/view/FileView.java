package by.training.parsers.view;

import by.training.parsers.bean.*;

/**
 * Created by Евгений on 29.01.2017.
 */
public class FileView {
    private BeanSet beanSet;

    public void setParsedFile(BeanSet beanSet) {
        this.beanSet = beanSet;
    }

    private void showDisplayName() {
        for (DisplayName d : beanSet.getDisplayNameList()) {
            System.out.println("display-name {" + d.getName() + "}");
        }
        System.out.println();
    }

    private void showWelcomeFileLists() {
        for (WelcomeFileList wfl : beanSet.getWelcomeFileLists()) {
            int n = wfl.getList().size();
            System.out.println("welcome-file-list {");
            for (int i = 0; i < n; i++) {
                System.out.println("   welcome-file -> " +
                        wfl.getList().get(i).getName());
            }
            System.out.println("}");
            System.out.println();
        }
    }

    private void showFilters() {
        for (Filter f : beanSet.getFilters()) {
            System.out.println("filter {");
            System.out.println("   filter-name -> " + f.getFilterName());
            System.out.println("   filter-class -> " + f.getFilterClass());
            System.out.println("   init-param {");
            System.out.println("      param-name -> " +
                    f.getInitParam().getParamName());
            System.out.println("      param-value -> " +
                    f.getInitParam().getParamValue().trim());
            System.out.println("   }");
            System.out.println("}");
            System.out.println();
        }
    }

    private void showFilterMappings() {
        for (FilterMapping fm : beanSet.getFilterMappings()) {
            System.out.println("filter-mapping {");
            System.out.println("   filter-name -> " + fm.getFilterName());
            System.out.println("   url-pattern -> " + fm.getUrlPattern());
            System.out.println("   dispatcher -> " + fm.getDispatcher());
            System.out.println("}");
            System.out.println();
        }
    }

    private void showListeners() {
        for (Listener l : beanSet.getListeners()) {
            System.out.println("listener {");
            System.out.println("   listener-class -> " +
                    l.getListenerClass().trim());
            System.out.println("}");
            System.out.println();
        }
    }

    private void showServlets() {
        for (Servlet s : beanSet.getServlets()) {
            System.out.println("servlet {");
            System.out.println("   servlet-name -> " +
                    s.getServletName());
            System.out.println("   servlet-class -> " +
                    s.getServletClass());
            System.out.println("}");
            System.out.println();
        }
    }

    private void showServletMappings() {
        for (ServletMapping sm : beanSet.getServletMappings()) {
            System.out.println("servlet-mapping {");
            System.out.println("   servlet-name -> " +
                    sm.getServletName());
            System.out.println("   url-pattern -> " +
                    sm.getUrlPattern());
            System.out.println("}");
            System.out.println();
        }
    }

    private void showErrorPages() {
        for (ErrorPage ep : beanSet.getErrorPages()) {
            System.out.println("error-page {");
            if (ep.getExceptionType() != null) {
                System.out.println("   exception-type -> " +
                        ep.getExceptionType().trim());
            }
            if (ep.getErrorCode() != null) {
                System.out.println("   error-code -> " +
                        ep.getErrorCode());
            }
            System.out.println("   location -> " +
                    ep.getLocation());
            System.out.println("}");
            System.out.println();
        }
    }

    public void displayParsedFile() {
        showDisplayName();
        showWelcomeFileLists();
        showFilters();
        showFilterMappings();
        showListeners();
        showServlets();
        showServletMappings();
        showErrorPages();
    }
}
