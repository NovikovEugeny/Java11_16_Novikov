package by.tc.onlinepharmacy.service;


import by.tc.onlinepharmacy.bean.*;
import by.tc.onlinepharmacy.service.exception.ServiceException;
import by.tc.onlinepharmacy.service.exception.ValidatorException;

import java.util.Date;
import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.service.impl.ClientServiceImpl ClientServiceImpl}.
 */
public interface ClientService {

    int signUp(User user) throws ServiceException, ValidatorException;

    void addAccount(int clientId) throws ServiceException;

    List<Drug> showDrugGroupToOrder(String group) throws ServiceException;

    RecipeDescription showRecipeDescription(String recipeCode) throws ServiceException, ValidatorException;

    double showCurrentBalance(int clientId) throws ServiceException;

    Date showRecipeEndDate(String recipeCode) throws ServiceException, ValidatorException;

    int showCurrentDrugQuantity(int drugId) throws ServiceException;

    void orderWithoutRecipe(Order order) throws ServiceException, ValidatorException;

    void orderWithRecipe(Order order, String recipeCode) throws ServiceException, ValidatorException;

    List<OrderDescription> clientShowOrderList(int id) throws ServiceException;

    List<OrderDescription> showSendingMessageList(int clientId) throws ServiceException;

    List<RERDescription> showDoctorResponseMessageList(int clientId) throws ServiceException;

    void sendRecipeExtensionRequest(String recipeCode, int clientId) throws ServiceException;

    void reportAboutDelivery(int orderId) throws ServiceException;

    void deleteMessage(int requestId) throws ServiceException;

    List<OrderDescription> showShoppingList(int clientId) throws ServiceException;

    boolean isDuplicateApplication(String recipeCode) throws ServiceException;

    void replenishBalance(int clientId, double amount) throws ServiceException, ValidatorException;

}