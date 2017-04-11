package by.tc.online_pharmacy.service;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;

import java.util.List;
import java.util.Map;


public interface PharmService {

    List<Drug> takeDrugGroup(String group) throws ServiceException;

    List<Drug> takeDrugGroupToOrder(String group) throws ServiceException;

    List<Drug> takeDrugsByName(String name) throws ServiceException, ValidatorException;

    RecipeDescription takeRecipeDescription(String recipeCode) throws ServiceException;

    void addDrugQuantity(int id, int quantity) throws ServiceException;

    void addNewDrug(Drug drug) throws ServiceException, ValidatorException;

    void removeDrug(int id) throws ServiceException;

    void orderWithoutRecipe(Order order) throws ServiceException;

    void orderWithRecipe(Order order, String recipeCode) throws ServiceException;

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
