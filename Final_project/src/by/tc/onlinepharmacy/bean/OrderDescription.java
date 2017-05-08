package by.tc.onlinepharmacy.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Class describing the transfer object for storing full information for users about an order.
 */
public class OrderDescription implements Serializable {

    private static final long serialVersionUID = -3195817512572919240L;

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

    public OrderDescription() {
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public void setResponseDate(Date responseDate) {
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

        OrderDescription od = (OrderDescription) obj;
        if (orderId != od.orderId) {
            return false;
        }
        if (null == requestDate) {
            return (requestDate == od.requestDate);
        }
        if (!requestDate.equals(od.requestDate)) {
            return false;
        }
        if (null == responseDate) {
            return (responseDate == od.responseDate);
        }
        if (!responseDate.equals(od.responseDate)) {
            return false;
        }
        if (null == clientMobile) {
            return (clientMobile == od.clientMobile);
        }
        if (!clientMobile.equals(od.clientMobile)) {
            return false;
        }
        if (null == drugName) {
            return (drugName == od.drugName);
        }
        if (!drugName.equals(od.drugName)) {
            return false;
        }
        if (null == pharmacologicalGroup) {
            return (pharmacologicalGroup == od.pharmacologicalGroup);
        }
        if (!pharmacologicalGroup.equals(od.pharmacologicalGroup)) {
            return false;
        }
        if (null == drugForm) {
            return (drugForm == od.drugForm);
        }
        if (!drugForm.equals(od.drugForm)) {
            return false;
        }
        if (null == drugAmount) {
            return (drugAmount == od.drugAmount);
        }
        if (!drugAmount.equals(od.drugAmount)) {
            return false;
        }
        if (null == activeSubstances) {
            return (activeSubstances == od.activeSubstances);
        }
        if (!activeSubstances.equals(od.activeSubstances)) {
            return false;
        }
        if (null == productingCountry) {
            return (productingCountry == od.productingCountry);
        }
        if (!productingCountry.equals(od.productingCountry)) {
            return false;
        }
        if (quantity != od.quantity) {
            return false;
        }
        if (cost != od.cost) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = hash * 19 * (0 == orderId ? 19 : orderId);
        hash = hash * 19 + (null == requestDate ? 0 : requestDate.hashCode());
        hash = hash * 19 + (null == responseDate ? 0 : responseDate.hashCode());
        hash = hash * 19 + (null == clientMobile ? 0 : clientMobile.hashCode());
        hash = hash * 19 + (null == drugName ? 0 : drugName.hashCode());
        hash = hash * 19 + (null == pharmacologicalGroup ? 0 : pharmacologicalGroup.hashCode());
        hash = hash * 19 + (null == drugForm ? 0 : drugForm.hashCode());
        hash = hash * 19 + (null == drugAmount ? 0 : drugAmount.hashCode());
        hash = hash * 19 + (null == activeSubstances ? 0 : activeSubstances.hashCode());
        hash = hash * 19 + (null == productingCountry ? 0 : productingCountry.hashCode());
        hash = hash * 19 + quantity * 19;
        hash = (int) (hash * 19 + cost * 19);
        return hash;
    }

    @Override
    public String toString() {
        return "OrderDescription[orderId=" + orderId + ", requestDate=" + requestDate + ", responseDate=" +
                responseDate + ", clientMobile=" + clientMobile + ", drugName=" + drugName +
                ", pharmacologicalGroup=" + pharmacologicalGroup + ", drugForm=" + drugForm + ", drugAmount=" +
                drugAmount + ", activeSubstances=" + activeSubstances + ", productingCountry=" + productingCountry +
                ", quantity=" + quantity + ", cost=" + cost + "]";
    }

}