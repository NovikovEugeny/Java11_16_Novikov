package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class ErrorPage implements Serializable {
    private String exceptionType;
    private String errorCode;
    private String location;

    public ErrorPage() {}

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getLocation() {
        return location;
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

        ErrorPage ep = (ErrorPage) obj;
        if (null == exceptionType) {
            return (exceptionType == ep.exceptionType);
        }
        if (null == errorCode) {
            return (errorCode == ep.errorCode);
        }
        if (null == location) {
            return (location == ep.location);
        }
        if (!exceptionType.equals(ep.exceptionType)) {
            return false;
        }
        if (!errorCode.equals(ep.errorCode)) {
            return false;
        }
        if (!location.equals(ep.location)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 71 + (null == exceptionType ? 0 : exceptionType.hashCode()) +
                (null == errorCode ? 0 : errorCode.hashCode()) +
                (null == location ? 0 : location.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "exceptionType:" + exceptionType +
                " errorCode:" + errorCode + " location:" + location;
    }
}
