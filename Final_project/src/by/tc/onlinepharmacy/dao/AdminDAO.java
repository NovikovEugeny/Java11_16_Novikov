package by.tc.onlinepharmacy.dao;


import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.exception.DAOException;

import java.util.List;


/**
 * Interface for {@link by.tc.onlinepharmacy.dao.impl.ClientDAOImpl AdminDAOImpl}.
 */
public interface AdminDAO {

    List<User> takeEmployeeList() throws DAOException;

    void removeEmployee(int id) throws DAOException;
}