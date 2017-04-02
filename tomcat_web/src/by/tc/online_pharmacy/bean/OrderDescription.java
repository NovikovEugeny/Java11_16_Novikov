package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Евгений on 29.03.2017.
 */
public class OrderDescription implements Serializable {

    private static final long serialVersionUID = 1L;

    private int orderId;
    private Date requestDate;
    private String clientMobile;
    private String drugName;
    private String pharmacologicalGroup;
    private String drugForm;
    private String drugAmount;
    private String activeSubstances;
    private String productingCountry;
    private int quantity;

    public OrderDescription() {}

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
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

    public int getOrderId() {
        return orderId;
    }

    public Date getRequestDate() {
        return requestDate;
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
}
