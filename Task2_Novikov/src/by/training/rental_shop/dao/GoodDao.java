package by.training.rental_shop.dao;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.exception.DaoException;

import java.util.List;

/**
 * Created by Евгений on 07.01.2017.
 */
public interface GoodDao {
    List<SportEquipment> getAvailableGoods() throws DaoException;
    List<SportEquipment> getRentedGoods(User user) throws DaoException;
    void rent(User user, SportEquipment sportEquipment) throws DaoException;
    List<SportEquipment> findGood(String title) throws DaoException;
    SportEquipment chooseGood(String title) throws DaoException;
}
