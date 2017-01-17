package by.training.rental_shop.dao.factory;

import by.training.rental_shop.dao.GoodDao;
import by.training.rental_shop.dao.UserDao;
import by.training.rental_shop.dao.impl.GoodDaoImpl;
import by.training.rental_shop.dao.impl.UserDaoImpl;

/**
 * Created by Евгений on 13.01.2017.
 */
public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final UserDao userDao = new UserDaoImpl();
    private final GoodDao goodDao = new GoodDaoImpl();

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public GoodDao getGoodDao() {
        return goodDao;
    }
}
