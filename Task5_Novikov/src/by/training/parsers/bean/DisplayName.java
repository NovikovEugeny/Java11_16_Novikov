package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class DisplayName implements Serializable {
    private String name;

    public DisplayName(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        DisplayName displayName = (DisplayName) obj;
        if (null == name) {
            return (name == displayName.name);
        }
        if (!name.equals(displayName.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 + (null == name ? 0 : name.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "name:" + name;
    }
}
