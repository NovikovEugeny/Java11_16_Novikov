package by.tc.online_pharmacy.service.impl;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Евгений on 17.02.2017.
 */
public class PharmServiceImpl implements PharmService {
    @Override
    public List<Drug> takeDrugsByName(String name) throws ServiceException {
        List<Drug> drugs = null;

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
    public String addDrugQuantity(int id, int quantity) throws ServiceException {
        String response = null;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            response = drugDao.addDrugQuantity(id, quantity);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return response;
    }

    @Override
    public String addNewDrug(Drug drug) throws ServiceException {
        String response = null;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            response = drugDao.addNewDrug(drug);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return response;
    }

    @Override
    public String removeDrug(int id) throws ServiceException {
        String response = null;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            response = drugDao.removeDrug(id);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return response;
    }


    @Override
    public String orderWithoutRecipe(Order order) throws ServiceException {
        String response = null;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            response = drugDao.toOrder(order);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return response;
    }


    @Override
    public String orderWithRecipe(Order order, Recipe2 recipe) throws ServiceException {
        String response = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            Date currentDate = new Date();
            Date endDate = drugDao.confirmRecipe(recipe);

            if (endDate == null) {
                response = "no recipe";
                System.out.println("null");
            }

            if (endDate.getTime() > currentDate.getTime()) {
                response = drugDao.toOrder(order);
            }

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }


    @Override
    public List<OrderDescription> showOrderList() throws ServiceException {
        List<OrderDescription> orderList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            orderList = drugDao.showOrderList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }


    @Override
    public String send(int orderId, int pharmacistId) throws ServiceException {
        String response = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            response = drugDao.send(orderId, pharmacistId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    @Override
    public String sendFeedback(int recipeId, String feedback) throws ServiceException {
        String response = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            response = drugDao.sendFeedback(recipeId, feedback);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }

}
