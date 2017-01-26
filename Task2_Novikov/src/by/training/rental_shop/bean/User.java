package by.training.rental_shop.bean;

/**
 * Created by Евгений on 07.01.2017.
 */
public class User {// что еще надо написать в этих классах, чтобы они стали полными bean-объектами
    private String name;
    private String phone;
    private String password;

    public User() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

}
