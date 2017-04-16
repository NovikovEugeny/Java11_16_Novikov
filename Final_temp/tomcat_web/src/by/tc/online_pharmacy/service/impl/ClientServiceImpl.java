package by.tc.online_pharmacy.service.impl;


import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.ClientDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.ClientService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.util.Encoder;
import by.tc.online_pharmacy.service.util.validator.Validator;

import java.util.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Override
    public int signUp(User user) throws ServiceException, ValidatorException {

        Validator.signUpValidate(user);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            if (!clientDao.isUnique(user.getMobilePhone())) {
                throw new ValidatorException("This mobile phone already exists");
            }

            String password = user.getPassword();
            password = Encoder.encode(password);
            user.setPassword(password);

            return clientDao.signUp(user);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void addAccount(int clientId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            clientDao.addAccount(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Drug> showDrugGroupToOrder(String group) throws ServiceException {
        List<Drug> drugs = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            drugs = clientDao.takeDrugGroupToOrder(group);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return drugs;
    }

    @Override
    public RecipeDescription showRecipeDescription(String recipeCode) throws ServiceException {
        RecipeDescription recipeDescription = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            recipeDescription = clientDao.takeRecipeDescription(recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return recipeDescription;
    }

    @Override
    public double showCurrentBalance(int clientId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            return clientDao.takeClientBalance(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public Date showRecipeEndDate(String recipeCode) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            return clientDao.takeRecipeEndDate(recipeCode);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public int showCurrentDrugQuantity(int drugId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            return clientDao.takeDrugQuantity(drugId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void orderWithoutRecipe(Order order) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            clientDao.addOrder(order);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void orderWithRecipe(Order order, String recipeCode) throws ServiceException {

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            int id = clientDao.addOrder(order);
            clientDao.closeRecipe(recipeCode);
            clientDao.linkOrderAndRecipe(id, recipeCode);

        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<OrderDescription> clientShowOrderList(int id) throws ServiceException {
        List<OrderDescription> orderList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            orderList = clientDao.takeClientOrderList(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orderList;
    }

    @Override
    public List<OrderDescription> showSendingMessageList(int clientId) throws ServiceException {

        List<OrderDescription> orderDescriptionList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            orderDescriptionList = clientDao.takeSendingMessageList(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return orderDescriptionList;
    }

    @Override
    public List<RERDescription> showDoctorResponseMessageList(int clientId) throws ServiceException {

        List<RERDescription> rerDescriptionList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            rerDescriptionList = clientDao.takeDoctorResponseMessageList(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return rerDescriptionList;
    }

    @Override
    public void sendRecipeExtensionRequest(String recipeCode, int clientId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            clientDao.addRecipeExtensionRequest(recipeCode, clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void reportAboutDelivery(int orderId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            clientDao.reportAboutDelivery(orderId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void hideMessage(int requestId) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            clientDao.hideMessage(requestId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<OrderDescription> showShoppingList(int clientId) throws ServiceException {
        List<OrderDescription> shoppingList = null;

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            ClientDao clientDao = daoFactory.getClientDao();

            shoppingList = clientDao.takeShoppingList(clientId);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
        return shoppingList;
    }
}