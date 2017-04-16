package by.tc.online_pharmacy.dao;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.List;

public interface CommonDao {

    User signIn(String login, String password) throws DaoException;

    List<Drug> takeDrugGroup(String group) throws DaoException;

    List<Drug> takeDrugsByName(String name) throws DaoException;

    void cancelOrder(int orderId) throws DaoException;

}
