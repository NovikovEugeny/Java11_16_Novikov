package by.tc.online_pharmacy.service.impl;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.util.validator.Validator;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PharmServiceImpl implements PharmService {

    @Override
    public List<Drug> takeDrugsByName(String name) throws ServiceException, ValidatorException {
        List<Drug> drugs = null;

        Validator.searchValidate(name);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugs = drugDao.takeDrugsByName(name);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return drugs;
    }

    @Override
    public List<Drug> takeDrugGroup(String group) throws ServiceException {
        List<Drug> drugs = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugs = drugDao.takeDrugGroup(group);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return drugs;
    }

    @Override
    public List<Drug> takeDrugGroupToOrder(String group) throws ServiceException {
        List<Drug> drugs = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugs = drugDao.takeDrugGroupToOrder(group);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return drugs;
    }


    @Override
    public double showCurrentBalance(int clientId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            return drugDao.takeClientBalance(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public int showCurrentDrugQuantity(int drugId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            return drugDao.takeDrugQuantity(drugId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public Date takeRecipeEndDate(String recipeCode) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            return drugDao.takeRecipeEndDate(recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public RecipeDescription takeRecipeDescription(String recipeCode) throws ServiceException {
        RecipeDescription recipeDescription = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            recipeDescription = drugDao.takeRecipeDescription(recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return recipeDescription;
    }

    @Override
    public void addDrugQuantity(int id, int quantity) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.addDrugQuantity(id, quantity);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void addNewDrug(Drug drug) throws ServiceException, ValidatorException {

        Validator.addNewDrugValidate(drug);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.addNewDrug(drug);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void removeDrug(int id) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.removeDrug(id);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void orderWithoutRecipe(Order order) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.addOrder(order);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void orderWithRecipe(Order order, String recipeCode) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            int id = drugDao.addOrder(order);
            drugDao.closeRecipe(recipeCode);
            drugDao.linkOrderAndRecipe(id, recipeCode);

        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public List<OrderDescription> pharmacistShowOrderList() throws ServiceException {
        List<OrderDescription> orderList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            orderList = drugDao.takePharmacistOrderList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public List<OrderDescription> clientShowOrderList(int id) throws ServiceException {
        List<OrderDescription> orderList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            orderList = drugDao.takeClientOrderList(id);
            System.out.println("service");
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }


    @Override
    public void send(int orderId, int pharmacistId) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            drugDao.send(orderId, pharmacistId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public List<OrderDescription> takeSendingMessageList(int clientId) throws ServiceException {

        List<OrderDescription> orderDescriptionList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            orderDescriptionList = drugDao.takeSendingMessageList(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return orderDescriptionList;
    }

    @Override
    public List<RERDescription> takeDoctorResponseMessageList(int clientId) throws ServiceException {
        List<RERDescription> rerDescriptionList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            rerDescriptionList = drugDao.takeDoctorResponseMessageList(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return rerDescriptionList;
    }

    @Override
    public void cancelOrder(int orderId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            drugDao.cancelOrder(orderId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void sendRecipeExtensionRequest(String recipeCode, int clientId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            drugDao.addRecipeExtensionRequest(recipeCode, clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public List<RERDescription> takeRecipeExtensionRequestList() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            return drugDao.takeRecipeExtensionRequestList();
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void approve(RERDescription rerDescription) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.approve(rerDescription);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void deny(RERDescription rerDescription) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.deny(rerDescription);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void reportAboutDelivery(int orderId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.reportAboutDelivery(orderId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }



}