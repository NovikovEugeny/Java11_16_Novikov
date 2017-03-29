package by.tc.online_pharmacy.service;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface PharmService {
    List<Drug> takeDrugGroup(String group) throws ServiceException;
    List<Drug> takeDrugsByName(String name) throws ServiceException;

    String addDrug(Drug drug) throws ServiceException;
    String removeDrug(int id) throws ServiceException;

    String orderWithoutRecipe(Order order) throws ServiceException;
    String orderWithRecipe(Order order, Recipe recipe) throws ServiceException;

    /*List<Order> showOrderList() throws ServiceException;
    Drug showOrder(int orderId, int pharmacistId) throws ServiceException;*/
    Map<Order, Drug> showOrderList() throws ServiceException;

    /*List<Recipe> showRecipeList() throws ServiceException;
    Map<String, String> showRecipe(int recipeId, int doctorId) throws ServiceException;*/
    Map<Recipe, Drug> showRecipeList() throws ServiceException;


    String approve(int recipeId, int doctorId) throws ServiceException;
    String deny(int recipeId, int doctorId) throws ServiceException;

    String send(int orderId, int pharmacistId) throws ServiceException;
    String sendFeedback(int recipeId, String feedback) throws ServiceException;

}
