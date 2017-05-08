package by.tc.onlinepharmacy.dao;


import by.tc.onlinepharmacy.bean.*;
import by.tc.onlinepharmacy.dao.exception.DAOException;

import java.util.Date;
import java.util.List;

/**
 * Interface for {@link by.tc.onlinepharmacy.dao.impl.ClientDAOImpl ClientDAOImpl}.
 */
public interface ClientDAO {

    int addUser(User user) throws DAOException;

    boolean isUnique(String mobile) throws DAOException;

    void addAccount(int clientId) throws DAOException;

    double takeClientBalance(int clientId) throws DAOException;

    List<Drug> takeDrugGroupToOrder(String group) throws DAOException;

    int addOrder(Order order) throws DAOException;

    List<OrderDescription> takeClientOrderList(int id) throws DAOException;

    int takeDrugQuantity(int drugId) throws DAOException;

    double takeDrugPrice(int drugId) throws DAOException;

    RecipeDescription takeRecipeDescription(String recipeCode) throws DAOException;

    Date takeRecipeEndDate(String recipeCode) throws DAOException;

    void closeRecipe(String recipeCode) throws DAOException;

    void linkOrderAndRecipe(int orderId, String recipeCode) throws DAOException;

    List<OrderDescription> takeSendingMessageList(int clientId) throws DAOException;

    List<RERDescription> takeDoctorResponseMessageList(int clientId) throws DAOException;

    void addRecipeExtensionRequest(String recipeCode, int clientId) throws DAOException;

    void reportAboutDelivery(int orderId) throws DAOException;

    void deleteMessage(int requestId) throws DAOException;

    List<OrderDescription> takeShoppingList(int clientId) throws DAOException;

    boolean isDuplicateApplication(String recipeCode) throws DAOException;

    void replenishBalance(int clientId, double amount) throws DAOException;

}