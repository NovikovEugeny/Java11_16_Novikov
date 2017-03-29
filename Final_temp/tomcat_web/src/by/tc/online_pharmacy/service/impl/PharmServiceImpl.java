package by.tc.online_pharmacy.service.impl;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.dao.DrugDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.PharmService;
import by.tc.online_pharmacy.service.exception.ServiceException;

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
    public String addDrug(Drug drug) throws ServiceException {
        String response = null;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();

            response = drugDao.addDrug(drug);
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
            response = drugDao.orderWithoutRecipe(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    @Override
    public String orderWithRecipe(Order order, Recipe recipe) throws ServiceException {
        String response = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            response = drugDao.orderWithRecipe(order, recipe);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }


    /*@Override
    public List<Order> showOrderList() throws ServiceException {
        List<Order> orderList = null;

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
    public Drug showOrder(int orderId, int pharmacistId) throws ServiceException {

        Drug drug = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            drug = drugDao.showOrder(orderId, pharmacistId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return drug;
    }*/

    @Override
    public Map<Order, Drug> showOrderList() throws ServiceException {
        Map<Order, Drug> orderList = null;

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
    public Map<Recipe, Drug> showRecipeList() throws ServiceException {
        Map<Recipe, Drug> recipeList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            recipeList = drugDao.showRecipeList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return recipeList;
    }

  /*  @Override
    public Map<String, String> showRecipe(int recipeId, int doctorId) throws ServiceException {
        Map<String, String> info = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            info = drugDao.showRecipe(recipeId, doctorId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return info;
    }
*/
    @Override
    public String approve(int recipeId, int doctorId) throws ServiceException {
        String response = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            response = drugDao.approve(recipeId, doctorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
    }

    @Override
    public String deny(int recipeId, int doctorId) throws ServiceException {
        String response = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DrugDao drugDao = daoFactory.getDrugDao();
            response = drugDao.deny(recipeId, doctorId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return response;
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
