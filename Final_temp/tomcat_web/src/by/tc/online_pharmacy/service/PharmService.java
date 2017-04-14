package by.tc.online_pharmacy.service;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface PharmService {

    List<Drug> takeDrugGroup(String group) throws ServiceException;

    List<Drug> takeDrugGroupToOrder(String group) throws ServiceException;

    List<Drug> takeDrugsByName(String name) throws ServiceException, ValidatorException;

    double showCurrentBalance(int clientId) throws ServiceException;

    int showCurrentDrugQuantity(int drugId) throws ServiceException;

    Date takeRecipeEndDate(String recipeCode) throws ServiceException;

    RecipeDescription takeRecipeDescription(String recipeCode) throws ServiceException;

    void addDrugQuantity(int id, int quantity) throws ServiceException;

    void addNewDrug(Drug drug) throws ServiceException, ValidatorException;

    void removeDrug(int id) throws ServiceException;

    void orderWithoutRecipe(Order order) throws ServiceException;

    void orderWithRecipe(Order order, String recipeCode) throws ServiceException;

    List<OrderDescription> pharmacistShowOrderList() throws ServiceException;

    List<OrderDescription> clientShowOrderList(int id) throws ServiceException;

    void send(int orderId, int pharmacistId) throws ServiceException;

    List<OrderDescription> takeSendingMessageList(int clientId) throws ServiceException;

    List<RERDescription> takeDoctorResponseMessageList(int clientId) throws ServiceException;

    void cancelOrder(int orderId) throws ServiceException;

    void sendRecipeExtensionRequest(String recipeCode, int clientId) throws ServiceException;

    List<RERDescription> takeRecipeExtensionRequestList() throws ServiceException;

    void approve(RERDescription rerDescription) throws ServiceException;

    void deny(RERDescription rerDescription) throws ServiceException;

    void reportAboutDelivery(int orderId) throws ServiceException;


}
