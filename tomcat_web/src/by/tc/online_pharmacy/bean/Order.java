package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Евгений on 24.02.2017.
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int clientId;
    private int pharmacistId;
    private int drugId;
    private int quantity;
    private double cost;
    private Date requestDate;
    private Date responseDate;
    private String status;

    public Order() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setPharmacistId(int pharmacistId) {
        this.pharmacistId = pharmacistId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public void setResponseDate(Timestamp responseDate) {
        this.responseDate = responseDate;
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

    public int getPharmacistId() {
        return pharmacistId;
    }

    public int getDrugId() {
        return drugId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
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
