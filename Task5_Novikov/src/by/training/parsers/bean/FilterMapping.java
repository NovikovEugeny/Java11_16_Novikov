package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class FilterMapping implements Serializable {
    private String filterName;
    private String urlPattern;
    private String dispatcher;

    public FilterMapping() {}

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public String getDispatcher() {
        return dispatcher;
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

        FilterMapping fm = (FilterMapping) obj;
        if (null == filterName) {
            return (filterName == fm.filterName);
        }
        if (null == urlPattern) {
            return (urlPattern == fm.urlPattern);
        }
        if (null == dispatcher) {
            return (dispatcher == fm.dispatcher);
        }
        if (!filterName.equals(fm.filterName)) {
            return false;
        }
        if (!urlPattern.equals(fm.urlPattern)) {
            return false;
        }
        if (!dispatcher.equals(fm.dispatcher)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 47 + (null == filterName ? 0 : filterName.hashCode()) +
                (null == urlPattern ? 0 : urlPattern.hashCode()) +
                (null == dispatcher ? 0 : dispatcher.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "filterName:" + filterName +
                " urlPattern:" + urlPattern + " dispatcher:" + dispatcher;
    }
}
