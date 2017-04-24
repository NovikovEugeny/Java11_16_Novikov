package by.tc.online_pharmacy.service;


import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.Date;
import java.util.List;

public interface ClientService {

    int signUp(User user) throws ServiceException, ValidatorException;

    void addAccount(int clientId) throws ServiceException;

    List<Drug> showDrugGroupToOrder(String group) throws ServiceException;

    RecipeDescription showRecipeDescription(String recipeCode) throws ServiceException, ValidatorException;

    double showCurrentBalance(int clientId) throws ServiceException;

    Date showRecipeEndDate(String recipeCode) throws ServiceException, ValidatorException;

    int showCurrentDrugQuantity(int drugId) throws ServiceException;

    void orderWithoutRecipe(Order order) throws ServiceException, ValidatorException;

    void orderWithRecipe(Order order, String recipeCode) throws ServiceException;

    List<OrderDescription> clientShowOrderList(int id) throws ServiceException;

    List<OrderDescription> showSendingMessageList(int clientId) throws ServiceException;

    List<RERDescription> showDoctorResponseMessageList(int clientId) throws ServiceException;

    void sendRecipeExtensionRequest(String recipeCode, int clientId) throws ServiceException;

    void reportAboutDelivery(int orderId) throws ServiceException;

    void hideMessage(int requestId) throws ServiceException;

    List<OrderDescription> showShoppingList(int clientId) throws ServiceException;
}
