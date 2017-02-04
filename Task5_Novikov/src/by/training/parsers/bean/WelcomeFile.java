package by.training.parsers.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 28.01.2017.
 */
public class WelcomeFile implements Serializable {
    private String name;

    public WelcomeFile() {}

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
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        WelcomeFile welcomeFile = (WelcomeFile) obj;
        if (null == name) {
            return (name == welcomeFile.name);
        }
        if (!name.equals(welcomeFile.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 33 + (null == name ? 0 : name.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + "name:" + name;
    }
}
