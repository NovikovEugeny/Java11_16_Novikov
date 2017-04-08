package by.tc.online_pharmacy.service;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.OrderDescription;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.List;
import java.util.Map;


public interface PharmService {

    List<Drug> takeDrugGroup(String group) throws ServiceException;

    List<Drug> takeDrugsByName(String name) throws ServiceException, ValidatorException;

    void addDrugQuantity(int id, int quantity) throws ServiceException;

    void addNewDrug(Drug drug) throws ServiceException;

    void removeDrug(int id) throws ServiceException;

    void orderWithoutRecipe(Order order) throws ServiceException;

    void orderWithRecipe(Order order, Recipe recipe) throws ServiceException;

    List<OrderDescription> pharmacistShowOrderList() throws ServiceException;

    List<OrderDescription> clientShowOrderList(int id) throws ServiceException;

    void send(int orderId, int pharmacistId) throws ServiceException;

    void cancelOrder(int orderId) throws ServiceException;

    void addRecipeExtensionRequest(String recipeCode) throws ServiceException;

    Map<Integer, String> takeRecipeExtensionRequestList() throws ServiceException;

    void approve(int id, String recipeCode) throws ServiceException;

    void deny(int id, String recipeCode) throws ServiceException;


    void sendFeedback(int recipeId, String feedback) throws ServiceException;

}