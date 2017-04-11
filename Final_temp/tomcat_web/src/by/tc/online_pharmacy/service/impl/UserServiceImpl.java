package by.tc.online_pharmacy.service.impl;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.UserDao;
import by.tc.online_pharmacy.dao.exception.DaoException;
import by.tc.online_pharmacy.dao.factory.DaoFactory;
import by.tc.online_pharmacy.service.UserService;
import by.tc.online_pharmacy.service.exception.ServiceException;
import by.tc.online_pharmacy.service.exception.ValidatorException;
import by.tc.online_pharmacy.service.util.Encoder;
import by.tc.online_pharmacy.service.util.validator.Validator;


public class UserServiceImpl implements UserService {

    @Override
    public User signIn(String mobile, String password) throws ServiceException, ValidatorException {

        Validator.signInValidate(mobile, password);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();

            password = Encoder.encode(password);

            return userDao.signIn(mobile, password);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }

    @Override
    public int signUp(User user) throws ServiceException, ValidatorException {

        Validator.signUpValidate(user);

        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            UserDao userDao = daoFactory.getUserDao();

            if (!userDao.isUnique(user.getMobilePhone())) {
                throw new ValidatorException("This mobile phone already exists");
            }

            System.out.println("in service");
            String password = user.getPassword();
            password = Encoder.encode(password);
            user.setPassword(password);

            return userDao.signUp(user);
        } catch (DaoException exc) {
            throw new ServiceException(exc);
        }
    }
}