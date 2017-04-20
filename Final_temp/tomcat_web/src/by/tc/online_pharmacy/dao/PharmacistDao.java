package by.tc.online_pharmacy.dao;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.List;

public interface PharmacistDao {

    void addDrugQuantity(int id, int quantity) throws DaoException;

    void addNewDrug(Drug drug) throws DaoException;

    void removeDrug(int id) throws DaoException;

    List<OrderDescription> takePharmacistOrderList() throws DaoException;

    void send(int orderId, int pharmacistId) throws DaoException;

    List<OrderDescription> takeOrders() throws DaoException;
}
