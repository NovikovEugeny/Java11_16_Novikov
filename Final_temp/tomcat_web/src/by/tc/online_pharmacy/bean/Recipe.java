package by.tc.online_pharmacy.bean;

import java.io.Serializable;


public class Recipe implements Serializable {

    private String recipeCode;
    private int drugId;
    private int quantity;

    public Recipe() {}

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public int getDrugId() {
        return drugId;
    }

    public int getQuantity() {
        return quantity;
    }
}