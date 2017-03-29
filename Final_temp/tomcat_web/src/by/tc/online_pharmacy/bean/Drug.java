package by.tc.online_pharmacy.bean;

import java.io.Serializable;

/**
 * Created by Евгений on 17.02.2017.
 */
public class Drug implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String group;
    private String form;
    private String drugAmount;
    private String activeSubstances;
    private String country;
    private String dispensing;
    private double price;
    private int quantity;

    public Drug() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setDrugAmount(String drugAmount) {
        this.drugAmount = drugAmount;
    }

    public void setActiveSubstances(String activeSubstances) {
        this.activeSubstances = activeSubstances;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDispensing(String dispensing) {
        this.dispensing = dispensing;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getForm() {
        return form;
    }

    public String getDrugAmount() {
        return drugAmount;
    }

    public String getActiveSubstances() {
        return activeSubstances;
    }

    public String getCountry() {
        return country;
    }

    public String getDispensing() {
        return dispensing;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
