package by.tc.online_pharmacy.bean;

import java.io.Serializable;

/**
 * Class describing the transfer object for storing user information.
 */

public class User implements Serializable {

    private static final long serialVersionUID = -1847441506455808683L;

    private int id;
    private String position;
    private String surname;
    private String name;
    private String patronymic;
    private String mobilePhone;
    private String password;
    private String confirmPassword;

    public User() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
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

        User user = (User) obj;
        if (id != user.id) {
            return false;
        }
        if (null == position) {
            return (position == user.position);
        }
        if (!position.equals(user.position)) {
            return false;
        }
        if (null == surname) {
            return (surname == user.surname);
        }
        if (!surname.equals(user.surname)) {
            return false;
        }
        if (null == name) {
            return (name == user.name);
        }
        if (!name.equals(user.name)) {
            return false;
        }
        if (null == patronymic) {
            return (patronymic == user.patronymic);
        }
        if (!patronymic.equals(user.patronymic)) {
            return false;
        }
        if (null == mobilePhone) {
            return (mobilePhone == user.mobilePhone);
        }
        if (!mobilePhone.equals(user.mobilePhone)) {
            return false;
        }
        if (null == password) {
            return (password == user.password);
        }
        if (!password.equals(user.password)) {
            return false;
        }
        if (null == confirmPassword) {
            return (confirmPassword == user.confirmPassword);
        }
        if (!confirmPassword.equals(user.confirmPassword)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 59;
        hash = hash * 31 + 31 * id;
        hash = hash * 31 + (null == position ? 0 : position.hashCode());
        hash = hash * 31 + (null == surname ? 0 : surname.hashCode());
        hash = hash * 31 + (null == name ? 0 : name.hashCode());
        hash = hash * 31 + (null == patronymic ? 0 : patronymic.hashCode());
        hash = hash * 31 + (null == mobilePhone ? 0 : mobilePhone.hashCode());
        hash = hash * 31 + (null == password ? 0 : password.hashCode());
        hash = hash * 31 + (null == confirmPassword ? 0 : confirmPassword.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", position=" + position + ", surname=" + surname + ", name=" + name +
                ", patronymic=" + patronymic + ", mobilePhone=" + mobilePhone + ", password=" + password +
                ", confirmPassowrd=" + confirmPassword + "]";
    }

}


