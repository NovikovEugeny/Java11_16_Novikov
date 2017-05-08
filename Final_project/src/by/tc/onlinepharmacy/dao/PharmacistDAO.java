package by.tc.onlinepharmacy.dao;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.OrderDescription;
import by.tc.onlinepharmacy.dao.exception.DAOException;

import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.dao.impl.PharmacistDAOImpl PharmacistDAOImpl}.
 */
public interface PharmacistDAO {

    void addDrugQuantity(int id, int quantity) throws DAOException;

    void addNewDrug(Drug drug) throws DAOException;

    void removeDrug(int id) throws DAOException;

    List<OrderDescription> takePharmacistOrderList() throws DAOException;

    void send(int orderId, int pharmacistId) throws DAOException;

    List<OrderDescription> takeOrders() throws DAOException;

}