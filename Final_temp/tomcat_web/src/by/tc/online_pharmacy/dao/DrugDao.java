package by.tc.online_pharmacy.dao;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface DrugDao {

    List<Drug> takeDrugGroup(String group) throws DaoException;

    List<Drug> takeDrugGroupToOrder(String group) throws DaoException;

    List<Drug> takeDrugsByName(String name) throws DaoException;

    RecipeDescription takeRecipeDescription(String recipeCode) throws DaoException;

    void addDrugQuantity(int id, int quantity) throws DaoException;

    void addNewDrug(Drug drug) throws DaoException;

    void removeDrug(int id) throws DaoException;

    List<OrderDescription> takePharmacistOrderList() throws DaoException;

    List<OrderDescription> takeClientOrderList(int id) throws DaoException;

    double takeClientBalance(int clientId) throws DaoException;

    int takeDrugQuantity(int drugId) throws DaoException;

    int addOrder(Order order) throws DaoException;

    Date takeRecipeEndDate(String recipeCode) throws DaoException;

    void closeRecipe(String recipeCode) throws DaoException;

    void linkOrderAndRecipe(int orderId, String recipeCode) throws DaoException;

    void send(int orderId, int pharmacistId) throws DaoException;

    List<OrderDescription> takeSendingMessageList(int clientId) throws DaoException;

    List<RERDescription> takeDoctorResponseMessageList(int clientId) throws DaoException;

    void cancelOrder(int orderId) throws DaoException;

    void addRecipeExtensionRequest(String recipeCode, int clientId) throws DaoException;

    List<RERDescription> takeRecipeExtensionRequestList() throws DaoException;

    void approve(RERDescription rerDescription) throws DaoException;

    void deny (RERDescription rerDescription) throws DaoException;

    void reportAboutDelivery(int orderId) throws DaoException;




}
