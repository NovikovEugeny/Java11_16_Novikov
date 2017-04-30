package by.tc.online_pharmacy.dao;


import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface ClientDao {

    int signUp(User user) throws DaoException;

    boolean isUnique(String mobile) throws DaoException;

    void addAccount(int clientId) throws DaoException;

    double takeClientBalance(int clientId) throws DaoException;

    List<Drug> takeDrugGroupToOrder(String group) throws DaoException;

    int addOrder(Order order) throws DaoException;

    List<OrderDescription> takeClientOrderList(int id) throws DaoException;

    int takeDrugQuantity(int drugId) throws DaoException;

    RecipeDescription takeRecipeDescription(String recipeCode) throws DaoException;

    Date takeRecipeEndDate(String recipeCode) throws DaoException;

    void closeRecipe(String recipeCode) throws DaoException;

    void linkOrderAndRecipe(int orderId, String recipeCode) throws DaoException;

    List<OrderDescription> takeSendingMessageList(int clientId) throws DaoException;

    List<RERDescription> takeDoctorResponseMessageList(int clientId) throws DaoException;

    void addRecipeExtensionRequest(String recipeCode, int clientId) throws DaoException;

    void reportAboutDelivery(int orderId) throws DaoException;

    void hideMessage(int requestId) throws DaoException;

    List<OrderDescription> takeShoppingList(int clientId) throws DaoException;

    boolean isDuplicateApplication(String recipeCode) throws DaoException;

}
