package by.tc.online_pharmacy.dao;


import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.List;

public interface DoctorDao {

    List<RERDescription> takeRecipeExtensionRequestList() throws DaoException;

    void approve(RERDescription rerDescription) throws DaoException;

    void deny (RERDescription rerDescription) throws DaoException;
}
