package by.training.rental_shop.bean;

/**
 * Created by Евгений on 07.01.2017.
 */
public class SportEquipment {
    private String category;
    private String title;
    private double price;

    public SportEquipment() {}

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
