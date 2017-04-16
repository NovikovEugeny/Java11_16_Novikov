package by.tc.online_pharmacy.service.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.dao.PharmacistDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.util.validator.Validator;

import java.util.List;

public class PharmacistServiceImpl implements PharmacistService {

    @Override
    public void addDrugQuantity(int id, int quantity) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            pharmacistDao.addDrugQuantity(id, quantity);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void addNewDrug(Drug drug) throws ServiceException, ValidatorException {

        Validator.addNewDrugValidate(drug);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            pharmacistDao.addNewDrug(drug);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void removeDrug(int id) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            pharmacistDao.removeDrug(id);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<OrderDescription> pharmacistShowOrderList() throws ServiceException {
        List<OrderDescription> orderList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            orderList = pharmacistDao.takePharmacistOrderList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public void send(int orderId, int pharmacistId) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            pharmacistDao.send(orderId, pharmacistId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

}
