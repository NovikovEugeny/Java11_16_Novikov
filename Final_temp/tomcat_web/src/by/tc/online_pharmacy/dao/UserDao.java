package by.tc.online_pharmacy.dao;

import by.tc.online_pharmacy.bean.User;
import by.tc.online_pharmacy.dao.exception.DaoException;

/**
 * Created by Евгений on 17.02.2017.
 */
public interface UserDao {
    User signIn(String login, String password) throws DaoException;
    int signUp(User user) throws DaoException;
    boolean isUnique(String mobile) throws DaoException;
}
