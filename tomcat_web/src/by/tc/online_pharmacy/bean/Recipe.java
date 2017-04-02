package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Евгений on 03.03.2017.
 */
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int clientId;
    private int doctorId;
    private int drugId;
    private int quantity;
    private String recipeCode;
    private Date requestDate;
    private Date responseDate;
    private String feedback;
    private String status;

    public Recipe() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public void setResponseDate(Timestamp responseDate) {
        this.responseDate = responseDate;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public int getDrugId() {
        return drugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getStatus() {
        return status;
    }
}
