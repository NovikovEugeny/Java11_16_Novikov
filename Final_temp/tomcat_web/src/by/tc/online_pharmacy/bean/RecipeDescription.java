package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.util.Date;


public class RecipeDescription implements Serializable {

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

    public RecipeDescription() {}

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
}
