package by.tc.onlinepharmacy.service.impl;


import by.tc.onlinepharmacy.bean.Drug;
import by.tc.onlinepharmacy.dao.PharmacistDAO;
import by.tc.onlinepharmacy.dao.factory.DAOFactory;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.util.validator.Validator;
import by.tc.onlinepharmacy.bean.OrderDescription;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.service.PharmacistService;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.util.ReportMaker;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.service.PharmacistService PharmacistService}.
 * Commands call these methods, which in turn call up DAO layer methods to receiving or transfer information.
 * Some methods check parameters coming from commands.
 */
public class PharmacistServiceImpl implements PharmacistService {

    @Override
    public void addDrugQuantity(int id, int quantity) throws ServiceException, ValidatorException {

        Validator.validateDrugQuantity(quantity);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            PharmacistDAO pharmacistDAO = daoFactory.getPharmacistDAO();

            pharmacistDAO.addDrugQuantity(id, quantity);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void addNewDrug(Drug drug) throws ServiceException, ValidatorException {

        Validator.validateNewDrugAddition(drug);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            PharmacistDAO pharmacistDAO = daoFactory.getPharmacistDAO();

            pharmacistDAO.addNewDrug(drug);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void removeDrug(int id) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            PharmacistDAO pharmacistDAO = daoFactory.getPharmacistDAO();

            pharmacistDAO.removeDrug(id);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<OrderDescription> pharmacistShowOrderList() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            PharmacistDAO pharmacistDAO = daoFactory.getPharmacistDAO();

            return pharmacistDAO.takePharmacistOrderList();
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void send(int orderId, int pharmacistId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            PharmacistDAO pharmacistDAO = daoFactory.getPharmacistDAO();

            pharmacistDAO.send(orderId, pharmacistId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public Map<Date, List<Drug>> showSalesReport() throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            PharmacistDAO pharmacistDAO = daoFactory.getPharmacistDAO();

            List<OrderDescription> orders = pharmacistDAO.takeOrders();
            return ReportMaker.makeReport(orders);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }
}