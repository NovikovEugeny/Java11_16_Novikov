package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class OrderDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private Date requestDate;
    private Date responseDate;
    private String clientMobile;
    private String drugName;
    private String pharmacologicalGroup;
    private String drugForm;
    private String drugAmount;
    private String activeSubstances;
    private String productingCountry;
    private int quantity;
    private double cost;

    public OrderDescription() {}

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public void setResponseDate(Timestamp responseDate) {
        this.responseDate = responseDate;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setPharmacologicalGroup(String pharmacologicalGroup) {
        this.pharmacologicalGroup = pharmacologicalGroup;
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

    public void setProductingCountry(String productingCountry) {
        this.productingCountry = productingCountry;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getPharmacologicalGroup() {
        return pharmacologicalGroup;
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

    public String getProductingCountry() {
        return productingCountry;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }
}
