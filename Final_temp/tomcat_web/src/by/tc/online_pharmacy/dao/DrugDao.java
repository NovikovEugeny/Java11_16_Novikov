package by.tc.online_pharmacy.dao;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface DrugDao {

    List<Drug> takeDrugGroup(String group) throws DaoException;

    List<Drug> takeDrugsByName(String name) throws DaoException;

    void addDrugQuantity(int id, int quantity) throws DaoException;

    void addNewDrug(Drug drug) throws DaoException;

    void removeDrug(int id) throws DaoException;

    List<OrderDescription> takePharmacistOrderList() throws DaoException;

    List<OrderDescription> takeClientOrderList(int id) throws DaoException;

    int addOrder(Order order) throws DaoException;

    Date confirmRecipe(Recipe recipe) throws DaoException;

    void closeRecipe(Recipe recipe) throws DaoException;

    void linkOrderAndRecipe(int orderId, String recipeCode) throws DaoException;

    void send(int orderId, int pharmacistId) throws DaoException;

    void cancelOrder(int orderId) throws DaoException;

    void addRecipeExtensionRequest(String recipeCode) throws DaoException;

    Map<Integer, String> takeRecipeExtensionRequestList() throws DaoException;

    void approve(int id, String recipeCode) throws DaoException;

    void deny (int id, String recipeCode) throws DaoException;









    void sendFeedback(int recipeId, String feedback) throws DaoException;


}
