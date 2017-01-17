package by.training.rental_shop.service.impl;

import by.training.rental_shop.bean.SportEquipment;
import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.GoodDao;
import by.training.rental_shop.dao.exception.DaoException;
import by.training.rental_shop.dao.factory.DaoFactory;
import by.training.rental_shop.service.ShopService;
import by.training.rental_shop.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Евгений on 14.01.2017.
 */
public class ShopServiceImpl implements ShopService {

    @Override
    public List<SportEquipment> getAvailableGoods() throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            GoodDao goodDao = daoFactory.getGoodDao();
            return goodDao.getAvailableGoods();
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<SportEquipment> getRentedGoods(User user) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            GoodDao goodDao = daoFactory.getGoodDao();
            return goodDao.getRentedGoods(user);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public SportEquipment chooseGood(String title) throws ServiceException {
        if (title == null || title.isEmpty()) {
            throw new ServiceException("error empty name");
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            GoodDao goodDao = daoFactory.getGoodDao();
            return goodDao.chooseGood(title);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void rent(User user, SportEquipment sportEquipment) throws ServiceException {
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            GoodDao goodDao = daoFactory.getGoodDao();
            goodDao.rent(user, sportEquipment);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public List<SportEquipment> findGood(String title) throws ServiceException {
        if (title == null || title.isEmpty()) {
            throw new ServiceException("error title is empty");
        }
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            GoodDao goodDao = daoFactory.getGoodDao();
            return goodDao.findGood(title);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }
}
