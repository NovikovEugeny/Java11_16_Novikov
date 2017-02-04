package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Servlet implements Serializable {
    private String servletName;
    private String servletClass;

    public Servlet() {}

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public String getServletName() {
        return servletName;
    }

    public String getServletClass() {
        return servletClass;
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

        Servlet servlet = (Servlet) obj;
        if (null == servletName) {
            return (servletName == servlet.servletName);
        }
        if (null == servletClass) {
            return (servletClass == servlet.servletClass);
        }
        if (!servletName.equals(servlet.servletName)) {
            return false;
        }
        if (!servletClass.equals(servlet.servletClass)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 59 + (null == servletName ? 0 : servletName.hashCode()) +
                (null == servletClass ? 0 : servletClass.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "servletName:" + servletName +
                " servletClass:" + servletClass;
    }
}