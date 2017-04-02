package by.tc.online_pharmacy.dao;

import by.tc.online_pharmacy.bean.*;
import by.tc.online_pharmacy.dao.exception.DaoException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface DrugDao {
    List<Drug> takeDrugGroup(String group) throws DaoException;
    List<Drug> takeDrugsByName(String name) throws DaoException;

    String addDrugQuantity(int id, int quantity) throws DaoException;
    String addNewDrug(Drug drug) throws DaoException;
    String removeDrug(int id) throws DaoException;
    List<OrderDescription> showOrderList() throws DaoException;

    String toOrder(Order order) throws DaoException;

    Date confirmRecipe(Recipe2 recipe2) throws DaoException;


    String send(int orderId, int pharmacistId) throws DaoException;
    String sendFeedback(int recipeId, String feedback) throws DaoException;


}
