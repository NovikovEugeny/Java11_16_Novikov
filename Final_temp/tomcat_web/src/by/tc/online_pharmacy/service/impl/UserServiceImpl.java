package by.tc.online_pharmacy.service.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.UserDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.UserService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.util.Encoder;

/**
 * Created by Евгений on 17.02.2017.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User signIn(String mobilePhone, String password) throws ServiceException {

        if (mobilePhone.isEmpty() && password.isEmpty()) {
            throw new ServiceException("Empty mobile phone and password");
        }

        if (mobilePhone.isEmpty()) {
            throw new ServiceException("Empty mobile phone");
        }

        if (password.isEmpty()) {
            throw new ServiceException("Empty password");
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();

            password = Encoder.encode(password);

            return userDao.signIn(mobilePhone, password);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public int signUp(User user, String confirmPassword) throws ServiceException {

        String emptyMessage = "You need to fill in all the fields";

        if (user.getSurname().isEmpty()) {
            throw new ServiceException(emptyMessage);
        }

        if (user.getName().isEmpty()) {
            throw new ServiceException(emptyMessage);
        }

        if (user.getPatronymic().isEmpty()) {
            throw new ServiceException(emptyMessage);
        }

        if (user.getMobilePhone().isEmpty()) {
            throw new ServiceException(emptyMessage);
        }

        if (user.getPassword().isEmpty()) {
            throw new ServiceException(emptyMessage);
        }

        if (confirmPassword.isEmpty()) {
            throw new ServiceException(emptyMessage);
        }

        if (!user.getPassword().equals(confirmPassword)) {
            throw new ServiceException("The passwords are different");
        }

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();

            if (!userDao.isUnique(user.getMobilePhone())) {
                throw new ServiceException("This mobile phone already exists");
            }

            String password = user.getPassword();
            password = Encoder.encode(password);
            user.setPassword(password);

            return userDao.signUp(user);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }
}