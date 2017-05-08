package by.tc.onlinepharmacy.service.impl;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.bean.User;
import by.tc.onlinepharmacy.dao.CommonDAO;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.factory.DAOFactory;
import by.tc.onlinepharmacy.service.CommonService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.util.Encoder;
import by.tc.onlinepharmacy.service.util.validator.Validator;

import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.service.CommonService CommonService}.
 * Commands call these methods, which in turn call up DAO layer methods to receiving or transfer information.
 * Some methods check parameters coming from commands.
 */
public class CommonServiceImpl implements CommonService {

    @Override
    public User signIn(String mobile, String password) throws ServiceException, ValidatorException {

        Validator.validateSignIn(mobile, password);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            CommonDAO commonDAO = daoFactory.getCommonDAO();

            password = Encoder.encode(password);

            return commonDAO.signIn(mobile, password);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Drug> showDrugsByName(String name) throws ServiceException, ValidatorException {

        Validator.validateSearch(name);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            CommonDAO commonDAO = daoFactory.getCommonDAO();

            return commonDAO.takeDrugsByName(name);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Drug> showDrugGroup(String group) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            CommonDAO commonDAO = daoFactory.getCommonDAO();

            return commonDAO.takeDrugGroup(group);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void cancelOrder(int orderId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            CommonDAO commonDAO = daoFactory.getCommonDAO();

            commonDAO.cancelOrder(orderId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

}