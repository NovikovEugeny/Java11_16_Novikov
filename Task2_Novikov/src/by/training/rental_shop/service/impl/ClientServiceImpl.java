package by.training.rental_shop.service.impl;

import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.UserDao;
import by.training.rental_shop.dao.exception.DaoException;
import by.training.rental_shop.dao.factory.DaoFactory;
import by.training.rental_shop.service.ClientService;
import by.training.rental_shop.service.exception.ServiceException;

/**
 * Created by Евгений on 13.01.2017.
 */
public class ClientServiceImpl implements ClientService {

    @Override
    public User signIn(String mobilePhone, String password) throws ServiceException {

        if (mobilePhone == null || mobilePhone.isEmpty()) {
            throw new ServiceException("mobile phone is empty");
        }

        if (password == null || password.isEmpty()) {
            throw new ServiceException("password is empty");
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();
            return userDao.signIn(mobilePhone, password);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public void registration(User user) throws ServiceException {

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ServiceException("Name is empty");
        }

        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            throw new ServiceException("Mobile phone is empty");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ServiceException("Password is empty");
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();
            userDao.registration(user);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }
}
