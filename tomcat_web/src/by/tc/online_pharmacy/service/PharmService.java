package by.tc.online_pharmacy.service;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface PharmService {
    List<Drug> takeDrugGroup(String group) throws ServiceException;
    List<Drug> takeDrugsByName(String name) throws ServiceException;

    String addDrugQuantity(int id, int quantity) throws ServiceException;
    String addNewDrug(Drug drug) throws ServiceException;
    String removeDrug(int id) throws ServiceException;

    String orderWithoutRecipe(Order order) throws ServiceException;
    String orderWithRecipe(Order order, Recipe2 recipe2) throws ServiceException;
    List<OrderDescription> showOrderList() throws ServiceException;

    String send(int orderId, int pharmacistId) throws ServiceException;
    String sendFeedback(int recipeId, String feedback) throws ServiceException;

}
