package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class Listener implements Serializable {
    private String listenerClass;

    public Listener() {}

    public void setListenerClass(String listenerClass) {
        this.listenerClass = listenerClass;
    }

    public String getListenerClass() {
        return listenerClass;
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

        Listener listener = (Listener) obj;
        if (null == listenerClass) {
            return (listenerClass == listener.listenerClass);
        }
        if (!listenerClass.equals(listener.listenerClass)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 53 + (null == listenerClass ? 0 : listenerClass.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "listenerClass:" + listenerClass;
    }
}
