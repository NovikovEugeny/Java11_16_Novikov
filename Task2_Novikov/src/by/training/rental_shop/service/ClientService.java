package by.training.rental_shop.service;

import by.training.rental_shop.bean.User;
import by.training.rental_shop.service.exception.ServiceException;

/**
 * Created by Евгений on 13.01.2017.
 */
public interface ClientService {
    User signIn(String mobilePhone, String password) throws ServiceException;
    void registration(User user) throws ServiceException;
}
