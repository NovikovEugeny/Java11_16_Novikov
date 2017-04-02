package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Евгений on 02.04.2017.
 */
public class Recipe2 implements Serializable {

    private String recipeCode;
    private int drugId;
    private int quantity;

    public Recipe2() {}

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
