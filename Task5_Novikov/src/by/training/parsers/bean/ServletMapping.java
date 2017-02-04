package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class ServletMapping implements Serializable {
    private String servletName;
    private String urlPattern;

    public ServletMapping() {}

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getServletName() {
        return servletName;
    }

    public String getUrlPattern() {
        return urlPattern;
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

        ServletMapping sm = (ServletMapping) obj;
        if (null == servletName) {
            return (servletName == sm.servletName);
        }
        if (null == urlPattern) {
            return (urlPattern == sm.urlPattern);
        }
        if (!servletName.equals(sm.servletName)) {
            return false;
        }
        if (!urlPattern.equals(sm.urlPattern)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 67 + (null == servletName ? 0 : servletName.hashCode()) +
                (null == urlPattern ? 0 : urlPattern.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "servletName:" + servletName +
                " urlPattern:" + urlPattern;
    }

}
