package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Filter implements Serializable {
    private String filterName;
    private String filterClass;
    private InitParam initParam;

    public Filter() {}

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public void setFilterClass(String filterClass) {
        this.filterClass = filterClass;
    }

    public void setInitParam(InitParam initParam) {
        this.initParam = initParam;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getFilterClass() {
        return filterClass;
    }

    public InitParam getInitParam() {
        return initParam;
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

        Filter filter = (Filter) obj;
        if (null == filterName) {
            return (filterName == filter.filterName);
        }
        if (null == filterClass) {
            return (filterClass == filter.filterClass);
        }
        if (null == initParam) {
            return (initParam == filter.initParam);
        }
        if (!filterName.equals(filter.filterName)) {
            return false;
        }
        if (!filterClass.equals(filter.filterClass)) {
            return false;
        }
        if (!initParam.equals(filter.initParam)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 43 + (null == filterName ? 0 : filterName.hashCode()) +
                (null == filterClass ? 0 : filterClass.hashCode()) +
                (null == initParam ? 0 : initParam.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "filterName:" + filterName +
                " filterClass:" + filterClass + "initParam:" +
                initParam.toString();
    }
}
