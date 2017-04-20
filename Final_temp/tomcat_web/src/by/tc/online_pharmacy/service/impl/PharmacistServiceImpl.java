package by.tc.online_pharmacy.service.impl;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.dao.PharmacistDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.PharmacistService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.util.ReportMaker;
import by.tc.online_pharmacy.service.util.validator.Validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            return pharmacistDao.takePharmacistOrderList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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

    @Override
    public Map<Date, List<Drug>> showSalesReport() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            PharmacistDao pharmacistDao = daoFactory.getPharmacistDao();

            List<OrderDescription> orders = pharmacistDao.takeOrders();
            return ReportMaker.makeReport(orders);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }
}