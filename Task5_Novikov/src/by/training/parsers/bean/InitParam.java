package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class InitParam implements Serializable{
    private String paramName;
    private String paramValue;

    public InitParam() {}

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamValue() {
        return paramValue;
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

        InitParam initParam = (InitParam) obj;
        if (null == paramName) {
            return (paramName == initParam.paramName);
        }
        if (null == paramValue) {
            return (paramValue == initParam.paramValue);
        }
        if (!paramName.equals(initParam.paramName)) {
            return false;
        }
        if (!paramValue.equals(initParam.paramValue)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 41 + (null == paramName ? 0 : paramName.hashCode()) +
                (null == paramValue ? 0 : paramValue.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "paramName:" + paramName +
                " paramValue:" + paramValue;
    }
}
