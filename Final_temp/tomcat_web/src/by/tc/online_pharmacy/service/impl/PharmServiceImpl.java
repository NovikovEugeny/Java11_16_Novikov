package by.tc.online_pharmacy.service.impl;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PharmServiceImpl implements PharmService {

    private final static String NOT_FOUND_MESSAGE = "Препарат с данным " +
            "названием не найден, проверьте правильность ввода!";

    @Override
    public List<Drug> takeDrugsByName(String name) throws ServiceException, ValidatorException {
        List<Drug> drugs = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugs = drugDao.takeDrugsByName(name);

            if (drugs.isEmpty()) {
                throw new ValidatorException(NOT_FOUND_MESSAGE);
            }
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
    public void addNewDrug(Drug drug) throws ServiceException {

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
    public void orderWithRecipe(Order order, Recipe recipe) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            Date currentDate = new Date();
            Date endDate = drugDao.confirmRecipe(recipe);

            if (endDate == null) {
                System.out.println("this recipe is not exists");
                throw new DaoException();
            }

            if (endDate.getTime() > currentDate.getTime()) {
                int id = drugDao.addOrder(order);
                drugDao.closeRecipe(recipe);
                drugDao.linkOrderAndRecipe(id, recipe.getRecipeCode());
            } else {
                System.out.println("too later");
                throw new DaoException();
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
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
    public void addRecipeExtensionRequest(String recipeCode) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            drugDao.addRecipeExtensionRequest(recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public Map<Integer, String> takeRecipeExtensionRequestList() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            return drugDao.takeRecipeExtensionRequestList();
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void approve(int id, String recipeCode) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.approve(id, recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void deny(int id, String recipeCode) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            drugDao.deny(id, recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void sendFeedback(int recipeId, String feedback) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            drugDao.sendFeedback(recipeId, feedback);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}