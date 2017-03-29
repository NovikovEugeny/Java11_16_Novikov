package by.tc.online_pharmacy.dao;

import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.Order;
import by.tc.online_pharmacy.bean.Recipe;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.List;
import java.util.Map;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface DrugDao {
    List<Drug> takeDrugGroup(String group) throws DaoException;
    List<Drug> takeDrugsByName(String name) throws DaoException;

    String addDrug(Drug drug) throws DaoException;
    String removeDrug(int id) throws DaoException;

    String orderWithoutRecipe(Order order) throws DaoException;
    String orderWithRecipe(Order order, Recipe recipe) throws DaoException;

   /* List<Order> showOrderList() throws DaoException;
    Drug showOrder(int id, int pharmacistId) throws DaoException;*/
    Map<Order, Drug> showOrderList() throws DaoException;

    /*List<Recipe> showRecipeList() throws DaoException;
    Map<String, String> showRecipe(int id, int doctorId) throws DaoException;*/
    Map<Recipe, Drug> showRecipeList() throws DaoException;

    String approve(int recipeId, int doctorId) throws DaoException;
    String deny(int recipeId, int doctorId) throws DaoException;

    String send(int orderId, int pharmacistId) throws DaoException;
    String sendFeedback(int recipeId, String feedback) throws DaoException;


}
