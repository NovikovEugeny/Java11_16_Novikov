package by.tc.online_pharmacy.bean;

import java.io.Serializable;
import java.util.Date;


public class RERDescription implements Serializable {

    private static final long serialVersionUID = -4135401062953462119L;

    private int id;
    private String recipeCode;
    private int clientId;
    private int doctorId;
    private Date requestDate;
    private Date responseDate;
    private String status;

    public RERDescription() {
    }

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

        RERDescription rerDescription = (RERDescription) obj;
        if (id != rerDescription.id) {
            return false;
        }
        if (null == recipeCode) {
            return (recipeCode == rerDescription.recipeCode);
        }
        if (!recipeCode.equals(rerDescription.recipeCode)) {
            return false;
        }
        if (clientId != rerDescription.id) {
            return false;
        }
        if (doctorId != rerDescription.id) {
            return false;
        }
        if (null == requestDate) {
            return (requestDate == rerDescription.requestDate);
        }
        if (!requestDate.equals(rerDescription.requestDate)) {
            return false;
        }
        if (null == responseDate) {
            return (responseDate == rerDescription.responseDate);
        }
        if (!responseDate.equals(rerDescription.responseDate)) {
            return false;
        }
        if (null == status) {
            return (status == rerDescription.status);
        }
        if (!status.equals(rerDescription.status)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 47;
        hash = hash * 29 + 29 * id;
        hash = hash * 29 + (null == recipeCode ? 0 : recipeCode.hashCode());
        hash = hash * 29 + 29 * clientId;
        hash = hash * 29 + 29 * doctorId;
        hash = hash * 29 + (null == requestDate ? 0 : requestDate.hashCode());
        hash = hash * 29 + (null == responseDate ? 0 : responseDate.hashCode());
        hash = hash * 29 + (null == status ? 0 : status.hashCode());
        return hash;
    }

    @Override
    public String toString() {
        return "RERDescription[id=" + id + ", recipeCode=" + recipeCode + ", clientId=" + clientId + ", doctorId=" +
                doctorId + ", requestDate=" + requestDate + ", responseDate=" + responseDate + ", status=" + status + "]";
    }

}