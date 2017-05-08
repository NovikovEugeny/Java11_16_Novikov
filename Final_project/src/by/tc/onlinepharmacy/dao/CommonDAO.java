package by.tc.onlinepharmacy.dao;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.exception.DAOException;

import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.dao.impl.CommonDAOImpl CommonDAOImpl}.
 */
public interface CommonDAO {

    User signIn(String login, String password) throws DAOException;

    List<Drug> takeDrugGroup(String group) throws DAOException;

    List<Drug> takeDrugsByName(String name) throws DAOException;

    void cancelOrder(int orderId) throws DAOException;

}