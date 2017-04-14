package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.util.Date;


public class RERDescription implements Serializable{

    private int id;
    private String recipeCode;
    private int clientId;
    private int doctorId;
    private Date requestDate;
    private Date responseDate;
    private String status;

    public RERDescription() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setRecipeCode(String recipeCode) {
        this.recipeCode = recipeCode;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getRecipeCode() {
        return recipeCode;
    }

    public int getClientId() {
        return clientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public String getStatus() {
        return status;
    }
}
