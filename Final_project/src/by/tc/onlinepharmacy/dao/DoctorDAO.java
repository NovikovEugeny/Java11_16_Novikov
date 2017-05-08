package by.tc.onlinepharmacy.dao;


import by.tc.onlinepharmacy.bean.RERDescription;
import by.tc.onlinepharmacy.dao.exception.DAOException;

import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.dao.impl.DoctorDAOImpl DoctorDAOImpl}.
 */
public interface DoctorDAO {

    List<RERDescription> takeRecipeExtensionRequestList() throws DAOException;

    void approve(RERDescription rerDescription) throws DAOException;

    void deny (RERDescription rerDescription) throws DAOException;

}