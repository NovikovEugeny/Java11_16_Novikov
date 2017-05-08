package by.tc.onlinepharmacy.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Class describing the transfer object for storing service information about an order.
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 7946715111086122031L;

    private int id;
    private int clientId;
    private int pharmacistId;
    private int drugId;
    private int quantity;
    private double cost;
    private Date requestDate;
    private Date responseDate;
    private String status;

    public Order() {
    }

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

        Order order = (Order) obj;
        if (id != order.id) {
            return false;
        }
        if (clientId != order.clientId) {
            return false;
        }
        if (pharmacistId != order.pharmacistId) {
            return false;
        }
        if (drugId != order.drugId) {
            return false;
        }
        if (quantity != order.quantity) {
            return false;
        }
        if (cost != order.cost) {
            return false;
        }
        if (null == requestDate) {
            return (requestDate == order.requestDate);
        }
        if (!requestDate.equals(order.requestDate)) {
            return false;
        }
        if (null == responseDate) {
            return (responseDate == order.responseDate);
        }
        if (!responseDate.equals(order.responseDate)) {
            return false;
        }
        if (null == status) {
            return (status == order.status);
        }
        if (!status.equals(order.status)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 33;
        hash = hash * 17 * (0 == id ? 11 : id);
        hash = hash * 17 * (0 == clientId ? 11 : clientId);
        hash = hash * 17 * (0 == pharmacistId ? 11 : pharmacistId);
        hash = hash * 17 * (0 == drugId ? 11 : drugId);
        hash = hash * 17 * (0 == quantity ? 11 : quantity);
        hash = (int) (hash * 17 * (0 == cost ? 11 : cost));
        hash = hash * 17 * (null == requestDate ? 11 : requestDate.hashCode());
        hash = hash * 17 * (null == responseDate ? 11 : responseDate.hashCode());
        hash = hash * 17 * (null == status ? 11 : status.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "Order[id=" + id + ", clientId=" + clientId + ", pharmacistId=" + pharmacistId + ", drugId=" + drugId +
                ", quantity=" + quantity + ", cost=" + cost + ", requestDate=" + requestDate + ", responseDate=" +
                responseDate + ", status=" + "]";
    }

}