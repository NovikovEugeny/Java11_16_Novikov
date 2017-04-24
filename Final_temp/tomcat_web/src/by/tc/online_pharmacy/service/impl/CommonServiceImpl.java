package by.tc.online_pharmacy.service.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.CommonDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.CommonService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.util.Encoder;
import by.tc.online_pharmacy.service.util.validator.Validator;

import java.util.List;

public class CommonServiceImpl implements CommonService {

    @Override
    public User signIn(String mobile, String password) throws ServiceException, ValidatorException {

        Validator.validateSignIn(mobile, password);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            CommonDao commonDao = daoFactory.getCommonDao();

            password = Encoder.encode(password);

            return commonDao.signIn(mobile, password);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Drug> showDrugsByName(String name) throws ServiceException, ValidatorException {

        Validator.validateSearch(name);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            CommonDao commonDao = daoFactory.getCommonDao();

            return commonDao.takeDrugsByName(name);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Drug> showDrugGroup(String group) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            CommonDao commonDao = daoFactory.getCommonDao();

            return commonDao.takeDrugGroup(group);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void cancelOrder(int orderId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            CommonDao commonDao = daoFactory.getCommonDao();

            commonDao.cancelOrder(orderId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

}