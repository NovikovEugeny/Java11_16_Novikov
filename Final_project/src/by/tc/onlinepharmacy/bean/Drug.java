package by.tc.onlinepharmacy.bean;

import java.io.Serializable;

/**
 * Class describing the transfer object for storing information about a pharmaceutical product.
 */
public class Drug implements Serializable {

    private static final long serialVersionUID = -7014785221162478818L;

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

    public Drug() {
    }

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

        Drug drug = (Drug) obj;
        if (id != drug.id) {
            return false;
        }
        if (null == name) {
            return (name == drug.name);
        }
        if (!name.equals(drug.name)) {
            return false;
        }
        if (null == group) {
            return (group == drug.group);
        }
        if (!group.equals(drug.group)) {
            return false;
        }
        if (null == form) {
            return (form == drug.form);
        }
        if (!form.equals(drug.form)) {
            return false;
        }
        if (null == drugAmount) {
            return (drugAmount == drug.drugAmount);
        }
        if (!drugAmount.equals(drug.drugAmount)) {
            return false;
        }
        if (null == activeSubstances) {
            return (activeSubstances == drug.activeSubstances);
        }
        if (!activeSubstances.equals(drug.activeSubstances)) {
            return false;
        }
        if (null == country) {
            return (country == drug.country);
        }
        if (!country.equals(drug.country)) {
            return false;
        }
        if (null == dispensing) {
            return (dispensing == drug.dispensing);
        }
        if (!dispensing.equals(drug.dispensing)) {
            return false;
        }
        if (price != drug.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 31;
        hash = hash * 11 * (0 == id ? 11 : id);
        hash = hash * 11 + (null == name ? 0 : name.hashCode());
        hash = hash * 11 + (null == group ? 0 : group.hashCode());
        hash = hash * 11 + (null == form ? 0 : form.hashCode());
        hash = hash * 11 + (null == drugAmount ? 0 : drugAmount.hashCode());
        hash = hash * 11 + (null == activeSubstances ? 0 : activeSubstances.hashCode());
        hash = hash * 11 + (null == country ? 0 : country.hashCode());
        hash = hash * 11 + (null == dispensing ? 0 : dispensing.hashCode());
        hash = (int) (hash * 11 + price);
        hash = hash * 11 * (0 == quantity ? 11 : quantity);
        return hash;
    }

    @Override
    public String toString() {
        return "Drug[id=" + id + ", name=" + name + ", group=" + group + ", form=" + form + ", drugAmount=" +
                drugAmount + ", activeSubstances=" + activeSubstances + ", country=" + country + ", dispensing=" +
                dispensing + ", price=" + price + ", quantity=" + quantity + "]";
    }

}