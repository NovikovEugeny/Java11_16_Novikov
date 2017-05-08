package by.tc.onlinepharmacy.service.impl;


import by.tc.onlinepharmacy.dao.DoctorDAO;
import by.tc.onlinepharmacy.bean.RERDescription;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.factory.DAOFactory;
import by.tc.onlinepharmacy.service.DoctorService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.util.validator.Validator;

import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.service.DoctorService DoctorService}.
 * Commands call these methods, which in turn call up DAO layer methods to receiving or transfer information.
 * Some methods check parameters coming from commands.
 */
public class DoctorServiceImpl implements DoctorService {

    @Override
    public List<RERDescription> showRecipeExtensionRequestList() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DoctorDAO doctorDAO = daoFactory.getDoctorDAO();

            return doctorDAO.takeRecipeExtensionRequestList();
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void approve(RERDescription rerDescription) throws ServiceException, ValidatorException {

        Validator.validateRecipeCode(rerDescription.getRecipeCode());

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DoctorDAO doctorDAO = daoFactory.getDoctorDAO();

            doctorDAO.approve(rerDescription);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void deny(RERDescription rerDescription) throws ServiceException, ValidatorException {

        Validator.validateRecipeCode(rerDescription.getRecipeCode());

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            DoctorDAO doctorDAO = daoFactory.getDoctorDAO();

            doctorDAO.deny(rerDescription);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

}