package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Class describing the transfer object for storing full information for users about a recipe.
 */

public class RecipeDescription implements Serializable {

    private static final long serialVersionUID = 1077628357181124180L;

    private String recipeCode;
    private int drugId;
    private String drugName;
    private String drugGroup;
    private String drugForm;
    private String drugAmount;
    private String activeSubstances;
    private String country;
    private double price;
    private int quantity;
    private double cost;
    private String status;
    private Date endDate;

    public RecipeDescription() {
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setDrugGroup(String drugGroup) {
        this.drugGroup = drugGroup;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public int getDrugId() {
        return drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugGroup() {
        return drugGroup;
    }

    public String getDrugForm() {
        return drugForm;
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

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public String getStatus() {
        return status;
    }

    public Date getEndDate() {
        return endDate;
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

        RecipeDescription rd = (RecipeDescription) obj;
        if (null == recipeCode) {
            return (recipeCode == rd.recipeCode);
        }
        if (!recipeCode.equals(rd.recipeCode)) {
            return false;
        }
        if (null == drugName) {
            return (drugName == rd.drugName);
        }
        if (!drugName.equals(rd.drugName)) {
            return false;
        }
        if (null == drugGroup) {
            return (drugGroup == rd.drugGroup);
        }
        if (!drugGroup.equals(rd.drugGroup)) {
            return false;
        }
        if (null == drugForm) {
            return (drugForm == rd.drugForm);
        }
        if (!drugForm.equals(rd.drugForm)) {
            return false;
        }
        if (null == drugAmount) {
            return (drugAmount == rd.drugAmount);
        }
        if (!drugAmount.equals(rd.drugAmount)) {
            return false;
        }
        if (null == activeSubstances) {
            return (activeSubstances == rd.activeSubstances);
        }
        if (!activeSubstances.equals(rd.activeSubstances)) {
            return false;
        }
        if (null == country) {
            return (country == rd.country);
        }
        if (!country.equals(rd.country)) {
            return false;
        }
        if (price != rd.price) {
            return false;
        }
        if (quantity != rd.quantity) {
            return false;
        }
        if (cost != rd.cost) {
            return false;
        }
        if (null == status) {
            return (status == rd.status);
        }
        if (!status.equals(rd.status)) {
            return false;
        }
        if (null == endDate) {
            return (endDate == rd.endDate);
        }
        if (!endDate.equals(rd.endDate)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 43;
        hash = hash * 23 + (null == recipeCode ? 0 : recipeCode.hashCode());
        hash = hash * 23 * (drugId == 0 ? 11 : drugId);
        hash = hash * 23 + (null == drugName ? 0 : drugName.hashCode());
        hash = hash * 23 + (null == drugGroup ? 0 : drugGroup.hashCode());
        hash = hash * 23 + (null == drugForm ? 0 : drugForm.hashCode());
        hash = hash * 23 + (null == drugAmount ? 0 : drugAmount.hashCode());
        hash = hash * 23 + (null == activeSubstances ? 0 : activeSubstances.hashCode());
        hash = hash * 23 + (null == country ? 0 : country.hashCode());
        hash = hash * 23 + (int) (0 == cost ? 23 : cost);
        hash = hash * 23 + (null == status ? 0 : status.hashCode());
        hash = hash * 23 + (null == endDate ? 0 : endDate.hashCode());
        return hash;
    }

    @Override
    public String toString(){
        return "RecipeDescription[recipeCode=" + recipeCode + ", drugId=" + drugId + ", drugName=" + drugName +
                ", drugGroup=" + drugGroup + ", drugForm=" + drugForm + ", drugAmount=" + drugAmount +
                ", activeSubstances=" + activeSubstances + ", country=" + country + ", price=" + price +
                ", quantity=" + quantity + ", cost=" + cost + ", status=" + status + ", endDate=" + endDate + "]";
    }

}