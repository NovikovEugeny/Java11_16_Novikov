package by.tc.onlinepharmacy.service.impl;


import by.tc.onlinepharmacy.bean.*;
import by.tc.onlinepharmacy.dao.ClientDAO;
import by.tc.onlinepharmacy.dao.exception.DAOException;
import by.tc.onlinepharmacy.dao.factory.DAOFactory;
import by.tc.onlinepharmacy.service.ClientService;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;
import by.tc.onlinepharmacy.service.util.Encoder;
import by.tc.onlinepharmacy.service.util.validator.Validator;

import java.util.Date;
import java.util.List;

/**
 * Implementation of the interface {@link by.tc.onlinepharmacy.service.ClientService ClientService}.
 * Commands call these methods, which in turn call up DAO layer methods to receiving or transfer information.
 * Some methods check parameters coming from commands.
 */
public class ClientServiceImpl implements ClientService {

    @Override
    public int signUp(User user) throws ServiceException, ValidatorException {

        Validator.validateSignUp(user);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            if (!clientDAO.isUnique(user.getMobilePhone())) {
                throw new ValidatorException("");
            }

            String password = user.getPassword();
            password = Encoder.encode(password);
            user.setPassword(password);

            return clientDAO.addUser(user);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void addAccount(int clientId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            clientDAO.addAccount(clientId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<Drug> showDrugGroupToOrder(String group) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeDrugGroupToOrder(group);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public RecipeDescription showRecipeDescription(String recipeCode) throws ServiceException, ValidatorException {

        Validator.validateRecipeCode(recipeCode);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeRecipeDescription(recipeCode);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public double showCurrentBalance(int clientId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeClientBalance(clientId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public Date showRecipeEndDate(String recipeCode) throws ServiceException, ValidatorException {

        Validator.validateRecipeCode(recipeCode);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeRecipeEndDate(recipeCode);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public int showCurrentDrugQuantity(int drugId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeDrugQuantity(drugId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void orderWithoutRecipe(Order order) throws ServiceException, ValidatorException {

        Validator.validateDrugQuantity(order.getQuantity());

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            double realPrice = clientDAO.takeDrugPrice(order.getDrugId());
            double realCost = realPrice * order.getQuantity();

            if (realCost != order.getCost()) {
                throw new NumberFormatException();
            }

            clientDAO.addOrder(order);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void orderWithRecipe(Order order, String recipeCode) throws ServiceException, ValidatorException {

        Validator.validateDrugQuantity(order.getQuantity());

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            double realPrice = clientDAO.takeDrugPrice(order.getDrugId());
            double realCost = realPrice * order.getQuantity();

            if (realCost != order.getCost()) {
                throw new NumberFormatException();
            }

            int id = clientDAO.addOrder(order);
            clientDAO.closeRecipe(recipeCode);
            clientDAO.linkOrderAndRecipe(id, recipeCode);

        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<OrderDescription> clientShowOrderList(int id) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeClientOrderList(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderDescription> showSendingMessageList(int clientId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeSendingMessageList(clientId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<RERDescription> showDoctorResponseMessageList(int clientId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeDoctorResponseMessageList(clientId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void sendRecipeExtensionRequest(String recipeCode, int clientId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            clientDAO.addRecipeExtensionRequest(recipeCode, clientId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void reportAboutDelivery(int orderId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            clientDAO.reportAboutDelivery(orderId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }


    @Override
    public void deleteMessage(int requestId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            clientDAO.deleteMessage(requestId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<OrderDescription> showShoppingList(int clientId) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.takeShoppingList(clientId);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public boolean isDuplicateApplication(String recipeCode) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            return clientDAO.isDuplicateApplication(recipeCode);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void replenishBalance(int clientId, double amount) throws ServiceException, ValidatorException {

        Validator.validateMoneyAmount(amount);

        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            ClientDAO clientDAO = daoFactory.getClientDAO();

            clientDAO.replenishBalance(clientId, amount);
        } catch (DAOException exc) {
            throw new ServiceException(exc);
        }
    }
}