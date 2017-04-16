package by.tc.online_pharmacy.service.impl;


import by.tc.online_pharmacy.bean.RERDescription;
import by.tc.online_pharmacy.dao.DoctorDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.DoctorService;
import by.tc.online_pharmacy.service.exception.ServiceException;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {


    @Override
    public List<RERDescription> showRecipeExtensionRequestList() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DoctorDao doctorDao = daoFactory.getDoctorDao();

            return doctorDao.takeRecipeExtensionRequestList();
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void approve(RERDescription rerDescription) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DoctorDao doctorDao = daoFactory.getDoctorDao();

            doctorDao.approve(rerDescription);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void deny(RERDescription rerDescription) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DoctorDao doctorDao = daoFactory.getDoctorDao();

            doctorDao.deny(rerDescription);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

}
