package by.training.rental_shop.service;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.exception.DaoException;
import by.training.rental_shop.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Евгений on 13.01.2017.
 */
public interface ShopService {
    List<SportEquipment> getAvailableGoods() throws ServiceException;
    List<SportEquipment> getRentedGoods(User user) throws ServiceException;
    SportEquipment chooseGood(String title) throws ServiceException;
    void rent(User user, SportEquipment sportEquipment) throws ServiceException;
    List<SportEquipment> findGood(String title) throws ServiceException;
}
