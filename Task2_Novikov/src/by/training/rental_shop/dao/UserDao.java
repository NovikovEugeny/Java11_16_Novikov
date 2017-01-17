package by.training.rental_shop.dao;

import by.training.rental_shop.bean.User;
import by.training.rental_shop.dao.exception.DaoException;

/**
 * Created by Евгений on 07.01.2017.
 */
public interface UserDao {
    User signIn(String mobilePhone, String password) throws DaoException;
    void registration(User user) throws DaoException;
}
