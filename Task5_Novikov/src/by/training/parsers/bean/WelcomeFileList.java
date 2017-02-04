package by.training.parsers.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Евгений on 28.01.2017.
 */
public class WelcomeFileList implements Serializable {
    private List<WelcomeFile> list = new ArrayList<>();

    public WelcomeFileList() {}

    public void addElement(WelcomeFile welcomeFile) {
        list.add(welcomeFile);
    }

    public List<WelcomeFile> getList() {
        return list;
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

        WelcomeFileList welcomeFileList = (WelcomeFileList) obj;
        if (list.size() != welcomeFileList.list.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(welcomeFileList.getList().get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 37 + list.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + list.toString();
    }
}
